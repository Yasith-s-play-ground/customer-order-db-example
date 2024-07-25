package lk.ijse.dep12.jpa.relationship.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "customer")
@ToString(exclude = {"contactList", "orderList"})
public class Customer implements Serializable {
    @Id
    private String id;
    private String name;
    private String address;
    @OneToMany(mappedBy = "customer", cascade = {CascadeType.PERSIST, CascadeType.DETACH})
    @Setter(AccessLevel.NONE)
    private List<Contact> contactList = new ArrayList<>();
    @OneToMany(mappedBy = "customer", cascade = {CascadeType.PERSIST, CascadeType.DETACH})
    @Setter(AccessLevel.NONE)
    private List<Order> orderList = new ArrayList<>();

    public Customer(String id, String name, String address) {
        this.id = id;
        this.name = name;
        this.address = address;
    }

    public Customer(String id, String name, String address, List<Contact> contactList) {
        if (contactList != null && !contactList.isEmpty()) {
            contactList.stream().filter(contact -> contact.getCustomer() == null).forEach(contact -> contact.setCustomer(this));
            contactList.forEach(contact -> {
                if (contact.getCustomer() != this)
                    throw new IllegalStateException("This contact is already associated with another customer %s"
                            .formatted(contact.getCustomer().getId()));
            });
        }
        this.id = id;
        this.name = name;
        this.address = address;
        this.contactList = contactList;
    }

//    public Customer(String id, String name, String address, List<Order> orderList) {
//        if (orderList != null && !orderList.isEmpty()) {
//            orderList.stream().filter(contact -> contact.getCustomer() == null).forEach(order -> order.setCustomer(this));
//            orderList.forEach(order -> {
//                if (order.getCustomer() != this)
//                    throw new IllegalStateException("This order is already associated with another customer %s"
//                            .formatted(order.getCustomer().getId()));
//            });
//        }
//        this.id = id;
//        this.name = name;
//        this.address = address;
//        this.orderList = orderList;
//    }
}
