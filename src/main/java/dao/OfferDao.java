package dao;

import config.HibernateUtil;
import model.Offer;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

public class OfferDao {
    public void save(Offer offer) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(offer);
        transaction.commit();
        session.close();
    }

    public void deleteOffer(int orderId) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("delete from Offer o  where o.orders.id=:id");
        query.setParameter("id", orderId);
        transaction.commit();
        session.close();
    }
}
