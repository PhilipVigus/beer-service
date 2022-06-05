package com.philvigus.beerservice.web.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BeerDto {
  @Null private UUID id;

  @Null private Integer version;

  @NotBlank private String name;

  @NotNull private BeerStyle style;

  @Positive @NotNull private Long upc;

  @Positive @NotNull private BigDecimal price;

  private Integer quantity;

  @Null private OffsetDateTime createdAt;

  @Null private OffsetDateTime updatedAt;
}
