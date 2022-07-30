package com.example.demo.Entiti;

import lombok.Data;

import javax.persistence.*;

@Data
@Table(name = "products")
@Entity
public class Product {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Integer id;
    @Column(name = "name")
    String name;
    @Column(name="price")
    int price;
    @Column(name = "description")
    String description;

}
