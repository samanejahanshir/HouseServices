package dao;

import config.HibernateUtil;
import model.Expert;
import model.Orders;
import model.SubServices;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import javax.persistence.NoResultException;
import java.util.List;

public class ExpertDao {
    public void save(Expert expert) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(expert);
        transaction.commit();
        session.close();
    }

    public Expert getExpertByEmailAndPass(String email, String password) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("from  Expert where email=:email and password=:password");
        query.setParameter("email", email);
        query.setParameter("password", password);
        Expert expert = null;
        try {
            expert = (Expert) query.getSingleResult();
            transaction.commit();
            session.close();
        } catch (NoResultException e) {
            e.printStackTrace();
        }
        return expert;
    }

    public Expert getExpertByEmail(String email) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("from  Expert where email=:email");
        query.setParameter("email", email);
        Expert expert = null;
        try {
            expert = (Expert) query.getSingleResult();
            expert.getAddresses();
            transaction.commit();
            session.close();
        } catch (NoResultException e) {
            e.printStackTrace();
        }
        return expert;
    }

    public void UpdateExpertServicesByEmail(String email, SubServices subServices) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("from  Expert where email=:email");
        query.setParameter("email", email);
        try {
            Expert expert = (Expert) query.getSingleResult();
            expert.getServices().add(subServices);
            session.update(expert);
        } catch (NoResultException e) {
            e.printStackTrace();
        }
        transaction.commit();
        session.close();
    }

    public int UpdatePassword(String email, String newPassword) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("update Expert set password=:password where email=:email ");
        query.setParameter("email", email);
        query.setParameter("password", newPassword);
        int id = query.executeUpdate();
        transaction.commit();
        session.close();
        return id;
    }

    public void deleteServiceFromExpert(String email, SubServices subServices) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("from  Expert where email=:email");
        query.setParameter("email", email);
        try {
            Expert expert = (Expert) query.getSingleResult();
            List<SubServices> services = expert.getServices();
            SubServices services1 = services.stream().filter(subServices1 -> subServices1.getId() == subServices.getId()).findFirst().get();
            services.remove(services1);
            expert.setServices(services);
            session.update(expert);
        } catch (NoResultException e) {
            e.printStackTrace();
        }
        transaction.commit();
        session.close();
    }

    public void update(Expert expert) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.update(expert);
        transaction.commit();
        session.close();
    }

    public Expert getExpertById(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("from Expert where id=:id");
        query.setParameter("id", id);
        Expert expert = null;
        try {
            expert = (Expert) query.getSingleResult();
        } catch (NoResultException e) {
            e.printStackTrace();
        }
        transaction.commit();
        session.close();
        return expert;
    }
}
