package dao;

import config.HibernateUtil;
import model.Offer;
import model.Orders;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class OfferDao {
    public void save(Offer offer){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(offer);
        transaction.commit();
        session.close();
    }
}
