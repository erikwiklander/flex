package io.wiklandia.flex.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Index;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
// @formatter:off
@Table(indexes = { 
		@Index(name = "value_attribute_id_index", columnList = "attribute_id"),
		@Index(name = "value_item_id_index", columnList = "item_id"),
		@Index(name = "value_text_value_index", columnList = "textValue"),
		@Index(name = "value_decimal_value_index", columnList = "decimalValue"),
		@Index(name = "value_long_value_index", columnList = "longValue"),
		@Index(name = "value_date_value_index", columnList = "dateTimeValue"),
		@Index(name = "date_value_index", columnList = "dateValue")
		})
// @formatter:on
public class Value extends Base {

	@NotNull
	@ManyToOne(fetch = FetchType.LAZY)
	private Attribute attribute;

	@NotNull
	@ManyToOne(fetch = FetchType.LAZY)
	private Item item;

	private String textValue;
	private BigDecimal decimalValue;
	private Long longValue;
	private LocalDateTime dateTimeValue;
	private LocalDate dateValue;

	public static Value of(Attribute attribute, Item item) {
		Value value = new Value();
		value.setAttribute(attribute);
		value.setItem(item);
		return value;
	}

}
