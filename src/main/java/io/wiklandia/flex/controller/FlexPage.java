package io.wiklandia.flex.controller;

import lombok.Data;
import org.springframework.data.domain.Page;

import java.util.List;

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
    int number, size, totalPages;
    long totalElements;
  }

}
