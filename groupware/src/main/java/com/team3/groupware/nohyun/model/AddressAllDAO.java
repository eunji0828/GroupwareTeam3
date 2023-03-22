package com.team3.groupware.nohyun.model;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class AddressAllDAO {

	@Autowired
	SqlSessionTemplate sqlSessionTemplate;
	
	public List<Map<String, Object>> selectList(Map<String, Object> map) {
		
		return this.sqlSessionTemplate.selectList("address_all.address_all_list",map);
}

	public List<Map<String, Object>> selectList_chosung(Map<String, Object> chosung) {
		// TODO Auto-generated method stub
		return this.sqlSessionTemplate.selectList("address_all.address_chosung_list",chosung);
	}

	public List<Map<String, Object>> selectList_whole(Map<String, Object> whole) {
		// TODO Auto-generated method stub
		return this.sqlSessionTemplate.selectList("address_all.address_whole_list",whole);
	}

	public List<Map<String, Object>> selectList_search(Map<String, Object> keyword) {
		// TODO Auto-generated method stub
		return this.sqlSessionTemplate.selectList("address_all.address_search",keyword);
	}

	public List<AddressAllVO> excelList(AddressAllVO excelDown) {
		// TODO Auto-generated method stub
		return this.sqlSessionTemplate.selectList("address_all.address_all_excel",excelDown);
	}

	public List<Map<String, Object>> selectList_info(Map<String, Object> AllInfo) {
		// TODO Auto-generated method stub
		return this.sqlSessionTemplate.selectList("address_all.address_all_info",AllInfo);
	}

	// 주소록 총 개수 구하기
	public int countTotal_addressAll() {
		return (Integer) this.sqlSessionTemplate.selectOne("address_all.countTotal_addressAll");
	}
}