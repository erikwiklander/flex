package io.wiklandia.flex.controller;

import io.wiklandia.flex.db.ItemFinderService;
import io.wiklandia.flex.service.ItemService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@Slf4j
@RunWith(SpringRunner.class)
@WebMvcTest(ItemController.class)
public class ItemControllerTest {

  @Autowired
  private MockMvc mvc;
  @MockBean
  private ItemFinderService itemFinderService;
  @MockBean
  private ItemService itemService;

  @Test
  public void test() throws Exception {
    when(itemFinderService.getPage(any(), any()))
      .thenReturn(new PageImpl<>(Collections.emptyList(), PageRequest.of(0, 10, Sort.unsorted()), 0));
    mvc.perform(get("/items/1"))
      .andDo(print())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
      .andExpect(jsonPath("$.content", hasSize(0)))
      .andExpect(jsonPath("$.page.number", is(0)))
      .andExpect(jsonPath("$.page.size", is(10)))
      .andExpect(jsonPath("$.page.totalElements", is(0)))
      .andExpect(jsonPath("$.page.totalPages", is(0)));
  }

}
