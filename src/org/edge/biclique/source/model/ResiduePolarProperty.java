package org.edge.biclique.source.model;

public enum ResiduePolarProperty {
	POLAR {
		@Override
		public String toString() {
			return "POLAR";
		}
	},
	APOLAR {
		@Override
		public String toString() {
			return "APOLAR";
		}
	}
}
