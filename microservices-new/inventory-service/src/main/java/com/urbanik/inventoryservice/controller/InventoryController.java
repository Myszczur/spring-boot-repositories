package com.urbanik.inventoryservice.controller;

import com.urbanik.inventoryservice.model.dto.InventoryResponseDTO;
import com.urbanik.inventoryservice.service.InventoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author kurbanik
 */

@Log4j2
@RestController
@CrossOrigin("*")
@RequiredArgsConstructor
@RequestMapping("/api/v1/inventory")
public class InventoryController {

    private final InventoryService inventoryService;


    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<InventoryResponseDTO> isInStock(@RequestParam List<String> skuCode) {

        return inventoryService.isInStock(skuCode);
    }
}
