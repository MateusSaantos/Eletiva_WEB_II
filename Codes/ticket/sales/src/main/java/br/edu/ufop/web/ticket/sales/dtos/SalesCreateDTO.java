package br.edu.ufop.web.ticket.sales.dtos;

import java.util.UUID;

import br.edu.ufop.web.ticket.sales.enums.EnumSaleStatusType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SalesCreateDTO {

    private UUID userId;

    private UUID eventId;

    private EnumSaleStatusType saleStatus;

}
