package lk.ijse.dep12.jpa.relationship;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import lk.ijse.dep12.jpa.relationship.entity.Contact;
import lk.ijse.dep12.jpa.relationship.entity.Customer;
import lk.ijse.dep12.jpa.relationship.util.JpaUtil;

import java.util.List;

public class SaveCustomerDemo {
    public static void main(String[] args) {
        try (EntityManagerFactory emf = JpaUtil.getEntityManagerFactory();
             EntityManager em = emf.createEntityManager()) {
            EntityTransaction tx = em.getTransaction();

            try {
                tx.begin();
                Customer c001 = new Customer("C001", "Kasun Sampath", "Galle");
                Customer c002 = new Customer("C002", "Nuwan Ramindu", "Matara");
                Customer c003 = new Customer("C003", "Sahan Fernando", "Colombo");

                Contact contact1_1 = new Contact(c001, "071-1234567");
                Contact contact1_2 = new Contact(c001, "011-1234567");
                Contact contact2_1 = new Contact(c002, "033-1234567");
                Contact contact3_1 = new Contact(c003, "055-1234567");

                List.of(contact1_1, contact1_2, contact2_1, contact3_1).forEach(em::persist);


                tx.commit();
            } catch (Throwable t) {
                tx.rollback();
                t.printStackTrace();
            }
        }
    }
}
