package com.team3.groupware.nohyun.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.team3.groupware.nohyun.model.AddressAllDAO;
import com.team3.groupware.nohyun.model.AddressAllVO;

@Service
public class AddressAllServiceImpl implements AddressAllService{

	@Autowired
	AddressAllDAO addressAllDao;
	
	@Override
	public List<Map<String, Object>> list(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return this.addressAllDao.selectList(map); // �����鼭 �޴� �ֵ� (ȣ��� ������ ���� �ϴ� ����)
	}

	@Override
	public List<Map<String, Object>> list_chosung(Map<String, Object> chosung) {
		// TODO Auto-generated method stub
		return this.addressAllDao.selectList_chosung(chosung);
	}

	@Override
	public List<Map<String, Object>> list_whole(Map<String, Object> whole) {
		// TODO Auto-generated method stub
		return this.addressAllDao.selectList_whole(whole);
	}

	@Override
	public List<Map<String, Object>> keyword_search(Map<String, Object> keyword) {
		// TODO Auto-generated method stub
		return this.addressAllDao.selectList_search(keyword);
	}

	@Override
	public List<AddressAllVO> excelList(AddressAllVO excelDown) {
		// TODO Auto-generated method stub
		return addressAllDao.excelList(excelDown);
	}

	@Override
	public List<Map<String, Object>> addressInfo(Map<String, Object> AllInfo) {
		// TODO Auto-generated method stub
		return this.addressAllDao.selectList_info(AllInfo);
	}

	// 주소록 총 개수 구하기
	@Override
	public int countTotal_addressAll() {
		return this.addressAllDao.countTotal_addressAll();
	}

}
