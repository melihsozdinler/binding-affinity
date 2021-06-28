package org.edge.biclique.build;

import org.graphstream.graph.Node;

interface FeatureCheck {
	boolean checkPolarAttributeAndRemove(Node source, Node target);
	boolean checkChargedAttributeAndRemove(Node source, Node target);
	boolean checkPolarChargedAttributeAndRemove(Node source, Node target);
	boolean checkApolarChargedAttributeAndRemove(Node source, Node target);
}
