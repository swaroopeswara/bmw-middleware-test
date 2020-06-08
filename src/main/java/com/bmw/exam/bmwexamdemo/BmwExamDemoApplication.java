package com.bmw.exam.bmwexamdemo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.Transactional;

import com.bmw.exam.bmwexamdemo.model.Invoice;
import com.bmw.exam.bmwexamdemo.model.LineItem;
import com.bmw.exam.bmwexamdemo.service.InvoiceServiceAPI;

@ComponentScan(basePackages = { "com.bmw.exam" })
@EnableJpaRepositories("com.bmw.exam")
@EntityScan("com.bmw.exam")
@EnableAutoConfiguration
@SpringBootApplication
public class BmwExamDemoApplication  implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(BmwExamDemoApplication.class, args);
	}

	@Autowired
	InvoiceServiceAPI invoiceService;

	@Transactional
	public void run(String... strings) throws Exception {

		final Invoice invoiceA = new Invoice("First Invoicee", new Date(), 10l);
		List<LineItem> lineItems = new ArrayList<LineItem>();
		lineItems.add(new LineItem(10l, "Description1", invoiceA, new BigDecimal(100).setScale(2)));
		lineItems.add(new LineItem(11l, "Description2", invoiceA, new BigDecimal(100).setScale(2)));
		lineItems.add(new LineItem(12l, "Description3", invoiceA, new BigDecimal(100).setScale(2)));
		invoiceA.setLine(lineItems);

		invoiceService.addInvoice(invoiceA);
	}

}
