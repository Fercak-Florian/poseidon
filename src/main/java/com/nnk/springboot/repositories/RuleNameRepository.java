package com.nnk.springboot.repositories;

import com.nnk.springboot.domain.RuleName;
import org.springframework.data.jpa.repository.JpaRepository;
/**
 * This interface contains necessary methods to perform CRUD actions to the
 * RuleName table in database This interface inherits the JpaRepository
 * interface
 */
public interface RuleNameRepository extends JpaRepository<RuleName, Integer> {
}
