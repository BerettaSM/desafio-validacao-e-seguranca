package com.devsuperior.bds04.controllers;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.devsuperior.bds04.dto.EventDTO;
import com.devsuperior.bds04.security.annotations.Authenticated;
import com.devsuperior.bds04.services.EventService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping(path = "events",
                produces = MediaType.APPLICATION_JSON_VALUE)
public class EventController {
    
    @Autowired
    private EventService eventService;

    @GetMapping
    public ResponseEntity<Page<EventDTO>> findAll(Pageable pageable) {
        return ResponseEntity.ok(eventService.findAll(pageable));
    }

    @Authenticated
    @PostMapping
    public ResponseEntity<EventDTO> postMethodName(@RequestBody @Valid EventDTO dto) {
        EventDTO saved = eventService.save(dto);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{id}")
            .build(saved.getId());
        return ResponseEntity.created(location).body(saved);
    }
    
}
