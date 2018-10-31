package com.service;

import java.util.HashMap;
import java.util.List;

import com.pojo.Onefight;
import com.pojo.cities;
import com.pojo.fight;

public interface service {
	
	//查询不重复的国家
	public List allCnty() throws Exception;
	
	//根据城市名查询编号
	public cities mid(HashMap map)throws Exception;
	
	//查询不重复的航班所在城市
	public List<cities> cities() throws Exception;
	
	//查询不重复的航班号
	public List fightNum() throws Exception;
	
	//查询不重复的航班线
	public List<Onefight> lines() throws Exception;
	
	//查询不重复的城市
	public List daohan() throws Exception;
	
	//查询出发城市
	public cities begincity(String begincity)  throws Exception;
	
	//查询目的城市
	public cities endcity(String endcity)  throws Exception;
	
	//根据出发城市和目的城市查询航班
	public List<fight> linestyle(HashMap map) throws Exception;
	
	//根据航班号查询航班信息
	public List<fight> fight(String fightNum) throws Exception;
	
	//根据城市编号查询城市
	public cities city(int mid) throws Exception;
	
	//根据城市编号统计航班
	public List<fight> Statistical(int mid) throws Exception;
	
	//统计地区航班
	public List<fight> diqu(String region) throws Exception;
	
	//统计省份航班
	public List<fight> prov(String prov) throws Exception;
	
	//统计国家航班
	public List<fight> cnty(String cnty) throws Exception;
	
	//繁忙的航线
	public List<Onefight> fanman() throws Exception;
	
	//已有航班的机场
	public List airports() throws Exception;
	
	//根据机场查询航班
	public List<fight> iata(HashMap map) throws Exception;
	
	//查询两个国家的航班信息
	public List<fight> Country(HashMap map) throws Exception;
	
	//查询两个省份之间的航班信息
	public List<fight> Provs(HashMap map) throws Exception;
	
	//查询两个地区之间的航班信息
	public List<fight> Regions(HashMap map) throws Exception;
	
	//查询航班的两点，连线
	public List<Onefight> cntyline(List fightNum) throws Exception;

	//查询某个国家下面的所有城市
	public List<cities> CntyCity(String cnty) throws Exception;
	
	//查询某个省下面的所有城市
	public List<cities> ProvCity(String prov,String region) throws Exception;
	
	//查询某个地区下面的所有省
	public List<cities> RegionProv(String region) throws Exception;
	
	//查询所有航空公司不重复
	public  int countAviationNum() throws Exception;
	
	//统计中国的所有航班数量
	public  int counCnty() throws Exception;
}
