package com.pusp.excelintegration.service;

import java.io.InputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.pusp.excelintegration.model.*;
import com.pusp.excelintegration.dao.ExcelRepository;
import com.pusp.excelintegration.helper.ExcelHelper;

@Service
public class ExcelService {
	
	@Autowired
	private ExcelRepository repository;
	
	public List<ProductCategory> saveCategories(InputStream is) throws Exception{
		List<ProductCategory> excelToObject = ExcelHelper.excelTo(is);
		List<ProductCategory> saveAll = repository.saveAll(excelToObject);
		return saveAll;
	}
	

}
