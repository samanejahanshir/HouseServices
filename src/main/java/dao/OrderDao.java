package dao;

import config.HibernateUtil;
import model.Expert;
import model.Offer;
import model.Orders;
import model.enums.OrderState;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import javax.persistence.NoResultException;
import java.util.ArrayList;
import java.util.List;

public class OrderDao {
    public void save(Orders order) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(order);
        transaction.commit();
        session.close();
    }

    public void update(Orders order) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.update(order);
        transaction.commit();
        session.close();
    }

    public List<Orders> getListOrders(Expert expert) {
        Session session = HibernateUtil.getSessionFactory().openSession();
       // List<Orders> orders = new ArrayList<>();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("select o from Orders o inner  join  o.subServices e inner  join e.experts expert where expert.id=:id and o.state=:state");
        query.setParameter("id", expert.getId());
        query.setParameter("state", OrderState.WAIT_OFFER_EXPERTS);
        List<Orders> list = query.list();
        System.out.println(list.size());
        transaction.commit();
        session.close();
        return list;
    }

    public void addOfferToOrder(Orders orders, Offer offer) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("from  Orders where id=:id");
        query.setParameter("id", orders.getId());
        try {
            Orders orders1 = (Orders) query.getSingleResult();
            orders1.getOffers().add(offer);
            session.update(orders1);
        } catch (NoResultException e) {
            e.printStackTrace();
        }
        transaction.commit();
        session.close();
    }

    public List<Offer> getListOffers(Orders orders) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("select offer from  Orders o inner  join Offer offer where o.id=offer.orders.id");
       // query.setParameter("id", orders.getId());
       // Orders orders1 = null;
        List<Offer> offers = new ArrayList<>();
        try {
            offers=query.list();
        } catch (NoResultException e) {
            e.printStackTrace();
        }
        transaction.commit();
        session.close();
        return offers;
    }


}
