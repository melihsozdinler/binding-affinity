package org.edge.biclique.source.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.edge.biclique.build.BindingAffinityExecutor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FileUtils {
	
	final static Logger logger = LoggerFactory.getLogger(FileUtils.class);

	public static String writeToFile(double[][] dataset, int K, int L) {
		String fileName = "";
		String TAB = "\t";
		String NEW_LINE = "\n";
		BufferedWriter output = null;
		try {
			logger.debug("File Processing Begins");
			fileName = Utils.getUniqueFileName("Input", "src\\bin\\", "txt");
			logger.debug("File Name to output {}", fileName);
			
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
			logger.debug("Write file is completed");
		}
		return fileName;
	}
}
