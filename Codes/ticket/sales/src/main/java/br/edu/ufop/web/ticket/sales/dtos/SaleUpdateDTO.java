package br.edu.ufop.web.ticket.sales.dtos;

import java.util.UUID;

import br.edu.ufop.web.ticket.sales.enums.EnumSaleStatusType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SaleUpdateDTO {

    private UUID id;

    private EnumSaleStatusType saleStatus;

}
