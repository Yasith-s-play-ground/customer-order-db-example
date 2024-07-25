package lk.ijse.dep12.jpa.relationship;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import lk.ijse.dep12.jpa.relationship.entity.Contact;
import lk.ijse.dep12.jpa.relationship.entity.Customer;
import lk.ijse.dep12.jpa.relationship.entity.Order;
import lk.ijse.dep12.jpa.relationship.entity.User;
import lk.ijse.dep12.jpa.relationship.util.JpaUtil;

import java.util.List;

public class GetUserOrdersDemo {
    public static void main(String[] args) {
        try (EntityManagerFactory emf = JpaUtil.getEntityManagerFactory();
             EntityManager em = emf.createEntityManager()) {
            EntityTransaction tx = em.getTransaction();

            try {
                tx.begin();
                User kasun = em.find(User.class, "kasunsam");
                System.out.printf("Orders placed by User %s\n".formatted(kasun.getUsername()));
                List<Order> orderList = kasun.getOrderList();
                orderList.forEach(System.out::println);


                tx.commit();
            } catch (Throwable t) {
                tx.rollback();
                t.printStackTrace();
            }
        }
    }
}
