package com.controller;

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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.pojo.Onefight;
import com.pojo.cities;
import com.pojo.fight;
import com.serviceImpl.serviceImpl;
import com.tool.Conversion;
import com.tool.DateJudge;
import com.tool.illegalChar;

import net.sf.json.JSONArray;

@Controller
public class SelectController {
	@Autowired
	private serviceImpl service;
	
	Conversion conv = new Conversion();
	illegalChar iChar = new illegalChar();
	DateJudge jud = new DateJudge();
	

	//查询某个机场到某个机场
	@RequestMapping("/iata")
	public String iata(Model model,String BegAir,String EndAir,String date,String date1) throws Exception{
		if(iChar.isSpecialChar(BegAir))
		{
			JOptionPane.showMessageDialog(new JFrame().getContentPane(), "您的输入存在非法字符！", "友情提示", JOptionPane.INFORMATION_MESSAGE); 
			return "redirect:/index";
		}
		if(iChar.isSpecialChar(EndAir))
		{
			JOptionPane.showMessageDialog(new JFrame().getContentPane(), "您的输入存在非法字符！", "友情提示", JOptionPane.INFORMATION_MESSAGE); 
			return "redirect:/index";
		}
		if(BegAir != null && EndAir != null)
		{
			if(!date.equals(""))
			{
				if(!date1.equals(""))
				{
					
					HashMap<String,Object> map = new HashMap<String, Object>();
					map.put("chufa",BegAir );
					map.put("daoda", EndAir);
					List<fight> fight = service.iata(map);
					if(fight.size()==0)
					{
						JOptionPane.showMessageDialog(new JFrame().getContentPane(), "暂无该航班！", "友情提示", JOptionPane.INFORMATION_MESSAGE); 
						return "redirect:/index";
					}
					cities ocity = service.city(fight.get(0).getOriginating());
					cities dcity = service.city(fight.get(0).getDestination());
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
						model.addAttribute("c", ocity);
						model.addAttribute("d", dcity);
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
					HashMap<String,Object> map = new HashMap<String, Object>();
					map.put("chufa",BegAir );
					map.put("daoda", EndAir);
					List<fight> fight = service.iata(map);
					if(fight.size()==0)
					{
						JOptionPane.showMessageDialog(new JFrame().getContentPane(), "暂无该航班！", "系统信息", JOptionPane.INFORMATION_MESSAGE); 
						return "redirect:/index";
					}
					cities ocity = service.city(fight.get(0).getOriginating());
					cities dcity = service.city(fight.get(0).getDestination());
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
						model.addAttribute("c", ocity);
						model.addAttribute("d", dcity);
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
				HashMap<String,Object> map = new HashMap<String, Object>();
				map.put("chufa",BegAir );
				map.put("daoda", EndAir);
				List<fight> fight = service.iata(map);
				if(fight.size()==0)
				{
					JOptionPane.showMessageDialog(new JFrame().getContentPane(), "暂无该航班！", "友情提示", JOptionPane.INFORMATION_MESSAGE); 
					return "redirect:/index";
				}
				else
				{
					cities ocity = service.city(fight.get(0).getOriginating());
					cities dcity = service.city(fight.get(0).getDestination());
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
					double size1 = fight.size();
					double h = 0-size1/2.0/10.0-0.1;
					model.addAttribute("h",h);
					model.addAttribute("c", ocity);
					model.addAttribute("d", dcity);
					model.addAttribute("aviationNum", aviationNum.size());
					model.addAttribute("linestyle", fight);
				}
			}
			
		}
		return "fightNum";
	}
	
	@RequestMapping("/Country")
	public String Country(Model model,String Ocnty,String Dcnty,String date,String date1) throws Exception{
		if(iChar.isSpecialChar(Ocnty))
		{
			JOptionPane.showMessageDialog(new JFrame().getContentPane(), "您的输入存在非法字符！", "友情提示", JOptionPane.INFORMATION_MESSAGE); 
			return "redirect:/index";
		}
		if(iChar.isSpecialChar(Dcnty))
		{
			JOptionPane.showMessageDialog(new JFrame().getContentPane(), "您的输入存在非法字符！", "友情提示", JOptionPane.INFORMATION_MESSAGE); 
			return "redirect:/index";
		}
		if(Ocnty != null && Dcnty != null)
		{
			if(!date.equals(""))
			{
				if(!date1.equals(""))
				{
					HashMap<String,Object> map = new HashMap<String, Object>();
					map.put("chufa",Ocnty );
					map.put("daoda", Dcnty);
					List<fight> fight = service.Country(map);
					System.out.println(fight);
					if(fight.size()==0)
					{
						JOptionPane.showMessageDialog(new JFrame().getContentPane(), "暂无该航班！", "友情提示", JOptionPane.INFORMATION_MESSAGE); 
						return "redirect:/index";
					}
					List<fight> line  = new ArrayList();
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
				        	line.add(fight.get(i));
				        }
					}
					if(line.size()!=0)
					{
						 List fightNum = new ArrayList();
							int size1 = line.size();
							List aviationNum = new ArrayList();
							for(int i = 0;i<size1;i++)
							{
								fightNum.add(line.get(i).getFightNum());
								if(!line.get(i).getAviationNum().equals("无"))
								{
									aviationNum.add(line.get(i).getAviationNum());
								}
							}
							List<Onefight> lines = service.cntyline(fightNum);
							aviationNum = new ArrayList<String>(new HashSet<String>(aviationNum));
							model.addAttribute("aviationNum", aviationNum.size());
							model.addAttribute("cities", lines);
							model.addAttribute("linestyle", line);
					}
					else
					{
						JOptionPane.showMessageDialog(new JFrame().getContentPane(), "该日期暂无航班！", "友情提示", JOptionPane.INFORMATION_MESSAGE); 
						return "redirect:/index";
					}
			       
				}
				else
				{
					HashMap<String,Object> map = new HashMap<String, Object>();
					map.put("chufa",Ocnty );
					map.put("daoda", Dcnty);
					List<fight> fight = service.Country(map);
					if(fight.size()==0)
					{
						JOptionPane.showMessageDialog(new JFrame().getContentPane(), "暂无该航班！", "系统信息", JOptionPane.INFORMATION_MESSAGE); 
						return "redirect:/index";
					}
					List<fight> line  = new ArrayList();
					fight = conv.getConversion(fight);
					int size = fight.size();
					for(int i = 0;i<size;i++)
					{
						String sd = fight.get(i).getFightDate();
				        if(date.equals(sd))
				        {
				        	line.add(fight.get(i));
				        }
					}
					if(line.size()!=0)
					{
						 List fightNum = new ArrayList();
							int size1 = line.size();
							List aviationNum = new ArrayList();
							for(int i = 0;i<size1;i++)
							{
								fightNum.add(line.get(i).getFightNum());
								if(!line.get(i).getAviationNum().equals("无"))
								{
									aviationNum.add(line.get(i).getAviationNum());
								}
							}
							List<Onefight> lines = service.cntyline(fightNum);
							aviationNum = new ArrayList<String>(new HashSet<String>(aviationNum));
							model.addAttribute("aviationNum", aviationNum.size());
							model.addAttribute("cities", lines);
							model.addAttribute("linestyle", line);
					}
					else
					{
						JOptionPane.showMessageDialog(new JFrame().getContentPane(), "该日期暂无航班！", "友情提示", JOptionPane.INFORMATION_MESSAGE); 
						return "redirect:/index";
					}

				}
			}
			else{
				HashMap<String,Object> map = new HashMap<String, Object>();
				map.put("chufa",Ocnty );
				map.put("daoda", Dcnty);
				List<fight> fight = service.Country(map);
				if(fight.size()==0)
				{
					JOptionPane.showMessageDialog(new JFrame().getContentPane(), "暂无该航班！", "系统信息", JOptionPane.INFORMATION_MESSAGE); 
					return "redirect:/index";
				}
				List fightNum = new ArrayList();
				fight = conv.getConversion(fight);
				int size = fight.size();
				List aviationNum = new ArrayList();
				for(int i = 0;i<size;i++)
				{
					fightNum.add(fight.get(i).getFightNum());
					if(!fight.get(i).getAviationNum().equals("无"))
					{
						aviationNum.add(fight.get(i).getAviationNum());
					}
				}
				List<Onefight> lines = service.cntyline(fightNum);
				aviationNum = new ArrayList<String>(new HashSet<String>(aviationNum));
				model.addAttribute("aviationNum", aviationNum.size());
				model.addAttribute("cities", lines);
				model.addAttribute("linestyle", fight);
			}

		}
		return "Country";
	}
	@RequestMapping("/provs")
	public String provs(Model model,String bprov,String eprov,String date,String date1) throws Exception{
		if(iChar.isSpecialChar(bprov))
		{
			JOptionPane.showMessageDialog(new JFrame().getContentPane(), "您的输入存在非法字符！", "友情提示", JOptionPane.INFORMATION_MESSAGE); 
			return "redirect:/index";
		}
		if(iChar.isSpecialChar(eprov))
		{
			JOptionPane.showMessageDialog(new JFrame().getContentPane(), "您的输入存在非法字符！", "友情提示", JOptionPane.INFORMATION_MESSAGE); 
			return "redirect:/index";
		}
		if(bprov != null && eprov != null)
		{
			if(!date.equals(""))
			{
				if(!date1.equals(""))
				{
					HashMap<String,Object> map = new HashMap<String, Object>();
					map.put("chufa",bprov );
					map.put("daoda", eprov);
					List<fight> fight = service.Provs(map);
					System.out.println(fight);
					if(fight.size()==0)
					{
						JOptionPane.showMessageDialog(new JFrame().getContentPane(), "暂无该航班！", "友情提示", JOptionPane.INFORMATION_MESSAGE); 
						return "redirect:/index";
					}
					List<fight> line  = new ArrayList();
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
				        	line.add(fight.get(i));
				        }
					}
					if(line.size()!=0)
					{
						 List fightNum = new ArrayList();
							int size1 = line.size();
							List aviationNum = new ArrayList();
							for(int i = 0;i<size1;i++)
							{
								fightNum.add(line.get(i).getFightNum());
								if(!line.get(i).getAviationNum().equals("无"))
								{
									aviationNum.add(line.get(i).getAviationNum());
								}
							}
							List<Onefight> lines = service.cntyline(fightNum);
							aviationNum = new ArrayList<String>(new HashSet<String>(aviationNum));
							model.addAttribute("aviationNum", aviationNum.size());
							model.addAttribute("cities", lines);
							model.addAttribute("linestyle", line);
					}
					else
					{
						JOptionPane.showMessageDialog(new JFrame().getContentPane(), "该日期暂无航班！", "友情提示", JOptionPane.INFORMATION_MESSAGE); 
						return "redirect:/index";
					}
			       
				}
				else
				{
					HashMap<String,Object> map = new HashMap<String, Object>();
					map.put("chufa",bprov );
					map.put("daoda", eprov);
					List<fight> fight = service.Provs(map);
					if(fight.size()==0)
					{
						JOptionPane.showMessageDialog(new JFrame().getContentPane(), "暂无该航班！", "系统信息", JOptionPane.INFORMATION_MESSAGE); 
						return "redirect:/index";
					}
					List<fight> line  = new ArrayList();
					fight = conv.getConversion(fight);
					int size = fight.size();
					for(int i = 0;i<size;i++)
					{
						String sd = fight.get(i).getFightDate();
				        if(date.equals(sd))
				        {
				        	line.add(fight.get(i));
				        }
					}
					if(line.size()!=0)
					{
						 List fightNum = new ArrayList();
							int size1 = line.size();
							List aviationNum = new ArrayList();
							for(int i = 0;i<size1;i++)
							{
								fightNum.add(line.get(i).getFightNum());
								if(!line.get(i).getAviationNum().equals("无"))
								{
									aviationNum.add(line.get(i).getAviationNum());
								}
							}
							List<Onefight> lines = service.cntyline(fightNum);
							aviationNum = new ArrayList<String>(new HashSet<String>(aviationNum));
							model.addAttribute("aviationNum", aviationNum.size());
							model.addAttribute("cities", lines);
							model.addAttribute("linestyle", line);
					}
					else
					{
						JOptionPane.showMessageDialog(new JFrame().getContentPane(), "该日期暂无航班！", "友情提示", JOptionPane.INFORMATION_MESSAGE); 
						return "redirect:/index";
					}

				}
			}
			else{
				HashMap<String,Object> map = new HashMap<String, Object>();
				map.put("chufa",bprov );
				map.put("daoda", eprov);
				List<fight> fight = service.Provs(map);
				if(fight.size()==0)
				{
					JOptionPane.showMessageDialog(new JFrame().getContentPane(), "暂无该航班！", "系统信息", JOptionPane.INFORMATION_MESSAGE); 
					return "redirect:/index";
				}
				List fightNum = new ArrayList();
				fight = conv.getConversion(fight);
				int size = fight.size();
				List aviationNum = new ArrayList();
				for(int i = 0;i<size;i++)
				{
					fightNum.add(fight.get(i).getFightNum());
					if(!fight.get(i).getAviationNum().equals("无"))
					{
						aviationNum.add(fight.get(i).getAviationNum());
					}
				}
				List<Onefight> lines = service.cntyline(fightNum);
				aviationNum = new ArrayList<String>(new HashSet<String>(aviationNum));
				model.addAttribute("aviationNum", aviationNum.size());
				model.addAttribute("cities", lines);
				model.addAttribute("linestyle", fight);
			}

		}
		return "Country";
	}
	
	@RequestMapping("/regions")
	public String regions(Model model,String bregion,String eregion,String date,String date1) throws Exception{
		if(iChar.isSpecialChar(bregion))
		{
			JOptionPane.showMessageDialog(new JFrame().getContentPane(), "您的输入存在非法字符！", "友情提示", JOptionPane.INFORMATION_MESSAGE); 
			return "redirect:/index";
		}
		if(iChar.isSpecialChar(eregion))
		{
			JOptionPane.showMessageDialog(new JFrame().getContentPane(), "您的输入存在非法字符！", "友情提示", JOptionPane.INFORMATION_MESSAGE); 
			return "redirect:/index";
		}
		if(bregion != null && eregion != null)
		{
			if(!date.equals(""))
			{
				if(!date1.equals(""))
				{
					HashMap<String,Object> map = new HashMap<String, Object>();
					map.put("chufa",bregion );
					map.put("daoda", eregion);
					List<fight> fight = service.Regions(map);
					System.out.println(fight);
					if(fight.size()==0)
					{
						JOptionPane.showMessageDialog(new JFrame().getContentPane(), "暂无该航班！", "友情提示", JOptionPane.INFORMATION_MESSAGE); 
						return "redirect:/index";
					}
					List<fight> line  = new ArrayList();
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
				        	line.add(fight.get(i));
				        }
					}
					if(line.size()!=0)
					{
						 List fightNum = new ArrayList();
							int size1 = line.size();
							List aviationNum = new ArrayList();
							for(int i = 0;i<size1;i++)
							{
								fightNum.add(line.get(i).getFightNum());
								if(!line.get(i).getAviationNum().equals("无"))
								{
									aviationNum.add(line.get(i).getAviationNum());
								}
							}
							List<Onefight> lines = service.cntyline(fightNum);
							aviationNum = new ArrayList<String>(new HashSet<String>(aviationNum));
							model.addAttribute("aviationNum", aviationNum.size());
							model.addAttribute("cities", lines);
							model.addAttribute("linestyle", line);
					}
					else
					{
						JOptionPane.showMessageDialog(new JFrame().getContentPane(), "该日期暂无航班！", "友情提示", JOptionPane.INFORMATION_MESSAGE); 
						return "redirect:/index";
					}
			       
				}
				else
				{
					HashMap<String,Object> map = new HashMap<String, Object>();
					map.put("chufa",bregion );
					map.put("daoda", eregion);
					List<fight> fight = service.Regions(map);
					if(fight.size()==0)
					{
						JOptionPane.showMessageDialog(new JFrame().getContentPane(), "暂无该航班！", "系统信息", JOptionPane.INFORMATION_MESSAGE); 
						return "redirect:/index";
					}
					List<fight> line  = new ArrayList();
					fight = conv.getConversion(fight);
					int size = fight.size();
					for(int i = 0;i<size;i++)
					{
						String sd = fight.get(i).getFightDate();
				        if(date.equals(sd))
				        {
				        	line.add(fight.get(i));
				        }
					}
					if(line.size()!=0)
					{
						List fightNum = new ArrayList();
						int size1 = line.size();
						List aviationNum = new ArrayList();
						for(int i = 0;i<size1;i++)
						{
							fightNum.add(line.get(i).getFightNum());
							if(!line.get(i).getAviationNum().equals("无"))
							{
								aviationNum.add(line.get(i).getAviationNum());
							}
						}
						List<Onefight> lines = service.cntyline(fightNum);
						aviationNum = new ArrayList<String>(new HashSet<String>(aviationNum));
						model.addAttribute("aviationNum", aviationNum.size());
						model.addAttribute("cities", lines);
						model.addAttribute("linestyle", line);
					}
					else
					{
						JOptionPane.showMessageDialog(new JFrame().getContentPane(), "该日期暂无航班！", "友情提示", JOptionPane.INFORMATION_MESSAGE); 
						return "redirect:/index";
					}

				}
			}
			else{
				HashMap<String,Object> map = new HashMap<String, Object>();
				map.put("chufa",bregion );
				map.put("daoda", eregion);
				List<fight> fight = service.Regions(map);
				if(fight.size()==0)
				{
					JOptionPane.showMessageDialog(new JFrame().getContentPane(), "暂无该航班！", "系统信息", JOptionPane.INFORMATION_MESSAGE); 
					return "redirect:/index";
				}
				List fightNum = new ArrayList();
				fight = conv.getConversion(fight);
				int size = fight.size();
				List aviationNum = new ArrayList();
				for(int i = 0;i<size;i++)
				{
					fightNum.add(fight.get(i).getFightNum());
					if(!fight.get(i).getAviationNum().equals("无"))
					{
						aviationNum.add(fight.get(i).getAviationNum());
					}
				}
				List<Onefight> lines = service.cntyline(fightNum);
				aviationNum = new ArrayList<String>(new HashSet<String>(aviationNum));
				model.addAttribute("aviationNum", aviationNum.size());
				model.addAttribute("cities", lines);
				model.addAttribute("linestyle", fight);
			}

		}
		return "Country";
	}
	
	
	

}
