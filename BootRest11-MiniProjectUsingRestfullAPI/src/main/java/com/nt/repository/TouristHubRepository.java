package com.nt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.nt.entity.TouristEntity;

import jakarta.transaction.Transactional;

@Repository
public interface TouristHubRepository extends JpaRepository<TouristEntity, Integer> {

	@Query("FROM TouristEntity WHERE city IN(:city1,:city2,:city3) ORDER BY name asc")
	List<TouristEntity> findTouristByCities(String city1, String city2, String city3);

	@Query("FROM TouristEntity WHERE id IN (:ids) ORDER BY id DESC")
	List<TouristEntity> findTouristsByIds(@Param("ids") List<Integer> ids);

	@Query("FROM TouristEntity WHERE name=:name")
	List<TouristEntity> findTouristByName(String name);

	@Query("delete FROM TouristEntity WHERE budget>=:start and budget<=:end")
	@Transactional
	@Modifying
	public int removeTouristByBudgetRange(double start, double end);

}
