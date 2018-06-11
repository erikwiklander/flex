package io.wiklandia.flex.model;

import java.math.BigDecimal;

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
@Table(indexes = { @Index(name = "value_attribute_id_index", columnList = "attribute_id"),
		@Index(name = "value_item_id_index", columnList = "item_id"),
		@Index(name = "value_text_value_index", columnList = "textValue"),
		@Index(name = "value_decimal_value_index", columnList = "decimalValue"),
		@Index(name = "value_long_value_index", columnList = "longValue") })
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

	public static Value of(Attribute attribute, Item item) {
		Value value = new Value();
		value.setAttribute(attribute);
		value.setItem(item);
		return value;
	}

}
