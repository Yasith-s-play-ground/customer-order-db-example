package lk.ijse.dep12.jpa.relationship;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import lk.ijse.dep12.jpa.relationship.entity.*;
import lk.ijse.dep12.jpa.relationship.util.JpaUtil;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

public class PlaceOrderDemoBiDirectional {
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

                Customer c002 = em.find(Customer.class, "C002");
//                Customer c003 = em.find(Customer.class, "C003");

                User kasun = em.find(User.class, "kasunsam");

                Order od002 = new Order("OD002", Date.valueOf(LocalDate.now()), kasun, c002);

                OrderDetail orderDetail1 = new OrderDetail(od002, i001, i001.getPrice(), 5);
                OrderDetail orderDetail2 = new OrderDetail(od002, i002, i002.getPrice(), 5);
                OrderDetail orderDetail3 = new OrderDetail(od002, i004, i004.getPrice(), 10);
                OrderDetail orderDetail4 = new OrderDetail(od002, i005, i005.getPrice(), 5);


                List.of(orderDetail1, orderDetail2, orderDetail3, orderDetail4).forEach(od002.getOrderDetailList()::add);

                em.persist(od002);

                tx.commit();
            } catch (Throwable t) {
                tx.rollback();
                t.printStackTrace();
            }
        }
    }
}
