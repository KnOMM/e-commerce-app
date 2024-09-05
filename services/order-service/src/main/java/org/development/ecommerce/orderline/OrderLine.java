package org.development.ecommerce.orderline;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.*;
import org.development.ecommerce.order.Order;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
public class OrderLine {
    @Id
    private UUID id;
    @ManyToOne
    private Order order;
    private UUID productId;
    private long quantity;
}
