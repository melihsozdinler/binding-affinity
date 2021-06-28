package org.edge.biclique.source.util;

import java.io.File;
import java.nio.file.FileSystems;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Utils {
	
	public static void pause(long millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
		}
	}

	public static List<Integer> returnLineList(String line){
		
		List<Integer> items = new ArrayList<Integer>();
		String[] integerStrings = line.split("\t");
		
		for(String iterate : integerStrings ) {
			items.add(Integer.parseInt(iterate));
		}
		
		return items;
	}
	
	public static <T>List<List<T>> chopIntoParts( final List<T> ls, final int iParts )
	{
	    final List<List<T>> lsParts = new ArrayList<List<T>>();
	    final int iChunkSize = ls.size() / iParts;
	    int iLeftOver = ls.size() % iParts;
	    int iTake = iChunkSize;

	    for( int i = 0, iT = ls.size(); i < iT; i += iTake )
	    {
	        if( iLeftOver > 0 )
	        {
	            iLeftOver--;

	            iTake = iChunkSize + 1;
	        }
	        else
	        {
	            iTake = iChunkSize;
	        }

	        lsParts.add( new ArrayList<T>( ls.subList( i, Math.min( iT, i + iTake ) ) ) );
	    }

	    return lsParts;
	}
	
	public static Double checkDoubleAndReturn(String doubleStr) {
		if(doubleStr.lastIndexOf(".") == doubleStr.indexOf(".")) {
			return Double.parseDouble(doubleStr);
		}
		else {
			return Double.parseDouble(doubleStr.substring(0, doubleStr.lastIndexOf(".")));
		}
	}
	
	public static String getUniqueFileName(String fileBaseName, String directory, String extension) {
		String fileName = FileSystems.getDefault().getPath(".").toAbsolutePath() + "\\" + directory + fileBaseName + "_"
				+ UUID.randomUUID() + "." + extension.trim();
		return fileName;
	}
	
	public static void removeFiles(String directory, String extension) {
		File folder = new File(FileSystems.getDefault().getPath(".").toAbsolutePath() + "\\" + directory);
		File fList[] = folder.listFiles();

		for (File f : fList) {
			if (f.getName().endsWith(extension)) {
				f.delete();
			}
		}
	}
}
