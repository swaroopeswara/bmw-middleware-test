package com.bmw.exam.bmwexamdemo.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PostLoad;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "line_item")
@Setter
@Getter
@ToString
public class LineItem {

	@Id
	@Column(name = "item_id", nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private Long quantity;

	private String description;

	private BigDecimal unitPrice;

	@Transient
	private BigDecimal lineItemTotal;

	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(foreignKey = @ForeignKey(name = "id"), name = "id", nullable = false)
	private Invoice invoice;

	@PostLoad
	public void getItemsTotal() {
		this.lineItemTotal = new BigDecimal(getUnitPrice().longValue() * getQuantity()).setScale(2);
	}

	public LineItem() {
		super();
	}

	public LineItem(Long quantity, String description, Invoice invoice,BigDecimal unitPrice) {
		this.quantity = quantity;
		this.description = description;
		this.invoice = invoice;
		this.unitPrice = unitPrice;
	}

	
	
}
