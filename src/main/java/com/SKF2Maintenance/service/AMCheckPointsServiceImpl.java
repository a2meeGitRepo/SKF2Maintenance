package com.SKF2Maintenance.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.SKF2Maintenance.model.AMCheckPoints;
import com.SKF2Maintenance.model.AMCheckPointsValue;
import com.SKF2Maintenance.repository.AMCheckPointsValueRepo;
import com.SKF2Maintenance.repository.AmCheckPointsRepo;

@Transactional
@Service
public class AMCheckPointsServiceImpl  implements AMCheckPointsService{

	@Autowired
	private AmCheckPointsRepo amCheckPointsRepo;
	
	@Autowired
	private AMCheckPointsValueRepo aMCheckPointsValueRepo;

	public Optional<AMCheckPoints> getAmCheckPointById(int amCheckPointId) {
		
		return amCheckPointsRepo.findById(amCheckPointId);
	}

	public void addAmCheckPoints(AMCheckPoints amCheckPoints) {
		amCheckPointsRepo.save(amCheckPoints);
	}

	public List<AMCheckPoints> getlistAmCheckPointByLimit(int page_no, int item_per_page) {
		
		return amCheckPointsRepo.getlistAmCheckPointByLimit(page_no,item_per_page);
	}

	public List<AMCheckPoints> getlistAMCheckPointsByLimitAndSearch(String searchText, int pageNo, int perPage) {
		
		return amCheckPointsRepo.getlistAMCheckPointsByLimitAndSearch(searchText,pageNo,perPage);
	}

	public int getAMCheckPointsCount() {
		
		return (int)amCheckPointsRepo.getAMCheckPointsCount();
	}

	public int getAMCheckPointsCountAndSearch(String searchText) {
		
		return amCheckPointsRepo.getAMCheckPointsCountAndSearch(searchText);
	}

	public List<AMCheckPoints> getAllAMCheckPoints() {
		
		return amCheckPointsRepo.findAll();
	}

	public Optional<AMCheckPointsValue> getAmCheckPointValueById(int amCheckPointsValueId) {
		// TODO Auto-generated method stub
		return aMCheckPointsValueRepo.findById(amCheckPointsValueId);
	}

	public void addAmCheckPointsValue(AMCheckPointsValue amCheckPointsValue) {
		// TODO Auto-generated method stub
		aMCheckPointsValueRepo.save(amCheckPointsValue);
	}

	public List<AMCheckPointsValue> getlistAMCheckPointValuesByLimit(int page_no, int item_per_page) {
		// TODO Auto-generated method stub
		return aMCheckPointsValueRepo.getlistAMCheckPointValuesByLimit(page_no, item_per_page);
	}

	public List<AMCheckPointsValue> getlistAMCheckPointValuesByLimitAndSearch(String searchText, int pageNo,
			int perPage) {
		// TODO Auto-generated method stub
		return aMCheckPointsValueRepo.getlistAMCheckPointValuesByLimitAndSearch(searchText, pageNo, perPage);
	}

	public int getAMCheckPointValuesCountAndSearch(String searchText) {
		// TODO Auto-generated method stub
		return aMCheckPointsValueRepo.getAMCheckPointValuesCountAndSearch(searchText);
	}

	public int getAMCheckPointValuesCount() {
		// TODO Auto-generated method stub
		return aMCheckPointsValueRepo.getAMCheckPointValuesCount();
	}

	public List<AMCheckPoints> dailyAMCheckPointByMachineCode(String code) {
		// TODO Auto-generated method stub
		return amCheckPointsRepo.dailyAMCheckPointByMachineCode(code);
	}

	public List<AMCheckPoints> quatorlyAMCheckPointByMachineCode(String code) {
		// TODO Auto-generated method stub
		return amCheckPointsRepo.quatorlyAMCheckPointByMachineCode(code);
	}

	public List<AMCheckPoints> halfYearlyAMCheckPointByMachineCode(String code) {
		// TODO Auto-generated method stub
		return amCheckPointsRepo.halfYearlyAMCheckPointByMachineCode(code);
	}

	public List<AMCheckPoints> yearlyAMCheckPointByMachineCode(String code) {
		// TODO Auto-generated method stub
		return amCheckPointsRepo.yearlyAMCheckPointByMachineCode(code);
	}

	public List<AMCheckPoints> weeklyAMCheckPointByMachineCode(String code) {
		// TODO Auto-generated method stub
		return amCheckPointsRepo.weeklyAMCheckPointByMachineCode(code);
	}

	@Override
	
	public List<AMCheckPoints> getPendingAmCheckPointByMachineId(int machineId) {
		// TODO Auto-generated method stub
		List<AMCheckPoints> returnPoints=new ArrayList<AMCheckPoints>();
		
		List<AMCheckPoints> allCheckPoint= amCheckPointsRepo.getAMCheckPointByMachine(machineId);
		for(AMCheckPoints points:allCheckPoint){
			if(points.getFrequency().equalsIgnoreCase("Daily")){
				Optional<AMCheckPointsValue> optional=aMCheckPointsValueRepo.getTodayAMCheckPointValueByAMCheckPoint(points.getAmCheckPointId(),new Date());
					if(! optional.isPresent()){
						returnPoints.add(points);
					}
			
			}else if(points.getFrequency().equalsIgnoreCase("Weekly")){
				Calendar calender=Calendar.getInstance();
				calender.setTime(new Date());
				//int dayOfWeek=calender.get(Calendar.DAY_OF_WEEK);
				calender.set(Calendar.DAY_OF_WEEK, calender.getFirstDayOfWeek());
				
				Calendar calender2= Calendar.getInstance();
				calender2.setTime(calender.getTime());
				calender2.add(Calendar.DATE, 6);
				
				Optional<AMCheckPointsValue> optional=aMCheckPointsValueRepo.getThisweekAMCheckPointValueByAMCheckPoint(points.getAmCheckPointId(),calender.getTime(),calender2.getTime());
				if(! optional.isPresent()){
					returnPoints.add(points);
				}
			}else if(points.getFrequency().equalsIgnoreCase("Monthly")){
				Calendar calender=Calendar.getInstance();
				calender.setTime(new Date());
				//int dayOfWeek=calender.get(Calendar.DAY_OF_WEEK);
				calender.set(Calendar.DAY_OF_MONTH,1);
				System.out.println("Fist Day Of MOnth :: "+calender.getTime());
				int res = calender.getActualMaximum(Calendar.DATE);
				Calendar calender2= Calendar.getInstance();
				calender2.setTime(calender.getTime());
				calender2.add(Calendar.DATE, res);
				System.out.println("Last Day Of MOnth :: "+calender2.getTime());

				Optional<AMCheckPointsValue> optional=aMCheckPointsValueRepo.getMonthyAMCheckPointValueByAMCheckPoint(points.getAmCheckPointId(),calender.getTime(),calender2.getTime());
				if(! optional.isPresent()){
					returnPoints.add(points);
				}
			}
			
			
		}
		return returnPoints;
	}
}
