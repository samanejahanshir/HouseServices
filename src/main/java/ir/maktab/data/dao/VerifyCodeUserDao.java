package ir.maktab.data.dao;

import ir.maktab.data.model.User;
import ir.maktab.data.model.VerifyCodeUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VerifyCodeUserDao extends JpaRepository<VerifyCodeUser, Integer> {

    Optional<VerifyCodeUser> findByEmail(String email);
}
