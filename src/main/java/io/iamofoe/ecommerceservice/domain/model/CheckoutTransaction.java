package io.iamofoe.ecommerceservice.domain.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "Checkout_transactions")
@Accessors(chain = true)
public class CheckoutTransaction {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @OneToMany(mappedBy = "transactions")
    private List<CheckoutProduct> products;
    private double amount;
    @ManyToOne
    private User user;
    private Date created;
}
