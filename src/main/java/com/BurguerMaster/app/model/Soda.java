package com.BurguerMaster.app.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "soda")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Soda extends Product {

    @Min(value = 0, message = "Stock cannot be negative")
    private int stockAvailable;

    private String size = "Personal";

    @OneToMany(mappedBy = "soda", cascade = CascadeType.ALL)
    private List<Combo> combos = new ArrayList<>();

    public void decreaseStock(int lot){
        if (stockAvailable< lot) {
            throw new IllegalArgumentException("Insufficient stock");
        }
        this.stockAvailable -= lot;
    }
}
