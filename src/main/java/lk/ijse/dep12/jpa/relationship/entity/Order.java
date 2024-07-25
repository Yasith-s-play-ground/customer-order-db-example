package lk.ijse.dep12.jpa.relationship.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "`order`")
@ToString(exclude = "orderDetailList")
public class Order implements Serializable {
    @Id
    private String id;
    private Date date;
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.DETACH})
    @JoinColumn(name = "username", referencedColumnName = "username")
    private User user;
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.DETACH})
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    private Customer customer;
    /* to make relationship bi directional */
    @OneToMany(mappedBy = "order", cascade = {CascadeType.PERSIST, CascadeType.DETACH}) /* order attribute in OrderDetail Class maps this */
    @Setter(AccessLevel.NONE) /* remove setter */
    private List<OrderDetail> orderDetailList = new ArrayList<>();

    public Order(String id, Date date, User user, Customer customer) {
        this.id = id;
        this.date = date;
        this.user = user;
        this.customer = customer;
    }

    public Order(String id, Date date, User user, Customer customer, List<OrderDetail> orderDetailList) {
        /*setting this as the order for details which have not been assigned an order*/
        if (orderDetailList != null && !orderDetailList.isEmpty()) {
            orderDetailList.stream().filter(od -> od.getOrder() == null).forEach(od -> od.setOrder(this));
            orderDetailList.forEach(od -> {
                if (od.getOrder() != this)
                    throw new IllegalStateException("This order detail is already associated with another order %s"
                            .formatted(od.getOrder().getId()));
            });
        }
        this.id = id;
        this.date = date;
        this.user = user;
        this.customer = customer;
        this.orderDetailList = orderDetailList;
    }
}
