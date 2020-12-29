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
			return analyzer;
		}

		public static Builder newInstance() {
			return new Builder();
		}

		private Builder() {
		}

		public Builder setConfigurations(String filePath, String excelFilePath, Double thresholdVal, Boolean debug,
				Integer numberOfBicliqueSteps, Integer maxNumberOfThreads, Integer chopIndexForFiles,
				Integer beginIndex, Integer endIndex, Boolean considerWeights, Boolean considerWeightsWithSize) {
			this.configurations.setFilePath(filePath);
			this.configurations.setExcelFilePath(excelFilePath);
			this.configurations.setSignificanceThreshold(thresholdVal);
			this.configurations.setDebug(debug);
			this.configurations.setNumberOfBicqliueSteps(numberOfBicliqueSteps);
			this.configurations.setMaxNumberOfThreads(maxNumberOfThreads);
			this.configurations.setChopIndexForFiles(chopIndexForFiles);
			this.configurations.setBeginIndex(beginIndex);
			this.configurations.setEndIndex(endIndex);
			this.configurations.setConsiderWeights(considerWeights);
			this.configurations.setConsiderWeightsWithSize(considerWeightsWithSize);
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
			// Parse title lines at the top
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
