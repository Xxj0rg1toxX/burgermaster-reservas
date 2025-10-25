package com.BurguerMaster.app.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "client", indexes = {
        @Index(name = "idx_client_email", columnList = "email")
})
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @NotBlank(message = "The name is obligatory")
    @NotBlank(message = "The name is obligatory")
    @Column(nullable = false)
    private String name;

    @Email(message = "The email must be in a valid format.")
    @NotBlank(message = "The email is obligatory")
    @Column(unique = true, nullable = false)
    private String email;

    @NotBlank(message = "The phone is obligatory")
    @Column(nullable = false)
    private String phone;

    @OneToMany(mappedBy = "client", fetch = FetchType.LAZY)
    private List<Reserve> reserves = new ArrayList<>();
}