package com.tool;

import java.util.Calendar;
import java.util.Date;

public class DateJudge {
	//判断某个日期是否在给定日期之中
		public static boolean belongCalendar(Date time, Date from, Date to) {
	        Calendar date = Calendar.getInstance();
	        date.setTime(time);

	        Calendar after = Calendar.getInstance();
	        after.setTime(from);
	        after.add(Calendar.DATE, -1);

	        Calendar before = Calendar.getInstance();
	        before.setTime(to);
	        before.add(Calendar.DATE, 1);
	        
	        if (date.after(after) && date.before(before)) {
	            return true;
	        } else {
	            return false;
	        }
	    }
}
