package org.edge.biclique.build;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.edge.biclique.source.model.Configurations;
import org.edge.biclique.source.model.ExcelStore;
import org.edge.biclique.source.model.ResidueChargedProperty;
import org.edge.biclique.source.model.ResiduePolarProperty;
import org.edge.biclique.source.model.ResultModel;
import org.edge.biclique.source.util.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class BuilderBindingAffinityAnalyzer{
	
	final static Logger logger = LoggerFactory.getLogger(BuilderBindingAffinityAnalyzer.class);

	private Configurations configurations;

	private List<ResultModel> allResults = new ArrayList<ResultModel>();

	// Fields omitted for brevity.
	public BuilderBindingAffinityAnalyzer(Builder builder) {
		// Constructor is now private.
		configurations = builder.getConfigurations();
	}

	public static class Builder {

		private Configurations configurations = new Configurations();

		public BuilderBindingAffinityAnalyzer build() {
			BuilderBindingAffinityAnalyzer analyzer = new BuilderBindingAffinityAnalyzer(this);
			if (configurations.getResidueBased() || configurations.getResidueEnergyMultiplier()) {
				configurations.setResidueEnergies(new ConcurrentHashMap<String, Double>());
				readFromResidueEnergyMapping();
			}
			mapResiduePolarProperty(); // Required for Polar and Polar
			mapResidueChargedProperty(); // Required for Charged and Un-charged
			return analyzer;
		}

		public static Builder newInstance() {
			return new Builder();
		}
		
		private void mapResidueChargedProperty() {
			/* CHARGED RESIDUES */
			configurations.getResidueChargedProperty().put("ARG", ResidueChargedProperty.CHARGED);
			configurations.getResidueChargedProperty().put("LYS", ResidueChargedProperty.CHARGED);
			configurations.getResidueChargedProperty().put("ASP", ResidueChargedProperty.CHARGED);
			configurations.getResidueChargedProperty().put("GLU", ResidueChargedProperty.CHARGED);
			configurations.getResidueChargedProperty().put("HIS", ResidueChargedProperty.CHARGED);
			
			/* UNCHARGED RESIDUES */
			configurations.getResidueChargedProperty().put("GLN", ResidueChargedProperty.UNCHARGED);
			configurations.getResidueChargedProperty().put("ASN", ResidueChargedProperty.UNCHARGED);
			configurations.getResidueChargedProperty().put("SER", ResidueChargedProperty.UNCHARGED);
			configurations.getResidueChargedProperty().put("THR", ResidueChargedProperty.UNCHARGED);
			configurations.getResidueChargedProperty().put("TYR", ResidueChargedProperty.UNCHARGED);
			configurations.getResidueChargedProperty().put("CYS", ResidueChargedProperty.UNCHARGED);
			configurations.getResidueChargedProperty().put("TRP", ResidueChargedProperty.UNCHARGED); 
			configurations.getResidueChargedProperty().put("MET", ResidueChargedProperty.UNCHARGED); 
			configurations.getResidueChargedProperty().put("ALA", ResidueChargedProperty.UNCHARGED); 
			configurations.getResidueChargedProperty().put("ILE", ResidueChargedProperty.UNCHARGED); 
			configurations.getResidueChargedProperty().put("LEU", ResidueChargedProperty.UNCHARGED); 
			configurations.getResidueChargedProperty().put("PHE", ResidueChargedProperty.UNCHARGED); 
			configurations.getResidueChargedProperty().put("VAL", ResidueChargedProperty.UNCHARGED); 
			configurations.getResidueChargedProperty().put("PRO", ResidueChargedProperty.UNCHARGED); 
			configurations.getResidueChargedProperty().put("GLY", ResidueChargedProperty.UNCHARGED); 
		}
		
		private void mapResiduePolarProperty() {
			/* POLAR/CHARGED RESIDUES */
			//**Charged (*****side chains often form salt bridges\*****):**
			configurations.getResiduePolarProperty().put("ARG", ResiduePolarProperty.POLAR); // 01 • Arginine - Arg - R
			configurations.getResiduePolarProperty().put("LYS", ResiduePolarProperty.POLAR); // 02 • Lysine - Lys - K
			configurations.getResiduePolarProperty().put("ASP", ResiduePolarProperty.POLAR); // 03 • Aspartic acid - Asp - D
			configurations.getResiduePolarProperty().put("GLU", ResiduePolarProperty.POLAR); // 04 • Glutamic acid - Glu - E

			/* POLAR RESIDUES */
			//**Polar (*****form hydrogen bonds as proton donors or acceptors\*****):**
			configurations.getResiduePolarProperty().put("GLN", ResiduePolarProperty.POLAR); // 05 • Glutamine - Gln - Q
			configurations.getResiduePolarProperty().put("ASN", ResiduePolarProperty.POLAR); // 06 • Asparagine - Asn - N
			configurations.getResiduePolarProperty().put("HIS", ResiduePolarProperty.POLAR); // 07 • Histidine - His - H
			configurations.getResiduePolarProperty().put("SER", ResiduePolarProperty.POLAR); // 08 • Serine - Ser - S
			configurations.getResiduePolarProperty().put("THR", ResiduePolarProperty.POLAR); // 09 • Threonine - Thr - T
			configurations.getResiduePolarProperty().put("TYR", ResiduePolarProperty.POLAR); // 10 • Tyrosine - Tyr - Y
			configurations.getResiduePolarProperty().put("CYS", ResiduePolarProperty.POLAR); // 11 • Cysteine - Cys - C
			
			/* APOLAR RESIDUES */
			//**Amphipathic (*****often found at the surface of proteins or lipid membranes, sometimes also classified as polar\*****):**
			configurations.getResiduePolarProperty().put("TRP", ResiduePolarProperty.APOLAR); // 12 • Tryptophan - Trp - W
			                                                                                  // 09 • Tyrosine - Tyr - Y
			configurations.getResiduePolarProperty().put("MET", ResiduePolarProperty.APOLAR); // 13 • Methionine - Met - M (may function as a ligand to metal ions)

			/* APOLAR RESIDUES */
			//**Hydrophobic (*****normally buried inside the protein core)\*****:**
			configurations.getResiduePolarProperty().put("ALA", ResiduePolarProperty.APOLAR); // 14 • Alanine - Ala - A
			configurations.getResiduePolarProperty().put("ILE", ResiduePolarProperty.APOLAR); // 15 • Isoleucine - Ile - I
			configurations.getResiduePolarProperty().put("LEU", ResiduePolarProperty.APOLAR); // 16 • Leucine - Leu - L
			                                                                                  // 13 • Methionine - Met - M
			configurations.getResiduePolarProperty().put("PHE", ResiduePolarProperty.APOLAR); // 17 • Phenylalanine - Phe - F
			configurations.getResiduePolarProperty().put("VAL", ResiduePolarProperty.APOLAR); // 18 • Valine - Val - V
			configurations.getResiduePolarProperty().put("PRO", ResiduePolarProperty.APOLAR); // 19 • Proline - Pro - P
			configurations.getResiduePolarProperty().put("GLY", ResiduePolarProperty.APOLAR); // 20 • Glycine - Gly - G
		}
		
		private void readFromResidueEnergyMapping() {
			File file = new File(configurations.getFilePath() + "amino_acid_energy.txt");
			String content;
			try {
				content = new String(Files.readAllBytes(Paths.get(file.getAbsolutePath())));
				StringTokenizer st = new StringTokenizer(content);
				int firstLineCount = 0;
				List<String> aminoAcidNames = new ArrayList<String>();
				while (st.hasMoreTokens()) {
					String line = st.nextToken("\n");
					StringTokenizer stInner = new StringTokenizer(line.trim());
					int count = 0;
					String aminoAcidTmp = "";
					while (stInner.hasMoreTokens()) {
						String word = stInner.nextToken(",");
						if (firstLineCount == 0) {
							aminoAcidNames.add(word.trim());
						}
						if (count == 0 && firstLineCount != 0) {
							aminoAcidTmp = word;
						}
						if (count != 0 && firstLineCount != 0) {
							//System.out.println(aminoAcidNames.get(count - 1) + "_" + aminoAcidTmp + "-" + word.trim());
							configurations.getResidueEnergies().put(aminoAcidNames.get(count - 1) + "_" + aminoAcidTmp,
									/*Math.abs*/(Double.parseDouble(word.trim())));
						}
						count++;
					}
					//System.out.println("");
					firstLineCount++;
				}
			} catch (IOException e) {
				System.out.println("Error occur reading while amino_acid_energy.txt file");
			}
		}

		private Builder() {
			// Builder patternon
		}

		public Builder setConfigurations(String filePath, String excelFilePath, Double thresholdVal,
				Double thresholdValMax, Boolean debug, Integer numberOfBicliqueSteps, Integer maxNumberOfThreads,
				Integer chopIndexForFiles, Integer beginIndex, Integer endIndex, Boolean considerWeights,
				Boolean considerWeightsWithSize, Boolean distanceMultiplier, Boolean residueBased,
				Boolean residueEnergyMultiplier) {
			this.configurations.setFilePath(filePath);
			this.configurations.setExcelFilePath(excelFilePath);
			this.configurations.setSignificanceThreshold(thresholdVal);
			this.configurations.setSignificanceThresholdMax(thresholdValMax);
			this.configurations.setDebug(debug);
			this.configurations.setNumberOfBicqliueSteps(numberOfBicliqueSteps);
			this.configurations.setMaxNumberOfThreads(maxNumberOfThreads);
			this.configurations.setChopIndexForFiles(chopIndexForFiles);
			this.configurations.setBeginIndex(beginIndex);
			this.configurations.setEndIndex(endIndex);
			this.configurations.setConsiderWeights(considerWeights);
			this.configurations.setConsiderWeightsWithSize(considerWeightsWithSize);
			this.configurations.setDistanceMultiplier(distanceMultiplier);
			this.configurations.setResidueBased(residueBased);
			this.configurations.setResidueEnergyMultiplier(residueEnergyMultiplier);
			return this;
		}

		public Builder setFilePath(String filePath) {
			this.configurations.setFilePath(filePath);
			return this;
		}

		public Builder setExcelFilePath(String excelFilePath) {
			this.configurations.setExcelFilePath(excelFilePath);
			return this;
		}
		
		public Builder setSignificanceThresholdMax(Double thresholdValMax) {
			this.configurations.setSignificanceThresholdMax(thresholdValMax);
			return this;
		}

		public Builder setSignificanceThreshold(Double thresholdVal) {
			this.configurations.setSignificanceThreshold(thresholdVal);
			return this;
		}

		public Builder setBicliqueSteps(Integer numberOfBicliqueSteps) {
			this.configurations.setNumberOfBicqliueSteps(numberOfBicliqueSteps);
			return this;
		}

		public Builder setMaxNumberOfThreads(Integer maxNumberOfThreads) {
			this.configurations.setMaxNumberOfThreads(maxNumberOfThreads);
			return this;
		}

		public Builder enableDebug(Boolean debug) {
			this.configurations.setDebug(debug);
			return this;
		}

		public Builder setChopIndex(Integer chopIndexForFiles) {
			this.configurations.setChopIndexForFiles(chopIndexForFiles);
			return this;
		}

		public Builder setBeginIndex(Integer beginIndex) {
			this.configurations.setBeginIndex(beginIndex);
			return this;
		}

		public Builder setEndIndex(Integer endIndex) {
			this.configurations.setEndIndex(endIndex);
			return this;
		}

		public Builder setTotalSamples(Integer sampleSize) {
			this.configurations.setTotalSamples(sampleSize);
			return this;
		}

		public Builder setConsiderWeights(Boolean considerWeigths) {
			this.configurations.setConsiderWeights(considerWeigths);
			return this;
		}
		
		public Builder setConsiderWeightsWithSize(Boolean considerWeigthsWithSize) {
			this.configurations.setConsiderWeightsWithSize(considerWeigthsWithSize);
			return this;
		}
		
		public Builder setDistanceMultiplier(Boolean distanceMultiplier) {
			this.configurations.setDistanceMultiplier(distanceMultiplier);
			return this;
		}
		
		public Builder setResidueBased(Boolean residueBased) {
			this.configurations.setResidueBased(residueBased);
			return this;
		}
		
		public Builder setResidueEnergyMultiplier(Boolean residueEnergyMultiplier) {
			this.configurations.setResidueEnergyMultiplier(residueEnergyMultiplier);
			return this;
		}
		
		public Builder setPropertyOne(String propertyOne) {
			this.configurations.setPropertyOne(propertyOne);
			return this;
		}
		
		public Builder setPropertyTwo(String propertyTwo) {
			this.configurations.setPropertyTwo(propertyTwo);
			return this;
		}

		public Configurations getConfigurations() {
			return configurations;
		}
	}

	public void startAnalyze() {
		logger.info("Start Analysis with configurations {}", configurations.toString());
		capriCIFFileProcessor();
		writeResultsToWorksheet();
		allResults.clear();
		logger.info("End of Analysis with configurations");
	}
	
	
	private void processFiles(String fileContent, List<Callable<ResultModel>> taskLists) {
		
		if (null != fileContent) {

			StringTokenizer st = new StringTokenizer(fileContent);

			String line = st.nextToken("\n");
			// Parse title lines a t the top
			line = st.nextToken("\n");
			while (st.hasMoreTokens() || !line.isEmpty()) {
				Callable<ResultModel> task = new BindingAffinityExecutor(configurations, line);
				taskLists.add(task);
				if (st.hasMoreTokens()) {
					line = st.nextToken("\n");
				}
				else {
					line = "";
				}
			}
		}
	}
	
	private void threadProcessing(List<Callable<ResultModel>> taskLists) {
		ExecutorService executor = Executors.newFixedThreadPool(configurations.getMaxNumberOfThreads());
		List<Future<ResultModel>> resultList = new ArrayList<>();

		for (Callable<ResultModel> items : taskLists) {
			Future<ResultModel> future = executor.submit(items);
			resultList.add(future);
		}

		for (Future<?> future : resultList) {
			try {
				allResults.add((ResultModel) future.get());
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ExecutionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} // get will block until the future is done
		}
		// shut down the executor service now
		executor.shutdown();
		try {
			if (!executor.awaitTermination(60, TimeUnit.SECONDS)) {
				executor.shutdownNow();
			}
		} catch (InterruptedException ex) {
			executor.shutdownNow();
			Thread.currentThread().interrupt();
		}
	}

	private void capriCIFFileProcessor() {
		logger.info("Start of CIF File Processor");

		File directory = new File(configurations.getFilePath() + "PDB_stucture_MAP.out");
		List<Callable<ResultModel>> taskLists = Collections.synchronizedList(new ArrayList<Callable<ResultModel>>());

		String ourFileContent = null;
		try {
			ourFileContent = new String(Files.readAllBytes(Paths.get(directory.getAbsolutePath())));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		processFiles(ourFileContent, taskLists);
		threadProcessing(taskLists);

		logger.info("End of CIF File Processor");
	}

	private void writeResultsToWorksheet() {

		List<List<ResultModel>> allResultsChopped = Utils.chopIntoParts(allResults,
				configurations.getChopIndexForFile());
		Integer partCount = 1;
		for (List<ResultModel> allResultsPart : allResultsChopped) {
			Workbook workbook = new XSSFWorkbook(); // new HSSFWorkbook() for generating `.xls` file

			// Create a Sheet
			Sheet sheetA = workbook.createSheet("Chain-A");
			Sheet sheetB = workbook.createSheet("Chain-B");

			int count = 0;
			for (ResultModel items : allResultsPart) {
				// Create a Row
				Row newRowA = sheetA.createRow(count);
				Row newRowB = sheetB.createRow(count);

				newRowA.createCell(0).setCellValue(items.complexName);
				newRowB.createCell(0).setCellValue(items.complexName);
				newRowA.createCell(1).setCellValue(items.fileName);
				newRowB.createCell(1).setCellValue(items.fileName);

				for (int innerCount = 0; innerCount < items.sizeA; innerCount++) {
					newRowA.createCell(innerCount + 2).setCellValue(items.energyPointLayerA.get(innerCount));
				}
				for (int innerCount = 0; innerCount < items.sizeB; innerCount++) {
					newRowB.createCell(innerCount + 2).setCellValue(items.energyPointLayerB.get(innerCount));
				}

				count++;
			}

			// Write the output to a file
			FileOutputStream fileOut = null;
			try {
				fileOut = new FileOutputStream(configurations.getExcelFilePath() + "_" + allResultsPart.size() + "_"
						+ partCount + "_" + configurations.getChopIndexForFile() + ".xlsx");
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				workbook.write(fileOut);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			try {
				fileOut.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			// Closing the workbook
			try {
				workbook.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			partCount++;
		}
	}
}
