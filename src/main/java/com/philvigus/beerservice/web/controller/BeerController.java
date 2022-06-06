package com.philvigus.beerservice.web.controller;

import com.philvigus.beerservice.web.model.BeerDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RequestMapping("/api/v1/beer")
@RestController
public class BeerController {
  @GetMapping("/{beerId}")
  public ResponseEntity<BeerDto> getBeerById(@PathVariable("beerId") UUID beerId) {
    // TODO: implement
    return new ResponseEntity<>(BeerDto.builder().build(), HttpStatus.OK);
  }

  @PostMapping
  public ResponseEntity<Void> saveNewBeer(@Valid @RequestBody BeerDto beerDto) {
    // TODO: implement
    return new ResponseEntity<>(HttpStatus.CREATED);
  }

  @PutMapping("/{beerId}")
  public ResponseEntity<Void> updateBeerById(
      @PathVariable("beerId") UUID beerId, @Valid @RequestBody BeerDto beerDto) {
    // TODO: implement
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }
}
