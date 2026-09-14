package com.nnk.springboot.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.repositories.CurvePointRepository;

@ExtendWith(MockitoExtension.class)
public class CurvePointServiceTest {
	
	private CurvePointService curvePointService;
	
	@Mock
	private CurvePointRepository curvePointRepository;
	
	@BeforeEach
	public void init() {
		curvePointService = new CurvePointService(curvePointRepository);
	}

	@Test
	public void testGetCurvePoint() {
		/*ARRANGE*/
		List<CurvePoint> curvePoints = new ArrayList<>();
		curvePoints.add(new CurvePoint(1, 10.0, 20.0));
		when(curvePointRepository.findAll()).thenReturn(curvePoints);
		/*ACT*/
		List<CurvePoint> result = curvePointService.getCurvePoints();
		/*ASSERT*/
		assertThat(result.get(0).getCurveId()).isEqualTo(1);
		verify(curvePointRepository).findAll();
	}
	
	@Test
	public void testGetCurvePointById() {
		/*ARRANGE*/
		int id = 0;
		CurvePoint curvePoint = new CurvePoint(1, 10.0, 20.0);
		Optional<CurvePoint> optCurvePoint = Optional.of(curvePoint);
		when(curvePointRepository.findById(id)).thenReturn(optCurvePoint);
		/*ACT*/
		CurvePoint result = curvePointService.getCurvePointById(id);
		/*ASSERT*/
		assertThat(result.getCurveId()).isEqualTo(1);
		verify(curvePointRepository).findById(id);
	}
	
	@Test
	public void testGetCurvePointByIdThrowsException() {
		/*ARRANGE*/
		int id = 0;
		/*ACT*/
		/*ASSERT*/
		assertThrows(IllegalArgumentException.class, () -> curvePointService.getCurvePointById(id));
	}
	
	@Test
	public void testSaveCurvePoint() {
		/*ARRANGE*/
		CurvePoint curvePointToSave = new CurvePoint(1, 10.0, 20.0);
		when(curvePointRepository.save(curvePointToSave)).thenReturn(curvePointToSave);
		/*ACT*/
		CurvePoint result = curvePointService.saveCurvePoint(curvePointToSave);
		/*ASSERT*/
		assertThat(result.getCurveId()).isEqualTo(1);
		verify(curvePointRepository).save(curvePointToSave);
	}
	
	@Test
	public void testUpdateCurvePoint() {
		/*ARRANGE*/
		int id = 0;
		CurvePoint curvePointToUpdate = new CurvePoint(1, 10.0, 20.0);
		CurvePoint updatedCurvePoint = new CurvePoint(2, 20.0, 30.0);
		
		CurvePoint curvePoint = new CurvePoint(5, 50.0, 50.0);
		Optional<CurvePoint> optCurvePoint = Optional.of(curvePoint);
		when(curvePointRepository.findById(id)).thenReturn(optCurvePoint);
		when(curvePointRepository.save(curvePointToUpdate)).thenReturn(updatedCurvePoint);
		/*ACT*/
		CurvePoint result = curvePointService.updateCurvePoint(id, curvePointToUpdate);
		/*ASSERT*/
		assertThat(result.getCurveId()).isEqualTo(2);
	}
	
	@Test
	public void testDeleteCurvePoint() {
		/*ARRANGE*/
		int id = 0;
		CurvePoint curvePoint = new CurvePoint(1, 10.0, 20.0);
		Optional<CurvePoint> optCurvePoint = Optional.of(curvePoint);
		//CurvePoint curvePointToDelete = new CurvePoint(3, 30.0, 30.0);
		when(curvePointRepository.findById(id)).thenReturn(optCurvePoint);
		//when(curvePointRepository.delete(curvePointToDelete)).thenReturn(null);
		/*ACT*/
		curvePointService.deleteCurvePoint(id);
		/*ASSERT*/
		verify(curvePointRepository).delete(curvePoint);
	}
	
	@Test
	public void testDeleteCurvePointThrowsException() {
		/*ARRANGE*/
		int id = 0;
		/*ACT*/
		/*ASSERT*/
		assertThrows(IllegalArgumentException.class, () -> curvePointService.deleteCurvePoint(id));
	}
}
