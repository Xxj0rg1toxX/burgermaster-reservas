package com.BurguerMaster.app.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import java.util.List;
import java.util.ArrayList;

@Entity
@Table(name = "burger")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Burger extends Product {

    @OneToMany(mappedBy = "burger", cascade = CascadeType.ALL)
    private List<Combo> combos = new ArrayList<>();
}