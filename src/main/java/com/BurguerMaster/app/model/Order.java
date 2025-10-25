package com.BurguerMaster.app.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @NotNull(message = "The order date is obligatory")
    @Column(nullable = false)
    private LocalDateTime date;

    private long total;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "status_order", length = 50, nullable = false)
    private StatusOrder estado;

    @OneToOne(mappedBy = "order")
    private Reserve reserve;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<ItemOrder> items = new ArrayList<>();

    private void calcularTotal() {
        this.total = items.stream()
                .mapToLong(ItemOrder::getSubtotal)
                .sum();
    }

    @PrePersist
    private void prePersist() {
        if (estado == null) {
            estado = StatusOrder.PENDING;
        }
        calcularTotal();
    }

    @PreUpdate
    private void preUpdate() {
        calcularTotal();
    }


}
