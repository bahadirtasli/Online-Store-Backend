package com.example.onlinestorebackend.models;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author Bahadir Tasli
 * @Date 3/21/2023
 */
@Entity
@Data
@EqualsAndHashCode(callSuper = true)
public class Order  extends Auditable<String> implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String userName;

    private String totalCost;

    private String deliveryAddress;

    private String userAddress;

    private Date dateOfOrder;

    @OneToMany(cascade = CascadeType.ALL)
    List<OrderLine>orderLines;

    //CUSTOMER??

    @Enumerated(EnumType.STRING)
    private Status status;







}
