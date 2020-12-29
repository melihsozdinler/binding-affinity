package org.edge.biclique.source;

import org.edge.biclique.build.BuilderBindingAffinityAnalyzer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BindingAffinityAnalyzer {

	final static Logger logger = LoggerFactory.getLogger(BindingAffinityAnalyzer.class);

	@SuppressWarnings({})
	public static void main(String[] args) {
		logger.info("Start Main Execution");

		logger.info("Start of ResultFile_BA_1_5.0_Algorithm-4");
		BuilderBindingAffinityAnalyzer analyzer = BuilderBindingAffinityAnalyzer.Builder.newInstance()
				.setFilePath("E:\\BIIP\\BindingAffinity\\").setExcelFilePath("ResultFile_BA_50_20.0_Algorithm-1-New-S_1")
				.setSignificanceThreshold(20.0).setBicliqueSteps(50).setMaxNumberOfThreads(8).setChopIndex(1)
				.setConsiderWeightsWithSize(false).setConsiderWeights(false).setTotalSamples(4).enableDebug(false)
				.build();

		analyzer.startAnalyze();
		logger.info("End of ResultFile_BA_1_5.0_Algorithm-4");
/*
		logger.info("Start of ResultFile_BA_25_6.0_Algorithm-3");
		analyzer = BuilderBindingAffinityAnalyzer.Builder.newInstance().setFilePath("E:\\BIIP\\BindingAffinity\\")
				.setExcelFilePath("ResultFile_BA_25_6.0_Algorithm-3-S").setSignificanceThreshold(6.0)
				.setBicliqueSteps(25).setMaxNumberOfThreads(8).setChopIndex(1).setConsiderWeightsWithSize(true)
				.setConsiderWeights(true).setTotalSamples(4).enableDebug(false).build();

		analyzer.startAnalyze();
		logger.info("End of ResultFile_BA_25_6.0_Algorithm-3");

		logger.info("Start of ResultFile_BA_25_7.0_Algorithm-3");
		analyzer = BuilderBindingAffinityAnalyzer.Builder.newInstance().setFilePath("E:\\BIIP\\BindingAffinity\\")
				.setExcelFilePath("ResultFile_BA_25_7.0_Algorithm-3-S").setSignificanceThreshold(7.0)
				.setBicliqueSteps(25).setMaxNumberOfThreads(8).setChopIndex(1).setConsiderWeightsWithSize(true)
				.setConsiderWeights(true).setTotalSamples(4).enableDebug(false).build();

		analyzer.startAnalyze();
		logger.info("End of ResultFile_BA_25_7.0_Algorithm-3");

		logger.info("Start of ResultFile_BA_25_8.0_Algorithm-3");
		analyzer = BuilderBindingAffinityAnalyzer.Builder.newInstance().setFilePath("E:\\BIIP\\BindingAffinity\\")
				.setExcelFilePath("ResultFile_BA_25_8.0_Algorithm-3-S").setSignificanceThreshold(8.0)
				.setBicliqueSteps(25).setMaxNumberOfThreads(8).setChopIndex(1).setConsiderWeightsWithSize(true)
				.setConsiderWeights(true).setTotalSamples(4).enableDebug(false).build();

		analyzer.startAnalyze();
		logger.info("End of ResultFile_BA_25_8.0_Algorithm-3");

		logger.info("Start of ResultFile_BA_25_9.0_Algorithm-3");
		analyzer = BuilderBindingAffinityAnalyzer.Builder.newInstance().setFilePath("E:\\BIIP\\BindingAffinity\\")
				.setExcelFilePath("ResultFile_BA_25_9.0_Algorithm-3-S").setSignificanceThreshold(9.0)
				.setBicliqueSteps(25).setMaxNumberOfThreads(8).setChopIndex(1).setConsiderWeightsWithSize(true)
				.setConsiderWeights(true).setTotalSamples(4).enableDebug(false).build();

		analyzer.startAnalyze();
		logger.info("End of ResultFile_BA_25_9.0_Algorithm-3");

		logger.info("Start of ResultFile_BA_25_10.0_Algorithm-3");
		analyzer = BuilderBindingAffinityAnalyzer.Builder.newInstance().setFilePath("E:\\BIIP\\BindingAffinity\\")
				.setExcelFilePath("ResultFile_BA_25_10.0_Algorithm-3-S").setSignificanceThreshold(10.0)
				.setBicliqueSteps(25).setMaxNumberOfThreads(8).setChopIndex(1).setConsiderWeightsWithSize(true)
				.setConsiderWeights(true).setTotalSamples(4).enableDebug(false).build();

		analyzer.startAnalyze();
		logger.info("End of ResultFile_BA_25_11.0_Algorithm-3");

		logger.info("Start of ResultFile_BA_25_11.0_Algorithm-3");
		analyzer = BuilderBindingAffinityAnalyzer.Builder.newInstance().setFilePath("E:\\BIIP\\BindingAffinity\\")
				.setExcelFilePath("ResultFile_BA_25_11.0_Algorithm-3-S").setSignificanceThreshold(11.0)
				.setBicliqueSteps(25).setMaxNumberOfThreads(8).setChopIndex(1).setConsiderWeightsWithSize(true)
				.setConsiderWeights(true).setTotalSamples(4).enableDebug(false).build();

		analyzer.startAnalyze();
		logger.info("End of ResultFile_BA_25_12.0_Algorithm-3");

		logger.info("Start of ResultFile_BA_25_12.0_Algorithm-3");
		analyzer = BuilderBindingAffinityAnalyzer.Builder.newInstance().setFilePath("E:\\BIIP\\BindingAffinity\\")
				.setExcelFilePath("ResultFile_BA_25_12.0_Algorithm-3-S").setSignificanceThreshold(12.0)
				.setBicliqueSteps(25).setMaxNumberOfThreads(8).setChopIndex(1).setConsiderWeightsWithSize(true)
				.setConsiderWeights(true).setTotalSamples(4).enableDebug(false).build();

		analyzer.startAnalyze();
		logger.info("End of ResultFile_BA_25_12.0_Algorithm-3");

		logger.info("Start of ResultFile_BA_25_13.0_Algorithm-3");
		analyzer = BuilderBindingAffinityAnalyzer.Builder.newInstance().setFilePath("E:\\BIIP\\BindingAffinity\\")
				.setExcelFilePath("ResultFile_BA_25_13.0_Algorithm-3-S").setSignificanceThreshold(13.0)
				.setBicliqueSteps(25).setMaxNumberOfThreads(8).setChopIndex(1).setConsiderWeightsWithSize(true)
				.setConsiderWeights(true).setTotalSamples(4).enableDebug(false).build();

		analyzer.startAnalyze();
		logger.info("End of ResultFile_BA_25_13.0_Algorithm-3");

		logger.info("Start of ResultFile_BA_25_14.0_Algorithm-3");
		analyzer = BuilderBindingAffinityAnalyzer.Builder.newInstance().setFilePath("E:\\BIIP\\BindingAffinity\\")
				.setExcelFilePath("ResultFile_BA_25_14.0_Algorithm-3-S").setSignificanceThreshold(14.0)
				.setBicliqueSteps(25).setMaxNumberOfThreads(8).setChopIndex(1).setConsiderWeightsWithSize(true)
				.setConsiderWeights(true).setTotalSamples(4).enableDebug(false).build();

		analyzer.startAnalyze();
		logger.info("End of ResultFile_BA_25_14.0_Algorithm-3");
		/*
		 * logger.info("Start of ResultFile_BA_25_15.0_Algorithm-3"); analyzer =
		 * BuilderBindingAffinityAnalyzer.Builder.newInstance().setFilePath(
		 * "E:\\BIIP\\BindingAffinity\\")
		 * .setExcelFilePath("ResultFile_BA_25_15.0_Algorithm-3-S").
		 * setSignificanceThreshold(15.0)
		 * .setBicliqueSteps(25).setMaxNumberOfThreads(8).setChopIndex(1).
		 * setConsiderWeightsWithSize(true).setConsiderWeights(true).setTotalSamples(4)
		 * .enableDebug(false).build();
		 * 
		 * analyzer.startAnalyze();
		 * logger.info("End of ResultFile_BA_25_15.0_Algorithm-3");
		 */
		logger.info("Finish Main Execution");
	}
}
