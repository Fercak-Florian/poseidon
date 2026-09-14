package com.nnk.springboot.repositories;

import com.nnk.springboot.domain.CurvePoint;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * This interface contains necessary methods to perform CRUD actions to the
 * CurvePoint table in database This interface inherits the JpaRepository
 * interface
 */
public interface CurvePointRepository extends JpaRepository<CurvePoint, Integer> {

}
