package bitedu.bipa.quiz.main;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.temporal.ChronoField;
import java.util.Calendar;

import bitedu.bipa.quiz.util.DateCalculation;

//날짜와 시간 관련 작업을 수행하고, 날짜 간의 차이를 계산하는 기능을 테스트하기 위한 것으로 보입니다
public class Main {

	public static void main(String[] args) {
		Main main = new Main();
		main.test1();
		//main.test2(LocalDate.now());
	}
	
	
//	Calendar 클래스를 사용하여 두 날짜 간의 차이를 계산하고, 결과를 출력하는 메서드
//	Calendar 클래스를 사용하여 날짜를 설정하고, DateCalculation 클래스의
//	calcuDiffDate 메서드를 호출하여 두 Timestamp 객체 간의 차이를 계산
	public void test1() {
		Calendar c1 = Calendar.getInstance();
		Calendar c2 = Calendar.getInstance();
		
		c1.set(2023, 5, 14);
		System.out.println(c1.getTimeInMillis());
		c2.set(2023 ,5 ,16);
		//System.out.println(c2.getTimeInMillis());
		//c1.add(Calendar.YEAR, 0);
		//c1.add(Calendar.MONTH, 0);
		System.out.println(new Date(c1.getTimeInMillis()));
		//System.out.println(c1.getTimeInMillis());
		//c1.add(Calendar.DAY_OF_MONTH, 5);
		//System.out.println(c1.getTimeInMillis());
		boolean flag = c1.before(c2);
		long aaa = c2.getTimeInMillis() - c1.getTimeInMillis();
		double bbb = aaa/1000/3600/24;
		//System.out.println(c1.get(Calendar.DAY_OF_MONTH)+" "+flag);
		System.out.println(new Date(c1.getTimeInMillis()));
		//;
		//System.out.println(aaa+","+(c2.c1));
		
		Timestamp t1 = new Timestamp(c1.getTimeInMillis());
		Timestamp t2 = new Timestamp(c2.getTimeInMillis());
		int result = DateCalculation.calcuDiffDate(t2, t1);
		System.out.println(result);
	}
	
//	LocalDate를 사용하여 날짜를 설정하고, Date 객체를 생성하여 출력하는 메서드
	public void test2(LocalDate endDate) {
		Date date = new Date(endDate.get(ChronoField.YEAR),endDate.getMonth().getValue()-1,endDate.getDayOfMonth());
		System.out.println(date);
	}

}
