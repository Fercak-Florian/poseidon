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

import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.repositories.TradeRepository;

@ExtendWith(MockitoExtension.class)
public class TradeServiceTest {
	
	private TradeService tradeService;
	
	@Mock
	private TradeRepository tradeRepository;
	
	@BeforeEach
	public void init() {
		tradeService = new TradeService(tradeRepository);
	}
	
	@Test
	public void testGetTrades() {
		/*ARRANGE*/
		List<Trade> trades = new ArrayList<>();
		trades.add(new Trade("testAccount", "testType", 10.0));
		when(tradeRepository.findAll()).thenReturn(trades);
		/*ACT*/
		List<Trade> result = tradeService.getTrades();
		/*ASSERT*/
		assertThat(result.get(0).getAccount()).isEqualTo("testAccount");
		verify(tradeRepository).findAll();
	}
	
	@Test
	public void testGetTradesById() {
		/*ARRANGE*/
		int id = 0;
		Trade trade = new Trade("testAccount", "testType", 10.0);
		Optional<Trade> optTrade = Optional.of(trade);
		when(tradeRepository.findById(id)).thenReturn(optTrade);
		/*ACT*/
		Trade result = tradeService.getTradeById(id);
		/*ASSERT*/
		assertThat(result.getAccount()).isEqualTo("testAccount");
		verify(tradeRepository).findById(id);
	}
	
	@Test
	public void testGetTradeByIdThrowsException() {
		/*ARRANGE*/
		int id = 0;
		/*ACT*/
		/*ASSERT*/
		assertThrows(IllegalArgumentException.class, () -> tradeService.getTradeById(id));
	}
	
	@Test
	public void testSaveTrade() {
		/*ARRANGE*/
		Trade trade = new Trade("testAccount", "testType", 10.0);
		when(tradeRepository.save(trade)).thenReturn(trade);
		/*ACT*/
		Trade result = tradeService.saveTrade(trade);
		/*ASSERT*/
		assertThat(result.getAccount()).isEqualTo("testAccount");
		verify(tradeRepository).save(trade);
	}

	@Test
	public void testUpdateTrade() {
		/*ARRANGE*/
		int id = 0;
		Trade trade = new Trade("testAccount", "testType", 10.0);
		Optional<Trade> optTrade = Optional.of(trade);
		when(tradeRepository.findById(id)).thenReturn(optTrade);
		when(tradeRepository.save(trade)).thenReturn(trade);
		/*ACT*/
		Trade result = tradeService.updateTrade(id, trade);
		/*ASSERT*/
		assertThat(result.getAccount()).isEqualTo("testAccount");
		verify(tradeRepository).findById(id);
		verify(tradeRepository).save(trade);
	}
	
	@Test
	public void testDeleteTrade() {
		/*ARRANGE*/
		int id = 0;
		Trade trade = new Trade("testAccount", "testType", 10.0);
		Optional<Trade> optTrade = Optional.of(trade);
		when(tradeRepository.findById(id)).thenReturn(optTrade);
		/*ACT*/
		tradeService.deleteTrade(id);
		/*ASSERT*/
		verify(tradeRepository).findById(id);
	}
	
	@Test
	public void testDeleteTradeThrowsException() {
		/*ARRANGE*/
		int id = 0;
		/*ACT*/
		/*ASSERT*/
		assertThrows(IllegalArgumentException.class, () -> tradeService.deleteTrade(id));
	}
}