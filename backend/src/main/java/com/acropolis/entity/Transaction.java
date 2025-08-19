package com.acropolis.entity;

import java.time.LocalDate;

import com.acropolis.models.TransactionModel;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Transaction {

		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private Integer transid;
		@Column(nullable = false)
		private String title;
		@Column(nullable = false)
		private LocalDate entrydate;
		@Column(nullable = false)
		private String type;
		@Column(nullable = false)
		private Float amount;
		private String description;
		private LocalDate created;
		private LocalDate updated;
		@ManyToOne
		@JoinColumn(name="customerid")
		private Users user;
		public Transaction(String title, LocalDate entrydate, String type, Float amount, String description,
				LocalDate created, LocalDate updated, Users user) {
			super();
			this.title = title;
			this.entrydate = entrydate;
			this.type = type;
			this.amount = amount;
			this.description = description;
			this.created = created;
			this.updated = updated;
			this.user = user;
		}
		public Transaction(TransactionModel model) {
			this.title=model.getTitle();
			this.type=model.getType();
			this.entrydate=model.getEntrydate();
			this.amount=model.getAmount();
			this.description=model.getDescription();
		}
		public Transaction update(TransactionModel model) {
			this.title=model.getTitle();
			this.type=model.getType();
			this.entrydate=model.getEntrydate();
			this.amount=model.getAmount();
			this.description=model.getDescription();
			this.setUpdated(LocalDate.now());
			return this;
		}
		public Transaction(Transaction other) {
	        this.title = other.getTitle();
	        this.entrydate = other.getEntrydate();
	        this.type = other.getType();
	        this.amount = other.getAmount();
	        this.description = other.getDescription();
	        this.created = other.getCreated();
	        this.updated = other.getUpdated();
	        this.user = other.getUser();
	    }
	
		
		
		
		
		
}
