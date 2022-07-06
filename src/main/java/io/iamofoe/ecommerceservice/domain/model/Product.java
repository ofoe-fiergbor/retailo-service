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
@Table(name = "Products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;
    private double price;
    private String imageUrl;
    private double quantity;
    private String description;
    private ProductVisibility productVisibility;
    @ManyToOne
    private User user;
    @ManyToOne
    private Category category;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "checkout_product_id")
    private CheckoutProduct checkoutProduct;
}
