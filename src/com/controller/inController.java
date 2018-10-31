package com.controller;

import java.net.URLDecoder;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
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

import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pojo.Onefight;
import com.pojo.cities;
import com.pojo.fight;
import com.serviceImpl.serviceImpl;
import com.tool.Conversion;
import com.tool.DateJudge;
import com.tool.illegalChar;


@Controller
public class inController {
	@Autowired
	private serviceImpl service;
	
	Conversion conv = new Conversion();
	illegalChar iChar = new illegalChar();
	DateJudge jud = new DateJudge();
	
	@RequestMapping(value="/index")
	public String in(Model model) throws Exception{
		//查询所有存在航班的城市
		List cities =service.cities();
		//查询所有航班（不重复）
		List<Onefight> lines =service.lines();
		//统计航空公司数量
		int aviationNum = service.countAviationNum();
		//统计中国航班数量
		int China = service.counCnty();
		//查询所有城市名
		List result = service.daohan();
		//查询所有航班号（用于搜索提示）
		List fightNum = service.fightNum();
		//查询所有国家（用于下拉列表和提示）
		List cnty = service.allCnty();
		//查询繁忙的航班
		List<Onefight> fan = service.fanman();
		//查询所有机场
		List airports = service.airports();
		model.addAttribute("result", result);
		model.addAttribute("cities", cities);
		model.addAttribute("lines", lines);
		model.addAttribute("aviationNum", aviationNum);
		model.addAttribute("China", China);
		model.addAttribute("hanbanhao", fightNum);
		model.addAttribute("cnty", cnty);
		model.addAttribute("fanman", fan);
		model.addAttribute("airports", airports);
		return "index";
	}
	
	//查询某地到某地的航班信息
	@RequestMapping("/fightNum")
	public String selectFightNum(Model model,String begincity,String endcity,String date,String date1) throws Exception{
		//判断是否存在非法字符
		if(iChar.isSpecialChar(begincity))
		{
			JOptionPane.showMessageDialog(new JFrame().getContentPane(), "您的输入存在非法字符！", "友情提示", JOptionPane.INFORMATION_MESSAGE); 
			return "redirect:/index";
		}
		if(iChar.isSpecialChar(endcity))
		{
			JOptionPane.showMessageDialog(new JFrame().getContentPane(), "您的输入存在非法字符！", "友情提示", JOptionPane.INFORMATION_MESSAGE); 
			return "redirect:/index";
		}
		if(!date.equals(""))
		{
			if(!date1.equals(""))
			{
				//通过城市名查询城市信息
				cities chufa =  service.begincity(begincity);
				cities daoda = service.endcity(endcity);
				HashMap<String,Object> map = new HashMap<String, Object>();
				map.put("chufa", chufa.getMid());
				map.put("daoda", daoda.getMid());
				//查询某地到某地的航班
				List<fight> fight = service.linestyle(map);
				if(fight.size()==0)
				{
					JOptionPane.showMessageDialog(new JFrame().getContentPane(), "暂无该航班！", "友情提示", JOptionPane.INFORMATION_MESSAGE); 
					return "redirect:/index";
				}
				List<fight> lines  = new ArrayList();
				fight = conv.getConversion(fight);
				int size = fight.size();
				for(int i = 0;i<size;i++)
				{
					SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
					String sd = fight.get(i).getFightDate();
			        Date datet = sdf.parse(sd);
			        Date datef = sdf.parse(date);
			        Date dateto = sdf.parse(date1);
			        if(jud.belongCalendar(datet,datef,dateto))
			        {
			        	lines.add(fight.get(i));
			        }

				}
				if(lines.size()!=0)
				{
					List aviationNum = new ArrayList();
					for(int i = 0;i<lines.size();i++)
					{
						if(!lines.get(i).getAviationNum().equals("无"))
						{
							aviationNum.add(lines.get(i).getAviationNum());
						}
					}
					aviationNum = new ArrayList<String>(new HashSet<String>(aviationNum));
					double size1 = lines.size();
					double h = 0-size1/2.0/10.0-0.1;
					model.addAttribute("h",h);
					model.addAttribute("c", chufa);
					model.addAttribute("d", daoda);
					model.addAttribute("aviationNum", aviationNum.size());
					model.addAttribute("linestyle", lines);
				}
				else
				{
					JOptionPane.showMessageDialog(new JFrame().getContentPane(), "该日期暂无航班！", "友情提示", JOptionPane.INFORMATION_MESSAGE); 
					return "redirect:/index";
				}
			}
			else
			{
				cities chufa =  service.begincity(begincity);
				cities daoda = service.endcity(endcity);
				HashMap<String,Object> map = new HashMap<String, Object>();		
				map.put("chufa", chufa.getMid());
				map.put("daoda", daoda.getMid());
				List<fight> fight = service.linestyle(map);
				if(fight.size()==0)
				{
					JOptionPane.showMessageDialog(new JFrame().getContentPane(), "暂无该航班！", "系统信息", JOptionPane.INFORMATION_MESSAGE); 
					return "redirect:/index";
				}
				List<fight> lines  = new ArrayList();
				fight = conv.getConversion(fight);
				int size = fight.size();
				for(int i = 0;i<size;i++)
				{
					String sd = fight.get(i).getFightDate();
			        if(date.equals(sd))
			        {
			        	lines.add(fight.get(i));
			        }
				}
				if(lines.size()!=0)
				{
					List aviationNum = new ArrayList();
					for(int i = 0;i<lines.size();i++)
					{
						if(!lines.get(i).getAviationNum().equals("无"))
						{
							aviationNum.add(lines.get(i).getAviationNum());
						}
					}
					aviationNum = new ArrayList<String>(new HashSet<String>(aviationNum));
					double size1 = lines.size();
					double h = 0-size1/2.0/10.0-0.1;
					model.addAttribute("h",h);
					model.addAttribute("c", chufa);
					model.addAttribute("d", daoda);
					model.addAttribute("aviationNum", aviationNum.size());
					model.addAttribute("linestyle", lines);
				}
				else
				{
					JOptionPane.showMessageDialog(new JFrame().getContentPane(), "该日期暂无航班！", "友情提示", JOptionPane.INFORMATION_MESSAGE); 
					return "redirect:/index";
				}
			}
		}
		else{			
			cities chufa =  service.begincity(begincity);
			cities daoda = service.endcity(endcity);
			HashMap<String,Object> map = new HashMap<String, Object>();
			map.put("chufa", chufa.getMid());
			map.put("daoda", daoda.getMid());
			List<fight> lines = service.linestyle(map);
			if(lines.size()==0)
			{
				JOptionPane.showMessageDialog(new JFrame().getContentPane(), "暂无该航班！", "系统信息", JOptionPane.INFORMATION_MESSAGE); 
				return "redirect:/index";
			}
			lines = conv.getConversion(lines);
			List aviationNum = new ArrayList();
			for(int i = 0;i<lines.size();i++)
			{
				if(!lines.get(i).getAviationNum().equals("无"))
				{
					aviationNum.add(lines.get(i).getAviationNum());
				}
			}
			aviationNum = new ArrayList<String>(new HashSet<String>(aviationNum));
			double size1 = lines.size();
			double h = 0-size1/2.0/10.0-0.1;
			model.addAttribute("h",h);
			model.addAttribute("c", chufa);
			model.addAttribute("d", daoda);
			model.addAttribute("aviationNum", aviationNum.size());
			model.addAttribute("linestyle", lines);
		}
		return "fightNum";
	}
	
