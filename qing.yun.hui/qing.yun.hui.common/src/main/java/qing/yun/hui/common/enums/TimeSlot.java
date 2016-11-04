package qing.yun.hui.common.enums;

import lombok.Getter;

/***
 ** @Description: 时间段
 ** @author: qing.yunhui
 ** @email: 280672161@.qq.com
 ** @dateTime: Nov 20, 2015 10:23:24 AM
 ** @version: V1.0
 ***/
@Getter
public enum TimeSlot implements ICommonEnum{
	/**
	 *  凌晨01:01到04:59
		清晨05:00到05:59
		早上06:00到08:00
		上午08:01到10:59
		中午11:00到12:59
		下午13:00到17:00
		傍晚17:01到18:00
		晚上18:01到23:59
		午夜第二天的00:00到01:00
	 * ****/
	BEFORE_DAWN(0,"凌晨",1,5),
	EARLY_MORNING(1,"清晨",5,6),
	MORNING(2,"早上",6,8),
	FORENOON(3,"上午",8,11),
	NOON(4,"中午",11,13),
	AFTERNOON(5,"下午",13,17),
	NIGHTFALL(6,"傍晚",17,18),
	EVENING(7,"晚上",18,24),
	MIDNIGHT(8,"午夜",0,1);
	
	private final int value;
    private final String name;
    private final int beginHour;
    private final int endHour;
    
    private TimeSlot(int value, String name,int beginHour,int endHour) {
        this.value = value;
        this.name = name;
        this.beginHour=beginHour;
        this.endHour=endHour;
    }

	public String getCode() {
		return String.valueOf(value);
	}
}
