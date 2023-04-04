package com.example.onlinestorebackend.models;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * @author Bahadir Tasli
 * @Date 3/30/2023
 */
@Entity
@Data
@EqualsAndHashCode
public class OrderDetails extends Auditable<String> implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private Long id;

    @OneToOne(cascade = CascadeType.MERGE)
    private User user;

    @OneToMany(cascade = CascadeType.MERGE)
    private List<OrderLine> orderLines;

    private float totalPrice;

}
