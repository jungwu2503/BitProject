package bitedu.bipa.lesson2;

import java.util.ArrayList;

public class DatabaseWork {
	public void insertData(ArrayList<StudentDTO> list) {
		// DB에 1000개의 DTO를 저장
		System.out.println(list.size());
		System.out.println(list.get(0));
	}
}
