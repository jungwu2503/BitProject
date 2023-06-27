package bitedu.bipa.quiz;

import java.io.*;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;

import org.json.simple.*;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import bitedu.bipa.quiz.service.*;
import bitedu.bipa.quiz.vo.*;

public class Solution {
	LibraryBookService lbs;
	
	public Solution() {
		lbs = new LibraryBookService();
	}

	public static void main(String[] args) {
		Solution solution = new Solution();
		
		//solution.readBloodyCSV();
		
		//String jsonString = solution.readJSON();
		//solution.putJSONIntoDB(jsonString);
		
		solution.getUserInfo("user1");
	}
	
	/*public List<List<String>> readBloodyCSV() {
		List<List<String>> csvList = new ArrayList<List<String>>();
		File bookInfoCsv = new File("C:\\Users\\jungw\\Desktop\\BitProgram\\bipa3_ready\\Sample\\SimpleLibrarySample\\data\\book_info.csv");
		//File bookCopyCsv = new File("C:\\Users\\jungw\\Desktop\\BitProgram\\bipa3_ready\\Sample\\SimpleLibrarySample\\data\\book_copy.csv");
		BufferedReader br = null;
		String line;
		
		try {
			br = new BufferedReader(new FileReader(bookInfoCsv));
			while ((line = br.readLine()) != null) {
				List<String> aLine = new ArrayList<String>();
				String[] lineArr = line.split(",");
				aLine = Arrays.asList(lineArr); // asList = split된 문자열 배열들을 arrayList 형식으로 바꿔주는듯
				System.out.println(aLine); // 인코딩 개박살 (엑셀 내부 세팅 문제?)
				csvList.add(aLine);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return csvList;
	}*/
	
