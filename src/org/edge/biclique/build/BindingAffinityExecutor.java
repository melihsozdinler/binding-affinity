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
import org.edge.biclique.source.model.ResultModel;
import org.edge.biclique.source.util.PolygonDraw;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.SingleGraph;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BindingAffinityExecutor implements Callable<ResultModel> {
	
	final static Logger logger = LoggerFactory.getLogger(BindingAffinityExecutor.class);

	protected final Configurations configurations;
	private String line;

	public BindingAffinityExecutor(Configurations configurations, String line) {
		this.configurations = configurations;
		this.line = line;
	}

	protected Configurations getConfigurations() {
		return configurations;
	}

	public AffinityStore parseLineFromOutFile() {
		AffinityStore affinityStore = new AffinityStore();
		StringTokenizer stInner = new StringTokenizer(line);
		int countValue = 0;
		while (stInner.hasMoreTokens()) {
			String cellValue = stInner.nextToken("\t");
			if (countValue == 1) {
				affinityStore.setChainA(cellValue);
			} else if (countValue == 2) {
				affinityStore.setChainAFileName(cellValue);
			} else if (countValue == 3) {
				affinityStore.setChainB(cellValue);
			} else if (countValue == 4) {
				affinityStore.setChainBFileName(cellValue);
			} else if (countValue == 7) {
				affinityStore.setUnboundChainA(cellValue);
			} else if (countValue == 8) {
				affinityStore.setUnboundChainAFileName(cellValue);
			} else if (countValue == 10) {
				affinityStore.setUnboundChainB(cellValue);
			} else if (countValue == 11) {
				affinityStore.setUnboundChainBFileName(cellValue);
			} else if (countValue == 0) {
				affinityStore.setComplex(cellValue);
			}

			countValue++;
		}
		return affinityStore;
	}
	
	private Double checkDoubleAndReturn(String doubleStr) {
		if(doubleStr.lastIndexOf(".") == doubleStr.indexOf(".")) {
			return Double.parseDouble(doubleStr);
		}
		else {
			return Double.parseDouble(doubleStr.substring(0, doubleStr.lastIndexOf(".")));
		}
	}
	
	public Integer readFromFileResidue(Map<String, List<ExcelStore>> mapExcelStore, Map<String, Integer> mapIndexCount,
			String fileName, String chainName, Integer indexerInput, String filePath) {

		logger.info("Reading filename " + fileName + " - for chain " + chainName + " - indexer:" + indexerInput);
		String content = null;
		Integer indexer = indexerInput;
		File file = new File(filePath + fileName);
		try {
			content = new String(Files.readAllBytes(Paths.get(file.getAbsolutePath())));
			StringTokenizer st = new StringTokenizer(content);
			String residueName = "";
			String lastResidueName = "";
			while (st.hasMoreTokens()) {
				String line = st.nextToken("\n");
				StringTokenizer stInner = new StringTokenizer(line);
				List<ExcelStore> storeList = new ArrayList<ExcelStore>();

				ExcelStore item = new ExcelStore();
				int innerCount = 0;
				while (stInner.hasMoreTokens()) {
					if (line.contains("BEGIN") || line.contains("END")) {
						continue;
					}
					String cellValue = stInner.nextToken(" ");
					if (!cellValue.isEmpty() && stInner.hasMoreTokens()) {

						if (Boolean.TRUE == getConfigurations().getDebug())
							System.out.print(cellValue + "\t");

						if (innerCount == 4)
							item.setChain(cellValue);
						else if (innerCount == 5) {
							try {
								item.setResi(checkDoubleAndReturn(cellValue));
							} catch (NumberFormatException e) {
								// not a double
							}
						} else if (innerCount == 3) {
							item.setResiName(cellValue);
							residueName = cellValue;
						}
						else if (innerCount == 2)
							item.setName(cellValue);
						else if (innerCount == 6)
							item.setxCoord(checkDoubleAndReturn(cellValue));
						else if (innerCount == 7)
							item.setyCoord(checkDoubleAndReturn(cellValue));
						else if (innerCount == 8)
							item.setzCoord(checkDoubleAndReturn(cellValue));
						innerCount++;

					}
				}

				if (!residueName.isEmpty() && !residueName.equals(lastResidueName)) {
					if (mapExcelStore.containsKey(chainName)) {
						item.setId(indexer);
						item.setChain(chainName);
						mapExcelStore.get(chainName).add(item);
					} else {
						item.setId(indexer);
						List<ExcelStore> listExcelStore = new ArrayList<ExcelStore>();
						mapExcelStore.put(chainName, listExcelStore);
						item.setChain(chainName);
						mapExcelStore.get(chainName).add(item);
					}
				}
				lastResidueName = residueName;
				indexer++;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		mapIndexCount.put(chainName, indexer);
		logger.info("Finished Reading filename " + fileName + " - for chain " + chainName + " - indexer:" + indexerInput);
		return indexer;
	}

	public Integer readFromFile(Map<String, List<ExcelStore>> mapExcelStore, Map<String, Integer> mapIndexCount,
			String fileName, String chainName, Integer indexerInput, String filePath) {

		logger.info("Reading filename " + fileName + " - for chain " + chainName + " - indexer:" + indexerInput);
		String content = null;
		Integer indexer = indexerInput;
		File file = new File(filePath + fileName);
		try {
			content = new String(Files.readAllBytes(Paths.get(file.getAbsolutePath())));
			StringTokenizer st = new StringTokenizer(content);
			while (st.hasMoreTokens()) {
				String line = st.nextToken("\n");
				StringTokenizer stInner = new StringTokenizer(line);

				ExcelStore item = new ExcelStore();
				int innerCount = 0;
				while (stInner.hasMoreTokens()) {
					if (line.contains("BEGIN") || line.contains("END")) {
						continue;
					}
					String cellValue = stInner.nextToken(" ");
					if (!cellValue.isEmpty() && stInner.hasMoreTokens()) {

						if (Boolean.TRUE == getConfigurations().getDebug())
							System.out.print(cellValue + "\t");

						if (innerCount == 4)
							item.setChain(cellValue);
						else if (innerCount == 5) {
							try {
								item.setResi(checkDoubleAndReturn(cellValue));
							} catch (NumberFormatException e) {
								// not a double
							}
						} else if (innerCount == 3)
							item.setResiName(cellValue);
						else if (innerCount == 2)
							item.setName(cellValue);
						else if (innerCount == 6)
							item.setxCoord(checkDoubleAndReturn(cellValue));
						else if (innerCount == 7)
							item.setyCoord(checkDoubleAndReturn(cellValue));
						else if (innerCount == 8)
							item.setzCoord(checkDoubleAndReturn(cellValue));
						innerCount++;

					}
				}

				if (mapExcelStore.containsKey(chainName)) {
					item.setId(indexer);
					item.setChain(chainName);
					mapExcelStore.get(chainName).add(item);
				} else {
					item.setId(indexer);
					List<ExcelStore> listExcelStore = new ArrayList<ExcelStore>();
					mapExcelStore.put(chainName, listExcelStore);
					item.setChain(chainName);
					mapExcelStore.get(chainName).add(item);
				}
				indexer++;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		mapIndexCount.put(chainName, indexer);
		logger.info("Finished Reading filename " + fileName + " - for chain " + chainName + " - indexer:" + indexerInput);
		return indexer;
	}

	protected List<PairPoints> parsePairPoints(AtomicReference<Double> minEdge, AtomicReference<Double> maxEdge,
			Map<String, List<ExcelStore>> mapExcelStore, Map<String, Integer> mapIndexCount) {

		List<PairPoints> listPairPoints = new ArrayList<PairPoints>();

		for (String keys : mapExcelStore.keySet()) {
			final List<ExcelStore> tempExcelStorList = mapExcelStore.get(keys);
			for (String keysInner : mapExcelStore.keySet()) {
				if (!keysInner.equals(keys)) {
					List<ExcelStore> tempExcelStorListInner = mapExcelStore.get(keysInner);
					tempExcelStorList.forEach(item -> {
						tempExcelStorListInner.forEach(itemInner -> {
							//logger.debug("Distance: " + item.distanceTo(itemInner));
							double temp = item.distanceTo(itemInner);
							if (temp < configurations.getSignificanceThreshold()) {

								if (Boolean.TRUE == configurations.getDebug())
									logger.debug("2:" + item.getId() + " - "
											+ (itemInner.getId() + mapIndexCount.get(item.getChain())));
								PairPoints pairPoints = new PairPoints();

								pairPoints.setSourceIndex(item.getId());
								pairPoints.setTargetIndex(itemInner.getId() + mapIndexCount.get(item.getChain()));

								pairPoints.setSourcePair(item.getChain());
								pairPoints.setTargetPair(itemInner.getChain());

								pairPoints.setSourcePoint(item.getPoint3d());
								pairPoints.setTargetPoint(itemInner.getPoint3d());
								listPairPoints.add(pairPoints);
								if (temp >= maxEdge.get()) {
									maxEdge.getAndSet(temp);
								}
								if (temp < minEdge.get()) {
									minEdge.getAndSet(temp);
								}
							}
						});
					});
					break;
				}
			}
			break;
		}

		logger.info("Minimum Edge Distance is: " + minEdge.get() + " - " + "Maximum Edge Distance is: " + maxEdge.get());
		return listPairPoints;
	}

	private ResultModel handlePDBProcessing() {

		AffinityStore affinityStore = parseLineFromOutFile();

		Map<String, List<ExcelStore>> mapExcelStore = new ConcurrentHashMap<String, List<ExcelStore>>();
		Map<String, Integer> mapIndexCount = new ConcurrentHashMap<String, Integer>();

		Integer indexer = readFromFile(mapExcelStore, mapIndexCount, affinityStore.getChainAFileName(),
				affinityStore.getChainA(), 1, configurations.getFilePath());
		indexer = readFromFile(mapExcelStore, mapIndexCount, affinityStore.getChainBFileName(),
				affinityStore.getChainB(), indexer, configurations.getFilePath());

		final AtomicReference<Double> minEdge = new AtomicReference<Double>();
		minEdge.set((double) Integer.MAX_VALUE);
		final AtomicReference<Double> maxEdge = new AtomicReference<Double>();
		maxEdge.set((double)Integer.MIN_VALUE);

		List<PairPoints> listPairPoints = parsePairPoints(minEdge, maxEdge, mapExcelStore, mapIndexCount);

		System.setProperty("org.graphstream.ui", "swing");
		Graph graph = new SingleGraph("test");
		graph.setAttribute("ui.quality");
		graph.setAttribute("ui.antialias");
		graph.setAttribute("ui.stylesheet", "url('data/style_shp.css')");

		// add some nodes and edges
		// Set<Integer> nodesLayerA = new HashSet<Integer>();
		Map<Integer, Node> nodesLayerAGraph = new ConcurrentHashMap<Integer, Node>();
		// Set<Integer> nodesLayerB = new HashSet<Integer>();
		Map<Integer, Node> nodesLayerBGraph = new ConcurrentHashMap<Integer, Node>();

		Map<Node, Set<Node>> sinkNeigbourNodes = new ConcurrentHashMap<Node, Set<Node>>();

		// List<Pair<Integer, Integer>> edges = new ArrayList<Pair<Integer, Integer>>();

		int count = 0;
		int edgeTotalIndex = 0;
		String prevChain = "";
		for (String keys : mapExcelStore.keySet()) {
			if (count == 0) {
				final List<ExcelStore> tempExcelStorList = mapExcelStore.get(keys);
				tempExcelStorList.forEach(item -> {
					Node temp = graph.addNode(Integer.toString(item.getId()));
					nodesLayerAGraph.put(item.getId(), temp);
					temp.addAttribute("ui.style", "fill-color: rgb(0,100,255);");
					temp.addAttribute("layout.frozen");
					temp.addAttribute("xy", item.getxCoord(), item.getyCoord());
					if (configurations.getConsiderWeights()) {
						logger.debug("x: {}, y: {} , z{}", item.getxCoord(), item.getyCoord(), item.getzCoord());
						temp.addAttribute("xyz", item.getxCoord(), item.getyCoord(), item.getzCoord());
					}
				});

				count++;
				prevChain = keys;
			} else {
				final List<ExcelStore> tempExcelStorList = mapExcelStore.get(keys);
				final String tempString = prevChain;
				tempExcelStorList.forEach(item -> {
					Node temp = graph.addNode(Integer.toString(item.getId() + mapIndexCount.get(tempString)));
					nodesLayerBGraph.put(item.getId(), temp);
					temp.addAttribute("ui.style", "fill-color: rgb(255,100,0);");
					temp.addAttribute("layout.frozen");
					temp.addAttribute("xy", item.getxCoord(), item.getyCoord());
					if (configurations.getConsiderWeights()) {
						logger.debug("x: {}, y: {} , z{}", item.getxCoord(), item.getyCoord(), item.getzCoord());
						temp.addAttribute("xyz", item.getxCoord(), item.getyCoord(), item.getzCoord());
					}
				});
			}
		}

		// now remove some nodes and edges
		count = 0;
		for (PairPoints temp : listPairPoints) {
			if (configurations.getConsiderWeights()) {
				graph.addEdge(
						Integer.toString((Integer) temp.getSourceIndex()) + "_"
								+ Integer.toString((Integer) temp.getTargetIndex()),
						Integer.toString((Integer) temp.getSourceIndex()),
						Integer.toString((Integer) temp.getTargetIndex()));
			} else {
				graph.addEdge(Integer.toString(edgeTotalIndex), Integer.toString((Integer) temp.getSourceIndex()),
						Integer.toString((Integer) temp.getTargetIndex()));
			}
			edgeTotalIndex++;
		}

		if (Boolean.TRUE == configurations.getDebug())
			logger.info(graph.getEdgeCount() + " - " + graph.getNodeCount());

		graph.getEdgeSet().stream().forEach(edge -> {
			AtomicInteger countAll = new AtomicInteger(0);
			Set<Node> nodeTempList = new HashSet<Node>();
			sinkNeigbourNodes.put(edge.getTargetNode(), nodeTempList);
			edge.getTargetNode().getEachEdge().forEach(action -> {
				if (action.getSourceNode().getEdgeSet().size() < 0) {
					countAll.getAndIncrement();
					sinkNeigbourNodes.get(edge.getTargetNode()).add(action.getSourceNode());
				}
			});

			if (configurations.getConsiderWeights()) {

				Node source = null;
				Node target = null;
				try {
					source = graph.getNode(edge.getSourceNode().getId());
					target = graph.getNode(edge.getTargetNode().getId());
				} catch (IndexOutOfBoundsException e1) {

				}
				if (source != null && target != null) {
					Object[] arraySource = source.getAttribute("xyz");
					Object[] arrayTarget = target.getAttribute("xyz");

					Point3d pointSource = new Point3d();
					pointSource.setxCoord((double) arraySource[0]);
					pointSource.setyCoord((double) arraySource[1]);
					pointSource.setzCoord((double) arraySource[2]);

					Point3d pointTarget = new Point3d();
					pointTarget.setxCoord((double) arrayTarget[0]);
					pointTarget.setyCoord((double) arrayTarget[1]);
					pointTarget.setzCoord((double) arrayTarget[2]);
					edge.addAttribute("distance", pointSource.distanceTo(pointTarget));
					logger.debug("Distance is {}", edge.getAttribute("distance").toString());
					/*
					 * logger.info("1: Node Source(" + edge.getSourceNode().getId() + "):" +
					 * pointSource.getxCoord() + "," + pointSource.getyCoord() + "," +
					 * pointSource.getzCoord()); logger.info("2: Node Target(" +
					 * edge.getTargetNode().getId() + "):" + pointTarget.getxCoord() + "," +
					 * pointTarget.getyCoord() + "," + pointTarget.getzCoord());
					 * logger.info("Total: " + edge.getAttribute("distance"));
					 */
				}
				// logger.info(
				// edge.getTargetNode().toString() + " - " +
				// sinkNeigbourNodes.get(edge.getTargetNode()).size());
			}
		});

		if (Boolean.TRUE == configurations.getDebug())
			logger.info("-------- A ---------");
		Map<Node, Integer> nodeAVector = new ConcurrentHashMap<Node, Integer>();
		count = 0;
		for (Entry<Integer, Node> temp : nodesLayerAGraph.entrySet()) {
			if (Boolean.TRUE == configurations.getDebug())
				logger.info(temp.getKey() + "\t" + temp.getValue().getDegree());
			nodeAVector.put(temp.getValue(), count);
			count++;
		}

		if (Boolean.TRUE == configurations.getDebug())
			logger.info("-------- B ---------");
		Map<Node, Integer> nodeBVector = new ConcurrentHashMap<Node, Integer>();
		count = 0;
		for (Entry<Integer, Node> temp : nodesLayerBGraph.entrySet()) {
			if (Boolean.TRUE == configurations.getDebug())
				logger.info(temp.getKey() + "\t" + temp.getValue().getDegree());
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
			dataset[nodeAVector.get(edge.getSourceNode())][nodeBVector.get(edge.getTargetNode())] = 1;

			if (configurations.getConsiderWeights()) {
				/* Comment out since negative values 
				edgeWeightDataset[nodeAVector.get(edge.getSourceNode())][nodeBVector.get(edge.getTargetNode())] = Math
						.log(((double) maxEdge.get() - ((double) edge.getAttribute("distance")))
								+ (double) minEdge.get());
				*/
				edgeWeightDataset[nodeAVector.get(edge.getSourceNode())][nodeBVector.get(edge.getTargetNode())] = Math
						.log(maxEdge.get() - (double)edge.getAttribute("distance") + 10.0);
			}
		});
		/*
		graph.getEdgeSet().stream().forEach(edge -> {
			logger.info("{}", edgeWeightDataset[nodeAVector.get(edge.getSourceNode())][nodeBVector.get(edge.getTargetNode())]);
		});
		*/
		// HeatmapDraw drawHeatmat = new HeatmapDraw();
		// drawHeatmat.run(dataset);

		String inputFileName = writeToFile(dataset, nodesLayerAGraph.entrySet().size(),
				nodesLayerBGraph.entrySet().size());
		if (Boolean.TRUE == configurations.getDebug())
			logger.info("File is ready:" + inputFileName);
		String outputFileName = getUniqueFileName("BIMAXResult", "src\\bin\\", "txt");
		String statusFileName = getUniqueFileName("Status", "src\\bin\\", "txt");

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

			inputFileName = writeToFile(dataset, nodesLayerAGraph.entrySet().size(),
					nodesLayerBGraph.entrySet().size());

			// pause(1000);
		}

		if (Boolean.TRUE == configurations.getDebug()) {
			logger.info("***********************\n");
			for (count = 0; count < nodesLayerAGraph.entrySet().size(); count++) {
				logger.info("Value of Layer-A EnergyPointCount for index {} value{}", count, energyPointLayerA[count]);
			}

			logger.info("***********************\n");
			for (count = 0; count < nodesLayerBGraph.entrySet().size(); count++) {
				logger.info("Value of Layer-B EnergyPointCount for index {} value{}", count, energyPointLayerB[count]);
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
	
	private Integer calculateResults(ArrayList<Pair<ArrayList<Integer>, ArrayList<Integer>>> resultPair,
			double[] energyPointLayerA, double[] energyPointLayerB, double[][] edgeWeightDataset, double[][] dataset) {

		Integer oneCounter = 0;

		for (Pair<ArrayList<Integer>, ArrayList<Integer>> pairs : resultPair) {

			Set<Integer> aLayerSet = new HashSet<Integer>();
			Set<Integer> bLayerSet = new HashSet<Integer>();
			for (Integer itemLayerA : pairs.item1) {
				for (Integer itemLayerB : pairs.item2) {
					if (dataset[itemLayerA - 1][itemLayerB] == 1) {
						aLayerSet.add(itemLayerA - 1);
						bLayerSet.add(itemLayerB);
					}
				}
			}
			
			if (configurations.getConsiderWeightsWithSize()) {
				for (Integer itemLayerA : pairs.item1) {
					for (Integer itemLayerB : pairs.item2) {
						if (dataset[itemLayerA - 1][itemLayerB] == 1) {
							dataset[itemLayerA - 1][itemLayerB] = 0;
							energyPointLayerA[itemLayerA - 1] += edgeWeightDataset[itemLayerA - 1][itemLayerB]
									* aLayerSet.size();
							energyPointLayerB[itemLayerB] += edgeWeightDataset[itemLayerA - 1][itemLayerB]
									* bLayerSet.size();
						}
					}
				}
			} else {
				for (Integer itemLayerA : pairs.item1) {
					for (Integer itemLayerB : pairs.item2) {
						if (dataset[itemLayerA - 1][itemLayerB] == 1) {
							dataset[itemLayerA - 1][itemLayerB] = 0;
							oneCounter++;

							if (configurations.getConsiderWeights()) {
								energyPointLayerA[itemLayerA - 1] += edgeWeightDataset[itemLayerA - 1][itemLayerB];
								energyPointLayerB[itemLayerB] += edgeWeightDataset[itemLayerA - 1][itemLayerB];
							} else {
								energyPointLayerA[itemLayerA - 1]++;
								energyPointLayerB[itemLayerB]++;
							}
						}
					}
				}
			}
		}

		return oneCounter;
	}

	private String getUniqueFileName(String fileBaseName, String directory, String extension) {
		String fileName = FileSystems.getDefault().getPath(".").toAbsolutePath() + "\\" + directory + fileBaseName + "_"
				+ UUID.randomUUID() + "." + extension.trim();
		return fileName;
	}

	private String writeToFile(double[][] dataset, int K, int L) {
		String fileName = "";
		String TAB = "\t";
		String NEW_LINE = "\n";
		BufferedWriter output = null;
		try {
			if (Boolean.TRUE == configurations.getDebug())
				logger.info("File Processing Begins");
			fileName = getUniqueFileName("Input", "src\\bin\\", "txt");
			if (Boolean.TRUE == configurations.getDebug())
				logger.info("File Name to output {}", fileName);
			
			File file = new File(fileName);
			if (file != null) {
				output = new BufferedWriter(new FileWriter(file));
				output.append(Integer.toString((int) K));
				output.append(" ");
				output.append(Integer.toString((int) L));
				output.append(" ");
				output.append(Integer.toString((int) 1));
				output.append(" ");
				output.append(Integer.toString((int) 1));
				output.append(NEW_LINE);
				for (int count = 0; count < K; count++) {
					for (int innerCount = 0; innerCount < L; innerCount++) {
						try {
							output.append(Integer.toString((int) dataset[count][innerCount]));
							if (innerCount != L - 1)
								output.write(TAB);
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					output.write(NEW_LINE);
				}
			}
		} catch (IOException e) {
			logger.error("File can not be created {}", e.getMessage());
			return fileName;
		} finally {
			if (output != null) {
				try {
					output.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (Boolean.TRUE == configurations.getDebug())
				logger.info("Write file is completed");
		}
		return fileName;
	}

	@Override
	public ResultModel call() throws Exception {
		return handlePDBProcessing();
	}
}
