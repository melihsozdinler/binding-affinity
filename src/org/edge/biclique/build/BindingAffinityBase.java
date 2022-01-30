package org.edge.biclique.build;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.edge.biclique.source.model.AffinityStore;
import org.edge.biclique.source.model.Configurations;
import org.edge.biclique.source.model.ExcelStore;
import org.edge.biclique.source.model.Pair;
import org.edge.biclique.source.model.PairPoints;
import org.edge.biclique.source.model.Point3d;
import org.edge.biclique.source.model.ResidueChargedProperty;
import org.edge.biclique.source.model.ResiduePolarProperty;
import org.edge.biclique.source.model.ResultModel;
import org.edge.biclique.source.util.Utils;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.SingleGraph;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class BindingAffinityBase implements FeatureCheck {

	final static Logger logger = LoggerFactory.getLogger(BindingAffinityExecutor.class);

	protected Configurations configurations;
	protected String line;
	protected Map<String, List<ExcelStore>> mapExcelStore;
	protected Map<String, Integer> mapIndexCount;
	protected AffinityStore affinityStore;
	protected Graph graph = new SingleGraph("test");
	// Set<Integer> nodesLayerA = new HashSet<Integer>();
	protected Map<Integer, Node> nodesLayerAGraph;
	// Set<Integer> nodesLayerB = new HashSet<Integer>();
	protected Map<Integer, Node> nodesLayerBGraph;
	protected Map<Node, Set<Node>> sinkNeigbourNodes;

	protected final AtomicReference<Double> minEdge;
	protected final AtomicReference<Double> maxEdge;
	protected final AtomicReference<Double> averageEdge;

	BindingAffinityBase() {
		configurations = new Configurations();
		mapExcelStore = new ConcurrentHashMap<String, List<ExcelStore>>();
		mapIndexCount = new ConcurrentHashMap<String, Integer>();
		graph = new SingleGraph("test");
		nodesLayerAGraph = new ConcurrentHashMap<Integer, Node>();
		nodesLayerBGraph = new ConcurrentHashMap<Integer, Node>();
		sinkNeigbourNodes = new ConcurrentHashMap<Node, Set<Node>>();
		minEdge = new AtomicReference<Double>();
		maxEdge = new AtomicReference<Double>();
		averageEdge = new AtomicReference<Double>();
	}

	protected Configurations getConfigurations() {
		return configurations;
	}

	protected AffinityStore parseLineFromOutFile() {
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

	protected Integer readFromFileResidue(Map<String, List<ExcelStore>> mapExcelStore,
			Map<String, Integer> mapIndexCount, String fileName, String chainName, Integer indexerInput,
			String filePath) {

		logger.info("Reading filename " + fileName + " - for chain " + chainName + " - indexer:" + indexerInput);
		String content = null;
		Integer indexer = indexerInput;
		File file = new File(filePath + fileName);
		try {
			content = new String(Files.readAllBytes(Paths.get(file.getAbsolutePath())));
			StringTokenizer st = new StringTokenizer(content);
			String residueName = "";
			String lastResidueName = "";
			Double resolution = 0.0;
			while (st.hasMoreTokens()) {
				String line = st.nextToken("\n");
				StringTokenizer stInner = new StringTokenizer(line);

				ExcelStore item = new ExcelStore();
				int innerCount = 0;
				while (stInner.hasMoreTokens()) {
					if (line.contains("BEGIN") || line.contains("END") || line.contains("REMARK")) {
						String cellValue = stInner.nextToken(" ");
						if (line.contains("REMARK")) {
							String[] words = line.split(" ");
							List<String> collect =
								    Stream.of(words)
								        .filter(itemStr -> itemStr != null && !"".equals(itemStr))
								        .collect(Collectors.toList());
							resolution = Double.parseDouble(collect.get(3));
							logger.info("Resolution is " + fileName + " - for chain " + chainName
									+ " - indexer:" + indexerInput + " ANGSTROMS:" + resolution);
						}
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
								item.setResi(Utils.checkDoubleAndReturn(cellValue));
							} catch (NumberFormatException e) {
								// not a double
							}
						} else if (innerCount == 3) {
							item.setResiName(cellValue);
							residueName = cellValue;
						} else if (innerCount == 2)
							item.setName(cellValue);
						else if (innerCount == 6)
							item.setxCoord(Utils.checkDoubleAndReturn(cellValue));
						else if (innerCount == 7)
							item.setyCoord(Utils.checkDoubleAndReturn(cellValue));
						else if (innerCount == 8)
							item.setzCoord(Utils.checkDoubleAndReturn(cellValue));
						innerCount++;

					}
				}

				if (!residueName.isEmpty() && !residueName.equals(lastResidueName)) {
					if (mapExcelStore.containsKey(chainName)) {
						item.setId(indexer);
						item.setChain(chainName);

						if (item.getName() != null) {
							mapExcelStore.get(chainName).add(item);
						}
					} else {
						item.setId(indexer);
						List<ExcelStore> listExcelStore = new ArrayList<ExcelStore>();
						mapExcelStore.put(chainName, listExcelStore);
						item.setChain(chainName);

						if (item.getName() != null) {
							mapExcelStore.get(chainName).add(item);
						}
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
		logger.info("Finished Reading filename based on Residues " + fileName + " - for chain " + chainName
				+ " - indexer:" + indexerInput);
		return indexer;
	}

	protected Integer readFromFile(Map<String, List<ExcelStore>> mapExcelStore, Map<String, Integer> mapIndexCount,
			String fileName, String chainName, Integer indexerInput, String filePath) {

		if (configurations.getResidueBased()) {
			return readFromFileResidue(mapExcelStore, mapIndexCount, fileName, chainName, indexerInput, filePath);
		}

		logger.info("Reading filename " + fileName + " - for chain " + chainName + " - indexer:" + indexerInput);
		String content = null;
		Integer indexer = indexerInput;
		File file = new File(filePath + fileName);
		try {
			content = new String(Files.readAllBytes(Paths.get(file.getAbsolutePath())));
			StringTokenizer st = new StringTokenizer(content);
			while (st.hasMoreTokens()) {
				String line = st.nextToken("\n");
				logger.debug(line);
				StringTokenizer stInner = new StringTokenizer(line);

				ExcelStore item = new ExcelStore();
				int innerCount = 0;
				while (stInner.hasMoreTokens()) {
					if (line.contains("BEGIN") || line.contains("END") || line.contains("REMARK")) {
						String cellValue = stInner.nextToken(" ");
						if (line.contains("REMARK")) {
							String[] words = line.split(" ");
							List<String> collect =
								    Stream.of(words)
								        .filter(itemStr -> itemStr != null && !"".equals(itemStr))
								        .collect(Collectors.toList());
							item.setResolution(Double.parseDouble(collect.get(3)));
							logger.info("Resolution is " + fileName + " - for chain " + chainName
									+ " - indexer:" + indexerInput + " ANGSTROMS:" + item.getResolution());
						}
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
								item.setResi(Utils.checkDoubleAndReturn(cellValue));
							} catch (NumberFormatException e) {
								// not a double
							}
						} else if (innerCount == 3)
							item.setResiName(cellValue);
						else if (innerCount == 2)
							item.setName(cellValue);
						else if (innerCount == 6)
							item.setxCoord(Utils.checkDoubleAndReturn(cellValue));
						else if (innerCount == 7)
							item.setyCoord(Utils.checkDoubleAndReturn(cellValue));
						else if (innerCount == 8)
							item.setzCoord(Utils.checkDoubleAndReturn(cellValue));
						innerCount++;

					}
				}

				if (mapExcelStore.containsKey(chainName)) {
					item.setId(indexer);
					item.setChain(chainName);
					if (item.getName() != null) {
						mapExcelStore.get(chainName).add(item);
					}
				} else {
					item.setId(indexer);
					List<ExcelStore> listExcelStore = new ArrayList<ExcelStore>();
					mapExcelStore.put(chainName, listExcelStore);
					item.setChain(chainName);

					if (item.getName() != null) {
						mapExcelStore.get(chainName).add(item);
					}
				}
				indexer++;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			logger.error("Error occurs while reading PDB file");
			e.printStackTrace();
		}

		mapIndexCount.put(chainName, indexer);
		logger.info("Finished Reading filename based on Atoms: " + fileName + " - for chain " + chainName
				+ " - indexer:" + indexerInput);
		return indexer;
	}

	protected List<PairPoints> parsePairPoints(AtomicReference<Double> minEdge, AtomicReference<Double> maxEdge,
			Map<String, List<ExcelStore>> mapExcelStore, Map<String, Integer> mapIndexCount,
			AtomicReference<Double> averageEdge) {

		List<PairPoints> listPairPoints = new ArrayList<PairPoints>();

		AtomicInteger count = new AtomicInteger();

		for (String keys : mapExcelStore.keySet()) {
			final List<ExcelStore> tempExcelStorList = mapExcelStore.get(keys);
			for (String keysInner : mapExcelStore.keySet()) {
				if (!keysInner.equals(keys)) {
					List<ExcelStore> tempExcelStorListInner = mapExcelStore.get(keysInner);
					tempExcelStorList.forEach(item -> {
						tempExcelStorListInner.forEach(itemInner -> {
							// logger.debug("Distance: " + item.distanceTo(itemInner));
							double temp = item.distanceTo(itemInner);

							averageEdge.set(averageEdge.get() + Math.abs(temp));
							count.getAndAdd(1);
							if (temp < configurations.getSignificanceThreshold()
									&& configurations.getSignificanceThresholdMax() == Double.MAX_VALUE) {
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
							} else {
								if (temp >= configurations.getSignificanceThreshold()
										&& temp < configurations.getSignificanceThresholdMax()) {

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
							}
						});
					});
					break;
				}
			}
			break;
		}

		averageEdge.set(averageEdge.get() / count.get());

		logger.info(
				"Minimum Edge Distance is: " + minEdge.get() + " - " + "Maximum Edge Distance is: " + maxEdge.get());
		return listPairPoints;
	}

	protected void initGraphNodes() {
		System.setProperty("org.graphstream.ui", "swing");
		graph.setAttribute("ui.quality");
		graph.setAttribute("ui.antialias");
		graph.setAttribute("ui.stylesheet", "url('data/style_shp.css')");

		// add some nodes
		int count = 0;
		String prevChain = "";
		for (String keys : mapExcelStore.keySet()) {
			if (count == 0) {
				final List<ExcelStore> tempExcelStorList = mapExcelStore.get(keys);
				tempExcelStorList.forEach(item -> {
					Node temp = graph.addNode(Integer.toString(item.getId()));
					nodesLayerAGraph.put(item.getId(), temp);
					temp.addAttribute("ui.style", "fill-color: rgb(0,100,255);");
					temp.addAttribute("residue", item.getResiName().toUpperCase());
					logger.debug("Residue " + "-" + item.getResiName());
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
					temp.addAttribute("residue", item.getResiName());
					logger.debug("Residue " + "-" + item.getResiName().toUpperCase());
					temp.addAttribute("layout.frozen");
					temp.addAttribute("xy", item.getxCoord(), item.getyCoord());
					if (configurations.getConsiderWeights()) {
						logger.debug("x: {}, y: {} , z{}", item.getxCoord(), item.getyCoord(), item.getzCoord());
						temp.addAttribute("xyz", item.getxCoord(), item.getyCoord(), item.getzCoord());
					}
				});
			}
		}
	}

	protected void initGraphEdges() {
		int edgeTotalIndex = 0;
		List<PairPoints> listPairPoints = parsePairPoints(minEdge, maxEdge, mapExcelStore, mapIndexCount, averageEdge);
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
					;
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
	}

	protected Integer calculateResults(ArrayList<Pair<ArrayList<Integer>, ArrayList<Integer>>> resultPair,
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
						if (dataset[itemLayerA - 1][itemLayerB] == 1 || (dataset[itemLayerA - 1][itemLayerB] != 0.0
								&& (configurations.getResidueBased() || configurations.getResidueEnergyMultiplier()))) {
							oneCounter++;
							if (configurations.getConsiderWeights()) {
								// Weights are negative so need to take abs
								edgeWeightDataset[itemLayerA - 1][itemLayerB] = Math
										.abs(edgeWeightDataset[itemLayerA - 1][itemLayerB]);
								energyPointLayerA[itemLayerA - 1] += edgeWeightDataset[itemLayerA - 1][itemLayerB];
								energyPointLayerB[itemLayerB] += edgeWeightDataset[itemLayerA - 1][itemLayerB];
							} else {
								energyPointLayerA[itemLayerA - 1] += (configurations.getResidueBased()
										|| configurations.getResidueEnergyMultiplier())
												? dataset[itemLayerA - 1][itemLayerB]
												: 1;
								energyPointLayerB[itemLayerB] += (configurations.getResidueBased()
										|| configurations.getResidueEnergyMultiplier())
												? dataset[itemLayerA - 1][itemLayerB]
												: 1;
							}
							logger.debug("Point {} - Dataset {}", energyPointLayerA[itemLayerA - 1],
									dataset[itemLayerA - 1][itemLayerB]);
							dataset[itemLayerA - 1][itemLayerB] = 0.0;
						}
					}
				}
			}
		}
		return oneCounter;
	}

	protected abstract ResultModel handlePDBProcessing();

	@Override
	public boolean checkPolarAttributeAndRemove(Node source, Node target) {
		// GDP and NAS
		ResiduePolarProperty targetProperty = configurations.getResiduePolarProperty()
				.get(target.getAttribute("residue").toString());
		ResiduePolarProperty sourceProperty = configurations.getResiduePolarProperty()
				.get(source.getAttribute("residue").toString());
		if (targetProperty == null || sourceProperty == null) {
			return false;
		}
		boolean polarCheckPropOne = configurations.getPropertyOne().equals(ResiduePolarProperty.POLAR.toString());
		boolean polarCheckPropTwo = configurations.getPropertyTwo().equals(ResiduePolarProperty.POLAR.toString());
		boolean apolarCheckPropOne = configurations.getPropertyOne().equals(ResiduePolarProperty.APOLAR.toString());
		boolean apolarCheckPropTwo = configurations.getPropertyTwo().equals(ResiduePolarProperty.APOLAR.toString());
		if ((polarCheckPropOne && polarCheckPropTwo) || (polarCheckPropOne && apolarCheckPropTwo)
				|| (apolarCheckPropOne && polarCheckPropTwo) || (apolarCheckPropOne && apolarCheckPropTwo)) {
			// POLAR-POLAR && POLAR-APOLAR && APOLAR-POLAR && APOLAR-APOLAR cases here
			if (polarCheckPropOne != polarCheckPropTwo || apolarCheckPropOne != apolarCheckPropTwo) {
				// POLAR-APOLAR && APOLAR-POLAR cases here
				if (targetProperty != sourceProperty) {
					return true;
				} else {
					return false;
				}
			} else {
				// POLAR-POLAR && APOLAR-APOLAR cases here
				if ((targetProperty == sourceProperty)
						&& configurations.getPropertyOne().equals(sourceProperty.toString())) {
					// POLAR-POLAR and the property is POLAR or APOLAR-APOLAR and the property is
					// APOLAR
					return true;
				} else {
					// POLAR-POLAR and the property is APOLAR or APOLAR-APOLAR and the property is
					// POLAR
					return false;
				}
			}
		}
		return false;
	}

	@Override
	public boolean checkChargedAttributeAndRemove(Node source, Node target) {
		// GDP and NAS
		if (configurations.getResidueChargedProperty().get(target.getAttribute("residue").toString()) == null
				|| configurations.getResidueChargedProperty().get(source.getAttribute("residue").toString()) == null) {
			return false;
		}
		boolean chargedCheckPropOne = configurations.getPropertyOne().equals(ResidueChargedProperty.CHARGED.toString());
		boolean chargedCheckPropTwo = configurations.getPropertyTwo().equals(ResidueChargedProperty.CHARGED.toString());
		boolean unchargedCheckPropOne = configurations.getPropertyOne()
				.equals(ResidueChargedProperty.UNCHARGED.toString());
		boolean unchargedCheckPropTwo = configurations.getPropertyTwo()
				.equals(ResidueChargedProperty.UNCHARGED.toString());
		if ((chargedCheckPropOne && chargedCheckPropTwo) || (chargedCheckPropOne && unchargedCheckPropTwo)
				|| (unchargedCheckPropOne && chargedCheckPropTwo) || (unchargedCheckPropOne && unchargedCheckPropTwo)) {
			if ((configurations.getPropertyOne()
					.contains(configurations.getResidueChargedProperty().get(source.getAttribute("residue").toString())
							.toString())
					&& configurations.getPropertyTwo()
							.contains(configurations.getResidueChargedProperty()
									.get(target.getAttribute("residue").toString()).toString()))
					|| (configurations.getPropertyOne()
							.contains(configurations.getResidueChargedProperty()
									.get(target.getAttribute("residue").toString()).toString())
							&& configurations.getPropertyTwo().contains(configurations.getResidueChargedProperty()
									.get(source.getAttribute("residue").toString()).toString()))) {
				return true;
			} else {
				return false;
			}
		}
		return false;
	}

	@Override
	public boolean checkPolarChargedAttributeAndRemove(Node source, Node target) {
		if (target.getAttribute("residue").toString().contains("GDP")
				|| source.getAttribute("residue").toString().contains("GDP")) {
			return true;
		}
		boolean polarCheckPropOne = configurations.getPropertyOne().equals(ResiduePolarProperty.POLAR.toString());
		boolean polarCheckPropTwo = configurations.getPropertyTwo().equals(ResiduePolarProperty.POLAR.toString());
		boolean chargedCheckPropOne = configurations.getPropertyOne().equals(ResidueChargedProperty.CHARGED.toString());
		boolean chargedCheckPropTwo = configurations.getPropertyTwo().equals(ResidueChargedProperty.CHARGED.toString());
		if ((polarCheckPropOne && chargedCheckPropTwo) || (chargedCheckPropOne && polarCheckPropTwo)) {
			if ((configurations.getPropertyOne().contains(
					configurations.getResiduePolarProperty().get(source.getAttribute("residue").toString()).toString())
					&& configurations.getPropertyTwo()
							.contains(configurations.getResidueChargedProperty()
									.get(target.getAttribute("residue").toString()).toString()))
					|| (configurations.getPropertyOne()
							.contains(configurations.getResidueChargedProperty()
									.get(target.getAttribute("residue").toString()).toString())
							&& configurations.getPropertyTwo().contains(configurations.getResiduePolarProperty()
									.get(source.getAttribute("residue").toString()).toString()))) {
				return true;
			} else {
				return false;
			}
		}
		return false;
	}

	@Override
	public boolean checkApolarChargedAttributeAndRemove(Node source, Node target) {
		if (target.getAttribute("residue").toString().contains("GDP")
				|| source.getAttribute("residue").toString().contains("GDP")) {
			return true;
		}
		boolean apolarCheckPropOne = configurations.getPropertyOne().equals(ResiduePolarProperty.APOLAR.toString());
		boolean apolarCheckPropTwo = configurations.getPropertyTwo().equals(ResiduePolarProperty.APOLAR.toString());
		boolean chargedCheckPropOne = configurations.getPropertyOne().equals(ResidueChargedProperty.CHARGED.toString());
		boolean chargedCheckPropTwo = configurations.getPropertyTwo().equals(ResidueChargedProperty.CHARGED.toString());
		if ((apolarCheckPropOne && chargedCheckPropTwo) || (chargedCheckPropOne && apolarCheckPropTwo)) {
			if ((configurations.getPropertyOne().contains(
					configurations.getResiduePolarProperty().get(source.getAttribute("residue").toString()).toString())
					&& configurations.getPropertyTwo()
							.contains(configurations.getResidueChargedProperty()
									.get(target.getAttribute("residue").toString()).toString()))
					|| (configurations.getPropertyOne()
							.contains(configurations.getResidueChargedProperty()
									.get(target.getAttribute("residue").toString()).toString())
							&& configurations.getPropertyTwo().contains(configurations.getResiduePolarProperty()
									.get(source.getAttribute("residue").toString()).toString()))) {
				return true;
			} else {
				return false;
			}
		}
		return false;
	}
}
