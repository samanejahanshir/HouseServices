package dao;

import config.HibernateUtil;
import model.Orders;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class OrderDao {
    public void save(Orders order) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(order);
        transaction.commit();
        session.close();
    }
}
