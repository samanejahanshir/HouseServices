package ir.maktab.data.dao;

import ir.maktab.data.model.Commend;
import ir.maktab.data.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommendDao extends JpaRepository<Commend, Integer> {

}
