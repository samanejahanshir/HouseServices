package dao;

import config.HibernateUtil;
import model.Expert;
import model.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

public class ExpertDao {
    public void save(Expert expert) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(expert);
        transaction.commit();
        session.close();
    }

    public Expert getExpertByEmail(String email,String password) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction=session.beginTransaction();
        Query query = session.createQuery("from  Expert where email=:email and password=:password");
        query.setParameter("email", email);
        query.setParameter("password", password);
        Expert expert= (Expert) query.getSingleResult();
        transaction.commit();
        session.close();
        return  expert;
    }
}
