package data.dao;

import data.enums.OrderState;
import data.model.Offer;
import data.model.Orders;
import data.model.SubServices;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
import java.util.List;

@Repository
public interface OrderDao extends JpaRepository<Orders, Integer> {

    /* public void update(Orders order) {
         Session session = HibernateUtil.getSessionFactory().openSession();
         Transaction transaction = session.beginTransaction();
         session.update(order);
         transaction.commit();
         session.close();
     }*/
    @org.springframework.data.jpa.repository.Query(value = "select o from Orders o inner  join o.subServices s where s.subService in :list and o.state='WAIT_OFFER_EXPERTS' or o.state='WAIT_SELECT_EXPERT'")
    List<Orders> getListOrdersOfSubServiceExpert(@Param("list") List<String> subServices);

    /*  public void addOfferToOrder(int ordersId, Offer offer) {
          Session session = HibernateUtil.getSessionFactory().openSession();
          Transaction transaction = session.beginTransaction();
          Query query = session.createQuery("from  Orders where id=:id");
          query.setParameter("id", ordersId);
          try {
              Orders orders = (Orders) query.getSingleResult();
              orders.getOffers().add(offer);
              session.update(orders);
          } catch (NoResultException e) {
              e.printStackTrace();
          }
          transaction.commit();
          session.close();
      }*/
    @org.springframework.data.jpa.repository.Query(value = "select o from Offer o inner join o.expert e inner  join e.services s where o.orders.id=:id")
    List<Offer> getListOffers(@Param("id") int ordersId);

    @org.springframework.data.jpa.repository.Query(value = "select o from Offer o inner join o.expert e inner  join e.services s where o.orders.id=:id")
    List<Offer> getListOffersBySort(@Param("id") int ordersId, Sort sort);

    @org.springframework.data.jpa.repository.Query(value = "from Orders o where o.expert.id=:id and o.state='WAIT_SELECT_EXPERT'")
    List<Orders> updateOrderStateToComeExpert(@Param("id") int expertId);

    @Modifying
    @org.springframework.data.jpa.repository.Query(value = "update Orders o set o.state=:state where o.id=:id")
    int updateOrderState(@Param("id") int idOrder, @Param("state") OrderState state);

    @Modifying
    @org.springframework.data.jpa.repository.Query(value = "update Orders o set o.Comment=:comment where o.id=:id")
    int updateOrderComment(@Param("id") int orderId, @Param("comment") String comment);

  /*  public void deleteOrder(int orderId) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("delete from Offer o  where o.orders.id=:id");
        query.setParameter("id", orderId);
        transaction.commit();
        session.close();

        Session session1 = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction1 = session1.beginTransaction();
        Query query2 = session1.createQuery("delete from Orders o where o.id=:id");
        query2.setParameter("id", orderId);
        transaction1.commit();
        session1.close();
    }*/
}
