package io.iamofoe.ecommerceservice.domain.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Accessors(chain = true)
@Table(name = "Checkout_product")
public class CheckoutProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @OneToOne(mappedBy = "checkoutProduct")
    private Product product;
    private double quantity;
    private double price;
    @ManyToOne
    private CheckoutTransaction checkoutTransaction;
}
