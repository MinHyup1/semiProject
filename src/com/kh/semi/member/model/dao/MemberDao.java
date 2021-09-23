package com.kh.semi.member.model.dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.kh.semi.common.db.JDBCTemplate;
import com.kh.semi.common.exception.DataAccessException;
import com.kh.semi.member.model.dto.Member;

import oracle.jdbc.proxy.annotation.Pre;

//DAO(DATA ACCESS OBJECT)
//DBMS에 접근해 데이터의 조회, 수정, 삽입, 삭제 요청을 보내는 클래스
//DAO의 메서드는 하나의 메서드 당 하나의 쿼리만 처리하도록 작성
public class MemberDao {
	
	private JDBCTemplate template = JDBCTemplate.getInstance();

	public Member memberAuthenticate(String userId, String password, Connection conn) {
		Member member = null;
		PreparedStatement pstm = null;
		ResultSet rset = null;
		String query = "select * from member where id = ? and password = ?";
		
		try {
			pstm = conn.prepareStatement(query);
			pstm.setString(1, userId);
			pstm.setString(2, password);
			rset = pstm.executeQuery();
			
			if(rset.next()) {
				member = convertAllToMember(rset);
			}
		} catch (SQLException e) {
			throw new DataAccessException(e);
		}finally {
			template.close(rset, pstm);
			
		}
		
		return member;
	}

	public Member selectMemberById(String userId, Connection conn) {
		Member member = null;
		PreparedStatement pstm = null;
		ResultSet rset = null;
		String query = "select * from member where id = ?";
		
		try {
			pstm = conn.prepareStatement(query);
			pstm.setString(1, userId);
			rset = pstm.executeQuery();
			if(rset.next()) {
				member = convertAllToMember(rset);
			}
		} catch (SQLException e) {
			throw new DataAccessException(e);
		} finally {
			template.close(rset, pstm);
		}
		
		return member;
	}

	public List<Member> selectMemberList(Connection conn) {
		List<Member> memberList = new ArrayList<Member>();
		PreparedStatement pstm = null;
		ResultSet rset = null;
		
		String columns = "id, email, phone, name";
		String query = "select" + columns + "from member";
	
		try {
			pstm = conn.prepareStatement(query);
			rset = pstm.executeQuery();
			
			while(rset.next()) {
				Member member = convertRowToMember(columns.split(","),rset);
				memberList.add(member);
			}
		} catch (SQLException e) {
			throw new DataAccessException(e);
		} finally {
			template.close(rset, pstm, conn);
		}
		return memberList;
	}

	public int insertMember(Member member, Connection conn) {
		int res = 0;
		PreparedStatement pstm = null;
		String query = "insert into member(user_code, id, password, name, nick, phone, address, email, gender)"
						+ " values(member_user_code.nextval,?,?,?,?,?,?,?,?)";

		try {
			pstm = conn.prepareStatement(query);
			pstm.setString(1, member.getId());
			pstm.setString(2, member.getPassword());
			pstm.setString(3, member.getName());
			pstm.setString(4, member.getNick());
			pstm.setString(5, member.getPhone());
			pstm.setString(6, member.getAddress());
			pstm.setString(7, member.getEmail());
			pstm.setString(8, member.getGender());
			
			res = pstm.executeUpdate();
		} catch (SQLException e) {
			throw new DataAccessException(e);
		} finally {
			template.close(pstm);
		}
		return res;	
	}

	// userId로 ' or 1=1 or user_id = ' 값을 전달받으면 모든 회원의 비밀번호가 수정
	// SQL Injection 공격
	// 악의적인 SQL구문을 주입해서 상대방의 DataBase를 공격하는 기법

	// SQL Injection 공격 막기 위해 PreparedStatement 사용
	// 인스턴스를 생성할 때 쿼리 템플릿을 미리 등록
	// 생성시 등록된 쿼리 템플릿의 구조가 변경되는 것을 방지
	// 문자열에 대해서 자동으로 이스케이프 처리
	// ex) ->\' or 1=1 or user_id = \'
	public int updatePassword(String userId, String password, Connection conn) {
		int res = 0;
		PreparedStatement pstm = null;
		
		String query = "update member set password = ? where id = ?";
		
		try {
			pstm = conn.prepareStatement(query);
			pstm.setString(1, password);
			pstm.setString(2, userId);
			res = pstm.executeUpdate();
		} catch (SQLException e) {
			throw new DataAccessException(e);
		} finally {
			template.close(pstm);
		}
		return res;
	}

	public int deleteMember(String userId, Connection conn) {
		int res = 0;
		PreparedStatement pstm = null;
		
		String query = "delete from member where id = ?";
		
		try {
			pstm = conn.prepareStatement(query);
			pstm.setString(1, userId);
			res = pstm.executeUpdate();
		} catch (SQLException e) {
			throw new DataAccessException(e);
		} finally {
			template.close(pstm);
		}
		return res;
	}

