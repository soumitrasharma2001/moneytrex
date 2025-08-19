package com.acropolis.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SummaryModel {
	private Float currentBalance;
	private Float income;
	private Float expense;
	private Integer expenseLimit;
}
