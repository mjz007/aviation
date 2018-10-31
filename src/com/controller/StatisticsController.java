package com.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.pojo.cities;
import com.pojo.fight;
import com.serviceImpl.serviceImpl;
import com.tool.Conversion;

import net.sf.json.JSONArray;

@Controller
public class StatisticsController {
	
	@Autowired
	private serviceImpl service;
	
	Conversion conv = new Conversion();
	
	@RequestMapping("/all")
	public String Statistical(Model model,String cnty,String region,String prov,String city){
		if(!city.equals(""))
		{
			model.addAttribute("city", city);
			model.addAttribute("cnty", cnty);
			return "redirect:/Statistical";
		}
		else if(!prov.equals(""))
		{
			model.addAttribute("prov", prov);
			return "redirect:/Prov";
		}
		else if(!region.equals(""))
		{
			model.addAttribute("region", region);
			return "redirect:/Region";
		}
		else if(!cnty.equals(""))
		{
			model.addAttribute("cnty", cnty);
			return "redirect:/Cnty";
		}
		else
		return "redirect:/index";
	}
	
	//统计某个城市的航班
		@RequestMapping("/Statistical")
		public String Statistical(Model model,String city,String cnty) throws Exception{
			HashMap<String,Object> m = new HashMap<String, Object>();
			m.put("city", city);
			m.put("cnty", cnty);
			cities cities = service.mid(m);
			int mid = cities.getMid();
			List<fight> fight = service.Statistical(mid);
			if(fight.size()==0)
			{
				JOptionPane.showMessageDialog(new JFrame().getContentPane(), "暂无该航班！", "系统信息", JOptionPane.INFORMATION_MESSAGE); 
				return "redirect:/index";
			}
			List id = new ArrayList();
			int size = fight.size();	
			JSONArray arr = new JSONArray();
			for(int i = 0; i<size;i++)
			{
				HashMap<String,Object> map = new HashMap<String, Object>();
				HashMap<String,Object> map1 = new HashMap<String, Object>();
				JSONArray jArray = new JSONArray();
				int ori = fight.get(i).getOriginating();
				int des = fight.get(i).getDestination();
				cities or = service.city(ori);
				fight.get(i).setChufadi(or.getCity());
				map.put("chufa", or);
				cities de = service.city(des);
				fight.get(i).setMudi(de.getCity());
				map1.put("daoda", de);
				jArray.add(map);
				jArray.add(map1);
				id.add(ori);
				id.add(des);
				arr.add(jArray);
			}
			List newList = new ArrayList(new HashSet(id));
			List<cities> citie = new ArrayList();
			for(int i = 0 ;i<newList.size();i++)
			{
				int getid = (int) newList.get(i);
				cities c = service.city(getid);
				citie.add(c);
			}
			fight = conv.getConversion(fight);
			List aviationNum = new ArrayList();
			for(int i = 0;i<fight.size();i++)
			{
				if(!fight.get(i).getAviationNum().equals("无"))
				{
					aviationNum.add(fight.get(i).getAviationNum());
				}
			}
			aviationNum = new ArrayList<String>(new HashSet<String>(aviationNum));
			model.addAttribute("aviationNum", aviationNum.size());
			model.addAttribute("cities", citie);
			model.addAttribute("fight", fight);
			model.addAttribute("lines",arr);
			return "Statistical";
		}
		
		//统计某个地区的航班
		@RequestMapping("/Region")
		public String Region(Model model,String region) throws Exception{
			List<fight> fight = service.diqu(region);
			int size = fight.size();	
			JSONArray arr = new JSONArray();
			List id = new ArrayList();
			for(int i = 0; i<size;i++)
			{
				HashMap<String,Object> map = new HashMap<String, Object>();
				HashMap<String,Object> map1 = new HashMap<String, Object>();
				JSONArray jArray = new JSONArray();
				int ori = fight.get(i).getOriginating();
				int des = fight.get(i).getDestination();
				cities or = service.city(ori);
				map.put("chufa", or);
				cities de = service.city(des);
				map1.put("daoda", de);
				jArray.add(map);
				jArray.add(map1);
				id.add(ori);
				id.add(des);
				arr.add(jArray);
			}
			List newList = new ArrayList(new HashSet(id));
			List<cities> citie = new ArrayList();
			for(int i = 0 ;i<newList.size();i++)
			{
				int getid = (int) newList.get(i);
				cities c = service.city(getid);
				citie.add(c);
			}
			fight = conv.getConversion(fight);
			List aviationNum = new ArrayList();
			for(int i = 0;i<fight.size();i++)
			{
				if(!fight.get(i).getAviationNum().equals("无"))
				{
					aviationNum.add(fight.get(i).getAviationNum());
				}
			}
			aviationNum = new ArrayList<String>(new HashSet<String>(aviationNum));
			model.addAttribute("aviationNum", aviationNum.size());
			model.addAttribute("cities", citie);
			model.addAttribute("fight", fight);
			model.addAttribute("lines",arr);
			return "/Region";
		}
		
