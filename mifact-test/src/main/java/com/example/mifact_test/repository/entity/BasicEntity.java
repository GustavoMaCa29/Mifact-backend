package com.example.mifact_test.repository.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.Id;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@MappedSuperclass
public class BasicEntity {

    @Id
    @Column(name = "product_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "creation_date", updatable = false, nullable = false)
    private LocalDateTime creationDate = LocalDateTime.now();

    @Column(name = "is_deleted")
    private Boolean isDeleted = false;
}
