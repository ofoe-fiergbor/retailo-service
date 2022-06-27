package io.iamofoe.ecommerceservice.domain.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "SellerRequests")
@Accessors(chain = true)
public class SellerRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long userId;
    private RequestStatus status;
    private String comment;
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;
}
