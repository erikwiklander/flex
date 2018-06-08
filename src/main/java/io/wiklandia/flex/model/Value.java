package io.wiklandia.flex.model;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@EqualsAndHashCode(of = "attribute", callSuper = false)
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
