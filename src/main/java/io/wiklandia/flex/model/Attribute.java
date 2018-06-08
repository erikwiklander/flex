package io.wiklandia.flex.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.NaturalId;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@EqualsAndHashCode(of = "name", callSuper = false)
public class Attribute extends Base {

	@NotNull
	@NaturalId
	private String name;

	@NotNull
	@Enumerated(EnumType.STRING)
	private AttributeType type;

	public static Attribute of(String name, AttributeType type) {
		Attribute attribute = new Attribute();
		attribute.setName(name);
		attribute.setType(type);
		return attribute;
	}
}
