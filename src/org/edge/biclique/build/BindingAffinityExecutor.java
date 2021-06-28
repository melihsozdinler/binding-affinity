package org.edge.biclique.build;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.ProcessBuilder.Redirect;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.UUID;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;

import org.edge.biclique.source.model.AffinityStore;
import org.edge.biclique.source.model.Configurations;
import org.edge.biclique.source.model.ExcelStore;
import org.edge.biclique.source.model.Pair;
import org.edge.biclique.source.model.PairPoints;
import org.edge.biclique.source.model.Point3d;
import org.edge.biclique.source.model.ResiduePolarProperty;
import org.edge.biclique.source.model.ResultModel;
import org.edge.biclique.source.util.FileUtils;
import org.edge.biclique.source.util.PolygonDraw;
import org.edge.biclique.source.util.Utils;
import org.graphstream.graph.Edge;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.SingleGraph;
import org.jfree.util.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BindingAffinityExecutor extends BindingAffinityBase implements Callable<ResultModel> {

	public BindingAffinityExecutor(Configurations configurations, String line) {
		this.configurations = configurations;
		this.line = line;
	}

	@Override
	protected ResultModel handlePDBProcessing() {
		
		initGraphNodes();
		initGraphEdges();

		logger.debug("-------- A ---------");
		Map<Node, Integer> nodeAVector = new ConcurrentHashMap<Node, Integer>();
		int count = 0;
		for (Entry<Integer, Node> temp : nodesLayerAGraph.entrySet()) {
			logger.debug(temp.getKey() + "\t" + temp.getValue().getDegree());
			nodeAVector.put(temp.getValue(), count);
			count++;
		}

		logger.debug("-------- B ---------");
		Map<Node, Integer> nodeBVector = new ConcurrentHashMap<Node, Integer>();
		count = 0;
		for (Entry<Integer, Node> temp : nodesLayerBGraph.entrySet()) {
			logger.debug(temp.getKey() + "\t" + temp.getValue().getDegree());
			nodeBVector.put(temp.getValue(), count);
			count++;
		}

		Set<Node> nodeRemove = new HashSet<Node>();

		for (Set<Node> action : sinkNeigbourNodes.values()) {
			for (Node temp : action) {
				if (!nodeRemove.contains(temp)) {
					graph.removeNode(temp);
					nodeRemove.add(temp);
				}
			}
		}

		double[][] dataset = new double[nodesLayerAGraph.entrySet().size() + 1][nodesLayerBGraph.entrySet().size() + 1];

		double[][] edgeWeightDataset = new double[nodesLayerAGraph.entrySet().size()
				+ 1][nodesLayerBGraph.entrySet().size() + 1];

		double[] energyPointLayerA = new double[nodesLayerAGraph.entrySet().size() + 1];
		double[] energyPointLayerB = new double[nodesLayerBGraph.entrySet().size() + 1];

		graph.getEdgeSet().stream().forEach(edge -> {
			dataset[nodeAVector.get(edge.getSourceNode())][nodeBVector.get(edge.getTargetNode())] = 1.0
					* ((configurations.getResidueBased() || configurations.getResidueEnergyMultiplier())
							&& configurations.getResidueEnergies()
									.containsKey(edge.getSourceNode().getAttribute("residue") + "_"
											+ edge.getTargetNode().getAttribute("residue"))
													? configurations.getResidueEnergies()
															.get(edge.getSourceNode().getAttribute("residue") + "_"
																	+ edge.getTargetNode().getAttribute("residue"))
													: 1.0);

			Node source = null;
			Node target = null;
			try {
				source = graph.getNode(edge.getSourceNode().getId());
				target = graph.getNode(edge.getTargetNode().getId());
			} catch (IndexOutOfBoundsException e1) {

			}

			/*if(true) 
			{*/
			/*if((checkPolarAttributeAndRemove(source, target)
					|| checkChargedAttributeAndRemove(source, target)
					|| checkPolarChargedAttributeAndRemove(source, target)
					|| checkApolarChargedAttributeAndRemove(source, target))) 
			{*/
			if (configurations.getConsiderWeights()) {
				/* Comment out since negative values 
				edgeWeightDataset[nodeAVector.get(edge.getSourceNode())][nodeBVector.get(edge.getTargetNode())] = Math
						.log(((double) maxEdge.get() - ((double) edge.getAttribute("distance")))
								+ (double) minEdge.get());
				*/
				logger.debug("Residue Pairs " + "-" + edge.getSourceNode().getAttribute("residue") + " - " + edge.getTargetNode().getAttribute("residue"));
				/*
				if (configurations.getResiduePolarProperty().get(target.getAttribute("residue").toString()) != null
						&& configurations.getResiduePolarProperty().get(source.getAttribute("residue").toString()) != null) {
					logger.debug(configurations.getResiduePolarProperty()
											.get(target.getAttribute("residue").toString()).toString() + " - " + 
								configurations.getResiduePolarProperty()
											.get(source.getAttribute("residue").toString()).toString());
				}*/
				/*
				if (configurations.getResidueChargedProperty().get(target.getAttribute("residue").toString()) != null
						&& configurations.getResidueChargedProperty().get(source.getAttribute("residue").toString()) != null) {
					logger.info(configurations.getResidueChargedProperty()
											.get(target.getAttribute("residue").toString()).toString() + " - " + 
								configurations.getResidueChargedProperty()
											.get(source.getAttribute("residue").toString()).toString());
				}*/
				edgeWeightDataset[nodeAVector.get(edge.getSourceNode())][nodeBVector.get(edge.getTargetNode())] = /*Math
						.log*/(((maxEdge.get() - (double) edge.getAttribute("distance"))
								* ((configurations.getResidueBased() || configurations.getResidueEnergyMultiplier())
										&& configurations.getResidueEnergies()
												.containsKey(edge.getSourceNode().getAttribute("residue") + "_"
														+ edge.getTargetNode().getAttribute("residue")) ? configurations
																.getResidueEnergies()
																.get(edge.getSourceNode().getAttribute("residue") + "_"
																		+ edge.getTargetNode().getAttribute("residue"))
																: 1.0)
								* (configurations.getDistanceMultiplier() ? averageEdge.get() : 1.0)) + 10.0);
			}
			//}
			else {
				logger.debug(configurations.getResiduePolarProperty().get(source.getAttribute("residue").toString())
						+ "-"
						+ configurations.getResiduePolarProperty().get(target.getAttribute("residue").toString()));
			}
		});
		/*
		graph.getEdgeSet().stream().forEach(edge -> {
			logger.info("{}", edgeWeightDataset[nodeAVector.get(edge.getSourceNode())][nodeBVector.get(edge.getTargetNode())]);
		});
		*/
		// HeatmapDraw drawHeatmat = new HeatmapDraw();
		// drawHeatmat.run(dataset);

		String inputFileName = FileUtils.writeToFile(dataset, nodesLayerAGraph.entrySet().size(),
				nodesLayerBGraph.entrySet().size());
		if (Boolean.TRUE == configurations.getDebug())
			logger.info("File is ready:" + inputFileName);
		String outputFileName = Utils.getUniqueFileName("BIMAXResult", "src\\bin\\", "txt");
		String statusFileName = Utils.getUniqueFileName("Status", "src\\bin\\", "txt");

		for (int counter = 0; counter < configurations.getNumberOfBicqliueSteps(); counter++) {
			logger.info("Bimax Step-" + (counter + 1) + ": is produced for " + affinityStore.getComplex());
			try {

				ProcessBuilder pb = new ProcessBuilder("src/bin/Bimax.exe", inputFileName, outputFileName,
						statusFileName);
				pb.redirectOutput(Redirect.PIPE);
				pb.directory(new File("src/bin"));
				Process p = pb.start(); // Start the process.
				BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
				while ((reader.readLine()) != null) {
				}
				p.waitFor(); // Wait for the process to finish.
				p.destroy();

				if (Boolean.TRUE == configurations.getDebug())
					logger.info(" Bimax Script executed successfully");
			} catch (Exception e) {
				e.printStackTrace();
			}

			PolygonDraw polygonDraw = new PolygonDraw();
			ArrayList<Pair<ArrayList<Integer>, ArrayList<Integer>>> temp = polygonDraw.runOnly(
					nodesLayerAGraph.entrySet().size() + 1, nodesLayerBGraph.entrySet().size() + 1, dataset, counter,
					outputFileName);
			
			int oneCounter = calculateResults(temp,energyPointLayerA, energyPointLayerB, edgeWeightDataset, dataset);

			if (Boolean.FALSE == configurations.getDebug()) {
				oneCounter = 0;

				for (count = 0; count < nodesLayerAGraph.entrySet().size(); count++) {
					for (int innerCount = 0; innerCount < nodesLayerBGraph.entrySet().size(); innerCount++) {
						if ((int) dataset[count][innerCount] == 1) {
							oneCounter++;
						}
					}
				}

				if (Boolean.TRUE == configurations.getDebug())
					logger.info("Layer A One Counter(1): " + oneCounter);
			}

			oneCounter = 0;

			if (Boolean.TRUE == configurations.getDebug())
				logger.info("All One Counter(1.1): " + oneCounter);

			if (Boolean.TRUE == configurations.getDebug()) {
				oneCounter = 0;

				for (count = 0; count < nodesLayerAGraph.entrySet().size(); count++) {
					for (int innerCount = 0; innerCount < nodesLayerBGraph.entrySet().size(); innerCount++) {
						if ((int) dataset[count][innerCount] == 1) {
							oneCounter++;
						}
					}
				}

				if (Boolean.TRUE == configurations.getDebug())
					logger.info("Layer B One Counter(2): " + oneCounter);
			}
			if (Boolean.TRUE == configurations.getDebug())
				logger.info("Delete Files\n" + inputFileName + "\n" + outputFileName + "\n" + statusFileName);

			try {
				Files.delete(Paths.get(inputFileName));
				Files.delete(Paths.get(outputFileName));
				Files.delete(Paths.get(statusFileName));

			} catch (IOException e) {
				logger.info("ERROR: file can not be deleted" + e.getMessage());
			}

			inputFileName = FileUtils.writeToFile(dataset, nodesLayerAGraph.entrySet().size(),
					nodesLayerBGraph.entrySet().size());

			// pause(1000);
		}

		if (Boolean.TRUE == configurations.getDebug()) {
			logger.debug("***********************\n");
			for (count = 0; count < nodesLayerAGraph.entrySet().size(); count++) {
				logger.info("Value of Layer-A EnergyPointCount for index {} value{}", count, energyPointLayerA[count]);
			}

			logger.debug("***********************\n");
			for (count = 0; count < nodesLayerBGraph.entrySet().size(); count++) {
				logger.debug("Value of Layer-B EnergyPointCount for index {} value{}", count, energyPointLayerB[count]);
			}
		}

		ResultModel resultModel = new ResultModel();
		resultModel.complexName = affinityStore.getComplex();
		resultModel.fileName = affinityStore.getChainAFileName() + "_" + affinityStore.getChainBFileName();
		resultModel.energyPointLayerA = DoubleStream.of(energyPointLayerA).boxed()
				.collect(Collectors.toCollection(ArrayList::new));
		resultModel.energyPointLayerB = DoubleStream.of(energyPointLayerB).boxed()
				.collect(Collectors.toCollection(ArrayList::new));
		resultModel.sizeA = nodesLayerAGraph.entrySet().size();
		resultModel.sizeB = nodesLayerBGraph.entrySet().size();

		/*
		 * Toolkit.computeLayout(graph); synchronized (graph) {
		 * 
		 * try { graph.write("E:\\BIIP\\" + resultModel.complexName + ".png"); } catch
		 * (IOException e) { // TODO Auto-generated catch block e.printStackTrace(); } }
		 * graph.clear();
		 */
		return resultModel;
	}

	@Override
	public ResultModel call() throws Exception {
		// init some values
		minEdge.set((double) Integer.MAX_VALUE);
		maxEdge.set((double)Integer.MIN_VALUE);
		averageEdge.set(0.0);
		
		affinityStore = parseLineFromOutFile();
		Integer indexer = readFromFile(mapExcelStore, mapIndexCount, affinityStore.getChainAFileName(),
				affinityStore.getChainA(), 1, configurations.getFilePath());
		indexer = readFromFile(mapExcelStore, mapIndexCount, affinityStore.getChainBFileName(),
				affinityStore.getChainB(), indexer, configurations.getFilePath());
		return handlePDBProcessing();
	}
}
