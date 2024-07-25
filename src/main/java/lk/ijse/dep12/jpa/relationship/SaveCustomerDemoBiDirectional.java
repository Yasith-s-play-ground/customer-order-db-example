package lk.ijse.dep12.jpa.relationship;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import lk.ijse.dep12.jpa.relationship.entity.Contact;
import lk.ijse.dep12.jpa.relationship.entity.Customer;
import lk.ijse.dep12.jpa.relationship.util.JpaUtil;

import java.util.List;

public class SaveCustomerDemoBiDirectional {
    public static void main(String[] args) {
        try (EntityManagerFactory emf = JpaUtil.getEntityManagerFactory();
             EntityManager em = emf.createEntityManager()) {
            EntityTransaction tx = em.getTransaction();

            try {
                tx.begin();
                Customer c004 = new Customer("C004", "Dasun Fernando", "Kandy");

                Contact contact4_1 = new Contact(c004, "081-1234567");
                Contact contact4_2 = new Contact(c004, "078-1234567");

                c004.getContactList().add(contact4_1);
                c004.getContactList().add(contact4_2);

                em.persist(c004);

                tx.commit();
            } catch (Throwable t) {
                tx.rollback();
                t.printStackTrace();
            }
        }
    }
}
