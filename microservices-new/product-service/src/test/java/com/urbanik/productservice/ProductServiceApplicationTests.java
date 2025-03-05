package com.urbanik.productservice;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.urbanik.productservice.model.dto.ProductRequestDTO;
import com.urbanik.productservice.repository.ProductRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.math.BigDecimal;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@Testcontainers
@AutoConfigureMockMvc
class ProductServiceApplicationTests {

    @Container
    static MySQLContainer<?> myContainer = new MySQLContainer<>("mysql:8.0.32");

    static {
        myContainer.start();
    }

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private ProductRepository productRepository;


    @DynamicPropertySource
    static void setProperties(DynamicPropertyRegistry dynamicPropertyRegistry) {

        dynamicPropertyRegistry.add("spring.datasource.url", myContainer::getJdbcUrl);
        dynamicPropertyRegistry.add("spring.datasource.username", myContainer::getUsername);
        dynamicPropertyRegistry.add("spring.datasource.password", myContainer::getPassword);
        dynamicPropertyRegistry.add("spring.datasource.driver-class-name", myContainer::getDriverClassName);
    }

    @Test
    void shouldCreateProduct() throws Exception {

        ProductRequestDTO productRequestDTO = getProductRequest();

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/product")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(productRequestDTO)))
                .andExpect(status().isCreated());

        Assertions.assertEquals(1, productRepository.findAll().size());
    }

    private ProductRequestDTO getProductRequest() {

        return ProductRequestDTO.builder()
                .name("test1")
                .description("test1")
                .price(BigDecimal.valueOf(199.99))
                .build();
    }
}
