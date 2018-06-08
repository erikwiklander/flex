package io.wiklandia.flex.model;

import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

@NoRepositoryBean
public interface BaseRepository<T>
		extends PagingAndSortingRepository<T, Long>, QuerydslPredicateExecutor<T>, QueryByExampleExecutor<T> {

}
