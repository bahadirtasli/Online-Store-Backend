package com.example.onlinestorebackend.models;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author Bahadir Tasli
 * @Date 3/21/2023
 */
@Entity
@Data
@Table(name = "users")
@EqualsAndHashCode(callSuper = true)
public class User extends Auditable<String> implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String email;
    private String password;

    private String city;
    private String address;

    private String logoType;


    //do we need roleId?
    private int roleId;

    @OneToOne(cascade = CascadeType.MERGE)
    private Authority authority;
    private boolean isActive;

}
