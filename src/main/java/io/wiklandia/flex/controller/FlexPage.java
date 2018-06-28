package io.wiklandia.flex.controller;

import java.util.List;

import org.springframework.data.domain.Page;

import lombok.Data;

@Data
public class FlexPage<T> {

	private List<T> content;
	private PageInfo page = new PageInfo();

	public FlexPage(Page<T> p) {
		this.content = p.getContent();
		this.page.number = p.getNumber();
		this.page.size = p.getSize();
		this.page.totalElements = p.getTotalElements();
		this.page.totalPages = p.getTotalPages();
	}

	@Data
	static class PageInfo {
		int number;
		int size;
		int totalPages;
		long totalElements;
	}

}
