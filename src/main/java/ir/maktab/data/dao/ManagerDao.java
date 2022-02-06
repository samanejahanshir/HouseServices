package ir.maktab.data.dao;

import ir.maktab.data.model.Manager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ManagerDao extends JpaRepository<Manager, Integer> {

    Optional<Manager> findByEmailAndPassword(String email, String password);
    Optional<Manager> findByEmail(String email);

}
