package com.nnk.springboot.services;

import java.util.List;
import org.springframework.stereotype.Service;
import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.repositories.TradeRepository;

/**
 * This class contains necessary methods to get, add, update and delete a Trade
 * This class uses the TradeRepository interface to perform this actions
 */
@Service
public class TradeService {

	private TradeRepository tradeRepository;
	
	public TradeService(TradeRepository tradeRepository) {
		this.tradeRepository = tradeRepository;
	}
	
	/**
     * This method finds all Trades
     * 
     * @return a list of Trade
     */
	public List<Trade> getTrades(){
		return tradeRepository.findAll();
	}
	
	/**
     * This method finds a Trade by its id in database
     * 
     * @param a Trade id
     * @return a Trade if it's found
     */
	public Trade getTradeById(int id){
		return tradeRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid trade Id : " + id));
	}
	
	/**
     * This method saves a Trade
     * 
     * @param a Trade to save
     * @return the saved Trade
     */
	public Trade saveTrade(Trade trade) {
		return tradeRepository.save(trade);
	}
	
	/**
     * This method finds and updates a Trade
     * 
     * @param a Trade to update and its id in database
     * @return the updated Trade
     */
	public Trade updateTrade(int id, Trade trade) {
		Trade tradeToUpdate = tradeRepository.findById(id).get();
		tradeToUpdate.setAccount(trade.getAccount());
		tradeToUpdate.setType(trade.getType());
		tradeToUpdate.setBuyQuantity(trade.getBuyQuantity());
		return tradeRepository.save(tradeToUpdate);
	}
	
	/**
     * This method finds a Trade and deletes it
     * 
     * @param a Trade id in database
     */
	public void deleteTrade(int id) {
		Trade trade = tradeRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid trade Id : " + id));
		tradeRepository.delete(trade);
	}
}
