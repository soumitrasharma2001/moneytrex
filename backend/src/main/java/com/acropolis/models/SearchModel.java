package com.acropolis.models;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SearchModel {
	@NotBlank(message="This month field is required")
	private Integer month;
	@NotBlank(message="This year field is required")
	private Integer year;
	
	private String type;
}
