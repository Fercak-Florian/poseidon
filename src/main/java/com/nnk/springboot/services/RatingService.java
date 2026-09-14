package com.nnk.springboot.services;

import java.util.List;
import org.springframework.stereotype.Service;
import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.repositories.RatingRepository;

/**
 * This class contains necessary methods to get, add, update and delete a Rating
 * This class uses the RatingRepository interface to perform this actions
 */
@Service
public class RatingService {
	
	private RatingRepository ratingRepository;
	
	public RatingService(RatingRepository ratingRepository) {
		this.ratingRepository = ratingRepository;
	}
	
	/**
     * This method finds all Ratings
     * 
     * @return a list of Rating
     */
	public List<Rating> getRatings(){
		return ratingRepository.findAll();
	}
	
	/**
     * This method finds a Rating by its id in database
     * 
     * @param a Rating id
     * @return a Rating if it's found
     */
	public Rating getRatingById(int id){
		return ratingRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid rating Id : " + id));
	}
	
	/**
     * This method saves a Rating
     * 
     * @param a Rating to save
     * @return the saved Rating
     */
	public Rating saveRating(Rating rating) {
		return ratingRepository.save(rating);
	}
	
	/**
     * This method finds and updates a Rating
     * 
     * @param a Rating to update and its id in database
     * @return the updated Rating
     */
	public Rating updateRating(int id, Rating rating) {
		Rating ratingToUpdate = ratingRepository.findById(id).get();
		ratingToUpdate.setMoodysRating(rating.getMoodysRating());
		ratingToUpdate.setSandRating(rating.getSandRating());
		ratingToUpdate.setFitchRating(rating.getFitchRating());
		ratingToUpdate.setOrderNumber(rating.getOrderNumber());
		return ratingRepository.save(ratingToUpdate);
	}
	
	/**
     * This method finds a Rating and deletes it
     * 
     * @param a Rating id in database
     */
	public void deleteRating(int id) {
		Rating rating = ratingRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid rating Id : " + id));
		ratingRepository.delete(rating);
	}
}
