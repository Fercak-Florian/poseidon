package com.nnk.springboot.services;

import java.util.List;
import org.springframework.stereotype.Service;
import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.repositories.CurvePointRepository;

/**
 * This class contains necessary methods to get, add, update and delete a CurvePoint
 * This class uses the CurvePointRepository interface to perform this actions
 */
@Service
public class CurvePointService {
	
	private CurvePointRepository curvePointRepository;
	
	public CurvePointService(CurvePointRepository curvePointRepository) {
		this.curvePointRepository = curvePointRepository;
	}
	
	/**
     * This method finds all CurvePoints
     * 
     * @return a list of CurvePoint
     */
	public List<CurvePoint> getCurvePoints(){
		return curvePointRepository.findAll();
	}
	
	/**
     * This method finds a CurvePoint by its id in database
     * 
     * @param a CurvePoint id
     * @return a CurvePoint if it's found
     */
	public CurvePoint getCurvePointById(int id) {
		return curvePointRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid curvePoint Id : " + id));
	}
	
	/**
     * This method saves a CurvePoint
     * 
     * @param a CurvePoint to save
     * @return the saved CurvePoint
     */
	public CurvePoint saveCurvePoint(CurvePoint curvePoint) {
		return curvePointRepository.save(curvePoint);
	}
	
	/**
     * This method finds and updates a CurvePoint
     * 
     * @param a CurvePoint to update and its id in database
     * @return the updated CurvePoint
     */
	public CurvePoint updateCurvePoint(int id, CurvePoint curvePoint) {
		CurvePoint curvePointToUpdate = curvePointRepository.findById(id).get();
		curvePointToUpdate.setCurveId(curvePoint.getCurveId());
		curvePointToUpdate.setTerm(curvePoint.getTerm());
		curvePointToUpdate.setValue(curvePoint.getValue());
		return curvePointRepository.save(curvePointToUpdate);
	}
	
	/**
     * This method finds a CurvePoint and deletes it
     * 
     * @param a CurvePoint id in database
     */
	public void deleteCurvePoint(int id) {
		CurvePoint curvePoint = curvePointRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid curvePoint Id : " + id));
		curvePointRepository.delete(curvePoint);
	}
}
