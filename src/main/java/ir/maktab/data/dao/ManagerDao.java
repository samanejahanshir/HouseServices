package ir.maktab.data.dao;

import ir.maktab.data.model.Manager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ManagerDao extends JpaRepository<Manager, Integer> {

    Optional<Manager> findByUserNameAndPassword(String userName, String password);
    Optional<Manager> findByUserName(String userName);

}