	public Member selectMemberByNick(String nick, Connection conn) {
		Member member = null;
		PreparedStatement pstm = null;
		ResultSet rset = null;
		String query = "select * from member where nick = ?";
		
		try {
			pstm = conn.prepareStatement(query);
			pstm.setString(1, nick);
			rset = pstm.executeQuery();
			if(rset.next()) {
				member = convertAllToMember(rset);
			}
		} catch (SQLException e) {
			throw new DataAccessException(e);
		} finally {
			template.close(rset, pstm);
		}
		
		return member;
	}
	
	public Member selectMemberByPhone(String phone, Connection conn) {
		Member member = null;
		PreparedStatement pstm = null;
		ResultSet rset = null;
		String query = "select * from member where phone = ?";
		
		try {
			pstm = conn.prepareStatement(query);
			pstm.setString(1, phone);
			rset = pstm.executeQuery();
			if(rset.next()) {
				member = convertAllToMember(rset);
			}
		} catch (SQLException e) {
			throw new DataAccessException(e);
		} finally {
			template.close(rset, pstm);
		}
		
		return member;
	}
	
	public Member selectMemberByEmail(String email, Connection conn) {
		Member member = null;
		PreparedStatement pstm = null;
		ResultSet rset = null;
		String query = "select * from member where email = ?";
		
		try {
			pstm = conn.prepareStatement(query);
			pstm.setString(1, email);
			rset = pstm.executeQuery();
			if(rset.next()) {
				member = convertAllToMember(rset);
			}
		} catch (SQLException e) {
			throw new DataAccessException(e);
		} finally {
			template.close(rset, pstm);
		}
		
		return member;
	}
	
	public Member findUserId(String userName, String email, String tell, Connection conn) {
		Member member = null;
		PreparedStatement pstm = null;
		ResultSet rset = null;
		String query = "select * from member where name = ? and email = ? and phone = ?";
		
		try {
			pstm = conn.prepareStatement(query);
			pstm.setString(1, userName);
			pstm.setString(2, email);
			pstm.setString(3, tell);
			rset = pstm.executeQuery();
			
			if(rset.next()) {
				member = convertAllToMember(rset);
			}
		} catch (SQLException e) {
			throw new DataAccessException(e);
		} finally {
			template.close(rset, pstm);
		}
		return member;
	}
	
	public Member findUserPassword(String userId, String userName, String email, String tell, Connection conn) {
		Member member = null;
		PreparedStatement pstm = null;
		ResultSet rset = null;
		String query = "select * from member where id = ? and name = ? and email = ? and phone = ?";
		
		try {
			pstm = conn.prepareStatement(query);
			pstm.setString(1, userId);
			pstm.setString(2, userName);
			pstm.setString(3, email);
			pstm.setString(4, tell);
			rset = pstm.executeQuery();
			
			if(rset.next()) {
				member = convertAllToMember(rset);
			}
		} catch (SQLException e) {
			throw new DataAccessException(e);
		} finally {
			template.close(rset, pstm);
		}
		return member;
	}
	
	
	
	
	private Member convertAllToMember(ResultSet rset) throws SQLException {
		Member member = new Member();
		member.setUserCode(rset.getString("user_code"));
		member.setId(rset.getString("id"));
		member.setPassword(rset.getString("password"));
		member.setName(rset.getString("name"));
		member.setNick(rset.getString("nick"));
		member.setPhone(rset.getString("phone"));
		member.setAddress(rset.getString("address"),"","");
		member.setEmail(rset.getString("email"));
		member.setGender(rset.getString("gender"));

		return member;
	}
	
	//쿼리에 원하는 컬럼값을 전달하면 그 컬럼값을 가져오는 메서드
	private Member convertRowToMember(String[] columns, ResultSet rset) throws SQLException {
		Member member = new Member();
		
		for (int i = 0; i < columns.length; i++) {
			String column = columns[i].toLowerCase(); //입력하는 컬럼명을 소문자로 변경
			column = column.trim();
			
			switch (column) {
			case "user_code" : member.setUserCode(rset.getString("user_code")); break;
			case "id": member.setId(rset.getString("id")); break;
			case "password" : member.setPassword(rset.getString("password")); break;
			case "name" : member.setName(rset.getString("name")); break;
			case "nick" : member.setNick(rset.getString("nick")); break;
			case "phone" : member.setPhone(rset.getString("phone")); break;
			case "address" : member.setAddress(rset.getString("address"),"",""); break;
			case "email" : member.setEmail(rset.getString("email")); break;
			case "gender" : member.setGender(rset.getString("gender")); break;
			default : throw new SQLException("부적절한 컬럼명을 전달했습니다."); //예외처리
			}
		}
		return member;
	}



	
	
	
	
	

}
