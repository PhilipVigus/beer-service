package com.philvigus.beerservice.web.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BeerDto {
  private UUID id;
  private Integer version;

  private String name;
  private BeerStyle style;
  private Long upc;
  private BigDecimal price;
  private Integer quantity;

  private OffsetDateTime createdAt;
  private OffsetDateTime updatedAt;
}
