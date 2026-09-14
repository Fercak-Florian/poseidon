package com.nnk.springboot.repositories;

import com.nnk.springboot.domain.Bid;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * This interface contains necessary methods to perform CRUD actions to the Bid
 * table in database This interface inherits the JpaRepository interface
 */
@Repository
public interface BidRepository extends JpaRepository<Bid, Integer> {

}
