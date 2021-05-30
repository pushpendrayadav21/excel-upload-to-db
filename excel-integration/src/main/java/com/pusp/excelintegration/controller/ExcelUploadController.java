package com.pusp.excelintegration.controller;

import java.io.InputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import com.pusp.excelintegration.service.ExcelService;

@Controller
public class ExcelUploadController {

	@Autowired
	private ExcelService excelService;
	
	Logger LOG = LoggerFactory.getLogger(ExcelUploadController.class);
	@GetMapping
	public String uploadFile() {
		return "index";
	}
	
	@PostMapping("upload")
    public String upload(@RequestParam("file") MultipartFile file,Model model) throws Exception {
		LOG.info(">upload");
		String msg = null;
		try {
			InputStream inputStream = file.getInputStream();
			excelService.saveCategories(inputStream);
			msg = "File Uploaded successfully";
			model.addAttribute("message",msg);
		} catch (Exception e) {
			msg = "An error occurred while trying to upload excel file: "+e;
			model.addAttribute("message",msg);
			
		}
		LOG.info("< upload");
        return "index";
    }
}
