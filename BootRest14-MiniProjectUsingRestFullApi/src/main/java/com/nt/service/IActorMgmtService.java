package com.nt.service;

import java.util.List;

import com.nt.entity.ActorEntity;
import com.nt.vo.ActorVo;

public interface IActorMgmtService {

	public String registerActor(ActorVo actorVo);
	
	public String registerGroupoOfActors(List<ActorVo> actorVo);
	
	public Iterable<ActorVo> fetchAllActors();
	
	public ActorEntity fetchActorById(int id);
	
	public List<ActorEntity> fetchMultipleActorById(int id1, int id2);
	
	public String updateActorDetails(ActorVo actorVo);
	
	public List<ActorEntity> fetchActorByName(String name);
	
	public String deleteActorById(int id);
	
	public String deleteActorByFeesRange(double start, double end);

}
