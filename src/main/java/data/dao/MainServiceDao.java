package data.dao;

import data.model.MainServices;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MainServiceDao extends JpaRepository<MainServices, Integer> {

    Optional<MainServices> findByGroupName(String groupName);



   /* public void update(MainServices mainServices) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.update(mainServices);
        transaction.commit();
        session.close();
    }*/
}
