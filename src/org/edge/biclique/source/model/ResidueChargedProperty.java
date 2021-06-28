package org.edge.biclique.source.model;

public enum ResidueChargedProperty {
	CHARGED {
		@Override
		public String toString() {
			return "CHARGED";
		}
	},
	UNCHARGED {
		@Override
		public String toString() {
			return "UNCHARGED";
		}
	}
}
