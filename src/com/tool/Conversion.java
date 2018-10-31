package com.tool;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.pojo.cities;
import com.pojo.fight;
import com.serviceImpl.serviceImpl;

/* 
 *
 * 	静态工具类注入service，不使用此方法注入会出现null指针异常
 * 	2018年6月16日14:38:07
 * 
 */
@Component
public class Conversion {
	@Autowired
	private serviceImpl service;
	private static Conversion conversion;
	
	 @PostConstruct
	    public void init(){
		 conversion = this;
		 conversion.service = this.service;
	    }

	 
	public List<fight> getConversion(List<fight> fight) throws Exception{
		int size = fight.size();
		for(int i = 0;i<size;i++)
		{
			int ori = fight.get(i).getOriginating();
			int des = fight.get(i).getDestination();
			cities or = conversion.service.city(ori);
			fight.get(i).setChufadi(or.getCity());
			cities de = conversion.service.city(des);
			fight.get(i).setMudi(de.getCity());
			String datetime = fight.get(i).getActFly();
			String datetime1 = fight.get(i).getActLand();
			long lt = new Long(datetime+"000");
			long lt1 = new Long(datetime1+"000");
	        SimpleDateFormat xianshi=new SimpleDateFormat("yyyy-MM-dd HH:mm");
	        String actFly = xianshi.format(new Date(Long.parseLong(String.valueOf(lt))));
	        String actLand = xianshi.format(new Date(Long.parseLong(String.valueOf(lt1))));
	        fight.get(i).setActFly(actFly);
	        fight.get(i).setActLand(actLand);
	        long dd = (lt1-lt)/1000/60;
	        long hh=dd/60;
	        long mm = dd%60;
	        String cha = hh+"小时"+mm+"分钟";
	        fight.get(i).setCha(cha);
	        
		}
		return fight;
	}
}
