package br.edu.ufop.web.ticket.sales.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import br.edu.ufop.web.ticket.sales.converter.EventConverter;
import br.edu.ufop.web.ticket.sales.domain.EventDomain;
import br.edu.ufop.web.ticket.sales.domain.usecases.events.CreateEventUseCase;
import br.edu.ufop.web.ticket.sales.dtos.events.CreateEventDTO;
import br.edu.ufop.web.ticket.sales.dtos.events.EventDTO;
import br.edu.ufop.web.ticket.sales.model.EventModel;
import br.edu.ufop.web.ticket.sales.repositories.IEventRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class EventService {
    
    private final IEventRepository eventRepository;

    public List<EventDTO> getAll() {
        return eventRepository.findAll().stream().map(EventConverter::toEventDTO).toList();
    }

    public EventDTO create(CreateEventDTO createEventDTO) {

        EventDomain eventDomain = EventConverter.toEventDomain(createEventDTO);
        CreateEventUseCase createEventUseCase = new CreateEventUseCase(eventDomain);
        createEventUseCase.validate();

        EventModel eventModel = EventConverter.toEventModel(eventDomain);

        return EventConverter.toEventDTO( eventRepository.save(eventModel) );

    }

    public EventDTO getById(UUID id) {
        EventModel event = eventRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Evento não encontrado"));

        return EventDTO.fromModel(event); 
    }

    public EventDTO update(EventDTO dto) {
        EventModel existing = eventRepository.findById(dto.getId())
            .orElseThrow(() -> new RuntimeException("Evento não encontrado"));

        existing.setDescription(dto.getDescription());
        existing.setType(dto.getType());
        existing.setDate(dto.getDate());
        existing.setStartSales(dto.getStartSales());
        existing.setEndSales(dto.getEndSales());
        existing.setPrice(dto.getPrice());
        existing.setUpdatedAt(LocalDateTime.now());

        EventModel saved = eventRepository.save(existing);
        return EventDTO.fromModel(saved);
    }

}
