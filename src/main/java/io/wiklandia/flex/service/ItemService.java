package io.wiklandia.flex.service;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Subquery;

import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.querydsl.core.types.ExpressionUtils;

import io.wiklandia.flex.model.Attribute;
import io.wiklandia.flex.model.Item;
import io.wiklandia.flex.model.ItemRepository;
import io.wiklandia.flex.model.ItemType;
import io.wiklandia.flex.model.QValue;
import io.wiklandia.flex.model.Value;
import io.wiklandia.flex.model.ValueRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@AllArgsConstructor
public class ItemService {

	private final ValueRepository values;
	private final ItemRepository items;
	private final EntityManager em;

	public Item create(ItemType itemType) {

		log.info("CREATE");

		return items.save(Item.of(itemType));
	}

	public void test() {
		CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
		CriteriaQuery<Item> query = criteriaBuilder.createQuery(Item.class);
		Root<Item> root = query.from(Item.class);
		Join<Item, Value> join = root.join("values", JoinType.LEFT);

		Subquery<Long> sq = criteriaBuilder.createQuery().subquery(Long.class);

		Root<Item> from = query.from(Item.class);
		Path<Value> path = root.join("values");
		query.orderBy(criteriaBuilder.asc(join.get("textValue")));
		query.select(root);
		List<Item> result = em.createQuery(query).getResultList();
	}

	public void save(Item item, Attribute attribute, Object val) {

		log.info("SAVE");
		log.info("em: {}", em);
		Assert.notNull(item, "Item cannot be null");
		Assert.notNull(attribute, "attribute cannot be null");
		Value value = values
				.findOne(ExpressionUtils.and(QValue.value.attribute.eq(attribute), QValue.value.item.eq(item)))
				.orElse(values.save(Value.of(attribute, item)));

		switch (attribute.getType()) {
		case TEXT:
			value.setTextValue((String) val);
			break;
		case DECIMAL:
			value.setDecimalValue((BigDecimal) val);
			break;
		case LONG:
			value.setLongValue((Long) val);
			break;
		default:
			throw new IllegalArgumentException("Unknown type: " + attribute.getType());
		}

		values.save(value);
	}

}
