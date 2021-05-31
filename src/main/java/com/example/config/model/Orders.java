package com.example.config.model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.Setter;

/**
 * @author shivam.rai
 *
 */
@Entity
@Getter
@Setter
public class Orders {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long orderId;
	
	private String userId;
	
	private double totalPrice;
	
	@Column(columnDefinition = "DATE")
	private LocalDate date;

	@Column(columnDefinition = "TIME")
	private LocalTime time;
	
	@OneToMany(cascade = { CascadeType.ALL })
	@JoinColumn(name="orderId")
	private List<OrderItems> orderItemsList;
	
	private String paymentStatus;
	
}
 