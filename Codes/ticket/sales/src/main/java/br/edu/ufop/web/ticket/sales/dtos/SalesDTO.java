package br.edu.ufop.web.ticket.sales.dtos;

import java.time.LocalDateTime;
import java.util.UUID;

import br.edu.ufop.web.ticket.sales.enums.EnumSaleStatusType;
import br.edu.ufop.web.ticket.sales.model.EventModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SalesDTO {

    private UUID id;

    private UUID userId;
    
    private LocalDateTime saleDate;

    private EnumSaleStatusType saleStatus;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private EventModel eventId;

}
