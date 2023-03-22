package com.example.onlinestorebackend.models;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * @author Bahadir Tasli
 * @Date 3/21/2023
 */
@Entity
@Data
@EqualsAndHashCode(callSuper = true)
public class OrderLine extends Auditable<String> implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private long id;

    @OneToMany(cascade = CascadeType.MERGE)
    private List<Product> products;

    private String qtyOfProducts;

    private String productPrice;

}
