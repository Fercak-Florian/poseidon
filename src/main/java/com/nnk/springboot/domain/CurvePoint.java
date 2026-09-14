package com.nnk.springboot.domain;

import lombok.Data;
import javax.persistence.*;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import java.sql.Timestamp;

@Data
@Entity
@Table(name = "curve_point")
public class CurvePoint {
	
	public CurvePoint() {
	}
	
	public CurvePoint(int curveId, double term,  double value) {
		this.curveId = curveId;
		this.term = term;
		this.value = value;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@DecimalMax(message = "must be lower than 128", value = "127", inclusive = true)
	@DecimalMin(message = "must be greater than 0", value = "0", inclusive = false)
	@Digits(message="numeric value out of range (<3 numbers> expected, for example 123)", integer=3, fraction=0)
	@Column(name = "curve_id")
	private int curveId;
	
	@Column(name = "as_of_date")
	private Timestamp asOfDate;
	
	@DecimalMin(message = "must be greater than 0.0", value = "0.0", inclusive = false)
	@Column(name = "term")
	private double term;
	
	@DecimalMin(message = "must be greater than 0.0", value = "0.0", inclusive = false)
	@Column(name = "value")
	private double value;
	
	@Column(name = "creation_date")
	private Timestamp creationDate;
}
