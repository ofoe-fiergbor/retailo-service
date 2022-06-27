package io.iamofoe.ecommerceservice.domain.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "Users")
@Accessors(chain = true)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Role role;
    private String firstName;
    private String lastName;
    private String password;
    @Column(unique = true)
    private String email;
    private String address;
    private String phone;
    private String pictureUrl;
    @OneToMany(mappedBy="user")
    private List<Product> products;
}
