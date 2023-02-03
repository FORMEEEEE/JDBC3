package edu.kh.jdbc.common;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class LoadXMLFile {

	public static void main(String[] args) {

		
		// XML 파일 읽어오기
		
		try {
			
			Properties prop = new Properties(); // Map<String, String>
			
			// driver.xml 파일을 읽어오기 위한 InputStream 객체 생성
			FileInputStream fis = new FileInputStream("driver.xml");
			
			// 연결된 driver.xml 파일에 있는 내용을 모두 읽어와
			// Properties 객체에 K:V 형식으로 저장
			prop.loadFromXML(fis);
			
			System.out.println(prop);
			
			// Property : 속성(데이터)
			
			// prop.getProperty("key") : key가 일치하는 속성(데이터)를 얻어옴
			
			String driver = prop.getProperty("driver");
			String url = prop.getProperty("url");
			String user = prop.getProperty("user");
			String pw = prop.getProperty("pw");
			
			System.out.println();
			
			// driver.xml 파일에서 읽어온 값들을 이용해 Connectrion 생성
			Class.forName(driver);
			Connection conn = DriverManager.getConnection(url,user,pw);
			
			System.out.println(conn);
			/* 왜 XML 파일을 이용해서 db 연결 정보를 읽어와야 할까
			 * 
			 * 1. 코드 중복 제거
			 * 2. 별도 관리 용도
			 *     - 별도 파일을 이용해서 수정이 용이
			 * 
			 * 3. 재 컴파일 진행하지 않기 위해서
			 * - 코드가 길수록 컴파일에 소요되는 시간이 크다
			 * --> 코드 수정으로 인한 컴파일 소요시간 없앰
			 * 
			 * 
			 * 
			 * 
			 * 
			 * 
			 * */
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}
