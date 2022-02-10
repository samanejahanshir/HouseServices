package ir.maktab.data.dao;

import ir.maktab.data.model.Customer;
import ir.maktab.data.model.PaymentHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentHistoryDao extends JpaRepository<PaymentHistory,Integer> {

    List<PaymentHistory> findByCustomer_Email(String email);

}
