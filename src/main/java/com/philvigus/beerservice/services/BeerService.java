package com.philvigus.beerservice.services;

import com.philvigus.beerservice.web.model.BeerDto;

import java.util.UUID;

public interface BeerService {
  BeerDto getById(UUID beerId);

  BeerDto save(BeerDto beerDto);

  BeerDto update(UUID beerId, BeerDto beerDto);
}
