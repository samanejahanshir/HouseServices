package data.dao;

import data.model.Offer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OfferDao extends JpaRepository<Offer, Integer> {

}
