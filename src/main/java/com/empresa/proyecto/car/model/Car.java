package com.empresa.proyecto.car.model;

import java.io.Serializable;

import org.springframework.data.annotation.Id;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.relational.core.mapping.Column;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Car implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(value = "ID")
	private Long id;
	@Column(value = "MODEL")
	private String model;
	@Column(value = "BRAND")
	private String brand;
	@Column(value = "COLOR")
	private String color;

}
