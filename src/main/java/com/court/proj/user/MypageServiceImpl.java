package com.court.proj.user;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MypageServiceImpl implements MypageService {

	@Autowired
	MypageMapper mypageMapper;

	@Override
	public UserVO getUser(int user_proper_num) {
		return mypageMapper.getUser(user_proper_num);
	}

	@Override
	public int deleteUpdate(UserVO vo) {
		return mypageMapper.deleteUpdate(vo);
	}

	@Override
	public int modifyUpdate(UserVO vo) {
		return mypageMapper.modifyUpdate(vo);
	}



}
