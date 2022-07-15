package review.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import inter.CommandException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ReviewDAO {
		static ReviewDAO instance = null;
		
		public static ReviewDAO getInstance() throws Exception{
			if(instance == null) instance = new ReviewDAO();
			return instance;
		}
		
		private String dbDriver = "oracle.jdbc.driver.OracleDriver";
		private String dbUrl = "jdbc:oracle:thin:@localhost:1521:rpa111";
		private String dbUser = "gundam";
		private String dbPass = "gundam";
		
		private Connection con;
		
		private ReviewDAO() {
			// 0. 드라이버를 메모리 로딩 (예외처리)
			try {
				Class.forName(dbDriver);
			}catch (Exception e) {
				// TODO: handle exception
				System.out.println("DB 연결시 오류:" + e);
			}
		}

		public List<TotalVO> getTotalList(String keyValue) throws Exception{
			if(keyValue.equals("totalPost")) {
				PreparedStatement ps = null;
				PreparedStatement ps2 = null;
				PreparedStatement ps3 = null;
				ResultSet rs = null;
				ResultSet rs2 = null;
				ResultSet rs3 = null;
				List<TotalVO> list = new ArrayList<TotalVO>();
				boolean isEmpty = true;
				
				try {
				// 1. 연결객체 얻어오기
				con = DriverManager.getConnection(dbUrl, dbUser, dbPass);
				// 2. sql 문장
				String sql = "select * from notice";
				String sql2 = "select * from qna";
				String sql3 = "select * from inquiry";
				// 3. 전송객체 얻어오기
				ps = con.prepareStatement(sql);
				ps2 = con.prepareStatement(sql2);
				ps3 = con.prepareStatement(sql3);
				// 4. 전송(executeQuery())
				rs = ps.executeQuery();			
				rs2 = ps2.executeQuery();			
				rs3 = ps3.executeQuery();			
				// 5. 결과 받아 List<CateVO>에 담기
				while(rs.next()) {
					isEmpty = false;
					TotalVO tv = new TotalVO();
					tv.no = rs.getString("notice_no");
					tv.title = rs.getString("notice_title");
					list.add(tv);
				}
				
				while(rs2.next()) {
					isEmpty = false;
					TotalVO tv = new TotalVO();
					tv.no = rs2.getString("qna_no");
					tv.title = rs2.getString("qna_title");
					list.add(tv);
				}	
				
				while(rs3.next()) {
					isEmpty = false;
					TotalVO tv = new TotalVO();
					tv.no = rs3.getString("inquiry_no");
					tv.title = rs3.getString("inquiry_title");
					list.add(tv);
				}
				
				for (TotalVO tv : list) {
					System.out.println(tv.no+"------"+tv.title);
				}
				
				} finally {
				// 6. 닫기
					try{rs.close();}catch (Exception e) {}
					try{rs2.close();}catch (Exception e) {}
					try{rs3.close();}catch (Exception e) {}
					try{ps.close();}catch (Exception e) {}
					try{ps2.close();}catch (Exception e) {}
					try{ps3.close();}catch (Exception e) {}
				}
				if(isEmpty) {return Collections.emptyList();}
				return list;
				
			}else{
				PreparedStatement ps = null;
				ResultSet rs = null;
				List<TotalVO> list = new ArrayList<TotalVO>();
				boolean isEmpty = true;
				
				try {
				// 1. 연결객체 얻어오기
				con = DriverManager.getConnection(dbUrl, dbUser, dbPass);
				// 2. sql 문장
				String sql = "select * from "+keyValue;
				// 3. 전송객체 얻어오기
				ps = con.prepareStatement(sql);
				// 4. 전송(executeQuery())
				rs = ps.executeQuery();			
				// 5. 결과 받아 List<CateVO>에 담기
				while(rs.next()) {
					isEmpty = false;
					TotalVO tv = new TotalVO();
					if(keyValue.equals("notice")) {
						tv.no = rs.getString("notice_no");
						tv.title = rs.getString("notice_title");
					}else if(keyValue.equals("qna")) {
						tv.no = rs.getString("qna_no");
						tv.title = rs.getString("qna_title");
					}else {
						tv.no = rs.getString("inquiry_no");
						tv.title = rs.getString("inquiry_title");
					}
					list.add(tv);
				}
				
				for (TotalVO tv : list) {
					System.out.println(tv.no+"------"+tv.title);
				}
				
				} finally {
				// 6. 닫기
					try{rs.close();}catch (Exception e) {
						// TODO: handle exception
					}
					try{ps.close();}catch (Exception e) {
						// TODO: handle exception
					}
				}
				if(isEmpty) {return Collections.emptyList();}
				return list;
				}
		}
		
		
		public List<NoticeVO> getNoticeList() throws Exception{
			PreparedStatement ps = null;
			ResultSet rs = null;
			List<NoticeVO> list = new ArrayList<NoticeVO>();
			boolean isEmpty = true;
			
			try {
			// 1. 연결객체 얻어오기
			con = DriverManager.getConnection(dbUrl, dbUser, dbPass);
			// 2. sql 문장
			String sql = "select * from notice";
			// 3. 전송객체 얻어오기
			ps = con.prepareStatement(sql);
			// 4. 전송(executeQuery())
			rs = ps.executeQuery();			
			// 5. 결과 받아 List<CateVO>에 담기
			while(rs.next()) {
				isEmpty = false;
				NoticeVO nv = new NoticeVO();
				nv.notice_no = rs.getString("notice_no");
				nv.notice_title = rs.getString("notice_title");			
				list.add(nv);
			}		
			} finally {
			// 6. 닫기
				try{rs.close();}catch (Exception e) {
					// TODO: handle exception
				}
				try{ps.close();}catch (Exception e) {
					// TODO: handle exception
				}
			}
			if(isEmpty) {return Collections.emptyList();}
			return list;
		}
		
		public List<QnaVO> getQnaList() throws Exception{
			PreparedStatement ps = null;
			ResultSet rs = null;
			List<QnaVO> list = new ArrayList<QnaVO>();
			boolean isEmpty = true;
			
			try {
			// 1. 연결객체 얻어오기
			con = DriverManager.getConnection(dbUrl, dbUser, dbPass);
			// 2. sql 문장
			String sql = "select * from qna";
			// 3. 전송객체 얻어오기
			ps = con.prepareStatement(sql);
			// 4. 전송(executeQuery())
			rs = ps.executeQuery();			
			// 5. 결과 받아 List<CateVO>에 담기
			while(rs.next()) {
				isEmpty = false;
				QnaVO qv = new QnaVO();
				qv.qna_no = rs.getString("qna_no");
				qv.qna_title = rs.getString("qna_title");
				list.add(qv);
			}		
			} finally {
			// 6. 닫기
				try{rs.close();}catch (Exception e) {
					// TODO: handle exception
				}
				try{ps.close();}catch (Exception e) {
					// TODO: handle exception
				}
			}
			if(isEmpty) {return Collections.emptyList();}
			return list;
		}
		
		
		public List<InquiryVO> getInquiryList() throws Exception{
			PreparedStatement ps = null;
			ResultSet rs = null;
			List<InquiryVO> list = new ArrayList<InquiryVO>();
			boolean isEmpty = true;
			
			try {
			// 1. 연결객체 얻어오기
			con = DriverManager.getConnection(dbUrl, dbUser, dbPass);
			// 2. sql 문장
			String sql = "select * from inquiry";
			// 3. 전송객체 얻어오기
			ps = con.prepareStatement(sql);
			// 4. 전송(executeQuery())
			rs = ps.executeQuery();			
			// 5. 결과 받아 List<CateVO>에 담기
			while(rs.next()) {
				isEmpty = false;
				InquiryVO iv = new InquiryVO();
				iv.inquiry_no = rs.getString("inquiry_no");
				iv.inquiry_title = rs.getString("inquiry_title");			
				list.add(iv);
			}		
			} finally {
			// 6. 닫기
				try{rs.close();}catch (Exception e) {
					// TODO: handle exception
				}
				try{ps.close();}catch (Exception e) {
					// TODO: handle exception
				}
			}
			if(isEmpty) {return Collections.emptyList();}
			return list;
		}
	
		public List<FoodVO> getFoodList(int cateNo) throws Exception{
			PreparedStatement ps = null;
			ResultSet rs = null;
			List<FoodVO> list = new ArrayList<FoodVO>();
			boolean isEmpty = true;
			
			try {
			// 1. 연결객체 얻어오기
			con = DriverManager.getConnection(dbUrl, dbUser, dbPass);
			// 2. sql 문장
			String sql = "select * from food where cate_no="+cateNo;
			// 3. 전송객체 얻어오기
			ps = con.prepareStatement(sql);
			// 4. 전송(executeQuery())
			rs = ps.executeQuery();			
			// 5. 결과 받아 List<CateVO>에 담기
			while(rs.next()) {
				isEmpty = false;
				FoodVO fv = new FoodVO();
				fv.food_no = rs.getString("food_no");
				fv.cate_no = rs.getString("cate_no");
				fv.food_name = rs.getString("food_name");				
				list.add(fv);
			}		
			} finally {
			// 6. 닫기
				try{rs.close();}catch (Exception e) {
					// TODO: handle exception
				}
				try{ps.close();}catch (Exception e) {
					// TODO: handle exception
				}
			}
			if(isEmpty) {return Collections.emptyList();}
			return list;
		}
		
		public List<CateVO> getCateList() throws Exception{
			PreparedStatement ps = null;
			ResultSet rs = null;
			List<CateVO> list = new ArrayList<CateVO>();
			boolean isEmpty = true;
			try {
			// 1. 연결객체 얻어오기
				con = DriverManager.getConnection(dbUrl, dbUser, dbPass);
			// 2. sql 문장
			String sql = "select * from cate";
			// 3. 전송객체 얻어오기
			ps = con.prepareStatement(sql);
			// 4. 전송(executeQuery())
			rs = ps.executeQuery();			
			// 5. 결과 받아 List<CateVO>에 담기
			while(rs.next()) {
				isEmpty = false;
				CateVO cv = new CateVO();
				cv.cate_no = rs.getString("cate_no");
				cv.cate_name = rs.getString("cate_name");				
				list.add(cv);
			}
			} finally {
			// 6. 닫기
				try{rs.close();}catch (Exception e) {
					// TODO: handle exception
				}
				try{ps.close();}catch (Exception e) {
					// TODO: handle exception
				}
			}
			if(isEmpty) {return Collections.emptyList();}
			return list;
		}
		
		
}
