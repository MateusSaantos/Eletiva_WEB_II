package br.edu.ufop.web.ticket.sales.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ufop.web.ticket.sales.dtos.events.CreateEventDTO;
import br.edu.ufop.web.ticket.sales.dtos.events.EventDTO;
import br.edu.ufop.web.ticket.sales.service.EventService;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/sales/events")
@AllArgsConstructor
public class EventController {

    public final EventService eventService;

    @GetMapping
    public ResponseEntity<List<EventDTO>> getAll() {
        return ResponseEntity.ok(eventService.getAll());
    }

    @PostMapping
    public ResponseEntity<EventDTO> create(@RequestBody CreateEventDTO createEventDTO) {
        EventDTO eventDTO = eventService.create(createEventDTO);
        return ResponseEntity.ok(eventDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EventDTO> getById(@PathVariable UUID id) {
        EventDTO eventDTO = eventService.getById(id);
        return ResponseEntity.ok(eventDTO);
    }

    @PutMapping
    public ResponseEntity<EventDTO> update(@RequestBody EventDTO eventDTO) {
        EventDTO updated = eventService.update(eventDTO);
        return ResponseEntity.ok(updated);
    }

    
}
