package de.luc1412.eai.utils;

public enum Database {
	ENGLISH("EN"),
	GERMAN("DE"),
	SPANISH("ES"),
	FRANCH("FR");

	public String value;

	Database(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
}
