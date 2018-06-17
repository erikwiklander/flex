package io.wiklandia.flex.model;

import lombok.Getter;

@Getter
public enum AttributeType {

	TEXT("text_value", "textValue"), DECIMAL("decimal_value", "decimalValue"), LONG("long_value", "longValue");

	private final String dbCol;
	private final String field;

	private AttributeType(String dbCol, String field) {
		this.dbCol = dbCol;
		this.field = field;
	}

}
