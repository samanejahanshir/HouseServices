package ir.maktab.data.dao;

import ir.maktab.data.enums.UserState;
import ir.maktab.data.model.Customer;
import ir.maktab.data.model.Orders;
import ir.maktab.data.model.User;
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
    @Query(value = "update Customer set password=:password where email=:email")
    int updatePassword(@Param("email") String email, @Param("password") String newPassword);

    Optional<Customer> findByEmail(String email);

//TODO delete توی order  گذاشتم
    @Query(value = "select o from  Customer c inner join c.orders o  where o.customer.id=:id")
    List<Orders> getAllOrders(@Param("id") int customerId);

    @Modifying
    @Query(value = "update Customer set credit=:amount where id=:id")
    int updateCredit(@Param("id") int customerId, @Param("amount") double amount);

    @Query(value = "from Customer c join c.addresses where c.email=:email")
    Optional<Customer> getCustomerByEmail(@Param("email") String email);

    List<Customer> findByStateEquals(UserState state);

}