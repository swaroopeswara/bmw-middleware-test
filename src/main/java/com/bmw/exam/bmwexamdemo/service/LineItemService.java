package com.bmw.exam.bmwexamdemo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bmw.exam.bmwexamdemo.model.LineItem;
import com.bmw.exam.bmwexamdemo.repository.LineItemRepository;

@Service
public class LineItemService {
	
	@Autowired
	LineItemRepository invoiceRepo;

	public List<LineItem> getAllInvoices() {
		return invoiceRepo.findAll();
	}

	public List<LineItem> addItem(List<LineItem> item) {
		return invoiceRepo.saveAll(item);
	}


}
