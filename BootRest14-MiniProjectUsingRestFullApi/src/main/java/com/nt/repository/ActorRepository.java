package com.nt.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.nt.entity.ActorEntity;

import jakarta.transaction.Transactional;

public interface ActorRepository extends JpaRepository<ActorEntity, Integer> {

	@Query("FROM ActorEntity WHERE id IN (:ids) ORDER BY id DESC")
	List<ActorEntity> findActorByMultipleId(@Param("ids") List<Integer> asList);

	@Query("FROM ActorEntity WHERE aname = :name")
	List<ActorEntity> findActorByName(@Param("name") String name);

	@Query("delete FROM ActorEntity WHERE fees>=:start and fees<=:end")
	@Transactional
	@Modifying
	int removeActorByFees(double start, double end);



}
