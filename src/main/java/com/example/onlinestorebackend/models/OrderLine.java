package com.example.onlinestorebackend.models;

/**
 * @author Bahadir Tasli
 * @Date 3/30/2023
 */

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Data
@EqualsAndHashCode

public class OrderLine extends Auditable<String> implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue
    private Long id;
    @OneToOne(cascade = CascadeType.MERGE)
    private Product product;
    private float qtyOfProducts;

    private float productPrice;

    private boolean isActive;

    @OneToOne(cascade = CascadeType.MERGE)
    private Cart cart;

}
