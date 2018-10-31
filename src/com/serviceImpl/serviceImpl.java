package com.serviceImpl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.mapper.Mapper;
import com.pojo.Onefight;
import com.pojo.cities;
import com.pojo.fight;
import com.service.service;

public class serviceImpl implements service{
	
	@Autowired
	private Mapper mapper;

	@Override
	public List allCnty() throws Exception {
		return mapper.allCnty();
	}

	@Override
	public cities mid(HashMap map) throws Exception {
		
		return mapper.mid(map);
	}

	@Override
	public List<cities> cities() throws Exception {
		return mapper.cities();
	}

	@Override
	public List fightNum() throws Exception {
		return mapper.fightNum();
	}

	@Override
	public List<Onefight> lines() throws Exception {
		return mapper.lines();
	}

	@Override
	public List daohan() throws Exception {
		return mapper.daohan();
	}

	@Override
	public cities begincity(String begincity) throws Exception {
		return mapper.begincity(begincity);
	}

	@Override
	public cities endcity(String endcity) throws Exception {
		return mapper.endcity(endcity);
	}

	@Override
	public List<fight> linestyle(HashMap map) throws Exception {
		return mapper.linestyle(map);
	}

	@Override
	public List<fight> fight(String fightNum) throws Exception {
		return mapper.fight(fightNum);
	}

	@Override
	public cities city(int mid) throws Exception {
		return mapper.city(mid);
	}

	@Override
	public List<fight> Statistical(int mid) throws Exception {
		return mapper.Statistical(mid);
	}

	@Override
	public List<fight> diqu(String region) throws Exception {
		return mapper.diqu(region);
	}

	@Override
	public List<fight> prov(String prov) throws Exception {
		return mapper.prov(prov);
	}

	@Override
	public List<fight> cnty(String cnty) throws Exception {
		return mapper.cnty(cnty);
	}

	@Override
	public List<Onefight> fanman() throws Exception {
		return mapper.fanman();
	}

	@Override
	public List airports() throws Exception {
		return mapper.airports();
	}

	@Override
	public List<fight> iata(HashMap map) throws Exception {
		return mapper.iata(map);
	}

	@Override
	public List<fight> Country(HashMap map) throws Exception {
		return mapper.Country(map);
	}

	@Override
	public List<Onefight> cntyline(List fightNum) throws Exception {
		return mapper.cntyline(fightNum);
	}


	@Override
	public List<com.pojo.fight> Provs(HashMap map) throws Exception {
		return mapper.Provs(map);
	}

	@Override
	public List<com.pojo.fight> Regions(HashMap map) throws Exception {
		return mapper.Regions(map);
	}

	@Override
	public List<com.pojo.cities> CntyCity(String cnty) throws Exception {
		return mapper.CntyCity(cnty);
	}

	@Override
	public List<com.pojo.cities> ProvCity(String prov,String region) throws Exception {
		return mapper.ProvCity(prov,region);
	}

	@Override
	public List<com.pojo.cities> RegionProv(String region) throws Exception {
		return mapper.RegionProv(region);
	}

	@Override
	public int countAviationNum() throws Exception {
		return mapper.countAviationNum();
	}

	@Override
	public int counCnty() throws Exception {
		return mapper.counCnty();
	}


}
