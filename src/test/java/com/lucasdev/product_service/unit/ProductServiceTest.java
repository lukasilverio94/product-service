package com.lucasdev.product_service.unit;

import com.lucasdev.product_service.model.Product;
import com.lucasdev.product_service.repository.ProductRepository;
import com.lucasdev.product_service.service.ProductService;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class ProductServiceTest {

    private final ProductRepository repository = mock(ProductRepository.class);
    private final ProductService service = new ProductService(repository);

    @Test
    void shouldReturnMappedProducts() {
        Product product = Product.builder()
                .id("1")
                .name("Phone")
                .description("desc")
                .price(BigDecimal.TEN)
                .build();

        when(repository.findAll()).thenReturn(List.of(product));

        var result = service.getAllProducts();

        assertThat(result).hasSize(1);
        assertThat(result.get(0).name()).isEqualTo("Phone");
    }

    @Test
    void shouldSaveProduct() {
        var request = new com.lucasdev.product_service.dto.ProductRequest(
                "Phone", "desc", BigDecimal.TEN
        );

        service.createProduct(request);

        verify(repository, times(1)).save(any(Product.class));
    }
}