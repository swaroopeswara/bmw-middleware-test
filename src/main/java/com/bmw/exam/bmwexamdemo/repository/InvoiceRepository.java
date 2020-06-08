package com.bmw.exam.bmwexamdemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bmw.exam.bmwexamdemo.model.Invoice;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, Long> {

}
