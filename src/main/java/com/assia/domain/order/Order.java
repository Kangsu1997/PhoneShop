package com.assia.domain.order;

import com.assia.model.order.OrderModel;
import lombok.Data;

import javax.persistence.*;
import java.math.BigInteger;

@Entity
@Data
@Table(name ="orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private BigInteger id;
    private String name;
    private String order_date;

    public OrderModel toOrder() {
        OrderModel rs = new OrderModel();
        rs.setId(id);
        rs.setName(name);
        rs.setOrder_date(order_date);
        return rs;
    }
}
