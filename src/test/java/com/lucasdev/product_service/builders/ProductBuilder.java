package com.lucasdev.product_service.builders;

import com.lucasdev.product_service.model.Product;

import java.math.BigDecimal;

public class ProductBuilder {

    private String id = null;
    private String name = "Default Product";
    private String description = "Default description";
    private BigDecimal price = BigDecimal.valueOf(100);

    public static ProductBuilder aProduct() {
        return new ProductBuilder();
    }

    public ProductBuilder withId(String id) {
        this.id = id;
        return this;
    }

    public ProductBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public ProductBuilder withDescription(String description) {
        this.description = description;
        return this;
    }

    public ProductBuilder withPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public Product build() {
        return Product.builder()
                .id(id)
                .name(name)
                .description(description)
                .price(price)
                .build();
    }
}
