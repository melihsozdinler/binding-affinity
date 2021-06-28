package org.edge.biclique.source;

import org.edge.biclique.build.BuilderBindingAffinityAnalyzer;
import org.edge.biclique.source.util.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BindingAffinityAnalyzer {

	final static Logger logger = LoggerFactory.getLogger(BindingAffinityAnalyzer.class);

	@SuppressWarnings({})
	public static void main(String[] args) {
		logger.info("Start Main Execution");
		BuilderBindingAffinityAnalyzer analyzer = BuilderBindingAffinityAnalyzer.Builder.newInstance()
				.setFilePath("E:\\BIIP\\BindingAffinity\\").setExcelFilePath("ResultFile_BA_1_8.75_Algorithm-Atom-15-Atom_ALL")
				.setSignificanceThreshold(0.0).setSignificanceThresholdMax(8.75).setBicliqueSteps(15)
				.setMaxNumberOfThreads(3).setChopIndex(1).setConsiderWeightsWithSize(false).setConsiderWeights(true)
				.setDistanceMultiplier(false).setResidueBased(false).setResidueEnergyMultiplier(false).setTotalSamples(4)
				.enableDebug(false).build();
		analyzer.startAnalyze();
		Utils.removeFiles("src\\bin\\", "txt");
		
		analyzer = BuilderBindingAffinityAnalyzer.Builder.newInstance()
				.setFilePath("E:\\BIIP\\BindingAffinity\\").setExcelFilePath("ResultFile_BA_1_8.75_Algorithm-Unweighted-Atom-15-Atom_ALL")
				.setSignificanceThreshold(0.0).setSignificanceThresholdMax(8.75).setBicliqueSteps(15)
				.setMaxNumberOfThreads(3).setChopIndex(1).setConsiderWeightsWithSize(false).setConsiderWeights(false)
				.setDistanceMultiplier(false).setResidueBased(false).setResidueEnergyMultiplier(false).setTotalSamples(4)
				.enableDebug(false).build();
		analyzer.startAnalyze();
		Utils.removeFiles("src\\bin\\", "txt");
		
		analyzer = BuilderBindingAffinityAnalyzer.Builder.newInstance()
				.setFilePath("E:\\BIIP\\BindingAffinity\\").setExcelFilePath("ResultFile_BA_1_8.75_Algorithm-Atom-20-Atom_ALL")
				.setSignificanceThreshold(0.0).setSignificanceThresholdMax(8.75).setBicliqueSteps(20)
				.setMaxNumberOfThreads(6).setChopIndex(1).setConsiderWeightsWithSize(false).setConsiderWeights(true)
				.setDistanceMultiplier(false).setResidueBased(false).setResidueEnergyMultiplier(false).setTotalSamples(4)
				.enableDebug(false).build();
		analyzer.startAnalyze();
		Utils.removeFiles("src\\bin\\", "txt");
		
		analyzer = BuilderBindingAffinityAnalyzer.Builder.newInstance()
				.setFilePath("E:\\BIIP\\BindingAffinity\\").setExcelFilePath("ResultFile_BA_1_8.75_Algorithm-Unweighted-Atom-20-Atom_ALL")
				.setSignificanceThreshold(0.0).setSignificanceThresholdMax(8.75).setBicliqueSteps(20)
				.setMaxNumberOfThreads(6).setChopIndex(1).setConsiderWeightsWithSize(false).setConsiderWeights(false)
				.setDistanceMultiplier(false).setResidueBased(false).setResidueEnergyMultiplier(false).setTotalSamples(4)
				.enableDebug(false).build();
		analyzer.startAnalyze();
		Utils.removeFiles("src\\bin\\", "txt");
		/*
		logger.info("Start Main Execution");
		logger.info("Start of ResultFile_BA_1_10.0_Algorithm-1-ResidueEnergy-S_1");
		BuilderBindingAffinityAnalyzer analyzer = BuilderBindingAffinityAnalyzer.Builder.newInstance()
				.setFilePath("E:\\BIIP\\BindingAffinity\\").setExcelFilePath("ResultFile_BA_1_20.0_Algorithm-25-Residue-S_ALL")
				.setSignificanceThreshold(10.0).setSignificanceThresholdMax(20.0).setBicliqueSteps(25)
				.setMaxNumberOfThreads(6).setChopIndex(1).setConsiderWeightsWithSize(false).setConsiderWeights(true)
				.setDistanceMultiplier(false).setResidueBased(true).setResidueEnergyMultiplier(false).setTotalSamples(4)
				.enableDebug(false).build();
		analyzer.startAnalyze();
		Utils.removeFiles("src\\bin\\", "txt");
		
		logger.info("Start of ResultFile_BA_1_10.0_Algorithm-1-ResidueEnergy-S_1");
		analyzer = BuilderBindingAffinityAnalyzer.Builder.newInstance()
				.setFilePath("E:\\BIIP\\BindingAffinity\\").setExcelFilePath("ResultFile_BA_1_15.0_Algorithm-25-Residue-S_ALL")
				.setSignificanceThreshold(10.0).setSignificanceThresholdMax(15.0).setBicliqueSteps(25)
				.setMaxNumberOfThreads(6).setChopIndex(1).setConsiderWeightsWithSize(false).setConsiderWeights(true)
				.setDistanceMultiplier(false).setResidueBased(true).setResidueEnergyMultiplier(false).setTotalSamples(4)
				.enableDebug(false).build();
		analyzer.startAnalyze();
		Utils.removeFiles("src\\bin\\", "txt");
		*/
		/*
		logger.info("Start Main Execution");
		BuilderBindingAffinityAnalyzer analyzer = BuilderBindingAffinityAnalyzer.Builder.newInstance()
				.setFilePath("E:\\BIIP\\BindingAffinity\\").setExcelFilePath("ResultFile_BA_1_7.5_Algorithm-Atom-15-Atom_ALL")
				.setSignificanceThreshold(0.0).setSignificanceThresholdMax(7.5).setBicliqueSteps(15)
				.setMaxNumberOfThreads(6).setChopIndex(1).setConsiderWeightsWithSize(false).setConsiderWeights(true)
				.setDistanceMultiplier(false).setResidueBased(false).setResidueEnergyMultiplier(false).setTotalSamples(4)
				.enableDebug(false).build();
		analyzer.startAnalyze();
		Utils.removeFiles("src\\bin\\", "txt");
		
		analyzer = BuilderBindingAffinityAnalyzer.Builder.newInstance()
				.setFilePath("E:\\BIIP\\BindingAffinity\\").setExcelFilePath("ResultFile_BA_1_7.5_Algorithm-Unweighted-Atom-15-Atom_ALL")
				.setSignificanceThreshold(0.0).setSignificanceThresholdMax(7.5).setBicliqueSteps(15)
				.setMaxNumberOfThreads(6).setChopIndex(1).setConsiderWeightsWithSize(false).setConsiderWeights(false)
				.setDistanceMultiplier(false).setResidueBased(false).setResidueEnergyMultiplier(false).setTotalSamples(4)
				.enableDebug(false).build();
		analyzer.startAnalyze();
		Utils.removeFiles("src\\bin\\", "txt");
		
		analyzer = BuilderBindingAffinityAnalyzer.Builder.newInstance()
				.setFilePath("E:\\BIIP\\BindingAffinity\\").setExcelFilePath("ResultFile_BA_1_7.5_Algorithm-Atom-20-Atom_ALL")
				.setSignificanceThreshold(0.0).setSignificanceThresholdMax(7.5).setBicliqueSteps(20)
				.setMaxNumberOfThreads(6).setChopIndex(1).setConsiderWeightsWithSize(false).setConsiderWeights(true)
				.setDistanceMultiplier(false).setResidueBased(false).setResidueEnergyMultiplier(false).setTotalSamples(4)
				.enableDebug(false).build();
		analyzer.startAnalyze();
		Utils.removeFiles("src\\bin\\", "txt");
		
		analyzer = BuilderBindingAffinityAnalyzer.Builder.newInstance()
				.setFilePath("E:\\BIIP\\BindingAffinity\\").setExcelFilePath("ResultFile_BA_1_7.5_Algorithm-Unweighted-Atom-20-Atom_ALL")
				.setSignificanceThreshold(0.0).setSignificanceThresholdMax(7.5).setBicliqueSteps(20)
				.setMaxNumberOfThreads(6).setChopIndex(1).setConsiderWeightsWithSize(false).setConsiderWeights(false)
				.setDistanceMultiplier(false).setResidueBased(false).setResidueEnergyMultiplier(false).setTotalSamples(4)
				.enableDebug(false).build();
		analyzer.startAnalyze();
		Utils.removeFiles("src\\bin\\", "txt");
		
		analyzer = BuilderBindingAffinityAnalyzer.Builder.newInstance()
				.setFilePath("E:\\BIIP\\BindingAffinity\\").setExcelFilePath("ResultFile_BA_1_10.0_Algorithm-Atom-15-Atom_ALL")
				.setSignificanceThreshold(0.0).setSignificanceThresholdMax(10.0).setBicliqueSteps(15)
				.setMaxNumberOfThreads(6).setChopIndex(1).setConsiderWeightsWithSize(false).setConsiderWeights(true)
				.setDistanceMultiplier(false).setResidueBased(false).setResidueEnergyMultiplier(false).setTotalSamples(4)
				.enableDebug(false).build();
		analyzer.startAnalyze();
		Utils.removeFiles("src\\bin\\", "txt");
		
		analyzer = BuilderBindingAffinityAnalyzer.Builder.newInstance()
				.setFilePath("E:\\BIIP\\BindingAffinity\\").setExcelFilePath("ResultFile_BA_1_10.0_Algorithm-Unweighted-Atom-15-Atom_ALL")
				.setSignificanceThreshold(0.0).setSignificanceThresholdMax(10.0).setBicliqueSteps(15)
				.setMaxNumberOfThreads(6).setChopIndex(1).setConsiderWeightsWithSize(false).setConsiderWeights(false)
				.setDistanceMultiplier(false).setResidueBased(false).setResidueEnergyMultiplier(false).setTotalSamples(4)
				.enableDebug(false).build();
		analyzer.startAnalyze();
		Utils.removeFiles("src\\bin\\", "txt");
		
		analyzer = BuilderBindingAffinityAnalyzer.Builder.newInstance()
				.setFilePath("E:\\BIIP\\BindingAffinity\\").setExcelFilePath("ResultFile_BA_1_10.0_Algorithm-Atom-20-Atom_ALL")
				.setSignificanceThreshold(0.0).setSignificanceThresholdMax(10.0).setBicliqueSteps(20)
				.setMaxNumberOfThreads(6).setChopIndex(1).setConsiderWeightsWithSize(false).setConsiderWeights(true)
				.setDistanceMultiplier(false).setResidueBased(false).setResidueEnergyMultiplier(false).setTotalSamples(4)
				.enableDebug(false).build();
		analyzer.startAnalyze();
		Utils.removeFiles("src\\bin\\", "txt");
		
		analyzer = BuilderBindingAffinityAnalyzer.Builder.newInstance()
				.setFilePath("E:\\BIIP\\BindingAffinity\\").setExcelFilePath("ResultFile_BA_1_10.0_Algorithm-Unweighted-Atom-20-Atom_ALL")
				.setSignificanceThreshold(0.0).setSignificanceThresholdMax(10.0).setBicliqueSteps(20)
				.setMaxNumberOfThreads(6).setChopIndex(1).setConsiderWeightsWithSize(false).setConsiderWeights(false)
				.setDistanceMultiplier(false).setResidueBased(false).setResidueEnergyMultiplier(false).setTotalSamples(4)
				.enableDebug(false).build();
		analyzer.startAnalyze();
		Utils.removeFiles("src\\bin\\", "txt");
		*/
		
		/*
		logger.info("Start of ResultFile_BA_1_10.0_Algorithm-1-AtomEnergy-S_1");
		BuilderBindingAffinityAnalyzer analyzer = BuilderBindingAffinityAnalyzer.Builder.newInstance()
				.setFilePath("E:\\BIIP\\BindingAffinity\\").setExcelFilePath("ResultFile_BA_1_6.0_Algorithm-Atom-75-Atom_APOL_APOL")
				.setSignificanceThreshold(0.0).setSignificanceThresholdMax(6.0).setBicliqueSteps(75)
				.setMaxNumberOfThreads(6).setChopIndex(1).setConsiderWeightsWithSize(false).setConsiderWeights(true)
				.setDistanceMultiplier(false).setResidueBased(false).setResidueEnergyMultiplier(false).setTotalSamples(4)
				.setPropertyOne("APOLAR").setPropertyTwo("APOLAR")
				.enableDebug(false).build();
		analyzer.startAnalyze();
		
		logger.info("Start of ResultFile_BA_1_10.0_Algorithm-1-AtomEnergy-S_1");
		analyzer = BuilderBindingAffinityAnalyzer.Builder.newInstance()
				.setFilePath("E:\\BIIP\\BindingAffinity\\").setExcelFilePath("ResultFile_BA_1_6.0_Algorithm-Atom-75-Atom_POL_POL")
				.setSignificanceThreshold(0.0).setSignificanceThresholdMax(6.0).setBicliqueSteps(75)
				.setMaxNumberOfThreads(6).setChopIndex(1).setConsiderWeightsWithSize(false).setConsiderWeights(true)
				.setDistanceMultiplier(false).setResidueBased(false).setResidueEnergyMultiplier(false).setTotalSamples(4)
				.setPropertyOne("POLAR").setPropertyTwo("POLAR")
				.enableDebug(false).build();
		analyzer.startAnalyze();
		
		logger.info("Start of ResultFile_BA_1_10.0_Algorithm-1-AtomEnergy-S_1");
		
		analyzer = BuilderBindingAffinityAnalyzer.Builder.newInstance()
				.setFilePath("E:\\BIIP\\BindingAffinity\\").setExcelFilePath("ResultFile_BA_1_6.0_Algorithm-Atom-75-Atom_CHAR_CHAR")
				.setSignificanceThreshold(0.0).setSignificanceThresholdMax(6.0).setBicliqueSteps(75)
				.setMaxNumberOfThreads(6).setChopIndex(1).setConsiderWeightsWithSize(false).setConsiderWeights(true)
				.setDistanceMultiplier(false).setResidueBased(false).setResidueEnergyMultiplier(false).setTotalSamples(4)
				.setPropertyOne("CHARGED").setPropertyTwo("CHARGED")
				.enableDebug(false).build();
		analyzer.startAnalyze();
		
		logger.info("Start of ResultFile_BA_1_10.0_Algorithm-1-AtomEnergy-S_1");
		
		analyzer = BuilderBindingAffinityAnalyzer.Builder.newInstance()
				.setFilePath("E:\\BIIP\\BindingAffinity\\").setExcelFilePath("ResultFile_BA_1_6.0_Algorithm-Atom-75-Atom_APOL_POL")
				.setSignificanceThreshold(0.0).setSignificanceThresholdMax(6.0).setBicliqueSteps(75)
				.setMaxNumberOfThreads(6).setChopIndex(1).setConsiderWeightsWithSize(false).setConsiderWeights(true)
				.setDistanceMultiplier(false).setResidueBased(false).setResidueEnergyMultiplier(false).setTotalSamples(4)
				.setPropertyOne("APOLAR").setPropertyTwo("POLAR")
				.enableDebug(false).build();
		analyzer.startAnalyze();
		logger.info("Start of ResultFile_BA_1_10.0_Algorithm-1-AtomEnergy-S_1");
		BuilderBindingAffinityAnalyzer analyzer = BuilderBindingAffinityAnalyzer.Builder.newInstance()
				.setFilePath("E:\\BIIP\\BindingAffinity\\").setExcelFilePath("ResultFile_BA_1_5.0_Algorithm-Atom-100-Atom_APOL_APOL")
				.setSignificanceThreshold(0.0).setSignificanceThresholdMax(5.0).setBicliqueSteps(100)
				.setMaxNumberOfThreads(6).setChopIndex(1).setConsiderWeightsWithSize(false).setConsiderWeights(true)
				.setDistanceMultiplier(false).setResidueBased(false).setResidueEnergyMultiplier(false).setTotalSamples(4)
				.setPropertyOne("APOLAR").setPropertyTwo("APOLAR")
				.enableDebug(false).build();
		analyzer.startAnalyze();
		Utils.removeFiles("src\\bin\\", "txt");
		
		logger.info("Start of ResultFile_BA_1_10.0_Algorithm-1-AtomEnergy-S_1");
		analyzer = BuilderBindingAffinityAnalyzer.Builder.newInstance()
				.setFilePath("E:\\BIIP\\BindingAffinity\\").setExcelFilePath("ResultFile_BA_1_5.0_Algorithm-Atom-100-Atom_POL_POL")
				.setSignificanceThreshold(0.0).setSignificanceThresholdMax(5.0).setBicliqueSteps(100)
				.setMaxNumberOfThreads(6).setChopIndex(1).setConsiderWeightsWithSize(false).setConsiderWeights(true)
				.setDistanceMultiplier(false).setResidueBased(false).setResidueEnergyMultiplier(false).setTotalSamples(4)
				.setPropertyOne("POLAR").setPropertyTwo("POLAR")
				.enableDebug(false).build();
		analyzer.startAnalyze();
		Utils.removeFiles("src\\bin\\", "txt");
		
		logger.info("Start of ResultFile_BA_1_10.0_Algorithm-1-AtomEnergy-S_1");
		
		analyzer = BuilderBindingAffinityAnalyzer.Builder.newInstance()
				.setFilePath("E:\\BIIP\\BindingAffinity\\").setExcelFilePath("ResultFile_BA_1_5.0_Algorithm-Atom-100-Atom_CHAR_CHAR")
				.setSignificanceThreshold(0.0).setSignificanceThresholdMax(5.0).setBicliqueSteps(100)
				.setMaxNumberOfThreads(6).setChopIndex(1).setConsiderWeightsWithSize(false).setConsiderWeights(true)
				.setDistanceMultiplier(false).setResidueBased(false).setResidueEnergyMultiplier(false).setTotalSamples(4)
				.setPropertyOne("CHARGED").setPropertyTwo("CHARGED")
				.enableDebug(false).build();
		analyzer.startAnalyze();
		Utils.removeFiles("src\\bin\\", "txt");
		
		logger.info("Start of ResultFile_BA_1_10.0_Algorithm-1-AtomEnergy-S_1");
		
		analyzer = BuilderBindingAffinityAnalyzer.Builder.newInstance()
				.setFilePath("E:\\BIIP\\BindingAffinity\\").setExcelFilePath("ResultFile_BA_1_5.0_Algorithm-Atom-100-Atom_APOL_POL")
				.setSignificanceThreshold(0.0).setSignificanceThresholdMax(5.0).setBicliqueSteps(100)
				.setMaxNumberOfThreads(6).setChopIndex(1).setConsiderWeightsWithSize(false).setConsiderWeights(true)
				.setDistanceMultiplier(false).setResidueBased(false).setResidueEnergyMultiplier(false).setTotalSamples(4)
				.setPropertyOne("APOLAR").setPropertyTwo("POLAR")
				.enableDebug(false).build();
		analyzer.startAnalyze();
		Utils.removeFiles("src\\bin\\", "txt");
		
		*/
		/*
		logger.info("Start Main Execution");
		logger.info("Start of ResultFile_BA_1_10.0_Algorithm-1-AtomEnergy-S_1");
		BuilderBindingAffinityAnalyzer analyzer = BuilderBindingAffinityAnalyzer.Builder.newInstance()
				.setFilePath("E:\\BIIP\\BindingAffinity\\").setExcelFilePath("ResultFile_BA_1_6.0_Algorithm-Atom-50-Atom_APOL_APOL")
				.setSignificanceThreshold(0.0).setSignificanceThresholdMax(6.0).setBicliqueSteps(50)
				.setMaxNumberOfThreads(6).setChopIndex(1).setConsiderWeightsWithSize(false).setConsiderWeights(true)
				.setDistanceMultiplier(false).setResidueBased(false).setResidueEnergyMultiplier(false).setTotalSamples(4)
				.setPropertyOne("APOLAR").setPropertyTwo("APOLAR")
				.enableDebug(false).build();
		analyzer.startAnalyze();
		
		logger.info("Start of ResultFile_BA_1_10.0_Algorithm-1-AtomEnergy-S_1");
		analyzer = BuilderBindingAffinityAnalyzer.Builder.newInstance()
				.setFilePath("E:\\BIIP\\BindingAffinity\\").setExcelFilePath("ResultFile_BA_1_6.0_Algorithm-Atom-50-Atom_POL_POL")
				.setSignificanceThreshold(0.0).setSignificanceThresholdMax(6.0).setBicliqueSteps(50)
				.setMaxNumberOfThreads(6).setChopIndex(1).setConsiderWeightsWithSize(false).setConsiderWeights(true)
				.setDistanceMultiplier(false).setResidueBased(false).setResidueEnergyMultiplier(false).setTotalSamples(4)
				.setPropertyOne("POLAR").setPropertyTwo("POLAR")
				.enableDebug(false).build();
		analyzer.startAnalyze();
		
		logger.info("Start of ResultFile_BA_1_10.0_Algorithm-1-AtomEnergy-S_1");
		
		analyzer = BuilderBindingAffinityAnalyzer.Builder.newInstance()
				.setFilePath("E:\\BIIP\\BindingAffinity\\").setExcelFilePath("ResultFile_BA_1_6.0_Algorithm-Atom-50-Atom_CHAR_CHAR")
				.setSignificanceThreshold(0.0).setSignificanceThresholdMax(6.0).setBicliqueSteps(50)
				.setMaxNumberOfThreads(6).setChopIndex(1).setConsiderWeightsWithSize(false).setConsiderWeights(true)
				.setDistanceMultiplier(false).setResidueBased(false).setResidueEnergyMultiplier(false).setTotalSamples(4)
				.setPropertyOne("CHARGED").setPropertyTwo("CHARGED")
				.enableDebug(false).build();
		analyzer.startAnalyze();
		
		logger.info("Start of ResultFile_BA_1_10.0_Algorithm-1-AtomEnergy-S_1");
		
		analyzer = BuilderBindingAffinityAnalyzer.Builder.newInstance()
				.setFilePath("E:\\BIIP\\BindingAffinity\\").setExcelFilePath("ResultFile_BA_1_6.0_Algorithm-Atom-50-Atom_APOL_POL")
				.setSignificanceThreshold(0.0).setSignificanceThresholdMax(6.0).setBicliqueSteps(50)
				.setMaxNumberOfThreads(6).setChopIndex(1).setConsiderWeightsWithSize(false).setConsiderWeights(true)
				.setDistanceMultiplier(false).setResidueBased(false).setResidueEnergyMultiplier(false).setTotalSamples(4)
				.setPropertyOne("APOLAR").setPropertyTwo("POLAR")
				.enableDebug(false).build();
		analyzer.startAnalyze();
		*/
		/*
		logger.info("Start Main Execution");
		logger.info("Start of ResultFile_BA_1_10.0_Algorithm-1-ResidueEnergy-S_1");
		BuilderBindingAffinityAnalyzer analyzer = BuilderBindingAffinityAnalyzer.Builder.newInstance()
				.setFilePath("E:\\BIIP\\BindingAffinity\\").setExcelFilePath("ResultFile_BA_1_12.5_Algorithm-Residue-25-Residue_APOL_APOL")
				.setSignificanceThreshold(0.0).setSignificanceThresholdMax(12.5).setBicliqueSteps(25)
				.setMaxNumberOfThreads(6).setChopIndex(1).setConsiderWeightsWithSize(false).setConsiderWeights(true)
				.setDistanceMultiplier(false).setResidueBased(true).setResidueEnergyMultiplier(false).setTotalSamples(4)
				.setPropertyOne("APOLAR").setPropertyTwo("APOLAR")
				.enableDebug(false).build();
		analyzer.startAnalyze();
		
		logger.info("Start of ResultFile_BA_1_10.0_Algorithm-1-ResidueEnergy-S_1");
		analyzer = BuilderBindingAffinityAnalyzer.Builder.newInstance()
				.setFilePath("E:\\BIIP\\BindingAffinity\\").setExcelFilePath("ResultFile_BA_1_12.5_Algorithm-Residue-25-Residue_POL_POL")
				.setSignificanceThreshold(0.0).setSignificanceThresholdMax(12.5).setBicliqueSteps(25)
				.setMaxNumberOfThreads(6).setChopIndex(1).setConsiderWeightsWithSize(false).setConsiderWeights(true)
				.setDistanceMultiplier(false).setResidueBased(true).setResidueEnergyMultiplier(false).setTotalSamples(4)
				.setPropertyOne("POLAR").setPropertyTwo("POLAR")
				.enableDebug(false).build();
		analyzer.startAnalyze();
		
		logger.info("Start of ResultFile_BA_1_10.0_Algorithm-1-ResidueEnergy-S_1");
		
		analyzer = BuilderBindingAffinityAnalyzer.Builder.newInstance()
				.setFilePath("E:\\BIIP\\BindingAffinity\\").setExcelFilePath("ResultFile_BA_1_12.5_Algorithm-Residue-25-Residue_CHAR_CHAR")
				.setSignificanceThreshold(0.0).setSignificanceThresholdMax(12.5).setBicliqueSteps(25)
				.setMaxNumberOfThreads(6).setChopIndex(1).setConsiderWeightsWithSize(false).setConsiderWeights(true)
				.setDistanceMultiplier(false).setResidueBased(true).setResidueEnergyMultiplier(false).setTotalSamples(4)
				.setPropertyOne("CHARGED").setPropertyTwo("CHARGED")
				.enableDebug(false).build();
		analyzer.startAnalyze();
		
		logger.info("Start of ResultFile_BA_1_10.0_Algorithm-1-ResidueEnergy-S_1");
		
		analyzer = BuilderBindingAffinityAnalyzer.Builder.newInstance()
				.setFilePath("E:\\BIIP\\BindingAffinity\\").setExcelFilePath("ResultFile_BA_1_12.5_Algorithm-Residue-25-Residue_APOL_POL")
				.setSignificanceThreshold(0.0).setSignificanceThresholdMax(12.5).setBicliqueSteps(25)
				.setMaxNumberOfThreads(6).setChopIndex(1).setConsiderWeightsWithSize(false).setConsiderWeights(true)
				.setDistanceMultiplier(false).setResidueBased(true).setResidueEnergyMultiplier(false).setTotalSamples(4)
				.setPropertyOne("APOLAR").setPropertyTwo("POLAR")
				.enableDebug(false).build();
		analyzer.startAnalyze();
		*/
		/*
		logger.info("Start Main Execution");
		logger.info("Start of ResultFile_BA_1_10.0_Algorithm-1-ResidueEnergy-S_1");
		BuilderBindingAffinityAnalyzer analyzer = BuilderBindingAffinityAnalyzer.Builder.newInstance()
				.setFilePath("E:\\BIIP\\BindingAffinity\\").setExcelFilePath("ResultFile_BA_1_20.0_40.0_Algorithm-Residue-25-Residue_APOL_APOL")
				.setSignificanceThreshold(20.0).setSignificanceThresholdMax(40.0).setBicliqueSteps(25)
				.setMaxNumberOfThreads(6).setChopIndex(1).setConsiderWeightsWithSize(false).setConsiderWeights(true)
				.setDistanceMultiplier(false).setResidueBased(true).setResidueEnergyMultiplier(false).setTotalSamples(4)
				.setPropertyOne("APOLAR").setPropertyTwo("APOLAR")
				.enableDebug(false).build();
		analyzer.startAnalyze();
		
		logger.info("Start of ResultFile_BA_1_10.0_Algorithm-1-ResidueEnergy-S_1");
		analyzer = BuilderBindingAffinityAnalyzer.Builder.newInstance()
				.setFilePath("E:\\BIIP\\BindingAffinity\\").setExcelFilePath("ResultFile_BA_1_20.0_40.0_Algorithm-Residue-25-Residue_POL_POL")
				.setSignificanceThreshold(20.0).setSignificanceThresholdMax(40.0).setBicliqueSteps(25)
				.setMaxNumberOfThreads(6).setChopIndex(1).setConsiderWeightsWithSize(false).setConsiderWeights(true)
				.setDistanceMultiplier(false).setResidueBased(true).setResidueEnergyMultiplier(false).setTotalSamples(4)
				.setPropertyOne("POLAR").setPropertyTwo("POLAR")
				.enableDebug(false).build();
		analyzer.startAnalyze();
		
		logger.info("Start of ResultFile_BA_1_10.0_Algorithm-1-ResidueEnergy-S_1");
		
		analyzer = BuilderBindingAffinityAnalyzer.Builder.newInstance()
				.setFilePath("E:\\BIIP\\BindingAffinity\\").setExcelFilePath("ResultFile_BA_1_20.0_40.0_Algorithm-Residue-25-Residue_CHAR_CHAR")
				.setSignificanceThreshold(20.0).setSignificanceThresholdMax(40.0).setBicliqueSteps(25)
				.setMaxNumberOfThreads(6).setChopIndex(1).setConsiderWeightsWithSize(false).setConsiderWeights(true)
				.setDistanceMultiplier(false).setResidueBased(true).setResidueEnergyMultiplier(false).setTotalSamples(4)
				.setPropertyOne("CHARGED").setPropertyTwo("CHARGED")
				.enableDebug(false).build();
		analyzer.startAnalyze();
		
		logger.info("Start of ResultFile_BA_1_10.0_Algorithm-1-ResidueEnergy-S_1");
		
		analyzer = BuilderBindingAffinityAnalyzer.Builder.newInstance()
				.setFilePath("E:\\BIIP\\BindingAffinity\\").setExcelFilePath("ResultFile_BA_1_20.0_40.0_Algorithm-Residue-25-Residue_APOL_POL")
				.setSignificanceThreshold(20.0).setSignificanceThresholdMax(40.0).setBicliqueSteps(25)
				.setMaxNumberOfThreads(6).setChopIndex(1).setConsiderWeightsWithSize(false).setConsiderWeights(true)
				.setDistanceMultiplier(false).setResidueBased(true).setResidueEnergyMultiplier(false).setTotalSamples(4)
				.setPropertyOne("APOLAR").setPropertyTwo("POLAR")
				.enableDebug(false).build();
		analyzer.startAnalyze();
		*/
		/*
		logger.info("Start Main Execution");
		logger.info("Start of ResultFile_BA_1_10.0_Algorithm-1-ResidueEnergy-S_1");
		BuilderBindingAffinityAnalyzer analyzer = BuilderBindingAffinityAnalyzer.Builder.newInstance()
				.setFilePath("E:\\BIIP\\BindingAffinity\\").setExcelFilePath("ResultFile_BA_1_20.0_Algorithm-Residue-25-Residue_APOL_APOL")
				.setSignificanceThreshold(0.0).setSignificanceThresholdMax(20.0).setBicliqueSteps(25)
				.setMaxNumberOfThreads(6).setChopIndex(1).setConsiderWeightsWithSize(false).setConsiderWeights(true)
				.setDistanceMultiplier(false).setResidueBased(true).setResidueEnergyMultiplier(false).setTotalSamples(4)
				.setPropertyOne("APOLAR").setPropertyTwo("APOLAR")
				.enableDebug(false).build();
		analyzer.startAnalyze();
		
		logger.info("Start of ResultFile_BA_1_10.0_Algorithm-1-ResidueEnergy-S_1");
		analyzer = BuilderBindingAffinityAnalyzer.Builder.newInstance()
				.setFilePath("E:\\BIIP\\BindingAffinity\\").setExcelFilePath("ResultFile_BA_1_20.0_Algorithm-Residue-25-Residue_POL_POL")
				.setSignificanceThreshold(0.0).setSignificanceThresholdMax(20.0).setBicliqueSteps(25)
				.setMaxNumberOfThreads(6).setChopIndex(1).setConsiderWeightsWithSize(false).setConsiderWeights(true)
				.setDistanceMultiplier(false).setResidueBased(true).setResidueEnergyMultiplier(false).setTotalSamples(4)
				.setPropertyOne("POLAR").setPropertyTwo("POLAR")
				.enableDebug(false).build();
		analyzer.startAnalyze();
		
		logger.info("Start of ResultFile_BA_1_10.0_Algorithm-1-ResidueEnergy-S_1");
		
		analyzer = BuilderBindingAffinityAnalyzer.Builder.newInstance()
				.setFilePath("E:\\BIIP\\BindingAffinity\\").setExcelFilePath("ResultFile_BA_1_20.0_Algorithm-Residue-25-Residue_CHAR_CHAR")
				.setSignificanceThreshold(0.0).setSignificanceThresholdMax(20.0).setBicliqueSteps(25)
				.setMaxNumberOfThreads(6).setChopIndex(1).setConsiderWeightsWithSize(false).setConsiderWeights(true)
				.setDistanceMultiplier(false).setResidueBased(true).setResidueEnergyMultiplier(false).setTotalSamples(4)
				.setPropertyOne("CHARGED").setPropertyTwo("CHARGED")
				.enableDebug(false).build();
		analyzer.startAnalyze();
		
		logger.info("Start of ResultFile_BA_1_10.0_Algorithm-1-ResidueEnergy-S_1");
		
		analyzer = BuilderBindingAffinityAnalyzer.Builder.newInstance()
				.setFilePath("E:\\BIIP\\BindingAffinity\\").setExcelFilePath("ResultFile_BA_1_20.0_Algorithm-Residue-25-Residue_APOL_POL")
				.setSignificanceThreshold(0.0).setSignificanceThresholdMax(20.0).setBicliqueSteps(25)
				.setMaxNumberOfThreads(6).setChopIndex(1).setConsiderWeightsWithSize(false).setConsiderWeights(true)
				.setDistanceMultiplier(false).setResidueBased(true).setResidueEnergyMultiplier(false).setTotalSamples(4)
				.setPropertyOne("APOLAR").setPropertyTwo("POLAR")
				.enableDebug(false).build();
		analyzer.startAnalyze();
		
		logger.info("Start of ResultFile_BA_1_10.0_Algorithm-1-ResidueEnergy-S_1");
		analyzer = BuilderBindingAffinityAnalyzer.Builder.newInstance()
				.setFilePath("E:\\BIIP\\BindingAffinity\\").setExcelFilePath("ResultFile_BA_1_22.5_Algorithm-Residue-25-Residue_APOL_APOL")
				.setSignificanceThreshold(0.0).setSignificanceThresholdMax(22.5).setBicliqueSteps(25)
				.setMaxNumberOfThreads(6).setChopIndex(1).setConsiderWeightsWithSize(false).setConsiderWeights(true)
				.setDistanceMultiplier(false).setResidueBased(true).setResidueEnergyMultiplier(false).setTotalSamples(4)
				.setPropertyOne("APOLAR").setPropertyTwo("APOLAR")
				.enableDebug(false).build();
		analyzer.startAnalyze();
		
		logger.info("Start of ResultFile_BA_1_10.0_Algorithm-1-ResidueEnergy-S_1");
		analyzer = BuilderBindingAffinityAnalyzer.Builder.newInstance()
				.setFilePath("E:\\BIIP\\BindingAffinity\\").setExcelFilePath("ResultFile_BA_1_22.5_Algorithm-Residue-25-Residue_POL_POL")
				.setSignificanceThreshold(0.0).setSignificanceThresholdMax(22.5).setBicliqueSteps(25)
				.setMaxNumberOfThreads(6).setChopIndex(1).setConsiderWeightsWithSize(false).setConsiderWeights(true)
				.setDistanceMultiplier(false).setResidueBased(true).setResidueEnergyMultiplier(false).setTotalSamples(4)
				.setPropertyOne("POLAR").setPropertyTwo("POLAR")
				.enableDebug(false).build();
		analyzer.startAnalyze();
		
		logger.info("Start of ResultFile_BA_1_10.0_Algorithm-1-ResidueEnergy-S_1");
		
		analyzer = BuilderBindingAffinityAnalyzer.Builder.newInstance()
				.setFilePath("E:\\BIIP\\BindingAffinity\\").setExcelFilePath("ResultFile_BA_1_22.5_Algorithm-Residue-25-Residue_CHAR_CHAR")
				.setSignificanceThreshold(0.0).setSignificanceThresholdMax(22.5).setBicliqueSteps(25)
				.setMaxNumberOfThreads(6).setChopIndex(1).setConsiderWeightsWithSize(false).setConsiderWeights(true)
				.setDistanceMultiplier(false).setResidueBased(true).setResidueEnergyMultiplier(false).setTotalSamples(4)
				.setPropertyOne("CHARGED").setPropertyTwo("CHARGED")
				.enableDebug(false).build();
		analyzer.startAnalyze();
		
		logger.info("Start of ResultFile_BA_1_10.0_Algorithm-1-ResidueEnergy-S_1");
		
		analyzer = BuilderBindingAffinityAnalyzer.Builder.newInstance()
				.setFilePath("E:\\BIIP\\BindingAffinity\\").setExcelFilePath("ResultFile_BA_1_22.5_Algorithm-Residue-25-Residue_APOL_POL")
				.setSignificanceThreshold(0.0).setSignificanceThresholdMax(22.5).setBicliqueSteps(25)
				.setMaxNumberOfThreads(6).setChopIndex(1).setConsiderWeightsWithSize(false).setConsiderWeights(true)
				.setDistanceMultiplier(false).setResidueBased(true).setResidueEnergyMultiplier(false).setTotalSamples(4)
				.setPropertyOne("APOLAR").setPropertyTwo("POLAR")
				.enableDebug(false).build();
		analyzer.startAnalyze();
		*/
		/*
		logger.info("Start Main Execution");
		logger.info("Start of ResultFile_BA_1_10.0_Algorithm-1-ResidueEnergy-S_1");
		BuilderBindingAffinityAnalyzer analyzer = BuilderBindingAffinityAnalyzer.Builder.newInstance()
				.setFilePath("E:\\BIIP\\BindingAffinity\\").setExcelFilePath("ResultFile_BA_1_17.5_Algorithm-Residue-25-Residue_APOL_APOL")
				.setSignificanceThreshold(0.0).setSignificanceThresholdMax(17.5).setBicliqueSteps(25)
				.setMaxNumberOfThreads(6).setChopIndex(1).setConsiderWeightsWithSize(false).setConsiderWeights(true)
				.setDistanceMultiplier(false).setResidueBased(true).setResidueEnergyMultiplier(false).setTotalSamples(4)
				.setPropertyOne("APOLAR").setPropertyTwo("APOLAR")
				.enableDebug(false).build();
		analyzer.startAnalyze();
		
		logger.info("Start of ResultFile_BA_1_10.0_Algorithm-1-ResidueEnergy-S_1");
		analyzer = BuilderBindingAffinityAnalyzer.Builder.newInstance()
				.setFilePath("E:\\BIIP\\BindingAffinity\\").setExcelFilePath("ResultFile_BA_1_17.5_Algorithm-Residue-25-Residue_POL_POL")
				.setSignificanceThreshold(0.0).setSignificanceThresholdMax(17.5).setBicliqueSteps(25)
				.setMaxNumberOfThreads(6).setChopIndex(1).setConsiderWeightsWithSize(false).setConsiderWeights(true)
				.setDistanceMultiplier(false).setResidueBased(true).setResidueEnergyMultiplier(false).setTotalSamples(4)
				.setPropertyOne("POLAR").setPropertyTwo("POLAR")
				.enableDebug(false).build();
		analyzer.startAnalyze();
		
		logger.info("Start of ResultFile_BA_1_10.0_Algorithm-1-ResidueEnergy-S_1");
		
		analyzer = BuilderBindingAffinityAnalyzer.Builder.newInstance()
				.setFilePath("E:\\BIIP\\BindingAffinity\\").setExcelFilePath("ResultFile_BA_1_17.5_Algorithm-Residue-25-Residue_CHAR_CHAR")
				.setSignificanceThreshold(0.0).setSignificanceThresholdMax(17.5).setBicliqueSteps(25)
				.setMaxNumberOfThreads(6).setChopIndex(1).setConsiderWeightsWithSize(false).setConsiderWeights(true)
				.setDistanceMultiplier(false).setResidueBased(true).setResidueEnergyMultiplier(false).setTotalSamples(4)
				.setPropertyOne("CHARGED").setPropertyTwo("CHARGED")
				.enableDebug(false).build();
		analyzer.startAnalyze();
		
		logger.info("Start of ResultFile_BA_1_10.0_Algorithm-1-ResidueEnergy-S_1");
		
		analyzer = BuilderBindingAffinityAnalyzer.Builder.newInstance()
				.setFilePath("E:\\BIIP\\BindingAffinity\\").setExcelFilePath("ResultFile_BA_1_17.5_Algorithm-Residue-25-Residue_APOL_POL")
				.setSignificanceThreshold(0.0).setSignificanceThresholdMax(17.5).setBicliqueSteps(25)
				.setMaxNumberOfThreads(6).setChopIndex(1).setConsiderWeightsWithSize(false).setConsiderWeights(true)
				.setDistanceMultiplier(false).setResidueBased(true).setResidueEnergyMultiplier(false).setTotalSamples(4)
				.setPropertyOne("APOLAR").setPropertyTwo("POLAR")
				.enableDebug(false).build();
		analyzer.startAnalyze();
		*/
		/*
		logger.info("Start Main Execution");
		logger.info("Start of ResultFile_BA_1_10.0_Algorithm-1-ResidueEnergy-S_1");
		BuilderBindingAffinityAnalyzer analyzer = BuilderBindingAffinityAnalyzer.Builder.newInstance()
				.setFilePath("E:\\BIIP\\BindingAffinity\\").setExcelFilePath("ResultFile_BA_1_15.0_Algorithm-Residue-25-Residue_APOL_APOL")
				.setSignificanceThreshold(0.0).setSignificanceThresholdMax(15.0).setBicliqueSteps(25)
				.setMaxNumberOfThreads(6).setChopIndex(1).setConsiderWeightsWithSize(false).setConsiderWeights(true)
				.setDistanceMultiplier(false).setResidueBased(true).setResidueEnergyMultiplier(false).setTotalSamples(4)
				.setPropertyOne("APOLAR").setPropertyTwo("APOLAR")
				.enableDebug(false).build();
		analyzer.startAnalyze();
		
		logger.info("Start of ResultFile_BA_1_10.0_Algorithm-1-ResidueEnergy-S_1");
		analyzer = BuilderBindingAffinityAnalyzer.Builder.newInstance()
				.setFilePath("E:\\BIIP\\BindingAffinity\\").setExcelFilePath("ResultFile_BA_1_15.0_Algorithm-Residue-25-Residue_POL_POL")
				.setSignificanceThreshold(0.0).setSignificanceThresholdMax(15.0).setBicliqueSteps(25)
				.setMaxNumberOfThreads(6).setChopIndex(1).setConsiderWeightsWithSize(false).setConsiderWeights(true)
				.setDistanceMultiplier(false).setResidueBased(true).setResidueEnergyMultiplier(false).setTotalSamples(4)
				.setPropertyOne("POLAR").setPropertyTwo("POLAR")
				.enableDebug(false).build();
		analyzer.startAnalyze();
		
		logger.info("Start of ResultFile_BA_1_10.0_Algorithm-1-ResidueEnergy-S_1");
		
		analyzer = BuilderBindingAffinityAnalyzer.Builder.newInstance()
				.setFilePath("E:\\BIIP\\BindingAffinity\\").setExcelFilePath("ResultFile_BA_1_15.0_Algorithm-Residue-25-Residue_CHAR_CHAR")
				.setSignificanceThreshold(0.0).setSignificanceThresholdMax(15.0).setBicliqueSteps(25)
				.setMaxNumberOfThreads(6).setChopIndex(1).setConsiderWeightsWithSize(false).setConsiderWeights(true)
				.setDistanceMultiplier(false).setResidueBased(true).setResidueEnergyMultiplier(false).setTotalSamples(4)
				.setPropertyOne("CHARGED").setPropertyTwo("CHARGED")
				.enableDebug(false).build();
		analyzer.startAnalyze();
		
		logger.info("Start of ResultFile_BA_1_10.0_Algorithm-1-ResidueEnergy-S_1");
		
		analyzer = BuilderBindingAffinityAnalyzer.Builder.newInstance()
				.setFilePath("E:\\BIIP\\BindingAffinity\\").setExcelFilePath("ResultFile_BA_1_15.0_Algorithm-Residue-25-Residue_APOL_POL")
				.setSignificanceThreshold(0.0).setSignificanceThresholdMax(15.0).setBicliqueSteps(25)
				.setMaxNumberOfThreads(6).setChopIndex(1).setConsiderWeightsWithSize(false).setConsiderWeights(true)
				.setDistanceMultiplier(false).setResidueBased(true).setResidueEnergyMultiplier(false).setTotalSamples(4)
				.setPropertyOne("APOLAR").setPropertyTwo("POLAR")
				.enableDebug(false).build();
		analyzer.startAnalyze();
		*/
		/*
		logger.info("Start Main Execution");
		logger.info("Start of ResultFile_BA_1_10.0_Algorithm-1-ResidueEnergy-S_1");
		BuilderBindingAffinityAnalyzer analyzer = BuilderBindingAffinityAnalyzer.Builder.newInstance()
				.setFilePath("E:\\BIIP\\BindingAffinity\\").setExcelFilePath("ResultFile_BA_1_10.0_Algorithm-Residue-25-Residue_APOL_APOL")
				.setSignificanceThreshold(0.0).setSignificanceThresholdMax(10.0).setBicliqueSteps(25)
				.setMaxNumberOfThreads(6).setChopIndex(1).setConsiderWeightsWithSize(false).setConsiderWeights(true)
				.setDistanceMultiplier(false).setResidueBased(true).setResidueEnergyMultiplier(false).setTotalSamples(4)
				.setPropertyOne("APOLAR").setPropertyTwo("APOLAR")
				.enableDebug(false).build();
		analyzer.startAnalyze();
		
		logger.info("Start of ResultFile_BA_1_10.0_Algorithm-1-ResidueEnergy-S_1");
		analyzer = BuilderBindingAffinityAnalyzer.Builder.newInstance()
				.setFilePath("E:\\BIIP\\BindingAffinity\\").setExcelFilePath("ResultFile_BA_1_10.0_Algorithm-Residue-25-Residue_POL_POL")
				.setSignificanceThreshold(0.0).setSignificanceThresholdMax(10.0).setBicliqueSteps(25)
				.setMaxNumberOfThreads(6).setChopIndex(1).setConsiderWeightsWithSize(false).setConsiderWeights(true)
				.setDistanceMultiplier(false).setResidueBased(true).setResidueEnergyMultiplier(false).setTotalSamples(4)
				.setPropertyOne("POLAR").setPropertyTwo("POLAR")
				.enableDebug(false).build();
		analyzer.startAnalyze();
		
		logger.info("Start of ResultFile_BA_1_10.0_Algorithm-1-ResidueEnergy-S_1");
		
		analyzer = BuilderBindingAffinityAnalyzer.Builder.newInstance()
				.setFilePath("E:\\BIIP\\BindingAffinity\\").setExcelFilePath("ResultFile_BA_1_10.0_Algorithm-Residue-25-Residue_CHAR_CHAR")
				.setSignificanceThreshold(0.0).setSignificanceThresholdMax(10.0).setBicliqueSteps(25)
				.setMaxNumberOfThreads(6).setChopIndex(1).setConsiderWeightsWithSize(false).setConsiderWeights(true)
				.setDistanceMultiplier(false).setResidueBased(true).setResidueEnergyMultiplier(false).setTotalSamples(4)
				.setPropertyOne("CHARGED").setPropertyTwo("CHARGED")
				.enableDebug(false).build();
		analyzer.startAnalyze();
		
		logger.info("Start of ResultFile_BA_1_10.0_Algorithm-1-ResidueEnergy-S_1");
		
		analyzer = BuilderBindingAffinityAnalyzer.Builder.newInstance()
				.setFilePath("E:\\BIIP\\BindingAffinity\\").setExcelFilePath("ResultFile_BA_1_10.0_Algorithm-Residue-25-Residue_APOL_POL")
				.setSignificanceThreshold(0.0).setSignificanceThresholdMax(10.0).setBicliqueSteps(25)
				.setMaxNumberOfThreads(6).setChopIndex(1).setConsiderWeightsWithSize(false).setConsiderWeights(true)
				.setDistanceMultiplier(false).setResidueBased(true).setResidueEnergyMultiplier(false).setTotalSamples(4)
				.setPropertyOne("APOLAR").setPropertyTwo("POLAR")
				.enableDebug(false).build();
		analyzer.startAnalyze();
		*/
		/*
		logger.info("Start Main Execution");
		logger.info("Start of ResultFile_BA_1_10.0_Algorithm-1-ResidueEnergy-S_1");
		BuilderBindingAffinityAnalyzer analyzer = BuilderBindingAffinityAnalyzer.Builder.newInstance()
				.setFilePath("E:\\BIIP\\BindingAffinity\\").setExcelFilePath("ResultFile_BA_1_7.5_Algorithm-Residue-25-Residue_APOL_APOL")
				.setSignificanceThreshold(0.0).setSignificanceThresholdMax(7.5).setBicliqueSteps(25)
				.setMaxNumberOfThreads(6).setChopIndex(1).setConsiderWeightsWithSize(false).setConsiderWeights(true)
				.setDistanceMultiplier(false).setResidueBased(true).setResidueEnergyMultiplier(false).setTotalSamples(4)
				.setPropertyOne("APOLAR").setPropertyTwo("APOLAR")
				.enableDebug(false).build();
		analyzer.startAnalyze();
		
		logger.info("Start of ResultFile_BA_1_10.0_Algorithm-1-ResidueEnergy-S_1");
		analyzer = BuilderBindingAffinityAnalyzer.Builder.newInstance()
				.setFilePath("E:\\BIIP\\BindingAffinity\\").setExcelFilePath("ResultFile_BA_1_7.5_Algorithm-Residue-25-Residue_POL_POL")
				.setSignificanceThreshold(0.0).setSignificanceThresholdMax(7.5).setBicliqueSteps(25)
				.setMaxNumberOfThreads(6).setChopIndex(1).setConsiderWeightsWithSize(false).setConsiderWeights(true)
				.setDistanceMultiplier(false).setResidueBased(true).setResidueEnergyMultiplier(false).setTotalSamples(4)
				.setPropertyOne("POLAR").setPropertyTwo("POLAR")
				.enableDebug(false).build();
		analyzer.startAnalyze();
		
		logger.info("Start of ResultFile_BA_1_10.0_Algorithm-1-ResidueEnergy-S_1");
		
		analyzer = BuilderBindingAffinityAnalyzer.Builder.newInstance()
				.setFilePath("E:\\BIIP\\BindingAffinity\\").setExcelFilePath("ResultFile_BA_1_7.5_Algorithm-Residue-25-Residue_CHAR_CHAR")
				.setSignificanceThreshold(0.0).setSignificanceThresholdMax(7.5).setBicliqueSteps(25)
				.setMaxNumberOfThreads(6).setChopIndex(1).setConsiderWeightsWithSize(false).setConsiderWeights(true)
				.setDistanceMultiplier(false).setResidueBased(true).setResidueEnergyMultiplier(false).setTotalSamples(4)
				.setPropertyOne("CHARGED").setPropertyTwo("CHARGED")
				.enableDebug(false).build();
		analyzer.startAnalyze();
		
		logger.info("Start of ResultFile_BA_1_10.0_Algorithm-1-ResidueEnergy-S_1");
		
		analyzer = BuilderBindingAffinityAnalyzer.Builder.newInstance()
				.setFilePath("E:\\BIIP\\BindingAffinity\\").setExcelFilePath("ResultFile_BA_1_7.5_Algorithm-Residue-25-Residue_APOL_POL")
				.setSignificanceThreshold(0.0).setSignificanceThresholdMax(7.5).setBicliqueSteps(25)
				.setMaxNumberOfThreads(6).setChopIndex(1).setConsiderWeightsWithSize(false).setConsiderWeights(true)
				.setDistanceMultiplier(false).setResidueBased(true).setResidueEnergyMultiplier(false).setTotalSamples(4)
				.setPropertyOne("APOLAR").setPropertyTwo("POLAR")
				.enableDebug(false).build();
		analyzer.startAnalyze();
		*/
		/*
		logger.info("Start Main Execution");
		logger.info("Start of ResultFile_BA_1_10.0_Algorithm-1-ResidueEnergy-S_1");
		BuilderBindingAffinityAnalyzer analyzer = BuilderBindingAffinityAnalyzer.Builder.newInstance()
				.setFilePath("E:\\BIIP\\BindingAffinity\\").setExcelFilePath("ResultFile_BA_1_15.0_25.0_Algorithm-Residue-5-Residue_APOL_APOL")
				.setSignificanceThreshold(15.0).setSignificanceThresholdMax(25.0).setBicliqueSteps(25)
				.setMaxNumberOfThreads(6).setChopIndex(1).setConsiderWeightsWithSize(false).setConsiderWeights(true)
				.setDistanceMultiplier(false).setResidueBased(true).setResidueEnergyMultiplier(false).setTotalSamples(4)
				.setPropertyOne("APOLAR").setPropertyTwo("APOLAR")
				.enableDebug(false).build();
		analyzer.startAnalyze();
		
		logger.info("Start of ResultFile_BA_1_10.0_Algorithm-1-ResidueEnergy-S_1");
		analyzer = BuilderBindingAffinityAnalyzer.Builder.newInstance()
				.setFilePath("E:\\BIIP\\BindingAffinity\\").setExcelFilePath("ResultFile_BA_1_15.0_25.0_Algorithm-Residue-5-Residue_POL_POL")
				.setSignificanceThreshold(15.0).setSignificanceThresholdMax(25.0).setBicliqueSteps(25)
				.setMaxNumberOfThreads(6).setChopIndex(1).setConsiderWeightsWithSize(false).setConsiderWeights(true)
				.setDistanceMultiplier(false).setResidueBased(true).setResidueEnergyMultiplier(false).setTotalSamples(4)
				.setPropertyOne("POLAR").setPropertyTwo("POLAR")
				.enableDebug(false).build();
		analyzer.startAnalyze();
		
		logger.info("Start of ResultFile_BA_1_10.0_Algorithm-1-ResidueEnergy-S_1");
		
		analyzer = BuilderBindingAffinityAnalyzer.Builder.newInstance()
				.setFilePath("E:\\BIIP\\BindingAffinity\\").setExcelFilePath("ResultFile_BA_1_15.0_25.0_Algorithm-Residue-5-Residue_CHAR_CHAR")
				.setSignificanceThreshold(15.0).setSignificanceThresholdMax(25.0).setBicliqueSteps(25)
				.setMaxNumberOfThreads(6).setChopIndex(1).setConsiderWeightsWithSize(false).setConsiderWeights(true)
				.setDistanceMultiplier(false).setResidueBased(true).setResidueEnergyMultiplier(false).setTotalSamples(4)
				.setPropertyOne("CHARGED").setPropertyTwo("CHARGED")
				.enableDebug(false).build();
		analyzer.startAnalyze();
		
		logger.info("Start of ResultFile_BA_1_10.0_Algorithm-1-ResidueEnergy-S_1");
		
		analyzer = BuilderBindingAffinityAnalyzer.Builder.newInstance()
				.setFilePath("E:\\BIIP\\BindingAffinity\\").setExcelFilePath("ResultFile_BA_1_15.0_25.0_Algorithm-Residue-5-Residue_APOL_POL")
				.setSignificanceThreshold(15.0).setSignificanceThresholdMax(25.0).setBicliqueSteps(25)
				.setMaxNumberOfThreads(6).setChopIndex(1).setConsiderWeightsWithSize(false).setConsiderWeights(true)
				.setDistanceMultiplier(false).setResidueBased(true).setResidueEnergyMultiplier(false).setTotalSamples(4)
				.setPropertyOne("APOLAR").setPropertyTwo("POLAR")
				.enableDebug(false).build();
		analyzer.startAnalyze();
		
		/*logger.info("Start of ResultFile_BA_1_10.0_Algorithm-1-ResidueEnergy-S_1");
		BuilderBindingAffinityAnalyzer analyzer = BuilderBindingAffinityAnalyzer.Builder.newInstance()
				.setFilePath("E:\\BIIP\\BindingAffinity\\").setExcelFilePath("ResultFile_BA_1_7.5_Algorithm-25-ResidueEnergy-S_CHAR_CHAR")
				.setSignificanceThreshold(0.0).setSignificanceThresholdMax(7.5).setBicliqueSteps(25)
				.setMaxNumberOfThreads(6).setChopIndex(1).setConsiderWeightsWithSize(false).setConsiderWeights(true)
				.setDistanceMultiplier(false).setResidueBased(false).setResidueEnergyMultiplier(false).setTotalSamples(4)
				.setPropertyOne("CHARGED").setPropertyTwo("CHARGED")
				.enableDebug(false).build();

		analyzer.startAnalyze();
		logger.info("End of ResultFile_BA_1_10.0_Algorithm-1-ResidueEnergy-S_1");
		
		*/
		/*logger.info("Start of ResultFile_BA_1_10.0_Algorithm-1-ResidueEnergy-S_1");
		BuilderBindingAffinityAnalyzer analyzer = BuilderBindingAffinityAnalyzer.Builder.newInstance()
				.setFilePath("E:\\BIIP\\BindingAffinity\\").setExcelFilePath("ResultFile_BA_1_10.0_Algorithm-25-ResidueEnergy-S_POL_POL")
				.setSignificanceThreshold(0.0).setSignificanceThresholdMax(10.0).setBicliqueSteps(25)
				.setMaxNumberOfThreads(6).setChopIndex(1).setConsiderWeightsWithSize(false).setConsiderWeights(true)
				.setDistanceMultiplier(false).setResidueBased(false).setResidueEnergyMultiplier(false).setTotalSamples(4)
				.setPropertyOne("POLAR").setPropertyTwo("POLAR")
				.enableDebug(false).build();

		analyzer.startAnalyze();
		logger.info("End of ResultFile_BA_1_10.0_Algorithm-1-ResidueEnergy-S_1");
		
		logger.info("Start of ResultFile_BA_1_10.0_Algorithm-2-ResidueEnergy-S_1");
		analyzer = BuilderBindingAffinityAnalyzer.Builder.newInstance()
				.setFilePath("E:\\BIIP\\BindingAffinity\\").setExcelFilePath("ResultFile_BA_1_10.0_Algorithm-25-ResidueEnergy-S_POL_APOL")
				.setSignificanceThreshold(0.0).setSignificanceThresholdMax(10.0).setBicliqueSteps(25)
				.setMaxNumberOfThreads(6).setChopIndex(1).setConsiderWeightsWithSize(false).setConsiderWeights(true)
				.setDistanceMultiplier(false).setResidueBased(false).setResidueEnergyMultiplier(false).setTotalSamples(4)
				.setPropertyOne("POLAR").setPropertyTwo("APOLAR")
				.enableDebug(false).build();

		analyzer.startAnalyze();
		logger.info("End of ResultFile_BA_1_10.0_Algorithm-2-ResidueEnergy-S_1");
		
		logger.info("Start of ResultFile_BA_1_10.0_Algorithm-1-ResidueEnergy-S_1");
		analyzer = BuilderBindingAffinityAnalyzer.Builder.newInstance()
				.setFilePath("E:\\BIIP\\BindingAffinity\\").setExcelFilePath("ResultFile_BA_1_10.0_Algorithm-25-ResidueEnergy-S_APOL_APOL")
				.setSignificanceThreshold(0.0).setSignificanceThresholdMax(10.0).setBicliqueSteps(25)
				.setMaxNumberOfThreads(6).setChopIndex(1).setConsiderWeightsWithSize(false).setConsiderWeights(true)
				.setDistanceMultiplier(false).setResidueBased(false).setResidueEnergyMultiplier(false).setTotalSamples(4)
				.setPropertyOne("APOLAR").setPropertyTwo("APOLAR")
				.enableDebug(false).build();

		analyzer.startAnalyze();
		logger.info("End of ResultFile_BA_1_10.0_Algorithm-1-ResidueEnergy-S_1");
		
		logger.info("Start of ResultFile_BA_1_10.0_Algorithm-1-ResidueEnergy-S_1");
		analyzer = BuilderBindingAffinityAnalyzer.Builder.newInstance()
				.setFilePath("E:\\BIIP\\BindingAffinity\\").setExcelFilePath("ResultFile_BA_1_7.5_Algorithm-25-ResidueEnergy-S_POL_POL")
				.setSignificanceThreshold(0.0).setSignificanceThresholdMax(7.5).setBicliqueSteps(25)
				.setMaxNumberOfThreads(6).setChopIndex(1).setConsiderWeightsWithSize(false).setConsiderWeights(true)
				.setDistanceMultiplier(false).setResidueBased(false).setResidueEnergyMultiplier(false).setTotalSamples(4)
				.setPropertyOne("POLAR").setPropertyTwo("POLAR")
				.enableDebug(false).build();

		analyzer.startAnalyze();
		logger.info("End of ResultFile_BA_1_10.0_Algorithm-1-ResidueEnergy-S_1");
		
		logger.info("Start of ResultFile_BA_1_10.0_Algorithm-2-ResidueEnergy-S_1");
		analyzer = BuilderBindingAffinityAnalyzer.Builder.newInstance()
				.setFilePath("E:\\BIIP\\BindingAffinity\\").setExcelFilePath("ResultFile_BA_1_7.5_Algorithm-25-ResidueEnergy-S_POL_APOL")
				.setSignificanceThreshold(0.0).setSignificanceThresholdMax(7.5).setBicliqueSteps(25)
				.setMaxNumberOfThreads(6).setChopIndex(1).setConsiderWeightsWithSize(false).setConsiderWeights(true)
				.setDistanceMultiplier(false).setResidueBased(false).setResidueEnergyMultiplier(false).setTotalSamples(4)
				.setPropertyOne("POLAR").setPropertyTwo("APOLAR")
				.enableDebug(false).build();

		analyzer.startAnalyze();
		logger.info("End of ResultFile_BA_1_10.0_Algorithm-2-ResidueEnergy-S_1");
		*/
		/*
		logger.info("Start of ResultFile_BA_1_10.0_Algorithm-1-ResidueEnergy-S_1");
		BuilderBindingAffinityAnalyzer analyzer = BuilderBindingAffinityAnalyzer.Builder.newInstance()
				.setFilePath("E:\\BIIP\\BindingAffinity\\").setExcelFilePath("ResultFile_BA_1_7.5_Algorithm-25-ResidueEnergy-S_APOL_APOL")
				.setSignificanceThreshold(0.0).setSignificanceThresholdMax(7.5).setBicliqueSteps(25)
				.setMaxNumberOfThreads(6).setChopIndex(1).setConsiderWeightsWithSize(false).setConsiderWeights(true)
				.setDistanceMultiplier(false).setResidueBased(false).setResidueEnergyMultiplier(false).setTotalSamples(4)
				.setPropertyOne("APOLAR").setPropertyTwo("APOLAR")
				.enableDebug(false).build();

		analyzer.startAnalyze();
		logger.info("End of ResultFile_BA_1_10.0_Algorithm-1-ResidueEnergy-S_1");
		

		
		logger.info("Start of ResultFile_BA_1_10.0_Algorithm-1-ResidueEnergy-S_1");
		analyzer = BuilderBindingAffinityAnalyzer.Builder.newInstance()
				.setFilePath("E:\\BIIP\\BindingAffinity\\").setExcelFilePath("ResultFile_BA_1_12.5_Algorithm-25-ResidueEnergy-S_POL_POL")
				.setSignificanceThreshold(0.0).setSignificanceThresholdMax(12.5).setBicliqueSteps(25)
				.setMaxNumberOfThreads(6).setChopIndex(1).setConsiderWeightsWithSize(false).setConsiderWeights(true)
				.setDistanceMultiplier(false).setResidueBased(false).setResidueEnergyMultiplier(false).setTotalSamples(4)
				.setPropertyOne("POLAR").setPropertyTwo("POLAR")
				.enableDebug(false).build();

		analyzer.startAnalyze();
		logger.info("End of ResultFile_BA_1_10.0_Algorithm-1-ResidueEnergy-S_1");
		
		logger.info("Start of ResultFile_BA_1_10.0_Algorithm-2-ResidueEnergy-S_1");
		analyzer = BuilderBindingAffinityAnalyzer.Builder.newInstance()
				.setFilePath("E:\\BIIP\\BindingAffinity\\").setExcelFilePath("ResultFile_BA_1_12.5_Algorithm-25-ResidueEnergy-S_POL_APOL")
				.setSignificanceThreshold(0.0).setSignificanceThresholdMax(12.5).setBicliqueSteps(25)
				.setMaxNumberOfThreads(6).setChopIndex(1).setConsiderWeightsWithSize(false).setConsiderWeights(true)
				.setDistanceMultiplier(false).setResidueBased(false).setResidueEnergyMultiplier(false).setTotalSamples(4)
				.setPropertyOne("POLAR").setPropertyTwo("APOLAR")
				.enableDebug(false).build();

		analyzer.startAnalyze();
		logger.info("End of ResultFile_BA_1_10.0_Algorithm-2-ResidueEnergy-S_1");
		
		logger.info("Start of ResultFile_BA_1_10.0_Algorithm-1-ResidueEnergy-S_1");
		analyzer = BuilderBindingAffinityAnalyzer.Builder.newInstance()
				.setFilePath("E:\\BIIP\\BindingAffinity\\").setExcelFilePath("ResultFile_BA_1_12.5_Algorithm-25-ResidueEnergy-S_APOL_APOL")
				.setSignificanceThreshold(0.0).setSignificanceThresholdMax(12.5).setBicliqueSteps(25)
				.setMaxNumberOfThreads(6).setChopIndex(1).setConsiderWeightsWithSize(false).setConsiderWeights(true)
				.setDistanceMultiplier(false).setResidueBased(false).setResidueEnergyMultiplier(false).setTotalSamples(4)
				.setPropertyOne("APOLAR").setPropertyTwo("APOLAR")
				.enableDebug(false).build();

		analyzer.startAnalyze();
		logger.info("End of ResultFile_BA_1_10.0_Algorithm-1-ResidueEnergy-S_1");
		*/
		/*logger.info("Start Main Execution");

		logger.info("Start of ResultFile_BA_1_15.0_Algorithm-7");
		BuilderBindingAffinityAnalyzer analyzer = BuilderBindingAffinityAnalyzer.Builder.newInstance()
				.setFilePath("E:\\BIIP\\BindingAffinity\\")
				.setExcelFilePath("ResultFile_BA_25_15.0_Algorithm-7-S_3").setSignificanceThreshold(0.0)
				.setSignificanceThresholdMax(15.0).setBicliqueSteps(25).setMaxNumberOfThreads(8).setChopIndex(1)
				.setConsiderWeightsWithSize(false).setConsiderWeights(false).setDistanceMultiplier(false)
				.setResidueBased(true).setTotalSamples(4).enableDebug(false).build();

		analyzer.startAnalyze();
		logger.info("End of ResultFile_BA_1_15.0_Algorithm-7");
		*/
		/*
		logger.info("Start of ResultFile_BA_1_5.0_Algorithm-6");
		BuilderBindingAffinityAnalyzer analyzer = BuilderBindingAffinityAnalyzer.Builder.newInstance()
				.setFilePath("E:\\BIIP\\BindingAffinity\\")
				.setExcelFilePath("ResultFile_BA_25_6.0_Algorithm-6-S_1").setSignificanceThreshold(0.0)
				.setSignificanceThresholdMax(6.0).setBicliqueSteps(25).setMaxNumberOfThreads(8).setChopIndex(1)
				.setConsiderWeightsWithSize(false).setConsiderWeights(true).setDistanceMultiplier(false)
				.setResidueBased(true).setTotalSamples(4).enableDebug(false).build();

		analyzer.startAnalyze();
		logger.info("End of ResultFile_BA_1_5.0_Algorithm-6");
		*/
		/*
		logger.info("Start of ResultFile_BA_1_5.0_Algorithm-5");
		BuilderBindingAffinityAnalyzer analyzer = BuilderBindingAffinityAnalyzer.Builder.newInstance()
				.setFilePath("E:\\BIIP\\BindingAffinity\\")
				.setExcelFilePath("ResultFile_BA_25_10.0_Algorithm-5-S_1").setSignificanceThreshold(0.0)
				.setSignificanceThresholdMax(6.0).setBicliqueSteps(25).setMaxNumberOfThreads(8).setChopIndex(1)
				.setConsiderWeightsWithSize(false).setConsiderWeights(true).setDistanceMultiplier(true)
				.setTotalSamples(4).enableDebug(false).build();

		analyzer.startAnalyze();
		logger.info("End of ResultFile_BA_1_5.0_Algorithm-5");
		*/
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
