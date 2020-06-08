package com.bmw.exam.bmwexamdemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bmw.exam.bmwexamdemo.model.LineItem;

public interface LineItemRepository  extends JpaRepository<LineItem, Long>  {

}
