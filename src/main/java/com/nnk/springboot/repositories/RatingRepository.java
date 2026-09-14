package com.nnk.springboot.repositories;

import com.nnk.springboot.domain.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
/**
 * This interface contains necessary methods to perform CRUD actions to the
 * Rating table in database This interface inherits the JpaRepository
 * interface
 */
public interface RatingRepository extends JpaRepository<Rating, Integer> {

}
