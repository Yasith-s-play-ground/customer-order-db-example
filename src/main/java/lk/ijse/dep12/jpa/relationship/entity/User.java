package lk.ijse.dep12.jpa.relationship.entity;

import jakarta.persistence.*;
import lombok.*;
import org.checkerframework.checker.units.qual.N;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "`user`")
@ToString(exclude = "orderList")
public class User implements Serializable {
    @Id
    private String username;
    private String name;
    private String password;
    /* bi directional */
    @OneToMany(mappedBy = "user", cascade = {CascadeType.PERSIST, CascadeType.DETACH})
    @Setter(AccessLevel.NONE)
    private List<Order> orderList = new ArrayList<>();

    public User(String username, String name, String password) {
        this.username = username;
        this.name = name;
        this.password = password;
    }

    public User(String username, String name, String password, List<Order> orderList) {
        if (orderList != null && !orderList.isEmpty()) {
            orderList.stream().filter(order -> order.getCustomer() == null).forEach(order -> order.setUser(this));
            orderList.forEach(order -> {
                if (order.getUser() != this)
                    throw new IllegalStateException("This order is already associated with another user %s"
                            .formatted(order.getUser().getUsername()));
            });
        }
        this.username = username;
        this.name = name;
        this.password = password;
        this.orderList = orderList;
    }
}
