package dao;

import config.HibernateUtil;
import model.MainServices;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import javax.persistence.NoResultException;
import java.util.List;

public class MainServiceDao {
    public void save(MainServices mainServices) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(mainServices);
        transaction.commit();
        session.close();
    }

    public MainServices getMainService(String groupName) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("from MainServices where  groupName=:groupName");
        query.setParameter("groupName", groupName);
        MainServices mainServices = null;
        try {
            mainServices = (MainServices) query.getSingleResult();
            transaction.commit();
            session.close();
        } catch (NoResultException e) {
            e.printStackTrace();
        }
        return mainServices;
    }

    public List<MainServices> getListMainServices() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("from MainServices");
        List<MainServices> list = query.list();
        transaction.commit();
        session.close();
        return list;
    }

    public void update(MainServices mainServices) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.update(mainServices);
        transaction.commit();
        session.close();
    }
}
