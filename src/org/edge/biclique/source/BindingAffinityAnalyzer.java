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
				.setFilePath("E:\\BIIP\\COVID19\\pdb\\").setExcelFilePath("ResultFileCOVID_BA_1_7.50_Algorithm-Residue-15-Atom_ALL")
				.setSignificanceThreshold(0.0).setSignificanceThresholdMax(7.5).setBicliqueSteps(15)
				.setMaxNumberOfThreads(5).setChopIndex(1).setConsiderWeightsWithSize(false).setConsiderWeights(true)
				.setDistanceMultiplier(false).setResidueBased(true).setResidueEnergyMultiplier(false).setTotalSamples(4)
				.enableDebug(false).build();
		analyzer.startAnalyze();
		Utils.removeFiles("src\\bin\\", "txt");
		logger.info("Finish Main Execution");
	}
}
