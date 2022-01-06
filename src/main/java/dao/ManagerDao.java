package dao;

import config.HibernateUtil;
import model.Manager;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class ManagerDao {
    public void save(Manager manager) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(manager);
        transaction.commit();
        session.close();
    }
}
