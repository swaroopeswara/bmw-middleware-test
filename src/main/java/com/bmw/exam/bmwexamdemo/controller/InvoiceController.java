package com.bmw.exam.bmwexamdemo.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bmw.exam.bmwexamdemo.model.Invoice;
import com.bmw.exam.bmwexamdemo.model.LineItem;
import com.bmw.exam.bmwexamdemo.service.InvoiceServiceAPI;
import com.bmw.exam.bmwexamdemo.service.LineItemService;

@RestController
@RequestMapping("/")
public class InvoiceController {

	@Autowired
	InvoiceServiceAPI invoiceService;

	@Autowired
	LineItemService lineItemService;

	@GetMapping("/invoices")
	public List<Invoice> getAllInvoices() {
		return invoiceService.getAllInvoices();
	}

	@GetMapping("/invoices/{id}")
	public Optional<Invoice> getInvoiceById(@PathVariable(value = "id") Long invoiceId) {
		return invoiceService.getInvoiceById(invoiceId);
	}

	@PostMapping("/invoices")
	public ResponseEntity<?> addInvoice(@RequestBody Invoice invoice) {

		final Invoice invoiceA = new Invoice(invoice.getClient(), new Date(), invoice.getVatRate());
		List<LineItem> lineItems = new ArrayList<LineItem>();
		for (LineItem items : invoice.getLine()) {
			lineItems.add(new LineItem(items.getQuantity(), items.getDescription(), invoiceA, items.getUnitPrice()));
		}
		invoiceA.setLine(lineItems);
		/*
		 * final Invoice invoiceA = new Invoice("Swaroop",new Date(),10l);
		 * List<LineItem> lineItems = new ArrayList<LineItem>(); lineItems.add(new
		 * LineItem(10l, "Desc1", invoiceA,new BigDecimal(100).setScale(2)));
		 * lineItems.add(new LineItem(11l, "Desc1", invoiceA,new
		 * BigDecimal(100).setScale(2))); lineItems.add(new LineItem(12l, "Desc1",
		 * invoiceA,new BigDecimal(100).setScale(2))); invoiceA.setLine(lineItems);
		 */
		return ResponseEntity.status(HttpStatus.OK).body(invoiceService.addInvoice(invoiceA));
	}
}
