package br.edu.ufop.web.ticket.sales.converter;


import br.edu.ufop.web.ticket.sales.domain.SaleDomain;
import br.edu.ufop.web.ticket.sales.dtos.SalesCreateDTO;
import br.edu.ufop.web.ticket.sales.dtos.SalesDTO;
import br.edu.ufop.web.ticket.sales.dtos.SaleUpdateDTO;
import br.edu.ufop.web.ticket.sales.model.SaleModel;

public class SaleConverter {

    public static SalesDTO toSaleDTO(SaleModel saleModel) {
        return new SalesDTO(
            saleModel.getId(),
            saleModel.getUserId(),
            saleModel.getSaleDate(),
            saleModel.getSaleStatus(),
            saleModel.getCreatedAt(),
            saleModel.getUpdatedAt(),
            saleModel.getEventModel()
        );
    }

    public static SaleModel toSaleModel(SaleDomain saleDomain){
        return SaleModel.builder()
        .id(saleDomain.getId())
        .userId(saleDomain.getUserId())
        .saleDate(saleDomain.getSaleDate())
        .saleStatus(saleDomain.getSaleStatus())
        .createdAt(saleDomain.getCreatedAt())
        .updatedAt(saleDomain.getUpdatedAt())
        .eventModel(saleDomain.getEventModel())
        .build();

    }

    public static SaleDomain toSaleDomain(SalesCreateDTO createSaleDTO) {
        return SaleDomain.builder()
        .userId(createSaleDTO.getUserId())
        .saleStatus(createSaleDTO.getSaleStatus())
        .build();

    }

    public static SaleDomain toSaleDomain(SaleUpdateDTO updateSaleDTO) {
        return SaleDomain.builder()
            .id(updateSaleDTO.getId())
            .saleStatus(updateSaleDTO.getSaleStatus())
            .build();
    }
}
