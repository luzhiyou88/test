package com.education.classroom.core.modules.spadmin.lesson.util;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.education.classroom.core.modules.spadmin.lesson.entity.WeekDayInfo;
import com.education.classroom.utils.DateUtils;



/**
 * 课程表帮助类
 * @Class Name ScheduleHelper
 * @author zhujie
 * @Create In 2016年8月11日
 */
public class ScheduleHelper {

	public static List<WeekDayInfo> getWeekInfo(Date start,Date end){
		List<WeekDayInfo> lst = new ArrayList<WeekDayInfo>();
		if(start==null || end == null){
			return lst;
		}
		if(end.before(start)){
			Date tmp = end;
			end = start;
			start = tmp;
		}
		for (int i = 0; i <= DateUtils.getDistanceOfTwoDate(start,end); i++) {
			WeekDayInfo info = new WeekDayInfo();
			Date d = DateUtils.addDays(start, i);
			info.setDate(DateUtils.formatDate(d));
			if(DateUtils.getDistanceOfTwoDate(d,DateUtils.getCurrentDate())==0){
				info.setWeekDay("今日");
				info.setTodayFlag(true);
			}else{
				info.setWeekDay(DateUtils.getChineseWeekday(d));
				info.setTodayFlag(false);
			}			
			info.setMonthDay(DateUtils.formatDate(d,"MM月dd日"));
			lst.add(info);
		}
		
		return lst;
	}
}
