package org.ecomm.foundation.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor
@Getter @Setter
@EqualsAndHashCode
@Entity
@Table(name = "OrderItem", schema = "sales")
public class OrderItem {

    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "item_qty")
    private Integer quantity;

    @Column(name = "shipping_ref", nullable = true)
    private String shippingRef;

    @Column(name = "item_status")
    private String status;

    @Column
    private String notes;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "shipping_address", referencedColumnName = "id")
    private Address address;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", referencedColumnName = "id")
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id", referencedColumnName = "id")
    private Item item;

}
