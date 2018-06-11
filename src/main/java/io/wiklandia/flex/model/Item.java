package io.wiklandia.flex.model;

import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(indexes = { @Index(name = "item_item_type_id_index", columnList = "item_type_id") })
public class Item extends Base {

	@NotNull
	@ManyToOne
	private ItemType itemType;

	public static Item of(ItemType itemType) {
		Item item = new Item();
		item.setItemType(itemType);
		return item;
	}

}
