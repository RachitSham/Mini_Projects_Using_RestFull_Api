package com.nt.service;

import java.util.List;

import com.nt.entity.TouristEntity;
import com.nt.vo.TouristVo;

public interface ITouristHubMgmtService {

	public String registerTourist(TouristVo touristVo);

	public String registerTouristBatch(Iterable<TouristVo> touristVo);

	public Iterable<TouristVo> showAllTourist();

	public List<TouristEntity> showTouristByCities(String city1, String city2, String city3);

	public TouristEntity fetchTouristById(int id);

	public List<TouristEntity> fetchTouristByMultipleId(int id1, int id2);

	public String updateTouristDetails(TouristVo TouristVo);

	public List<TouristEntity> fetchTouristByName(String name);

	public String updateTouristById(int id, double hikePercentage);
	
	public String deleteTouristById(int id);
	
	public String deleteTouristByBudgetRange(double start, double end);	
}
