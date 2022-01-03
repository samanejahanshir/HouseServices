package dao;

import config.HibernateUtil;
import model.Expert;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

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
        List<Expert> expert = query.list();
        transaction.commit();
        session.close();
        if(!expert.isEmpty()){
            return expert.get(0);
        }
        else {
            return null;
        }
    }

    public Expert getExpertByEmail(String email) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("from  Expert where email=:email");
        query.setParameter("email", email);
        List<Expert> experts = query.list();
        transaction.commit();
        session.close();
        if(!experts.isEmpty()) {
            return experts.get(0);
        }else
            return null;
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

    public int deleteServiceFromExpert(String groupName) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        //  Query query = session.createQuery("update Expert e fetch join set where email=:email ");
        return 0;//TODO
    }

    public void update(Expert expert){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.update(expert);
        transaction.commit();
        session.close();
    }
}
