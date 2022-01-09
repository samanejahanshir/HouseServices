package data.dao;

import data.model.Manager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ManagerDao extends JpaRepository<Manager, Integer> {

    Optional<Manager> findByUserNameAndPassword(String userName, String password);
}
