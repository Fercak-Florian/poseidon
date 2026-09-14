package com.nnk.springboot.services;

import java.util.List;
import org.springframework.stereotype.Service;
import com.nnk.springboot.domain.Bid;
import com.nnk.springboot.repositories.BidRepository;
/**
 * This class contains necessary methods to get, add, update and delete a Bid
 * This class uses the BidRepository interface to perform this actions
 */
@Service
public class BidService {
	
	private BidRepository bidRepository;
	
	public BidService (BidRepository bidRepository) {
		this.bidRepository = bidRepository;
	}

	/**
     * This method finds all Bids
     * 
     * @return a list of Bid
     */
	public List<Bid> getBids() {
		return bidRepository.findAll();
	}
	
	/**
     * This method finds a Bid by its id in database
     * 
     * @param a bid id
     * @return a Bid if it's found
     */
	public Bid getBidById(int id){
		return bidRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid bid Id : " + id));
	}
	
	/**
     * This method saves a Bid
     * 
     * @param a Bid to save
     * @return the saved Bid
     */
	public Bid saveBid(Bid bid) {
		return bidRepository.save(bid);
	}
	
	/**
     * This method finds and updates a Bid
     * 
     * @param a Bid to update and its id in database
     * @return the updated Bid
     */
	public Bid updateBid(int id, Bid bid) {
		Bid bidToUpdate = bidRepository.findById(id).get();
		bidToUpdate.setAccount(bid.getAccount());
		bidToUpdate.setType(bid.getType());
		bidToUpdate.setBidQuantity(bid.getBidQuantity());
		Bid updatedBid = bidRepository.save(bidToUpdate);
		return updatedBid;
	}
	
	/**
     * This method finds a Bid and deletes it
     * 
     * @param a Bid id in database
     */
	public void deleteBid(int id) {
		Bid bid = bidRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid bid Id : " + id));
		bidRepository.delete(bid);
	}
}
