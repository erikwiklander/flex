package io.wiklandia.flex.model;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.NaturalId;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class ItemType extends Base {

	@NotNull
	@NaturalId
	private String name;

	public static ItemType of(String name) {
		ItemType itemType = new ItemType();
		itemType.setName(name);
		return itemType;
	}

}
