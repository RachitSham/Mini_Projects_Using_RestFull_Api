package com.nt.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

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
@Table(name = "JPA_ACTOR_API")
@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class ActorEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@NonNull
	@Column(name = "Actor_Name", length = 40)
	private String aname;

	@NonNull
	@Column(name = "Category", length = 40)
	private String category;

	@NonNull
	@Column(name = "Address", length = 50)
	private String address;

	@NonNull
	@Column(name = "Nationality", length = 30)
	private String nationality;

	@NonNull
	@Column(name = "Contact_Info")
	private Long contactInfo;

	@NonNull
	@Column(name = "Fees")
	private Float fees;

	// Versoning
	@Version
	@Column(name = "Update_Count")
	private Integer updateCount;

	// Meta Data
	@Column(name = "Created_By", length = 30)
	private String createdBy;

	@Column(name = "Updated_By", length = 30)
	private String updatedBy;

	@CreationTimestamp
	@Column(name = "Created_Date", insertable = true, updatable = false)
	private LocalDateTime createdDate;

	@UpdateTimestamp
	@Column(name = "Update_Time", insertable = true, updatable = false)
	private LocalDateTime updatedDate;

}
