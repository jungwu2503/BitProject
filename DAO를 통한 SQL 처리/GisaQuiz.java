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
		/*지역코드가 B 인 자료에 대하여 (국어점수 + 영어점수)으로 내림차순 정렬했을 
		때 5번째 학번 출력하시오. 동일값 발생시 학번에 대한 오름차순 정렬하시오.(20점) (※ 결
		과 값은 수검자 PC의 C:\C_it\data\Ans1.txt 파일에 출력되도록 프로그램을 작성할 것)
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
