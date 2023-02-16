package com.koreaIT.java.BAM;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Util {

	public static String gettine() {
		//현재 날짜 시간 출력 함수
		SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		//yyyy : 년도   MM : 월   dd : 일   HH : 시간   mm : 분  ss  :초
		Date time = new Date();

	//	String time1 = format1.format(time);
    //	return time1;
		
		return format1.format(time);
	
	}

}
