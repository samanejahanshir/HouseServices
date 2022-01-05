package dao;

import config.HibernateUtil;
import model.MainServices;
import model.SubServices;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import javax.persistence.NoResultException;
import java.util.List;

public class SubServiceDao {
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

    public SubServices getService(String groupName, String subService) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("from SubServices where subService=:subService and groupService=:groupName");
        query.setParameter("subService", subService);
        query.setParameter("groupName", groupName);
        SubServices subServices = null;
        try {
            subServices = (SubServices) query.getSingleResult();
            transaction.commit();
            session.close();
        } catch (NoResultException e) {
            e.printStackTrace();
        }
        return subServices;
    }

    public SubServices getSubServiceByName(String subService) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("from SubServices where subService=:subService");
        query.setParameter("subService", subService);
        SubServices subServices = null;
        try {
            subServices = (SubServices) query.getSingleResult();
            transaction.commit();
            session.close();
        } catch (NoResultException e) {
            e.printStackTrace();
        }
        return subServices;
    }

    public List<SubServices> getListSubServices(String groupName) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("from SubServices where  groupService=:groupName");
        query.setParameter("groupName", groupName);
        List<SubServices> list = query.list();
        transaction.commit();
        session.close();
        return list;
    }

    public void update(SubServices subServices) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        //TODO
        Query query = session.createQuery("update SubServices u set u.experts =:experts where u.id in (select id from Expert )");
        transaction.commit();
        session.close();
    }
}