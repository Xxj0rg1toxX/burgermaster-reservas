package com.BurguerMaster.app.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "combos")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Combo extends Product {

    @NotNull(message = "The  total price is obligatory")
    private long totalPrice;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "burger_id", nullable = false)
    @NotNull(message = "The burger is obligatory")
    private Burger burger;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "soda_id")
    @NotNull(message = "The soda is obligatory")
    private Soda soda;

    @PrePersist
    @PreUpdate
    private void calculateTotalPrice() {
        setTotalPrice(burger.getPrice() + soda.getPrice());
    }
}
