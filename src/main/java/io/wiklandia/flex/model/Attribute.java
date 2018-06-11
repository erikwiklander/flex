package io.wiklandia.flex.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Index;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.NaturalId;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@EqualsAndHashCode(of = "name", callSuper = false)
@Table(indexes = { @Index(name = "attribute_attribute_type_index", columnList = "attributeType"),
		@Index(name = "attribute_item_type_id_index", columnList = "item_type_id") })
public class Attribute extends Base {

	@NotNull
	@NaturalId
	private String name;

	@NotNull
	@Enumerated(EnumType.STRING)
	private AttributeType attributeType;

	@NotNull
	@ManyToOne
	private ItemType itemType;

	public static Attribute of(String name, AttributeType attributeType, ItemType itemType) {
		Attribute attribute = new Attribute();
		attribute.setName(name);
		attribute.setAttributeType(attributeType);
		attribute.setItemType(itemType);
		return attribute;
	}

	public String getColName() {
		return String.format("%s_%d", attributeType.getDbCol(), getId());
	}
}
