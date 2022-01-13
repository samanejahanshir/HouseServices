package data.dao;

import data.model.SubServices;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SubServiceDao extends JpaRepository<SubServices, Integer> {

    int deleteByGroupName(String groupName);

    @Modifying
    @org.springframework.data.jpa.repository.Query(value = "delete from SubServices where subService=:subService")
    int deleteOneSubServices(@Param("subService") String subService);

    @org.springframework.data.jpa.repository.Query(value = "from SubServices where subService=:subService and groupName=:groupName")
    Optional<SubServices> getService(@Param("groupName") String groupName, @Param("subService") String subService);

    @org.springframework.data.jpa.repository.Query(value = "from SubServices where subService=:subService")
    Optional<SubServices> getSubServiceByName(@Param("subService") String subService);

    List<SubServices> findAllByGroupName(String groupName);

    void deleteAllById(int id);
}