	//点选
	@RequestMapping("/onfightNum")
	public String selectOnFightNum(Model model,String begincity,String endcity) throws Exception
	{	
		cities chufa =  service.begincity(begincity);
		cities daoda = service.endcity(endcity);
		HashMap<String,Object> map = new HashMap<String, Object>();
		map.put("chufa", chufa.getMid());
		map.put("daoda", daoda.getMid());
		List<fight> lines = service.linestyle(map);
		if(lines.size()==0)
		{
			JOptionPane.showMessageDialog(new JFrame().getContentPane(), "暂无该航班！", "友情提示", JOptionPane.INFORMATION_MESSAGE); 
			return "redirect:/index";
		}
		double size = lines.size();
		double h = 0-size/2.0/10.0-0.1;
		model.addAttribute("h",h);
		model.addAttribute("c", chufa);
		model.addAttribute("d", daoda);
		model.addAttribute("linestyle", lines);
		return "fightNum";
	}

	
	//查询某个航班号的信息
	@RequestMapping("/fight")
	public String fight(Model model,String fightNum) throws Exception{
		if(iChar.isSpecialChar(fightNum))
		{
			JOptionPane.showMessageDialog(new JFrame().getContentPane(), "您的输入存在非法字符！", "友情提示", JOptionPane.INFORMATION_MESSAGE); 
			return "redirect:/index";
		}
		List<fight> fight = service.fight(fightNum);
		int size = fight.size();
		if(size==0)
		{					
			JOptionPane.showMessageDialog(new JFrame().getContentPane(), "该日期暂无航班！", "友情提示", JOptionPane.INFORMATION_MESSAGE); 
			return "redirect:/index";
		}
		else
		{
			int chufa = fight.get(0).getOriginating();
			int daoda = fight.get(0).getDestination();
			int z = 0;
			for(int i = 0;i<size;i++)
			{
				long lt = new Long(fight.get(i).getExpFly()+"000");
				long lt1 = new Long(fight.get(i).getActFly()+"000");
		        long p = (lt1-lt)/1000/60;
		        if(p<15)
		        {
		        	z++;
		        }
			}
			NumberFormat numberFormat = NumberFormat.getInstance();
			numberFormat.setMaximumFractionDigits(2);  
			String result = numberFormat.format((float) z / (float) size * 100); 
			String yanwu = numberFormat.format(100-Double.parseDouble(result));
			cities chufadi = service.city(chufa);
			cities daodadi = service.city(daoda);
			fight = conv.getConversion(fight);
			model.addAttribute("c", chufadi);
			model.addAttribute("d", daodadi);
			model.addAttribute("fight", fight);
			model.addAttribute("result", result);
			model.addAttribute("yanwu", yanwu);
		}
		return "fight";
	}
	

		
}
