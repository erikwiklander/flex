package io.wiklandia.flex.model;

import com.google.common.base.CaseFormat;
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


  TEXT(val -> val,
			(rs, col) -> Try.of(() -> rs.getString(col)).get()),

  DECIMAL(BigDecimal::new,
			(rs, col) -> Try.of(() -> rs.getBigDecimal(col)).get()),

  LONG(Long::valueOf,
			(rs, col) -> Try.of(() -> Optional.of(rs.getObject(col)).map(o -> (Long) o)).getOrNull()),

  DATE_TIME(val -> ZonedDateTime.parse(val).withZoneSameInstant(ZoneId.systemDefault()).toLocalDateTime(),
			(rs, col) -> Try.of(() -> Optional.of(rs.getTimestamp(col)).map(Timestamp::toLocalDateTime)).getOrNull()),

  DATE(val -> Optional.of(val).map(LocalDate::parse).orElse(null),
			(rs, col) -> Try.of(() -> Optional.of(rs.getDate(col)).map(Date::toLocalDate)).getOrNull());


	private final Function<String, Object> objectConverter;
	private final BiFunction<ResultSet, String, Object> resultSetConverter;

  AttributeType(Function<String, Object> objectConverter,
                BiFunction<ResultSet, String, Object> resultSetConverter) {
		this.objectConverter = objectConverter;
		this.resultSetConverter = resultSetConverter;
	}

  public String getDbCol() {
    return CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.LOWER_UNDERSCORE, this.name() + "_VALUE");
  }

  public String getField() {
    return CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, this.name() + "_VALUE");
  }

}
