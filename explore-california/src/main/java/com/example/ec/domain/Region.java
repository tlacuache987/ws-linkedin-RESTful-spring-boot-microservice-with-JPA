package com.example.ec.domain;

public enum Region {

	CENTRAL_COAST("Central Coast"),
	SOUTHERN_CALIFORNIA("Southern California"),
	NORTHEN_CALIFORNIA("Northen California"),
	VARIES("Varies");

	private String label;

	private Region(String label) {
		this.label = label;
	}

	public static Region findByLabel(String label) {
		for (Region r : Region.values()) {
			if (r.label.equalsIgnoreCase(label))
				return r;
		}
		return null;
	}

}
