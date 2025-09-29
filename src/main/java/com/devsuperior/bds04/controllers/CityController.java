package com.devsuperior.bds04.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.devsuperior.bds04.dto.CityDTO;
import com.devsuperior.bds04.security.annotations.AdminOnly;
import com.devsuperior.bds04.services.CityService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping(path = "/cities",
                produces = MediaType.APPLICATION_JSON_VALUE)
public class CityController {
    
    @Autowired
    private CityService cityService;

    @GetMapping
    public ResponseEntity<List<CityDTO>> findAll() {
        return ResponseEntity.ok(cityService.findAll());
    }

    @AdminOnly
    @PostMapping
    public ResponseEntity<CityDTO> save(@RequestBody @Valid CityDTO dto) {
        CityDTO saved = cityService.save(dto);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{id}")
            .build(saved.getId());
        return ResponseEntity.created(location).body(saved);
    }
    
}
