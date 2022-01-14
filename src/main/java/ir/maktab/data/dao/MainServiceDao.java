package ir.maktab.data.dao;

import ir.maktab.data.model.MainServices;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MainServiceDao extends JpaRepository<MainServices, Integer> {

    Optional<MainServices> findByGroupName(String groupName);

    void deleteByGroupName(String groupName);

}
