package com.bmw.exam.bmwexamdemo.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bmw.exam.bmwexamdemo.model.Invoice;
import com.bmw.exam.bmwexamdemo.model.LineItem;
import com.bmw.exam.bmwexamdemo.repository.InvoiceRepository;

@Service
public class InvoiceServiceImpl implements InvoiceServiceAPI {

	@Autowired
	InvoiceRepository invoiceRepo;

	@Override
	public List<Invoice> getAllInvoices() {
		
		List<Invoice> invoiceList = invoiceRepo.findAll();
		for(Invoice i: invoiceList) {
			List<LineItem> li = i.getLine();
			Long sum = 0l;
			for (LineItem item : li) {
				sum = sum + item.getLineItemTotal().longValue();
			}
			i.setVat(new BigDecimal(sum * i.getVatRate() / 100).setScale(2));
			i.setTotal(new BigDecimal(sum + (sum * i.getVatRate() / 100)).setScale(2));
			i.setSubTotal(new BigDecimal(sum).setScale(2));
		}
		
		
		return invoiceList;
	}

	@Override
	public Optional<Invoice> getInvoiceById(Long invoiceId) {
		return invoiceRepo.findById(invoiceId);
	}

	@Override
	public Invoice addInvoice(Invoice invoice) {
		return invoiceRepo.save(invoice);
	}

}
