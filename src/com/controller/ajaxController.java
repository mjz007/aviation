package com.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pojo.cities;
import com.pojo.fight;
import com.serviceImpl.serviceImpl;
import com.tool.Conversion;

@Controller
public class ajaxController {
	@Autowired
	private serviceImpl service;
	
	Conversion conv = new Conversion();
	
	
	@RequestMapping("/menu")
	@ResponseBody
	public List<cities> menu(String cnty) throws Exception{
		List<cities> city = service.CntyCity(cnty);
		return city;
	}
	
	@RequestMapping("/provmenu")
	@ResponseBody
	public List<cities> provmenu(String prov,String region) throws Exception{
		List<cities> city = service.ProvCity(prov,region);
		return city;
	}
	
	@RequestMapping("/regionmenu")
	@ResponseBody
	public List<cities> regionmenu(String region) throws Exception{
		List<cities> prov = service.RegionProv(region);
		return prov;
	}
}
