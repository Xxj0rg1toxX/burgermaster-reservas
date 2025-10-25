package com.BurguerMaster.app.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Table(name = "reserves", indexes = {
        @Index(name = "idx_reserves_qr", columnList = "qr_reserve")
})
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Reserve {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @NotNull(message = "The date and hour are obligatory")
    @Column(nullable = false)
    private LocalDateTime dateTime;

    @Min(value = 1, message = "The people number must be at least one")
    @Column(nullable = false)
    private int numPeople;

    @Column(unique = true, name = "qr_reserve")
    private String qrReserve;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "status_reserve", length = 50, nullable = false)
    private StatusReserve status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id", nullable = false)
    @NotNull(message = "The client is obligatory")
    private Client client;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "table_id", nullable = false)
    @NotNull(message = "The table is obligatory")
    private RestaurantTables restaurantTable;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "order_id")
    private Order order;

    @PrePersist
    private void setDefaultStatus() {
        if (status == null) {
            status = StatusReserve.PENDING;
        }
    }
}
