package com.urbanik.inventoryservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//@EnableE
@SpringBootApplication
public class InventoryServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(InventoryServiceApplication.class, args);
    }

//    @Bean
//    public CommandLineRunner loadData(InventoryRepository inventory) {
//        return _ -> {
//
//            Inventory inventory1 = new Inventory();
//            inventory1.setSkuCode("test12");
//            inventory1.setQuantity(100);
//            inventory.save(inventory1);
//            inventory.save(Inventory.builder()
//                    .skuCode("test13")
//                    .quantity(67)
//                    .build());
//            inventory.save(Inventory.builder()
//                    .skuCode("test14")
//                    .quantity(22)
//                    .build());
//            inventory.save(Inventory.builder()
//                    .skuCode("test15")
//                    .quantity(0)
//                    .build());
//        };
//    }
}
