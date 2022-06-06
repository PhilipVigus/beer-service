package com.philvigus.beerservice.web.mappers;

import com.philvigus.beerservice.domain.Beer;
import com.philvigus.beerservice.web.model.BeerDto;
import org.mapstruct.Mapper;

@Mapper(uses = DateMapper.class)
public interface BeerMapper {
  BeerDto BeerToBeerDto(Beer beer);

  Beer BeerDtoToBeer(BeerDto beerDto);
}
