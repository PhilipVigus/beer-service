package com.philvigus.beerservice.web.model;

import com.fasterxml.jackson.annotation.JsonFormat;
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

  private @NotNull String upc;

  @JsonFormat(shape = JsonFormat.Shape.STRING)
  @Positive
  @NotNull
  private BigDecimal price;

  private Integer quantity;

  @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssZ", shape = JsonFormat.Shape.STRING)
  @Null
  private OffsetDateTime createdAt;

  @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssZ", shape = JsonFormat.Shape.STRING)
  @Null
  private OffsetDateTime updatedAt;
}
