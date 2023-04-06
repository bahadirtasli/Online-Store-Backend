package com.example.onlinestorebackend.models;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * @author Bahadir Tasli
 * @Date 3/23/2023
 */

@Entity
@Data
@EqualsAndHashCode
public class Cart extends Auditable<String> implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private Long id;
    @OneToMany(cascade = CascadeType.MERGE)
    private List<OrderLine> orderLine;
    private float totalCost;
    private boolean isActive;

    //cart created at signup




}
