package com.philvigus.beerservice.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.philvigus.beerservice.web.model.BeerDto;
import com.philvigus.beerservice.web.model.BeerStyle;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.UUID;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.request.RequestDocumentation.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BeerController.class)
@AutoConfigureRestDocs
@ExtendWith(RestDocumentationExtension.class)
class BeerControllerTest {
  @Autowired MockMvc mockMvc;

  @Autowired ObjectMapper objectMapper;

  @Test
  void getBeerById() throws Exception {
    mockMvc
        .perform(
            get("/api/v1/beer/{beerId}", UUID.randomUUID().toString())
                .param("iscold", "yes")
                .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andDo(
            document(
                "v1/beer",
                pathParameters(
                    parameterWithName("beerId").description("UUID of desired beer to get.")),
                requestParameters(
                    parameterWithName("iscold").description("Is beer cold query param")),
                responseFields(
                    fieldWithPath("id").description("Id of Beer"),
                    fieldWithPath("version").description("Version number"),
                    fieldWithPath("name").description("Beer name"),
                    fieldWithPath("style").description("Beer style"),
                    fieldWithPath("upc").description("Beer UPC"),
                    fieldWithPath("price").description("Beer price"),
                    fieldWithPath("quantity").description("Number of beers"),
                    fieldWithPath("createdAt").description("Date created"),
                    fieldWithPath("updatedAt").description("Date updated"))));
  }

  @Test
  void saveNewBeer() throws Exception {
    String beerDtoJson = objectMapper.writeValueAsString(createValidBeerDto());

    mockMvc
        .perform(post("/api/v1/beer").contentType(MediaType.APPLICATION_JSON).content(beerDtoJson))
        .andExpect(status().isCreated());
  }

  @Test
  void updateBeerById() throws Exception {
    String beerDtoJson = objectMapper.writeValueAsString(createValidBeerDto());

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
        .upc(123123123L)
        .build();
  }
}
