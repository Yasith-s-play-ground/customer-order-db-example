package lk.ijse.dep12.jpa.relationship;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import lk.ijse.dep12.jpa.relationship.entity.*;
import lk.ijse.dep12.jpa.relationship.util.JpaUtil;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

public class PlaceOrderDemo {
    public static void main(String[] args) {
        try (EntityManagerFactory emf = JpaUtil.getEntityManagerFactory();
             EntityManager em = emf.createEntityManager()) {
            EntityTransaction tx = em.getTransaction();

            try {
                tx.begin();

                Item i001 = em.find(Item.class, "I001");
                Item i002 = em.find(Item.class, "I002");
                Item i003 = em.find(Item.class, "I003");
                Item i004 = em.find(Item.class, "I004");
                Item i005 = em.find(Item.class, "I005");

                Customer c001 = em.find(Customer.class, "C001");
//                Customer c002 = em.find(Customer.class, "C002");
//                Customer c003 = em.find(Customer.class, "C003");

                User u001 = em.find(User.class, "yasithperera");

                Order od001 = new Order("OD001", Date.valueOf(LocalDate.now()), u001, c001);

                OrderDetail orderDetail1 = new OrderDetail(od001, i001, i001.getPrice(), 5);
                OrderDetail orderDetail2 = new OrderDetail(od001, i002, i002.getPrice(), 10);
                OrderDetail orderDetail3 = new OrderDetail(od001, i003, i003.getPrice(), 10);
                OrderDetail orderDetail4 = new OrderDetail(od001, i005, i005.getPrice(), 5);


                List.of(orderDetail1, orderDetail2, orderDetail3, orderDetail4).forEach(em::persist);

                tx.commit();
            } catch (Throwable t) {
                tx.rollback();
                t.printStackTrace();
            }
        }
    }
}
