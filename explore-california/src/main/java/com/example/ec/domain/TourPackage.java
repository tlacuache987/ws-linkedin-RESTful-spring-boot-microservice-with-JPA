package com.example.ec.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class TourPackage implements Serializable {

	private static final long serialVersionUID = -3873188561449036395L;

	@Id
	private String code;

	private String name;
}
