package lk.ijse.dep12.jpa.relationship;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import lk.ijse.dep12.jpa.relationship.entity.Item;
import lk.ijse.dep12.jpa.relationship.entity.User;
import lk.ijse.dep12.jpa.relationship.util.JpaUtil;

import java.math.BigDecimal;
import java.util.List;

public class SaveItemDemo {
    public static void main(String[] args) {
        try (EntityManagerFactory emf = JpaUtil.getEntityManagerFactory();
             EntityManager em = emf.createEntityManager()) {
            EntityTransaction tx = em.getTransaction();

            try {
                tx.begin();

                Item i001 = new Item("I001", "Mouse", 2000, new BigDecimal("1500"));
                Item i002 = new Item("I002", "Keyboard", 500, new BigDecimal("1800"));
                Item i003 = new Item("I003", "Monitor", 200, new BigDecimal("10000"));
                Item i004 = new Item("I004", "Head Phone", 300, new BigDecimal("5000"));
                Item i005 = new Item("I005", "Laptop", 100, new BigDecimal("75000"));

                List.of(i001, i002, i003, i004, i005).forEach(em::persist);

                tx.commit();
            } catch (Throwable t) {
                tx.rollback();
                t.printStackTrace();
            }
        }
    }
}
