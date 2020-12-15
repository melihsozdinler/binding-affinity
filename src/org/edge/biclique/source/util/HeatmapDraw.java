package org.edge.biclique.source.util;

/*******************************************************************************
 * Copyright (c) 2019
 *   
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *  
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/


import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import smile.data.AttributeDataset;
import smile.data.parser.microarray.RESParser;
import smile.plot.Contour;
import smile.plot.Heatmap;
import smile.plot.Palette;
import smile.plot.PlotCanvas;


@SuppressWarnings("serial")
public class HeatmapDraw extends JPanel {
    public HeatmapDraw() {
        super(new GridLayout(2,4));
        setBackground(Color.white);

    }

    @Override
    public String toString() {
        return "Heatmap";
    }

    public void run(double[][] dataset) {
        try {
            JFrame frame = new JFrame("Heatmap");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLocationRelativeTo(null);
            frame.getContentPane().add(Heatmap.plot(dataset, Palette.jet(256)));
            frame.setVisible(true);
        } catch (Exception ex) {
            System.err.println(ex);
        }
    }
}