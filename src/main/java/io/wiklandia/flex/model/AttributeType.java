package io.wiklandia.flex.model;

import io.vavr.control.Try;
import lombok.Getter;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.Function;

@Getter
public enum AttributeType {


	TEXT("text_value",
			"textValue",
			val -> val,
			(rs, col) -> Try.of(() -> rs.getString(col)).get()),

	DECIMAL("decimal_value",
			"decimalValue",
			BigDecimal::new,
			(rs, col) -> Try.of(() -> rs.getBigDecimal(col)).get()),

	LONG("long_value",
			"longValue",
			Long::valueOf,
			(rs, col) -> Try.of(() -> Optional.of(rs.getObject(col)).map(o -> (Long) o)).getOrNull()),

	DATE_TIME("date_time_value",
			"dateTimeValue",
			val -> ZonedDateTime.parse(val).withZoneSameInstant(ZoneId.systemDefault()).toLocalDateTime(),
			(rs, col) -> Try.of(() -> Optional.of(rs.getTimestamp(col)).map(Timestamp::toLocalDateTime)).getOrNull()),

	DATE("date_value",
			"dateValue",
			val -> Optional.of(val).map(LocalDate::parse).orElse(null),
			(rs, col) -> Try.of(() -> Optional.of(rs.getDate(col)).map(Date::toLocalDate)).getOrNull());


	private final String dbCol;
	private final String field;
	private final Function<String, Object> objectConverter;
	private final BiFunction<ResultSet, String, Object> resultSetConverter;

	AttributeType(String dbCol, String field, Function<String, Object> objectConverter,
				  BiFunction<ResultSet, String, Object> resultSetConverter) {
		System.out.println("test");
		this.dbCol = dbCol;
		this.field = field;
		this.objectConverter = objectConverter;
		this.resultSetConverter = resultSetConverter;
	}

}
