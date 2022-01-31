package ir.maktab.data.dao;

import ir.maktab.data.enums.UserType;
import ir.maktab.data.model.Expert;
import ir.maktab.data.model.SubServices;
import ir.maktab.data.model.User;
import ir.maktab.dto.ConditionSearch;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Repository
public interface ExpertDao extends JpaRepository<Expert, Integer>, JpaSpecificationExecutor<Expert> {


    Optional<Expert> findByEmailAndPassword(String email, String password);

    Optional<Expert> findByEmail(String email);

    //@transactional
    @Query(value = "from Expert e join fetch e.services where e.email=:email")
    Optional<Expert> getExpertByEmailJoinSubService(@Param("email") String email);

    @Query(value = "select e from Expert e join fetch e.services s join fetch s.mainServices m where m.groupName=:groupName")
    List<Expert> getListExpertByGroupName(@Param("groupName") String groupName);


    @Query(value = "select e from Expert e join fetch e.services s where s.name=:subService")
    List<Expert> getListExpertBySubServiceName(@Param("subService") String subService);

    @Modifying
    @Query(value = "update Expert set password=:password where email=:email")
    int UpdatePassword(@Param("email") String email, @Param("password") String newPassword);

    static Specification<Expert> selectByCondition(ConditionSearch condition) {
        return (root, cq, cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (condition.getFirstName() != null && !condition.getFirstName().equals("")) {
                predicates.add(cb.equal(root.get("firstName"), condition.getFirstName()));
            }
            if (condition.getLastName() != null && !condition.getLastName().equals("")) {
                predicates.add(cb.equal(root.get("lastName"), condition.getLastName()));
            }
            if (condition.getEmail() != null && !condition.getEmail().equals("")) {
                predicates.add(cb.equal(root.get("email"), condition.getEmail()));
            }
            if (condition.getSubServiceName() != null && !Objects.equals(condition.getSubServiceName(), "")) {
                Join<Expert, SubServices> serviceJoin = root.joinList("services");
                predicates.add(cb.equal(serviceJoin.get("name"), condition.getSubServiceName()));
            }
            if(condition.getMinScore()!=0){
                predicates.add(cb.ge(root.get("score"),condition.getMinScore()));
            }
            if(condition.getMaxScore()!=0){
                predicates.add(cb.le(root.get("score"),condition.getMaxScore()));
            }
            if (condition.getStartDate()!=null && !condition.getStartDate().equals("")) {
                predicates.add(cb.greaterThanOrEqualTo(root.get("registerDate"),condition.getStartDate()));
            }
            if (condition.getEndDate()!=null && !condition.getEndDate().equals("")) {
                predicates.add(cb.lessThanOrEqualTo(root.get("registerDate"),condition.getEndDate()));
            }
            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}
