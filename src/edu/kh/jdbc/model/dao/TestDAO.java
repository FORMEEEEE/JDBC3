package edu.kh.jdbc.model.dao;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import static edu.kh.jdbc.common.JDBCTemplate.*;
import edu.kh.jdbc.model.vo.TestVO;






public class TestDAO {

	// 필드
	// jdbc객체를 참조하기 위한 참조변수 선언
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	private Properties prop;
	
	//생성자
	public TestDAO() {
		// TestDAO 객체 생성시
		// test- query.xml 파일의 내용을 읽어와
		// properties객체에 저장
		try {
			prop = new Properties();
			prop.loadFromXML(new FileInputStream("test-query.xml"));
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	//메서드
	
	
	/** 1행 삽입용 DAO
	 * @param conn
	 * @param vo1
	 * @return result
	 */
	public int insert(Connection conn, TestVO vo1) throws SQLException{
		
		// 1. 결과 저장용 변수 선언
		int result = 0;
	
		try {
			
			// 2. sql 작성(test-query.xml에 작성된 sql 얻어오기) 
			String sql = prop.getProperty("insert");
			
			
			
			
			// 3. preparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
			
			// 4. ?(위치홀더)에 알맞은 값 세팅
			pstmt.setInt(1, vo1.getTestNo());
			pstmt.setString(2, vo1.getTestTitle());
			pstmt.setString(3, vo1.getTestContent());
			
			// 5. sql 수행후 결과 반환 받기
			
			result = pstmt.executeUpdate(); //dml수행, 반영된 행의 개수 반환(int)
			
			
		}finally {
			// 6. 사용한 jdbc 객체 자원 반환(close)
			close(pstmt);
			
		}
		
		return result;
	}


	public int update(Connection conn, TestVO vo) throws SQLException{
		int result = 0;
		
		try {
			
			String sql = prop.getProperty("update");
			
			pstmt = conn.prepareStatement(sql);		
			
			pstmt.setString(1, vo.getTestTitle());
			pstmt.setString(2, vo.getTestContent());
			pstmt.setInt(3, vo.getTestNo());
			
			result = pstmt.executeUpdate();
			
		}finally {
			close(pstmt);
			
		}
		
		
		return result;
	}

}
