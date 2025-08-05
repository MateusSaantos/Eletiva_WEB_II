package br.edu.ufop.web.ticket.sales.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ufop.web.ticket.sales.dtos.SaleUpdateDTO;
import br.edu.ufop.web.ticket.sales.dtos.SalesCreateDTO;
import br.edu.ufop.web.ticket.sales.dtos.SalesDTO;
import br.edu.ufop.web.ticket.sales.dtos.SalesDeleteDTO;
import br.edu.ufop.web.ticket.sales.dtos.externals.UserDTO;
import br.edu.ufop.web.ticket.sales.service.SaleService;
import br.edu.ufop.web.ticket.sales.service.clients.UserServiceClient;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/sales")
@AllArgsConstructor
public class SalesController {

    private final SaleService SaleService;
    private final UserServiceClient userServiceClient;

    @GetMapping("/status")
    public ResponseEntity<String> status() {
        return ResponseEntity.ok("Sales service is running.");
    }

    @GetMapping("/users")
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        return ResponseEntity.ok(
            userServiceClient.getAllUsers()
        );
    }

    @GetMapping
    public ResponseEntity<List<SalesDTO>> getAllSales() {

        List<SalesDTO> salesList = SaleService.getAllSales();

        return ResponseEntity.ok(salesList);
    }

    @PostMapping
    public ResponseEntity<SalesDTO> createSale(@RequestBody SalesCreateDTO salesCreateDTO) {

        SalesDTO salesDTO = SaleService.createSale(salesCreateDTO);
        return ResponseEntity.ok(salesDTO);
    }

    @GetMapping("/{saleId}")
    public ResponseEntity<SalesDTO> getSaleById(@PathVariable(value = "saleId") String saleId) {

        SalesDTO saleDTO = SaleService.getSaleById(saleId);

        if (saleDTO == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(saleDTO);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<SalesDTO>> getSalesByUserId(@PathVariable(value = "userId") String userId) {

        List<SalesDTO> salesList = SaleService.getAllSales()
            .stream()
            .filter(sale -> sale.getUserId().toString().equals(userId))
            .toList();

        return ResponseEntity.ok(salesList);
    }

    @PutMapping("/saleStatus")
    public ResponseEntity<SalesDTO> updateSaleStatus(@RequestBody SaleUpdateDTO saleUpdateDTO) {

        SalesDTO saleDTO = SaleService.updateSale(saleUpdateDTO);

        if (saleDTO == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(saleDTO);
    }

    @DeleteMapping("/remove")
    public ResponseEntity<Object> deleteSale(@RequestBody SalesDeleteDTO salesDeleteDTO) {

        SaleService.deleteSale(salesDeleteDTO);

        return ResponseEntity.ok("Venda excluida com sucesso.");
    }
    
}
