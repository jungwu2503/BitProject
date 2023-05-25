package bitedu.bipa.lesson3;

import java.util.*;

public class GisaQuiz {
	private GisaDAO dao;
	public GisaQuiz() {
		dao = new GisaDAO();		
	}
	
	public String solveQuiz1() {
		String answer = null;
		// 로직 처리(SQL)
		/*
		 *  select std_no
			from gisaTBL
			where loc_code = 'B' order by (kor+eng) desc, std_no asc limit 4, 1;
		 */
		StringBuffer sb = new StringBuffer("select std_no from gisaTBL ");
		sb.append("where loc_code = 'B' order by (kor+eng) desc, ");
		sb.append("std_no asc limit 4, 1");
		String sql = sb.toString();
		System.out.println(sql);
		int stdNo = dao.selectQuiz1(sql);
		answer = String.valueOf(stdNo);
		return answer;
	}
	
	public String solveQuiz2() {
		String answer = null;
		
		return answer;
	}
	
	public String solveQuiz3() {
		String answer = null;
		
		return answer;
	}
	
	public String solveQuiz4() {
		String answer = null;
		
		return answer;
	}
		
}
