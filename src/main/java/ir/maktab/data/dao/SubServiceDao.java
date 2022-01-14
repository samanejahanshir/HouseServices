package ir.maktab.data.dao;

import ir.maktab.data.model.SubServices;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SubServiceDao extends JpaRepository<SubServices, Integer> {

    int deleteByGroupName(String groupName);

    @Modifying
    @Query(value = "delete from SubServices where name=:subService")
    int deleteOneSubServices(@Param("subService") String subService);

    Optional<SubServices> findByNameAndGroupName(String name,String groupName);

    //@Query(value = "from SubServices where name=:subService")
    Optional<SubServices> findByName(String name);
    //Optional<SubServices> findByNameAndGroupName(String name,String groupName);

    //Optional<SubServices> findByName(String name);

    List<SubServices> findAllByGroupName(String groupName);

    void deleteAllById(int id);
}
