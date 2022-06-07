package com.philvigus.beerservice.services;

import com.philvigus.beerservice.domain.Beer;
import com.philvigus.beerservice.exceptions.NotFoundException;
import com.philvigus.beerservice.repositories.BeerRepository;
import com.philvigus.beerservice.web.mappers.BeerMapper;
import com.philvigus.beerservice.web.model.BeerDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@RequiredArgsConstructor
@Service
public class BeerServiceImpl implements BeerService {
  private final BeerRepository beerRepository;
  private final BeerMapper beerMapper;

  @Override
  public BeerDto getById(UUID beerId) {
    return beerMapper.beerToBeerDto(
        beerRepository.findById(beerId).orElseThrow(NotFoundException::new));
  }

  @Override
  public BeerDto save(BeerDto beerDto) {
    Beer savedBeer = beerRepository.save(beerMapper.beerDtoToBeer(beerDto));

    return beerMapper.beerToBeerDto(savedBeer);
  }

  @Override
  public BeerDto update(UUID beerId, BeerDto beerDto) {
    Beer beer = beerRepository.findById(beerId).orElseThrow(NotFoundException::new);

    beer.setName(beerDto.getName());
    beer.setStyle(beerDto.getStyle().name());
    beer.setPrice(beerDto.getPrice());
    beer.setUpc(beerDto.getUpc());

    return beerMapper.beerToBeerDto(beerRepository.save(beer));
  }
}
