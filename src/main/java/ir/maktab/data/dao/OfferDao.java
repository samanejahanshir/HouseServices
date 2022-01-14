package ir.maktab.data.dao;

import ir.maktab.data.model.Offer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface OfferDao extends JpaRepository<Offer, Integer> {
    @Transactional
    @Modifying
    void deleteByIdIn(List<Integer> ids);

    @Query(value = "from  Offer  o join fetch o.expert e where e.id=:id")
    List<Offer> getListOfferByExpertId(@Param("id") int expertId);

   /* @Query(value = "select o from Offer o join fetch o.orders order where order.orderDoingDate=:date and o.durationTime+o.startTime>:time")
    Optional<Offer> getOfferByCondition(@Param("date") Date date, @Param("time") int time);*/
}
