package com.example.mifact_test.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "products")
public class Product extends BasicEntity {
    @NotNull(message = "El nombre del producto no puede estar vacío.")
    @Column(name = "name", nullable = false)
    private String name;
    @NotNull(message = "La descripción del producto no puede estar vacía.")
    @Column(name = "description", nullable = false)
    private String description;
    @NotNull(message = "La cantidad no puede estar vacía.")
    @Column(name = "quantity", nullable = false)
    private Integer quantity;
    @NotNull(message = "El precio no puede estar vacío.")
    @Column(name = "price", nullable = false)
    private Double price;
}
