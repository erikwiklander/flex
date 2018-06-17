package io.wiklandia.flex.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.function.Function;

import lombok.Getter;

@Getter
public enum AttributeType {

	// @formatter:off
	TEXT("text_value", "textValue", val -> val),
	DECIMAL("decimal_value", "decimalValue", BigDecimal::new), 
	LONG("long_value", "longValue", Long::valueOf),
	DATE_TIME("date_time_value", "dateTimeValue", 
			val -> LocalDateTime.parse(val, DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"))),
	DATE("date_value", "dateValue", LocalDate::parse);
	// @formatter:on

	private final String dbCol;
	private final String field;
	private final Function<String, Object> objectConverter;

	private AttributeType(String dbCol, String field, Function<String, Object> objectConverter) {
		this.dbCol = dbCol;
		this.field = field;
		this.objectConverter = objectConverter;
	}

}
