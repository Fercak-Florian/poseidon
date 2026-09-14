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

import com.nnk.springboot.domain.Bid;
import com.nnk.springboot.repositories.BidRepository;

@ExtendWith(MockitoExtension.class)
public class BidServiceTest {
	
	private BidService bidService;
	
	@Mock
	private BidRepository bidRepository;
	
	@BeforeEach
	public void init() {
		bidService = new BidService(bidRepository);
	}
	
	@Test
	public void testGetBids() {
		/*ARRANGE*/
		List<Bid> bids = new ArrayList<>();
		bids.add(new Bid("testAccount", "testType", 10.0));
		when(bidRepository.findAll()).thenReturn(bids);
		/*ACT*/
		List<Bid> result = bidService.getBids();
		/*ASSERT*/
		assertThat(result.get(0).getAccount()).isEqualTo("testAccount");
		verify(bidRepository).findAll();
	}
	
	@Test
	public void testGetBidById() {
		/*ARRANGE*/
		int id = 0;
		Bid bid = new Bid("testAccount", "testType", 10.0);
		Optional<Bid> optBid = Optional.of(bid);
		when(bidRepository.findById(id)).thenReturn(optBid);
		/*ACT*/
		Bid result = bidService.getBidById(id);
		/*ASSERT*/
		assertThat(result.getAccount()).isEqualTo("testAccount");
		verify(bidRepository).findById(id);
	}
	
	@Test
	public void testGetBidByIdThrowsException() {
		/*ARRANGE*/
		int id = 0;
		/*ACT*/
		/*ASSERT*/
		assertThrows(IllegalArgumentException.class, () -> bidService.getBidById(id));
	}
	
	@Test
	public void testSaveBid() {
		/*ARRANGE*/
		Bid bidToSave = new Bid ("testAccount", "testType", 10.0);
		when(bidRepository.save(bidToSave)).thenReturn(bidToSave);
		/*ACT*/
		Bid result = bidService.saveBid(bidToSave); 
		/*ASSERT*/
		assertThat(result.getAccount()).isEqualTo("testAccount");
		verify(bidRepository).save(bidToSave);
	}
	
	@Test
	public void testUpdateBid() {
		/*ARRANGE*/
		int id = 0;
		Bid bidToUpdate = new Bid("testAccount", "testType", 10.0);
		Bid updatedBid = new Bid("updatedAccount", "updatedType", 20.0);
		
		Bid bid = new Bid("Account", "Type", 10.0);
		Optional<Bid> optBid = Optional.of(bid);
		
		when(bidRepository.findById(id)).thenReturn(optBid);
		when(bidRepository.save(bidToUpdate)).thenReturn(updatedBid);
		/*ACT*/
		Bid result = bidService.updateBid(id, bidToUpdate); 
		/*ASSERT*/
		assertThat(result.getAccount()).isEqualTo("updatedAccount");
		verify(bidRepository).save(bidToUpdate);
	}
	
	@Test
	public void testDeleteBid() {
		/*ARRANGE*/
		int id = 0;
		Bid bid = new Bid("testAccount", "testType", 10.0);
		Optional<Bid> optBid = Optional.of(bid);
		when(bidRepository.findById(id)).thenReturn(optBid);
		/*ACT*/
		bidService.deleteBid(id);
		/*ASSERT*/
		verify(bidRepository).findById(id);
	}
	
	@Test
	public void testDeleteBidThrowsException() {
		/*ARRANGE*/
		int id = 0;
		/*ACT*/
		/*ASSERT*/
		assertThrows(IllegalArgumentException.class, () -> bidService.deleteBid(id));
	}
}
