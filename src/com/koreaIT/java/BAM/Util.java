package com.koreaIT.java.BAM;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Util {
	//메인 메서드가 없어 실행 X
	public static String gettine() {
		//현재 날짜 시간 출력 함수
		SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		//yyyy : 년도   MM : 월   dd : 일   HH : 시간   mm : 분  ss  :초
		Date time = new Date();

		return format1.format(time);
	
	}

}
