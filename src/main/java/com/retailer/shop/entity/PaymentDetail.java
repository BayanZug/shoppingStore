package com.retailer.shop.entity;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.DecimalMin;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.lang.NonNull;
/**
 * Class that connects the payment table
 */
@Entity
public class PaymentDetail {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "payment_id")
    private Long id;
	@NonNull
    private String userName;
	@DecimalMin(value = "0")
    private Double amount;
	
	@Column(nullable = false,columnDefinition = "TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP")
	private Timestamp createdTime;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	public Timestamp getCreatedTime() {
		return createdTime;
	}
	public void setCreatedTime(Timestamp createdTime) {
		this.createdTime = createdTime;
	}


	
}
