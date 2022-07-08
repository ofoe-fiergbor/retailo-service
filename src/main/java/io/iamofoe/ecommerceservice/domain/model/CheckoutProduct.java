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
@Table(name = "Checkout_products")
public class CheckoutProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private double price;
    private double quantity;
    @OneToOne
    private Product product;
    @ManyToOne
    private CheckoutTransaction transactions;
}
