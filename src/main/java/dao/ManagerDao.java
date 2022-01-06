package dao;

import config.HibernateUtil;
import model.Manager;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import javax.persistence.NoResultException;

public class ManagerDao {
    public void save(Manager manager) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(manager);
        transaction.commit();
        session.close();
    }

    public Manager get(String userName, String password) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("from Manager where userName=:userName and password=:password");
        query.setParameter("userName", userName);
        query.setParameter("password", password);
        Manager manager = null;
        try {
            manager = (Manager) query.getSingleResult();
        } catch (NoResultException e) {
            e.printStackTrace();
        }
        return manager;
    }
}
