package br.edu.ufop.web.ticket.sales.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import java.util.Optional;

import org.springframework.stereotype.Service;

import br.edu.ufop.web.ticket.sales.converter.SaleConverter;
import br.edu.ufop.web.ticket.sales.domain.SaleDomain;
import br.edu.ufop.web.ticket.sales.dtos.SalesCreateDTO;
import br.edu.ufop.web.ticket.sales.dtos.SalesDeleteDTO;
import br.edu.ufop.web.ticket.sales.dtos.SalesDTO;
import br.edu.ufop.web.ticket.sales.dtos.SaleUpdateDTO;
import br.edu.ufop.web.ticket.sales.enums.EnumSaleStatusType;
import br.edu.ufop.web.ticket.sales.model.EventModel;
import br.edu.ufop.web.ticket.sales.model.SaleModel;
import br.edu.ufop.web.ticket.sales.repositories.IEventRepository;
import br.edu.ufop.web.ticket.sales.repositories.ISalesRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class SaleService {

    private final ISalesRepository saleRepository;

    private final IEventRepository eventRepository;

    public List<SalesDTO> getAllSales() {
        
        List<SaleModel> saleModelList = saleRepository.findAll();

        return saleModelList
            .stream()
            .map(SaleConverter::toSaleDTO)
            .toList();

    }

    public SalesDTO getSaleById(String saleId) {
        
        UUID uuid = UUID.fromString(saleId);
        Optional<SaleModel> optionalSaleModel = saleRepository.findById(uuid);
        if (optionalSaleModel.isPresent()){
            SaleModel saleModel = optionalSaleModel.get();
            return SaleConverter.toSaleDTO(saleModel);
        }

        return null;
    }
    
    public SalesDTO createSale(SalesCreateDTO salesCreateDTO) {

        EventModel eventModel = eventRepository.findById(salesCreateDTO.getEventId())
            .orElseThrow(() -> new RuntimeException("Nenhum evento encontrado com o ID fornecido"));

        SaleDomain saleDomain = SaleConverter.toSaleDomain(salesCreateDTO);

        saleDomain.setEventModel(eventModel);
        saleDomain.setSaleDate(LocalDateTime.now());
        if (saleDomain.getSaleStatus() == null) {
            saleDomain.setSaleStatus(EnumSaleStatusType.EM_ABERTO);
        }

        SaleModel saleModel = SaleConverter.toSaleModel(saleDomain);
        
        return SaleConverter.toSaleDTO(saleRepository.save(saleModel));
    }

    public SalesDTO updateSale(SaleUpdateDTO saleUpdateDTO) {

        Optional<SaleModel> optionalSaleModel = saleRepository.findById(saleUpdateDTO.getId());

        if (!optionalSaleModel.isPresent()) {
            return null;
        }

        SaleModel saleModel = optionalSaleModel.get();

        saleModel.setSaleStatus(saleUpdateDTO.getSaleStatus());

        return SaleConverter.toSaleDTO(saleRepository.save(saleModel));
    }

    public void deleteSale(SalesDeleteDTO salesDeleteDTO) {
        
        Optional<SaleModel> optionalSaleModel = saleRepository.findById(salesDeleteDTO.id());
        
        if (optionalSaleModel.isEmpty()) {
            throw new RuntimeException("Venda não encontrada");
        }   

        saleRepository.delete(optionalSaleModel.get());
    }

}
