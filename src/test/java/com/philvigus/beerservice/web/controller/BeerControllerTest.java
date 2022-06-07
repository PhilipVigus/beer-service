package com.philvigus.beerservice.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.philvigus.beerservice.bootstrap.BeerLoader;
import com.philvigus.beerservice.services.BeerService;
import com.philvigus.beerservice.web.model.BeerDto;
import com.philvigus.beerservice.web.model.BeerStyle;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BeerController.class)
@AutoConfigureRestDocs
@ExtendWith(RestDocumentationExtension.class)
class BeerControllerTest {
  @Autowired MockMvc mockMvc;

  @Autowired ObjectMapper objectMapper;

  @MockBean BeerService beerService;

  @Test
  void getBeerById() throws Exception {
    given(beerService.getById(any())).willReturn(createValidBeerDto());

    mockMvc
        .perform(
            get("/api/v1/beer/{beerId}", UUID.randomUUID().toString())
                .param("iscold", "yes")
                .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk());
  }

  @Test
  void saveNewBeer() throws Exception {
    String beerDtoJson = objectMapper.writeValueAsString(createValidBeerDto());

    given(beerService.save(any())).willReturn(createValidBeerDto());

    mockMvc
        .perform(post("/api/v1/beer").contentType(MediaType.APPLICATION_JSON).content(beerDtoJson))
        .andExpect(status().isCreated());
  }

  @Test
  void updateBeerById() throws Exception {
    String beerDtoJson = objectMapper.writeValueAsString(createValidBeerDto());

    given(beerService.update(any(), any())).willReturn(createValidBeerDto());

    mockMvc
        .perform(
            put("/api/v1/beer/" + UUID.randomUUID().toString())
                .contentType(MediaType.APPLICATION_JSON)
                .content(beerDtoJson))
        .andExpect(status().isNoContent());
  }

  BeerDto createValidBeerDto() {
    return BeerDto.builder()
        .name("beer name")
        .style(BeerStyle.ALE)
        .price(new BigDecimal("2.99"))
        .upc(BeerLoader.UPC_1)
        .build();
  }
}
