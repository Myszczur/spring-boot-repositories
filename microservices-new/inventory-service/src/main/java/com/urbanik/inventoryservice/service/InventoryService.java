package com.urbanik.inventoryservice.service;

import com.urbanik.inventoryservice.model.dto.InventoryResponseDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface InventoryService {

    List<InventoryResponseDTO> isInStock(List<String> skuCode);
}
