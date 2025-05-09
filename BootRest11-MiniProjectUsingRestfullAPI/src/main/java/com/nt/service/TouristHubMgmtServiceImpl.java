package com.nt.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nt.entity.TouristEntity;
import com.nt.exception.TouristNotFoundException;
import com.nt.repository.TouristHubRepository;
import com.nt.vo.TouristVo;

@Service
public class TouristHubMgmtServiceImpl implements ITouristHubMgmtService {

	@Autowired
	private TouristHubRepository touristHubRepository;

	@Override
	public String registerTourist(TouristVo touristVo) {
		// To Convert Touristvo class object to TouristEntity class obbject
		TouristEntity touristEntity = new TouristEntity();
		BeanUtils.copyProperties(touristVo, touristEntity);
		touristEntity.setCreatedBy(System.getProperty("user.name"));

		int idVal = touristHubRepository.save(touristEntity).getId();
		return "Tourist is Registered Successfully !! " + idVal;
	}

	@Override
	public String registerTouristBatch(Iterable<TouristVo> touristVo) {
		// To Convert List<touristvo> to List<TouristEntity>Objects
		List<TouristEntity> listEntity = new ArrayList<TouristEntity>();
		touristVo.forEach(vo -> {
			TouristEntity entity = new TouristEntity();
			BeanUtils.copyProperties(vo, entity);
			entity.setCreatedBy(System.getProperty("user.name"));
			listEntity.add(entity);
		});

		// use save all Objects
		Iterable<TouristEntity> savedEntities = touristHubRepository.saveAll(listEntity);
		List<Integer> ids = StreamSupport.stream(savedEntities.spliterator(), false).map(TouristEntity::getId)
				.collect(Collectors.toList());
		return ids.size() + "Successfully saved all records to the database!" + ids;
	}

	@Override
	public Iterable<TouristVo> showAllTourist() {

		Iterable<TouristEntity> listEntities = touristHubRepository.findAll();
		// Convert List Of Entities To List Of Vo
		List<TouristVo> listVo = new ArrayList<TouristVo>();
		listEntities.forEach(entity -> {
			TouristVo vo = new TouristVo();
			BeanUtils.copyProperties(entity, vo);
			listVo.add(vo);
		});
		return listVo;
	}

	@Override
	public List<TouristEntity> showTouristByCities(String city1, String city2, String city3) {
		return touristHubRepository.findTouristByCities(city1, city2, city3);
	}

	@Override
	public TouristEntity fetchTouristById(int id) {
		return touristHubRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException(id + " Tourist ID not found in the database "));
	}

	@Override
	public List<TouristEntity> fetchTouristByMultipleId(int id1, int id2) {
		return touristHubRepository.findTouristsByIds(Arrays.asList(id1, id2));
	}

	@Override
	public String updateTouristDetails(TouristVo touristVo) {
		TouristEntity entity = touristHubRepository.findById(touristVo.getId())
				.orElseThrow(() -> new IllegalArgumentException("Invalid Id"));
		BeanUtils.copyProperties(touristVo, entity);
		entity.setUpdatedBy("osuser");
		int idVal = touristHubRepository.save(entity).getId();
		return idVal + " Tourist details have been successfully updated ";
	}

	@Override
	public List<TouristEntity> fetchTouristByName(String name) {
		return touristHubRepository.findTouristByName(name);

	}

	@Override
	public String updateTouristById(int id, double hikePercentage) {
		// get the tourist id
		Optional<TouristEntity> optional = touristHubRepository.findById(id);
		if (optional.isPresent()) {

			// get existing tourist budget of tourist
			TouristEntity touristEntity = optional.get();
			double budget = touristEntity.getBudget();
			double newBudget = budget + (budget * hikePercentage / 100.0);

			touristEntity.setUpdatedBy("osuser");
			// update tourist with new budget
			touristEntity.setBudget(newBudget);
			touristHubRepository.save(touristEntity);

			return " Tourist budget successfully revised — the updated amount is " + newBudget;

		} else {
			throw new TouristNotFoundException(id + " Unable to update: Tourist does not exist in the records ");
		}

	}

	@Override
	public String deleteTouristById(int id) {
		Optional<TouristEntity> optional = touristHubRepository.findById(id);
		if (optional.isPresent()) {
			touristHubRepository.deleteById(id);

			return id + " Tourist with the given ID was found and deleted successfully !";
		} else {
			throw new TouristNotFoundException(id + " Deletion failed — no matching record found for the provided ID ");
		}

	}

	@Override
	public String deleteTouristByBudgetRange(double start, double end) {
	int count  = touristHubRepository.removeTouristByBudgetRange(start, end);
	return null;
	}
}
