package br.edu.ufop.web.ticket.sales.repositories;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import br.edu.ufop.web.ticket.sales.enums.EnumSaleStatusType;
import br.edu.ufop.web.ticket.sales.model.EventModel;
import br.edu.ufop.web.ticket.sales.model.SaleModel;


public interface ISalesRepository extends JpaRepository<SaleModel, UUID> {

    List<SaleModel> findBySaleStatus(EnumSaleStatusType saleStatus);
    List<SaleModel> findByEventModel(EventModel event);
    List<SaleModel> findByUserId(UUID user);
    List<SaleModel> findBySaleDate(LocalDateTime saleDate);

}
