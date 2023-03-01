package com.koreaIT.java.BAM.service;

import com.koreaIT.java.BAM.container.Container;
import com.koreaIT.java.BAM.dto.Member;

public class MemberService {

	//아이디 중복검사
	public Member getMembeByLoginId(String loginID) {
		return Container.memberDao.getMembeByLoginId(loginID);
	}
	//고유번호 
	public int getLastId() {
		return Container.memberDao.getLastId();
	}
	//멤버스에 넣기
	public void add(Member member) {
		Container.memberDao.add(member);
		
	}

}
