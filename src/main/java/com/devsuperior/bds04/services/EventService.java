package com.devsuperior.bds04.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.bds04.dto.EventDTO;
import com.devsuperior.bds04.entities.City;
import com.devsuperior.bds04.entities.Event;
import com.devsuperior.bds04.repositories.EventRepository;

@Service
public class EventService {

    @Autowired
    private EventRepository eventRepository;

    @Transactional(readOnly = true)
    public Page<EventDTO> findAll(Pageable pageable) {
        return eventRepository.findAll(pageable)
            .map(EventDTO::new);
    }

    @Transactional
    public EventDTO save(EventDTO dto) {
        Event entity = new Event();
        copyDtoToEntity(dto, entity);
        Event saved = eventRepository.save(entity);
        return new EventDTO(saved);
    }

    private static void copyDtoToEntity(EventDTO dto, Event entity) {
        entity.setName(dto.getName());
        entity.setUrl(dto.getUrl());
        entity.setDate(dto.getDate());
        entity.setCity(new City(dto.getCityId(), null));
    }
    
}
