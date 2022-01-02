package dao;

import config.HibernateUtil;
import model.SubServices;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

public class ServicesDao {
    public void save(SubServices subServices) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(subServices);
        transaction.commit();
        session.close();
    }

    public int deleteOneServices(String groupName) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("delete from SubServices where groupService=:groupName");
        query.setParameter("groupName", groupName);
        int id = query.executeUpdate();
        transaction.commit();
        session.close();
        return id;
    }

    public int deleteOneSubServices(String subService) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("delete from SubServices where subService=:subService");
        query.setParameter("subService", subService);
        int id = query.executeUpdate();
        transaction.commit();
        session.close();
        return id;
    }

    public SubServices getService(String groupName, String subService){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("from SubServices where subService=:subService and groupService=:groupName");
        query.setParameter("subService", subService);
        query.setParameter("groupName",groupName);
        SubServices subServices = (SubServices)query.getSingleResult();
        transaction.commit();
        session.close();
        return subServices;
    }
}
