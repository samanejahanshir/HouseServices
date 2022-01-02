package dao;

import config.HibernateUtil;
import model.Customer;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

public class CustomerDao {
    public void save(Customer customer) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(customer);
        transaction.commit();
        session.close();
    }

    public Customer getCustomerByEmail(String email, String password) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("from  Customer where email=:email and password=:password");
        query.setParameter("email", email);
        query.setParameter("password", password);
        Customer customer = (Customer) query.getSingleResult();
        transaction.commit();
        session.close();
        return customer;
    }

    public int UpdatePassword(String email,String newPassword){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction=session.beginTransaction();
        Query query = session.createQuery("update Customer set password=:password where email=:email ");
        query.setParameter("email", email);
        query.setParameter("password", newPassword);
        int id= query.executeUpdate();
        transaction.commit();
        session.close();
        return  id;
    }
}
