package org.ecomm.foundation.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sales.orderitem_id_seq")
    @SequenceGenerator(name = "sales.orderitem_id_seq", sequenceName = "sales.orderitem_id_seq",
      initialValue = 10,
      allocationSize = 1)
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
    @JoinColumn(name = "order_id", referencedColumnName = "id")
    @JsonIgnore
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id", referencedColumnName = "id")
    private Item item;

}
