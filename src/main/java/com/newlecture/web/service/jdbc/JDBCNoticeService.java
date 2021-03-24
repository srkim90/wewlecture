package com.newlecture.web.service.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.newlecture.web.entity.Notice;
import com.newlecture.web.service.NoticeService;


/*
CREATE TABLE NOTICE
( ID NUMBER NOT NULL,
TITLE NVARCHAR2(100) NOT NULL,
WRITER_ID NVARCHAR2(50) NOT NULL,
CONTENT CLOB,
REGDATE TIMESTAMP (6) DEFAULT systimestamp NOT NULL,
HIT NUMBER DEFAULT 0 NOT NULL,
FILES NVARCHAR2(1000),
PUB NUMBER(1,0) DEFAULT 0 NOT NULL,
CONSTRAINT "NOTICE_PK" PRIMARY KEY ("ID")
)


for mariadb

CREATE TABLE NOTICE( 
	ID INT NOT NULL,
	TITLE VARCHAR(100)  NOT NULL,
	WRITER_ID VARCHAR(50)  NOT NULL,
	CONTENT BLOB,
            REGDATE TIMESTAMP  NOT NULL DEFAULT CURRENT_TIMESTAMP,
	HIT INT DEFAULT 0 NOT NULL,
	FILES VARCHAR(1000),
	PUB INT DEFAULT 0 NOT NULL,
	PRIMARY KEY (ID)
);

mysql -u stock -p -h localhost stock
show tables;

 * 
 * 
 * */
//import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Component
public class JDBCNoticeService implements NoticeService {
	private String url = "jdbc:mariadb://192.168.20.100:3306/stock";
	private String uid = "stock";
	private String pwd = "9998";
	private String driver = "org.mariadb.jdbc.Driver";
	
	@Autowired
	private DataSource dataSource;
	
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	public Notice getItem(String id)
	{
		
		String sql = "SELECT * FROM NOTICE WHERE ID = ? ";	
		Connection con = null;
		try {
			con = dataSource.getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		PreparedStatement st = null;
		try {
			st = con.prepareStatement(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			st.setString(1, id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		ResultSet rs;
		
		try {
			
			rs = st.executeQuery();
			while(rs.next()){
			    int __id = rs.getInt("ID");
			    String title = rs.getString("TITLE");
			    String writerId = rs.getString("WRITER_ID");
			    Date regDate = rs.getDate("REGDATE");
			    String content = rs.getString("CONTENT");
			    int hit = rs.getInt("hit");
			    String files = rs.getString("FILES");
			    
			    Notice notice = new Notice(
						__id,
						title,
						writerId,
						regDate,
						content,
						hit,
						files
					);
			    
			    return notice;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	    return null;
	}
	
	public List<Notice> getList(int page, String field, String query) throws ClassNotFoundException, SQLException{
		
		int start = 1 + (page-1)*10;     // 1, 11, 21, 31, ..
		int end = 10*page; // 10, 20, 30, 40...
		
		String sql = "SELECT * FROM NOTICE WHERE "+field+" LIKE ? limit ?, 10";	
		
		//Class.forName(driver);
		System.out.println(sql);
		//Connection con = DriverManager.getConnection(url,uid, pwd);
		Connection con = dataSource.getConnection();
		PreparedStatement st = con.prepareStatement(sql);
		st.setString(1, "%"+query+"%");
		st.setInt(2, start);
		//st.setInt(3, end);
		ResultSet rs = st.executeQuery();
		
		List<Notice> list = new ArrayList<Notice>();
		
		while(rs.next()){
		    int id = rs.getInt("ID");
		    String title = rs.getString("TITLE");
		    String writerId = rs.getString("WRITER_ID");
		    Date regDate = rs.getDate("REGDATE");
		    String content = rs.getString("CONTENT");
		    int hit = rs.getInt("hit");
		    String files = rs.getString("FILES");
		    //System.out.println("" + title + writerId);
		    Notice notice = new Notice(
		    					id,
		    					title,
		    					writerId,
		    					regDate,
		    					content,
		    					hit,
		    					files
		    				);
		    
		    list.add(notice);
		    
		}

		
		rs.close();
		st.close();
		con.close();
		
		return list;
	}
	
	// Scalar 
	public int getCount() throws ClassNotFoundException, SQLException {
		int count = 0;
		
		String sql = "SELECT COUNT(ID) COUNT FROM NOTICE";	
		
		Class.forName(driver);
		Connection con = DriverManager.getConnection(url,uid, pwd);
		Statement st = con.createStatement();
		
		ResultSet rs = st.executeQuery(sql);
		
		if(rs.next())
			count = rs.getInt("COUNT");		
		
		rs.close();
		st.close();
		con.close();
		
		return count;
	}

	public int insert(Notice notice) throws SQLException, ClassNotFoundException {
		String title = notice.getTitle();
		String writerId = notice.getWriterId();
		String content = notice.getContent();
		String files = notice.getFiles();
		
		String url = "jdbc:mariadb://192.168.20.100:3306/stock";
		String sql = "INSERT INTO notice (    " + 
				"    title," + 
				"    writer_id," + 
				"    content," + 
				"    files" + 
				") VALUES (?,?,?,?)";	
		
		//Class.forName(driver);
		//Connection con = DriverManager.getConnection(url,uid, pwd);
		Connection con = dataSource.getConnection();
		//Statement st = con.createStatement();
		//st.ex....(sql)
		PreparedStatement st = con.prepareStatement(sql);
		st.setString(1, title);
		st.setString(2, writerId);
		st.setString(3, content);
		st.setString(4, files);
		
		int result = st.executeUpdate();
		
		
		st.close();
		con.close();
		
		return result;
	}
	
	public int update(Notice notice) throws SQLException, ClassNotFoundException {
		String title = notice.getTitle();
		String content = notice.getContent();
		String files = notice.getFiles();
		int id = notice.getId();
		
		String url = "jdbc:mariadb://192.168.20.100:3306/stock";
		String sql = "UPDATE NOTICE " + 
				"SET" + 
				"    TITLE=?," + 
				"    CONTENT=?," + 
				"    FILES=?" + 
				"WHERE ID=?";
		
		//Class.forName(driver);
		//Connection con = DriverManager.getConnection(url,uid, pwd);
		Connection con = dataSource.getConnection();
		//Statement st = con.createStatement();
		//st.ex....(sql)
		PreparedStatement st = con.prepareStatement(sql);
		st.setString(1, title);
		st.setString(2, content);
		st.setString(3, files);
		st.setInt(4, id);
		
		int result = st.executeUpdate();
				
		st.close();
		con.close();
		
		return result;
	}
	
	public int delete(int id) throws ClassNotFoundException, SQLException {
	
		String url = "jdbc:mariadb://192.168.20.100:3306/stock";
		String sql = "DELETE NOTICE WHERE ID=?";
		
		//Class.forName(driver);
		//Connection con = DriverManager.getConnection(url,uid, pwd);
		Connection con = dataSource.getConnection();
		//Statement st = con.createStatement();
		//st.ex....(sql)
		PreparedStatement st = con.prepareStatement(sql);		
		st.setInt(1, id);
		
		int result = st.executeUpdate();
				
		st.close();
		con.close();
		
		return result;
	}

	
}
