package com.nnk.springboot.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.repositories.RatingRepository;

@ExtendWith(MockitoExtension.class)
public class RatingServiceTest {
	
	private RatingService ratingService;
	
	@Mock
	private RatingRepository ratingRepository;
	
	@BeforeEach
	public void init() {
		ratingService = new RatingService(ratingRepository);
	}
	
	@Test
	public void testGetRatings() {
		/*ARRANGE*/
		List<Rating> ratings = new ArrayList<>();
		ratings.add(new Rating("moodysRating", "sandRating", "fitchRating", 1));
		when(ratingRepository.findAll()).thenReturn(ratings);
		/*ACT*/
		List<Rating> result = ratingService.getRatings();
		/*ASSERT*/
		assertThat(result.get(0).getOrderNumber()).isEqualTo(1);
		verify(ratingRepository).findAll();
	}
	
	@Test
	public void testGetRatingById() {
		/*ARRANGE*/
		int id = 0;
		Rating rating = new Rating("moodysRating", "sandRating", "fitchRating", 1);
		Optional<Rating> optRating = Optional.of(rating);
		when(ratingRepository.findById(id)).thenReturn(optRating);
		/*ACT*/
		Rating result = ratingService.getRatingById(id);
		/*ASSERT*/
		assertThat(result.getOrderNumber()).isEqualTo(1);
		verify(ratingRepository).findById(id);
	}
	
	@Test
	public void testGetRatingByIdThrowsException() {
		/*ARRANGE*/
		int id = 0;
		/*ACT*/
		/*ASSERT*/
		assertThrows(IllegalArgumentException.class, () -> ratingService.getRatingById(id));
	}
	
	@Test
	public void testSaveRating() {
		/*ARRANGE*/
		Rating ratingToSave = new Rating("moodysRating", "sandRating", "fitchRating", 1);
		when(ratingRepository.save(ratingToSave)).thenReturn(ratingToSave);
		/*ACT*/
		Rating result = ratingService.saveRating(ratingToSave);
		/*ASSERT*/
		assertThat(result.getOrderNumber()).isEqualTo(1);
		verify(ratingRepository).save(ratingToSave);
	}
	
	@Test
	public void testUpdateRating() {
		/*ARRANGE*/
		int id = 0;
		Rating rating = new Rating("moodysRating", "sandRating", "fitchRating", 1);
		Optional<Rating> optRating = Optional.of(rating);
		when(ratingRepository.findById(id)).thenReturn(optRating);
		when(ratingRepository.save(rating)).thenReturn(rating);
		/*ACT*/
		Rating result = ratingService.updateRating(id, rating);
		/*ASSERT*/
		assertThat(result.getOrderNumber()).isEqualTo(1);
		verify(ratingRepository).findById(id);
		verify(ratingRepository).save(rating);
	}
	
	@Test
	public void testDeleteRating() {
		/*ARRANGE*/
		int id = 0;
		Rating rating = new Rating("moodysRating", "sandRating", "fitchRating", 1);
		Optional<Rating> optRating = Optional.of(rating);
		when(ratingRepository.findById(id)).thenReturn(optRating);
		/*ACT*/
		ratingService.deleteRating(id);
		/*ASSERT*/
		verify(ratingRepository).findById(id);
	}
	
	@Test
	public void testDeleteRatingThrowsException() {
		/*ARRANEG*/
		int id = 0;
		/*ACT*/
		/*ASSERT*/
		assertThrows(IllegalArgumentException.class, () -> ratingService.deleteRating(id));
	}
}
