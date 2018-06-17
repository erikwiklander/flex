package io.wiklandia.flex.model;

import java.math.BigDecimal;
import java.util.function.Function;

import lombok.Getter;

@Getter
public enum AttributeType {

	// @formatter:off
	TEXT("text_value", "textValue", String::valueOf),
	DECIMAL("decimal_value", "decimalValue", (val) -> new BigDecimal(val.toString())), 
	LONG("long_value", "longValue", (val) -> Long.valueOf(val.toString()));
	// @formatter:on

	private final String dbCol;
	private final String field;
	private final Function<Object, Object> objectConverter;

	private AttributeType(String dbCol, String field, Function<Object, Object> objectConverter) {
		this.dbCol = dbCol;
		this.field = field;
		this.objectConverter = objectConverter;
	}

}
