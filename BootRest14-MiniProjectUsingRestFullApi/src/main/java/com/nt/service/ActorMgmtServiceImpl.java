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

import com.nt.entity.ActorEntity;
import com.nt.globalexceptions.ActorNotFoundException;
import com.nt.repository.ActorRepository;
import com.nt.vo.ActorVo;

@Service
public class ActorMgmtServiceImpl implements IActorMgmtService {

	@Autowired
	private ActorRepository actorRepository;

	@Override
	public String registerActor(ActorVo actorVo) {

		// To Convert Actorvo class object ActorEntity class obbject
		ActorEntity entity = new ActorEntity();
		BeanUtils.copyProperties(actorVo, entity);
		entity.setCreatedBy(System.getProperty("user.name"));

		int idVal = actorRepository.save(entity).getId();
		return "Actor is Registered Successfully !!" + idVal;
	}

	@Override
	public String registerGroupoOfActors(List<ActorVo> actorVo) {
		// To Convert List<Actorvo> to List<ActorsEntity>Objects
		List<ActorEntity> listEntity = new ArrayList<ActorEntity>();
		actorVo.forEach(vo -> {
			ActorEntity actorEntity = new ActorEntity();
			BeanUtils.copyProperties(vo, actorEntity);
			actorEntity.setCreatedBy(System.getProperty("user.name"));
			listEntity.add(actorEntity);
		});

		// use save all Objects
		Iterable<ActorEntity> saveEntity = actorRepository.saveAll(listEntity);
		List<Integer> ids = StreamSupport.stream(saveEntity.spliterator(), false).map(ActorEntity::getId)
				.collect(Collectors.toList());
		return ids.size() + "Successfully Saved All Records To The Database !! " + ids;
	}

	@Override
	public Iterable<ActorVo> fetchAllActors() {
		Iterable<ActorEntity> listEntities = actorRepository.findAll();
		// Convert List Of Entities To List Of Vo
		List<ActorVo> listVo = new ArrayList<ActorVo>();
		listEntities.forEach(entity -> {
			ActorVo vo = new ActorVo();
			BeanUtils.copyProperties(entity, vo);
			listVo.add(vo);
		});
		return listVo;
	}

	@Override
	public ActorEntity fetchActorById(int id) {
		return actorRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException(id + " Actor ID not found in the database "));
	}

	@Override
	public List<ActorEntity> fetchMultipleActorById(int id1, int id2) {
		return actorRepository.findActorByMultipleId(Arrays.asList(id1, id2));
	}

	@Override
	public String updateActorDetails(ActorVo actorVo) {
		ActorEntity entity = actorRepository.findById(actorVo.getId())
				.orElseThrow(() -> new IllegalArgumentException("Invalid Id"));
		BeanUtils.copyProperties(actorVo, entity);
		entity.setUpdatedBy("osuser");
		int idVal = actorRepository.save(entity).getId();
		return idVal + " Actor details have been successfully updated ";
	}

	@Override
	public List<ActorEntity> fetchActorByName(String name) {
		return actorRepository.findActorByName(name);
	}

	@Override
	public String deleteActorById(int id) {
		Optional<ActorEntity> optioinal = actorRepository.findById(id);
		if (optioinal.isPresent()) {
			actorRepository.deleteById(id);

			return id + " Actor with the given ID was found and deleted successfully !";
		} else {
			throw new ActorNotFoundException(id + " Deletion failed — no matching record found for the provided ID ");

		}
	}

	@Override
	public String deleteActorByFeesRange(double start, double end) {
	int count = actorRepository.removeActorByFees(start, end);
	return count==0? "Actor not found for deletion" : count + "No.of Actor are found and Deleted";
	}
}
