package co.istad.sokkeang.ecommerce.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "orders")

public class Order {
    @Id         //because UUID we don't take identity yg yk UUID to Support
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String customerId;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private Float discount;

    @Column(nullable = false)
    private String remark;

    @Column(nullable = false)
    private Boolean status;

    @Column(nullable = false)
    private LocalDateTime orderedAt;

    @Column(nullable = false)
    private Boolean isDeleted;

    //bidirectional (with order_line)
    @OneToMany(mappedBy = "order")
    private List<OrderLine> orderLines;
}
