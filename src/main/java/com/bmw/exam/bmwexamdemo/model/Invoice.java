/**
 * 
 */
package com.bmw.exam.bmwexamdemo.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PostLoad;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author Swaroop
 *
 */
@Entity
@Table(name = "Invoice")
@Setter
@Getter
@ToString
public class Invoice implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "client")
	private String client;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "invoiceDate")
	private Date invoiceDate;

	@Transient
	private BigDecimal subTotal;

	private Long vatRate;

	@Transient
	private BigDecimal vat;

	@Transient
	private BigDecimal total;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "invoice")
	private List<LineItem> line;

	@PostLoad
	public void getCalcTotal() {

		//System.out.println(getLine());
		//getLine().stream().forEach(System.out::println);
		
		//logn sum = getLine().stream().mapToInt(obj -> obj.getLineItemTotal()).sum();
		
		/*BigDecimal value3 = new BigDecimal(this.vatRate);
		int newScale = 2;
		this.vat = value3.setScale(newScale);

		int inputValue2 = 200;
		BigDecimal value2 = new BigDecimal(inputValue2);
		this.subTotal = value2.setScale(newScale);

		this.total = this.getSubTotal().add(this.getVat());
		*/
	}

	

	public Invoice() {
	}



	public Invoice(String client, Date invoiceDate, Long vatRate) {
		this.client = client;
		this.invoiceDate = invoiceDate;
		this.vatRate = vatRate;
	}


}
