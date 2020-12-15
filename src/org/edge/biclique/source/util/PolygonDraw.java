package org.edge.biclique.source.util;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.StringTokenizer;

import javax.imageio.ImageIO;
import javax.swing.JComponent;
import javax.swing.JFrame;

import org.edge.biclique.source.model.Pair;

public class PolygonDraw {

	class PolygonDrawInternal extends JComponent {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1678006460621167321L;
		Shape shape;
		int K, L;
		double[][] dataset = {};
		String fileName;
		
		ArrayList<Pair<ArrayList<Integer>, ArrayList<Integer>>> clusterList;	
		public PolygonDrawInternal(int K, int L, double[][] dataset, String fileName) {
			this.clusterList = new ArrayList<Pair<ArrayList<Integer>, ArrayList<Integer>>>();
			this.K = K;
			this.L = L;
			this.dataset = dataset;
			this.fileName = fileName;
			create();
		}

		protected void create() {
			String content = null;

			try {
				content = new String(Files.readAllBytes(Paths.get(fileName)));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			Pair<ArrayList<Integer>, ArrayList<Integer>> pair = new Pair<ArrayList<Integer>, ArrayList<Integer>>();
			
			ArrayList<Integer> cluster1 = new ArrayList<Integer>();
			ArrayList<Integer> cluster2 = new ArrayList<Integer>();
			
			String lineFirst = "", lineSecond = "", lineThird = "";
			StringTokenizer st = new StringTokenizer(content);
			
			while (st.hasMoreTokens()) {  
				lineFirst = st.nextToken("\n");
				if ( st.hasMoreTokens() ) {
					lineSecond = st.nextToken("\n");
					StringTokenizer stSecondLine = new StringTokenizer(lineSecond);
					while (stSecondLine.hasMoreTokens()) {  
						String temp = stSecondLine.nextToken("\t\n");
						if ( temp != null && !temp.trim().isEmpty() )
							cluster1.add(Integer.parseInt(temp));
					}
				}
				if ( st.hasMoreTokens() ) {
					lineThird = st.nextToken("\n");
					StringTokenizer stThirdLine = new StringTokenizer(lineThird);
					while (stThirdLine.hasMoreTokens()) {  
						String temp = stThirdLine.nextToken("\t\n");
						if ( temp != null && !temp.trim().isEmpty() )
							cluster2.add(Integer.parseInt(temp));
					}
				}
			};
			
			pair.item1 = cluster1;
			pair.item2 = cluster2;
			
			this.clusterList.add(pair);
		}

		public void paint(Graphics g) {
			double factor = 0.2; 
			Graphics2D g2 = (Graphics2D) g;
			g2.setStroke(new BasicStroke(1));

			g2.setPaint(Color.gray);
			for (int count = 0; count < K; count++) {
				for (int innerCount = 0; innerCount < L; innerCount++) {
					if ( (int)dataset[count][innerCount] == 1){
						g2.drawLine(100 + (int)((double)count/factor), 100, 100 + (int)((double)innerCount/factor), 500);
					}
				}
			}
			
			g2.setStroke(new BasicStroke(5));
			g2.setPaint(Color.red);
			
			g2.drawLine(100, 100, (int)((double) K/factor) + 100, 100);
			g2.drawLine(100, 500, (int)((double) L/factor) + 100, 500);
			

			g2.setStroke(new BasicStroke(1));
			g2.setPaint(Color.orange);
			
			for(Pair<ArrayList<Integer>, ArrayList<Integer>> pairs : this.clusterList) {
				for(Integer itemLayerA : pairs.item1 ) {
					for(Integer itemLayerB : pairs.item2 ) {
						g2.drawLine(100 + (int)((double) itemLayerA/factor), 100, 100 + (int)((double) itemLayerB/factor), 500);
					}
				}
			}
		}	
		
		public ArrayList<Pair<ArrayList<Integer>, ArrayList<Integer>>> getClusterList() {
			return clusterList;
		}

	}
	
	public ArrayList<Pair<ArrayList<Integer>, ArrayList<Integer>>> runOnly(int K, int L, double[][] dataset, int round, String fileName) {
		PolygonDrawInternal polygonDrawInternal = new PolygonDrawInternal(K,L,dataset,fileName);
		return polygonDrawInternal.getClusterList();
	}

	public ArrayList<Pair<ArrayList<Integer>, ArrayList<Integer>>> run(int K, int L, double[][] dataset, int round, String fileName) {
		JFrame jf = new JFrame("Results");
		Container cp = jf.getContentPane();
		PolygonDrawInternal polygonDrawInternal = new PolygonDrawInternal(K,L,dataset,fileName);
		cp.add(polygonDrawInternal);
		jf.setSize(L * 5, 600);
		//jf.setVisible(true);
		
		BufferedImage img = new BufferedImage(K * 5, 600, BufferedImage.TYPE_INT_RGB);
		Graphics2D ig2 = img.createGraphics();
		ig2.setBackground(Color.WHITE);
		ig2.clearRect(0, 0, K * 5, 600);
		polygonDrawInternal.paint(img.getGraphics());
		File outputfile = new File("saved" + "_" + round + ".png");
		try {
			ImageIO.write(img, "png", outputfile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return polygonDrawInternal.getClusterList();
	}
}
