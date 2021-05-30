package com.pusp.excelintegration.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pusp.excelintegration.model.ProductCategory;

@Repository
public interface ExcelRepository extends JpaRepository<ProductCategory, Integer>{

}
