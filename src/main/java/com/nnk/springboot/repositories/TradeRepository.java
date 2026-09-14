package com.nnk.springboot.repositories;

import com.nnk.springboot.domain.Trade;
import org.springframework.data.jpa.repository.JpaRepository;
/**
 * This interface contains necessary methods to perform CRUD actions to the
 * Trade table in database This interface inherits the JpaRepository
 * interface
 */
public interface TradeRepository extends JpaRepository<Trade, Integer> {
}
