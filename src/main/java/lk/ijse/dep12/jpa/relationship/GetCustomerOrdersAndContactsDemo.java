package lk.ijse.dep12.jpa.relationship;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import lk.ijse.dep12.jpa.relationship.entity.Contact;
import lk.ijse.dep12.jpa.relationship.entity.Customer;
import lk.ijse.dep12.jpa.relationship.entity.Order;
import lk.ijse.dep12.jpa.relationship.util.JpaUtil;

import java.util.List;

public class GetCustomerOrdersAndContactsDemo {
    public static void main(String[] args) {
        try (EntityManagerFactory emf = JpaUtil.getEntityManagerFactory();
             EntityManager em = emf.createEntityManager()) {
            EntityTransaction tx = em.getTransaction();

            try {
                tx.begin();
                Customer c001 = em.find(Customer.class, "C001");
                System.out.printf("%s Customer's Contacts\n".formatted(c001.getId()));
                List<Contact> contactList = c001.getContactList();
                contactList.forEach(System.out::println);

                System.out.printf("%s Customer's Orders\n".formatted(c001.getId()));
                List<Order> orderList = c001.getOrderList();
                orderList.forEach(System.out::println);


                tx.commit();
            } catch (Throwable t) {
                tx.rollback();
                t.printStackTrace();
            }
        }
    }
}
