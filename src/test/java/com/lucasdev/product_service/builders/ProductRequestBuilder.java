package com.lucasdev.product_service.builders;

import com.lucasdev.product_service.dto.ProductRequest;

import java.math.BigDecimal;

public class ProductRequestBuilder {

    private String name = "Default Product";
    private String description = "Default Description";
    private BigDecimal price = BigDecimal.valueOf(100);

    public static ProductRequestBuilder aProductRequest() {
        return new ProductRequestBuilder();
    }

    public ProductRequestBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public ProductRequestBuilder withDescription(String description) {
        this.description = description;
        return this;
    }

    public ProductRequestBuilder withPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public ProductRequest build() {
        return ProductRequest.builder()
                .name(name)
                .description(description)
                .price(price)
                .build();
    }
}