		//统计某个省的航班
		@RequestMapping("/Prov")
		public String Prov(Model model,String prov) throws Exception{
			List<fight> fight = service.prov(prov);
			int size = fight.size();	
			JSONArray arr = new JSONArray();
			List id = new ArrayList();
			for(int i = 0; i<size;i++)
			{
				HashMap<String,Object> map = new HashMap<String, Object>();
				HashMap<String,Object> map1 = new HashMap<String, Object>();
				JSONArray jArray = new JSONArray();
				int ori = fight.get(i).getOriginating();
				int des = fight.get(i).getDestination();
				cities or = service.city(ori);
				map.put("chufa", or);
				cities de = service.city(des);
				map1.put("daoda", de);
				jArray.add(map);
				jArray.add(map1);
				id.add(ori);
				id.add(des);
				arr.add(jArray);
			}
			List newList = new ArrayList(new HashSet(id));
			List<cities> citie = new ArrayList();
			for(int i = 0 ;i<newList.size();i++)
			{
				int getid = (int) newList.get(i);
				cities c = service.city(getid);
				citie.add(c);
			}
			fight = conv.getConversion(fight);
			List aviationNum = new ArrayList();
			for(int i = 0;i<fight.size();i++)
			{
				if(!fight.get(i).getAviationNum().equals("无"))
				{
					aviationNum.add(fight.get(i).getAviationNum());
				}
			}
			aviationNum = new ArrayList<String>(new HashSet<String>(aviationNum));
			model.addAttribute("aviationNum", aviationNum.size());
			model.addAttribute("fight", fight);
			model.addAttribute("cities", citie);
			model.addAttribute("lines",arr);
			return "/Region";
		}
		
		//统计某个国家的航班
		@RequestMapping("/Cnty")
		public String Cnty(Model model,String cnty) throws Exception{
			List<fight> fight = service.cnty(cnty);
			int size = fight.size();	
			JSONArray arr = new JSONArray();
			List id = new ArrayList();
			for(int i = 0; i<size;i++)
			{
				HashMap<String,Object> map = new HashMap<String, Object>();
				HashMap<String,Object> map1 = new HashMap<String, Object>();
				JSONArray jArray = new JSONArray();
				int ori = fight.get(i).getOriginating();
				int des = fight.get(i).getDestination();
				cities or = service.city(ori);
				map.put("chufa", or);
				cities de = service.city(des);
				map1.put("daoda", de);
				jArray.add(map);
				jArray.add(map1);
				id.add(ori);
				id.add(des);
				arr.add(jArray);
			}
			List newList = new ArrayList(new HashSet(id));
			List<cities> citie = new ArrayList();
			for(int i = 0 ;i<newList.size();i++)
			{
				int getid = (int) newList.get(i);
				cities c = service.city(getid);
				citie.add(c);
			}
			fight = conv.getConversion(fight);
			List aviationNum = new ArrayList();
			for(int i = 0;i<fight.size();i++)
			{
				if(!fight.get(i).getAviationNum().equals("无"))
				{
					aviationNum.add(fight.get(i).getAviationNum());
				}
			}
			aviationNum = new ArrayList<String>(new HashSet<String>(aviationNum));
			model.addAttribute("aviationNum", aviationNum.size());
			model.addAttribute("cities", citie);
			model.addAttribute("lines",arr);
			model.addAttribute("fight",fight);
			return "/Region";
		}
		

}
