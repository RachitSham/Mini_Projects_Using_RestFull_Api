package com.nt.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.web.jsf.FacesContextUtils;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Version;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Entity
@Table(name = "JPA_TOURIST_HUB")
@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class TouristEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@NonNull
	@Column(length = 30)
	private String name;

	@Column(length = 30)
	@NonNull
	private String email;

	@Column(length = 10)
	@NonNull
	private Long mobileNo;

	@Column(length = 30)
	@NonNull
	private String city;

	@Column(length = 60)
	@NonNull
	private String packageType;

	@NonNull
	private Double budget;

	// these are Optional But Very Helpful
	@Version
	@Column(name = "Update_Count")
	private Integer updateCount;

	@Column(name = "Created_BY", length = 30)
	private String createdBy;

	@Column(name = "Updated_BY", length = 30)
	private String updatedBy;

	@Column(name = "Created_Time", updatable = false, insertable = true)
	@CreationTimestamp
	private LocalDateTime createdTime;

	@Column(name = "Update_Time", updatable = false, insertable = true)
	@UpdateTimestamp
	private LocalDateTime updateTime;

}
