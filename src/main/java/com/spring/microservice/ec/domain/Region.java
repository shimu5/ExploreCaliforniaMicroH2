package com.spring.microservice.ec.domain;

public enum Region {
	Central_Coast("Central Coast"), 
	Southern_California("Southern California"), 
	Northern_California("Northern California"),
	Varies("Varies"),
	Other("Other");

	private final String label1;

	private Region(String label1) {
		this.label1 = label1;
	}
	
	public static Region findByLabel(String byLabel) {
		
		for(Region r: Region.values()) {
			if(r.label1.equalsIgnoreCase(byLabel)) {
				return r;
			}
		}
		return Other;
	}
}
