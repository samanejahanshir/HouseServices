package data.dao;

import data.model.Expert;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ExpertDao extends JpaRepository<Expert, Integer> {


    Optional<Expert> findByEmailAndPassword(String email, String password);

    Optional<Expert> findByEmail(String email);

    @Query(value = "from Expert e join fetch e.services where e.email=:email")
    Optional<Expert> getExpertByEmailJoinSubService(@Param("email") String email);

    @Query(value = "select e from Expert e join fetch e.services s where s.groupName=:groupName")
    List<Expert> getListExpertByGroupName(@Param("groupName") String groupName);

    @Query(value = "select e from Expert e join fetch e.services s where s.subService=:subService")
    List<Expert> getListExpertBySubServiceName(@Param("subService") String subService);

    /*  public void UpdateExpertServicesByEmail(String email, SubServices subServices) {
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
          }*/
    @Modifying
    @org.springframework.data.jpa.repository.Query(value = "update Expert set password=:password where email=:email")
    int UpdatePassword(@Param("email") String email, @Param("password") String newPassword);

   /* public void deleteServiceFromExpert(String email, SubServices subServices) {
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
    }*/

   /* public void update(Expert expert) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.update(expert);
        transaction.commit();
        session.close();
    }
*/

}
