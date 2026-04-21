package com.lucasdev.product_service.integration;

import com.lucasdev.product_service.builders.ProductBuilder;
import com.lucasdev.product_service.builders.ProductRequestBuilder;
import com.lucasdev.product_service.config.AbstractIntegrationTest;
import com.lucasdev.product_service.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import tools.jackson.databind.ObjectMapper;

import java.math.BigDecimal;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
class ProductControllerIT extends AbstractIntegrationTest {

    // Mock MVC Object
    @Autowired
    private MockMvc mockMvc;

    // Object mapper to transform the ProductRequest to String
    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private ProductRepository productRepository;

    // setup
    @BeforeEach
    void setup() {
        productRepository.deleteAll();
    }

    @Test
    void shouldCreateProduct() throws Exception {
        var request = ProductRequestBuilder.aProductRequest().build();

        mockMvc.perform(post("/api/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated());

        assert productRepository.findAll().size() == 1;
    }

    @Test
    void shouldReturnAllProducts() throws Exception {
        var product = ProductBuilder.aProduct()
                .withName("Motorola G10")
                .withPrice(BigDecimal.valueOf(999))
                .build();

        productRepository.save(product);

        mockMvc.perform(get("/api/products"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].name").value("Motorola G10"));
    }

    @Test
    void shouldReturnEmptyList() throws Exception {
        mockMvc.perform(get("/api/products"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isEmpty());
    }
}
