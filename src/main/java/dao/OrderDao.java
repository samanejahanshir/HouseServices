package dao;

import config.HibernateUtil;
import model.Expert;
import model.Orders;
import model.enums.OrderState;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

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
        List<Orders> orders = new ArrayList<>();
        Transaction transaction = session.beginTransaction();
       /* for (SubServices service : expert.getServices()) {
            Query query = session.createQuery("from Orders  where subServices.subService=:service and state=:state1 or state=:state2");
            query.setParameter("service", service);
            query.setParameter("state1", OrderState.WAIT_OFFER_EXPERTS);
            query.setParameter("state2", OrderState.WAIT_SELECT_EXPERT);
            orders.addAll(query.list());
        }*/
        Criteria criteria = session.createCriteria(Orders.class, "orders");
        criteria.createAlias("orders.subServices", "subServices");
        criteria.createAlias("subServices.expertSet", "experts");
        criteria.add(Restrictions.eq("orders.state", OrderState.WAIT_OFFER_EXPERTS));
        // criteria.add(Restrictions.eq("subServices.expertSet",expert));

       /* criteria.setProjection(Projections.distinct(Projections.projectionList()
                .add(Projections.property("orders.description").as("description"))
                .add(Projections.property("orders.orderDoneDate").as("orderDoneDate"))
                .add(Projections.property("orders.orderDoneTime").as("orderDoneTime"))
                .add(Projections.property("orders.orderRegisterDate").as("orderRegisterDate"))
                .add(Projections.property("orders.proposedPrice").as("proposedPrice"))));
        criteria.setResultTransformer(Transformers.aliasToBean(Orders.class));*/
        orders = criteria.list();


        transaction.commit();
        session.close();
        return orders;
    }
}
