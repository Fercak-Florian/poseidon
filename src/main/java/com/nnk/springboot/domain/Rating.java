package com.nnk.springboot.domain;

import javax.persistence.*;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Entity
@Table(name = "rating")
public class Rating {
	
	public Rating() {
	}
	
	public Rating(String moodysRating, String sandRating, String fitchRating, int orderNumber) {
		this.moodysRating = moodysRating;
		this.sandRating = sandRating;
		this.fitchRating = fitchRating;
		this.orderNumber = orderNumber;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@NotBlank(message = "Moodys Rating is mandatory")
	@Column(name = "moodys_rating")
	private String moodysRating;
	
	@NotBlank(message = "Sand Rating is mandatory")
	@Column(name = "sand_rating")
	private String sandRating;
	
	@NotBlank(message = "Fitch Rating is mandatory")
	@Column(name = "fitch_rating")
	private String fitchRating;
	
	@DecimalMax(message = "must be lower than 128", value = "127", inclusive = true)
	@DecimalMin(message = "must be greater than 0", value = "0", inclusive = false)
	@Digits(message="numeric value out of range (<3 numbers> expected, for example 123)", integer=3, fraction=0)
	@Column(name = "order_number")
	private int orderNumber;
}
