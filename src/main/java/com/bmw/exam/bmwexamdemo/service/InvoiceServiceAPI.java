package com.bmw.exam.bmwexamdemo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.bmw.exam.bmwexamdemo.model.Invoice;

@Service
public interface InvoiceServiceAPI {
	public List<Invoice> getAllInvoices();


	public Invoice addInvoice(Invoice invoice);

	public Optional<Invoice> getInvoiceById(Long invoiceId);
}
