package dao;

import config.HibernateUtil;
import model.Services;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class ServicesDao {
    public void save(Services services) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(services);
        transaction.commit();
        session.close();
    }
}
