package com.example.ec.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Tour implements Serializable {
	
	private static final long serialVersionUID = 1014987788520460267L;
	
	@Id
	@GeneratedValue
	private Integer id;
	
	private String title;
	
	@Column(length = 2000)
	private String description;
	
	@Column(length = 2000)
	private String blurb;
	
	private Integer price;
	
	private String duration;
	
	@Column(length = 2000)
	private String bullets;
	
	private String keywords;
	
	@ManyToOne
	private TourPackage tourPackage;
	
	private Difficulty difficulty;
	
	private Region region;
	

}
