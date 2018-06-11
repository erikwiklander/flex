package io.wiklandia.flex.model;

import lombok.Getter;

@Getter
public enum AttributeType {

	TEXT("text_value"), DECIMAL("decimal_value"), LONG("long_value");

	private final String dbCol;

	private AttributeType(String dbCol) {
		this.dbCol = dbCol;
	}

}
