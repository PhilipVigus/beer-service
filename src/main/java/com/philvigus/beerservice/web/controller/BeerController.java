package com.philvigus.beerservice.web.controller;

import com.philvigus.beerservice.services.BeerService;
import com.philvigus.beerservice.web.model.BeerDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RequiredArgsConstructor
@RequestMapping("/api/v1/beer")
@RestController
public class BeerController {
  private final BeerService beerService;

  @GetMapping("/{beerId}")
  public ResponseEntity<BeerDto> getBeerById(@PathVariable("beerId") UUID beerId) {
    return new ResponseEntity<>(beerService.getById(beerId), HttpStatus.OK);
  }

  @PostMapping
  public ResponseEntity<BeerDto> saveNewBeer(@Valid @RequestBody BeerDto beerDto) {
    return new ResponseEntity<>(beerService.save(beerDto), HttpStatus.CREATED);
  }

  @PutMapping("/{beerId}")
  public ResponseEntity<BeerDto> updateBeerById(
      @PathVariable("beerId") UUID beerId, @Valid @RequestBody BeerDto beerDto) {
    return new ResponseEntity<>(beerService.update(beerId, beerDto), HttpStatus.NO_CONTENT);
  }
}
