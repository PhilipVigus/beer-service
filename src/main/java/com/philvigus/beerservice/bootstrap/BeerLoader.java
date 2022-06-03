package com.philvigus.beerservice.bootstrap;

import com.philvigus.beerservice.domain.Beer;
import com.philvigus.beerservice.repositories.BeerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class BeerLoader implements CommandLineRunner {
  private final BeerRepository beerRepository;

  public BeerLoader(BeerRepository beerRepository) {
    this.beerRepository = beerRepository;
  }

  @Override
  public void run(String... args) throws Exception {
    loadBeerObjects();
  }

  private void loadBeerObjects() {
    if (beerRepository.count() == 0) {
      beerRepository.save(
          Beer.builder()
              .name("Uber")
              .style("IPA")
              .numberToBrew(200)
              .minimumAvailable(12)
              .upc(310303030L)
              .price(new BigDecimal("12.95"))
              .build());

      beerRepository.save(
          Beer.builder()
              .name("Wibble")
              .style("PALE_ALE")
              .numberToBrew(200)
              .minimumAvailable(12)
              .upc(310303033L)
              .price(new BigDecimal("11.95"))
              .build());
    }
  }
}
