package data.dao;

import data.model.Customer;
import data.model.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerDao extends JpaRepository<Customer, Integer> {


    Optional<Customer> findByEmailAndPassword(String email, String password);

    @Transactional
    @Modifying
    @org.springframework.data.jpa.repository.Query(value = "update Customer set password=:password where email=:email")
    int UpdatePassword(@Param("email") String email, @Param("password") String newPassword);

    Optional<Customer> findByEmail(String email);

    @org.springframework.data.jpa.repository.Query(value = "select o from  Customer c inner join c.orders o  where o.customer.id=:id")
    List<Orders> getAllOrders(@Param("id") int customerId);

    @org.springframework.data.jpa.repository.Query(value = "select o from  Customer c inner join c.orders o  where o.customer.id=:id and o.state <> 'PAID'")
    List<Orders> getListOrdersThatNotFinished(@Param("id") int customerId);

    @Modifying
    @org.springframework.data.jpa.repository.Query(value = "update Customer set credit=:amount where id=:id")
    int updateCredit(@Param("id") int customerId, @Param("amount") double amount);

    @Query(value = "from Customer c join c.addresses where c.email=:email")
    Optional<Customer> getCustomerByEmail(@Param("email") String email);
}