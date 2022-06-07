package com.philvigus.beerservice.domain;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Beer {
  @Id
  @GeneratedValue(generator = "UUID")
  @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
  @Column(length = 36, columnDefinition = "varchar", updatable = false, nullable = false)
  UUID id;

  @Version private Long version;
  private String name;
  private String style;

  @Column(unique = true)
  private String upc;

  private BigDecimal price;
  private Integer minimumAvailable;
  private Integer numberToBrew;

  @CreationTimestamp
  @Column(updatable = false)
  private Timestamp createdAt;

  @UpdateTimestamp private Timestamp updatedAt;
}
