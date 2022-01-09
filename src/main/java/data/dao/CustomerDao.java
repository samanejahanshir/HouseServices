package dao;

import config.HibernateUtil;
import data.Customer;
import data.Orders;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Component;

import javax.persistence.NoResultException;
import java.util.ArrayList;
import java.util.List;
@Component
public class CustomerDao {
    public void save(Customer customer) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(customer);
        transaction.commit();
        session.close();
    }

    public Customer getCustomerByEmailAndPass(String email, String password) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("from  Customer where email=:email and password=:password");
        query.setParameter("email", email);
        query.setParameter("password", password);
        Customer customer = null;
        try {
            customer = (Customer) query.getSingleResult();
            transaction.commit();
            session.close();
        } catch (NoResultException e) {
            e.printStackTrace();
        }
        return customer;
    }

    public int UpdatePassword(String email, String newPassword) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("update Customer set password=:password where email=:email ");
        query.setParameter("email", email);
        query.setParameter("password", newPassword);
        int id = query.executeUpdate();
        transaction.commit();
        session.close();
        return id;
    }

    public Customer getCustomerByEmail(String email) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("from  Customer where email=:email");
        query.setParameter("email", email);
        Customer customer = null;
        try {
            customer = (Customer) query.getSingleResult();
            customer.getAddresses();
            transaction.commit();
            session.close();
        } catch (NoResultException e) {
            e.printStackTrace();
        }
        return customer;
    }

    public void update(Customer customer) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.update(customer);
        transaction.commit();
        session.close();
    }

    public List<Orders> getListOrders(Customer customer) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("select o from  Customer c inner join c.orders o  where o.customer.id=:id");
        query.setParameter("id", customer.getId());
        Customer customer1 = null;
        List<Orders> orders = new ArrayList<>();
        try {
            orders = query.list();
            transaction.commit();
            session.close();
        } catch (NoResultException e) {
            e.printStackTrace();
        }
        return orders;
    }

    public int updateCredit(Customer customer,double amount){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("update Customer set credit=:amount where id=:id");
        query.setParameter("id", customer.getId());
        query.setParameter("amount",amount);
        int result=query.executeUpdate();
        transaction.commit();
        session.close();
        return  result;
    }
}
