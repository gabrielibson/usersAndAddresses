package com.orange.talents.users.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Builder
@AllArgsConstructor
public class Address {
    public Address() {}
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String street;
    private Integer number;
    private String complement;
    private String neighborhood;
    private String city;
    private String state;
    private String cep;
    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonBackReference
    @Setter
    private User user;
}