	public String readJSON() { // 파일 읽어서 DB에 박아보자
		String filePath = "./data/user1.json";
		String result = null;
		try {
			FileReader fr = new FileReader(filePath);
			BufferedReader br = new BufferedReader(fr);
			String line = null;
			StringBuilder jsonBuilder = new StringBuilder();
			while ((line = br.readLine()) != null) {
				jsonBuilder.append(line);
			}
			result = jsonBuilder.toString();
			
			br.close();
			fr.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	public void putJSONIntoDB(String jsonString) {
		JSONParser parser = new JSONParser();

		try {
			JSONObject json = (JSONObject)parser.parse(jsonString);
			
			JSONObject data = (JSONObject)json.get("data");
			JSONObject bookInfo = (JSONObject) data.get("bookInfo");
			JSONArray total = (JSONArray) bookInfo.get("list");
			JSONArray returned = (JSONArray) bookInfo.get("returned");
			JSONArray notReturned = (JSONArray) bookInfo.get("notReturned");
			JSONArray expectingReturn = (JSONArray) bookInfo.get("expectingReturn");

			JSONObject userInfo = (JSONObject) data.get("userInfo");
			JSONArray user = (JSONArray) userInfo.get("user");
			
			ArrayList<BookUseStatusVO> list = new ArrayList<BookUseStatusVO>();
			ArrayList<BookUseStatusVO> returnedList = new ArrayList<BookUseStatusVO>();
			ArrayList<BookUseStatusVO> notReturnedList = new ArrayList<BookUseStatusVO>();
			ArrayList<BookUseStatusVO> expectingReturnList = new ArrayList<BookUseStatusVO>();
			ArrayList<UserBookStatusVO> userStateList = new ArrayList<UserBookStatusVO>();

			JSONObject jsonObject;
//			System.out.println(total); // JSONArray 일때는 출력 잘됨 ArrayList 담는과정에서 값이 null로 드감

			DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			Date date;
			Timestamp ts1, ts2, ts3;
			
			// total
			for (int i = 0; i < total.size(); i++) {
				jsonObject = (JSONObject) total.get(i);
				
				date = df.parse((String) jsonObject.get("borrowStart"));
				ts1 = new Timestamp(date.getTime());
				date = df.parse((String) jsonObject.get("borrowEnd"));
				ts2 = new Timestamp(date.getTime());
				// - 일때 처리
				if (jsonObject.get("returnDate").equals("-")) {
					ts3 = null;
				} else {
					date = df.parse((String) jsonObject.get("returnDate"));
					ts3 = new Timestamp(date.getTime());
				}				
				
				BookUseStatusVO vo = new BookUseStatusVO(Integer.parseInt((String)jsonObject.get("bookSeq")), (String)jsonObject.get("userId"), 
						ts1, ts2, ts3);
				vo.setBookIsbn((String)jsonObject.get("bookIsbn"));
				vo.setBookTitle((String)jsonObject.get("bookTitle"));
				vo.setBookAuthor((String)jsonObject.get("bookAuthor"));

				list.add(vo);
			}
			
			// returned
			for (int i = 0; i < returned.size(); i++) {
				jsonObject = (JSONObject) returned.get(i);
				
				date = df.parse((String) jsonObject.get("borrowStart"));
				ts1 = new Timestamp(date.getTime());
				date = df.parse((String) jsonObject.get("borrowEnd"));
				ts2 = new Timestamp(date.getTime());
				// - 일때 처리
				if (jsonObject.get("returnDate").equals("-")) {
					ts3 = null;
				} else {
					date = df.parse((String) jsonObject.get("returnDate"));
					ts3 = new Timestamp(date.getTime());
				}				
				
				BookUseStatusVO vo = new BookUseStatusVO(Integer.parseInt((String)jsonObject.get("bookSeq")), (String)jsonObject.get("userId"), 
						ts1, ts2, ts3);
				vo.setBookIsbn((String)jsonObject.get("bookIsbn"));
				vo.setBookTitle((String)jsonObject.get("bookTitle"));
				vo.setBookAuthor((String)jsonObject.get("bookAuthor"));

				returnedList.add(vo);
			}
			
			// notReturned
			for (int i = 0; i < notReturned.size(); i++) {
				jsonObject = (JSONObject) notReturned.get(i);
				
				date = df.parse((String) jsonObject.get("borrowStart"));
				ts1 = new Timestamp(date.getTime());
				date = df.parse((String) jsonObject.get("borrowEnd"));
				ts2 = new Timestamp(date.getTime());
				// - 일때 처리
				if (jsonObject.get("returnDate").equals("-")) {
					ts3 = null;
				} else {
					date = df.parse((String) jsonObject.get("returnDate"));
					ts3 = new Timestamp(date.getTime());
				}				
				
				BookUseStatusVO vo = new BookUseStatusVO(Integer.parseInt((String)jsonObject.get("bookSeq")), (String)jsonObject.get("userId"), 
						ts1, ts2, ts3);
				vo.setBookIsbn((String)jsonObject.get("bookIsbn"));
				vo.setBookTitle((String)jsonObject.get("bookTitle"));
				vo.setBookAuthor((String)jsonObject.get("bookAuthor"));

				notReturnedList.add(vo);
			}
			
			// expectingReturn
			for (int i = 0; i < expectingReturn.size(); i++) {
				jsonObject = (JSONObject) expectingReturn.get(i);
				
				date = df.parse((String) jsonObject.get("borrowStart"));
				ts1 = new Timestamp(date.getTime());
				date = df.parse((String) jsonObject.get("borrowEnd"));
				ts2 = new Timestamp(date.getTime());
				// - 일때 처리
				if (jsonObject.get("returnDate").equals("-")) {
					ts3 = null;
				} else {
					date = df.parse((String) jsonObject.get("returnDate"));
					ts3 = new Timestamp(date.getTime());
				}				
				
				BookUseStatusVO vo = new BookUseStatusVO(Integer.parseInt((String)jsonObject.get("bookSeq")), (String)jsonObject.get("userId"), 
						ts1, ts2, ts3);
				vo.setBookIsbn((String)jsonObject.get("bookIsbn"));
				vo.setBookTitle((String)jsonObject.get("bookTitle"));
				vo.setBookAuthor((String)jsonObject.get("bookAuthor"));

				expectingReturnList.add(vo);
			}
			
//			System.out.println(user);
			// user
			jsonObject = (JSONObject)user.get(0);
			UserBookStatusVO userVO = new UserBookStatusVO((String)jsonObject.get("userId"), (String)jsonObject.get("userState"),
					(String)jsonObject.get("stopDate"), Integer.parseInt((String)jsonObject.get("totalUsingBook")), Integer.parseInt((String)jsonObject.get("returnedBook")), 
					Integer.parseInt((String)jsonObject.get("notReturnedBook")), Integer.parseInt((String)jsonObject.get("expectingReturnBook")), Integer.parseInt((String)jsonObject.get("availableBook")));
			
//			System.out.println(userVO);
			
			if (lbs.putIntoDB_Book(list)) {
				System.out.println("Insert 성공");
			} else {
				System.out.println("Insert 실패");
			}
//			lbs.putIntoDB_Book(returnedList);
//			lbs.putIntoDB_Book(notReturnedList);
//			lbs.putIntoDB_Book(expectingReturnList);
//			lbs.putIntoDB_User(userStateList);
			
			
			
			//인코딩 해결해야
			
			//JSONArray expectingReturnArray = bookInfo.getJSONArray("expectingReturn");
			
			//System.out.println(jsonObject.get("data"));
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (java.text.ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		/*JSONArray jsonArray = new JSONArray();
		
		jsonArray.add(jsonObject.get("data"));
		System.out.println(jsonArray);*/
	}
	
	public void getUserInfo(String userId) {
		HashMap<String, Object> data = lbs.getUserStatus(userId, "6"); 
		HashMap<String,UserBookStatusVO> map1 = (HashMap<String,UserBookStatusVO>)data.get("userInfo");
		HashMap<String,ArrayList<BookUseStatusVO>>  map2 = (HashMap<String,ArrayList<BookUseStatusVO>>)data.get("bookInfo");
		
		UserBookStatusVO status = map1.get("user");
		ArrayList<BookUseStatusVO> list = map2.get("total");
		ArrayList<BookUseStatusVO> allReturned = map2.get("allReturned");
		ArrayList<BookUseStatusVO> notReturned = map2.get("notReturned");
		ArrayList<BookUseStatusVO> expectingReturn = map2.get("expectingReturn");
		

		JSONObject json = new JSONObject();
		JSONObject info = new JSONObject();
		JSONObject user = new JSONObject();
		JSONObject book = new JSONObject();
		
		JSONArray array1 = new JSONArray();
		JSONArray array2 = new JSONArray();
		JSONArray array3 = new JSONArray(); 
		JSONArray array4 = new JSONArray();

		JSONArray array5 = new JSONArray();
		array5.add(status);
		
		user.put("user", array5);
		
		for(BookUseStatusVO vo : list) {
			array1.add(vo);
		}
		book.put("list", array1);
		
		for(BookUseStatusVO vo : allReturned) {
			array2.add(vo);
		}
		book.put("returned", array2);
		
		for(BookUseStatusVO vo : notReturned) {
			array3.add(vo);
		}
		book.put("notReturned", array3);
		
		for(BookUseStatusVO vo : expectingReturn) {
			array4.add(vo);
			//System.out.println(vo);
		}
		book.put("expectingReturn", array4);
		
		
		info.put("userInfo", user);
		info.put("bookInfo", book);
		
		json.put("data", info);

//		System.out.println(json.toJSONString());
		//JSON형태로 변환한뒤에 파일로 저장
		try {
			this.saveUserInfo(userId,json.toJSONString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void saveUserInfo(String userId, String userInfo) throws IOException {
		String path = String.format("C:/bitDev/test/%s.json",userId);
		File file = new File(path);
		FileWriter fw = new FileWriter(file);
		PrintWriter pw = new PrintWriter(fw);
		pw.print(userInfo);
		pw.close();
		fw.close();
	}
}
