package dao;

import config.HibernateUtil;
import data.Expert;
import data.Offer;
import data.Orders;
import data.enums.OrderState;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Component;

import javax.persistence.NoResultException;
import java.util.ArrayList;
import java.util.List;
@Component
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
            orders = (Orders) query.getSingleResult();
            orders.getOffers().add(offer);
            session.update(orders);
        } catch (NoResultException e) {
            e.printStackTrace();
        }
        transaction.commit();
        session.close();
    }

    public List<Offer> getListOffers(Orders orders) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("select o from Offer o inner join o.expert e inner  join e.services s where o.orders.id=:id");
        query.setParameter("id", orders.getId());
        List<Offer> offers = new ArrayList<>();
        try {
            offers = query.list();
        } catch (NoResultException e) {
            e.printStackTrace();
        }
        transaction.commit();
        session.close();
        return offers;
    }

    public Orders getOrderById(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("from Orders where id=:id");
        query.setParameter("id", id);
        Orders orders = null;
        try {
            orders = (Orders) query.getSingleResult();
        } catch (NoResultException e) {
            e.printStackTrace();
        }
        transaction.commit();
        session.close();
        return orders;
    }

    public List<Orders> updateOrderStateToComeExpert(Expert expert) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("from Orders o where o.expert.id=:id and o.state=:state");
        query.setParameter("id", expert.getId());
        query.setParameter("state", OrderState.WAIT_SELECT_EXPERT);
        List<Orders> orders = query.list();
        transaction.commit();
        session.close();
        return orders;
    }

    public int updateOrderState(int idOrder,OrderState state) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("update Orders o set o.state=:state where o.id=:id");
        query.setParameter("id", idOrder);
        query.setParameter("state",state);
        int result=query.executeUpdate();
        transaction.commit();
        session.close();
        return result;
    }

    public int updateOrderComment(int orderId,String comment){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("update Orders o set o.Comment=:comment where o.id=:id");
        query.setParameter("id", orderId);
        query.setParameter("comment",comment);
        int result=query.executeUpdate();
        transaction.commit();
        session.close();
        return result;
    }

    public void deleteOrder(int orderId){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("delete from Offer o  where o.orders.id=:id");
        query.setParameter("id",orderId);
        transaction.commit();
        session.close();

        Session session1 = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction1 = session1.beginTransaction();
       Query query2=session1.createQuery("delete from Orders o where o.id=:id");
        query2.setParameter("id",orderId);
        transaction1.commit();
        session1.close();
    }
}
