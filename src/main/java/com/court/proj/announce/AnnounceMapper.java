package com.court.proj.announce;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;

import com.court.proj.aplcnReg.TrialVO;

@Mapper
public interface AnnounceMapper {

	public AnnounceVO getinfo(String admin_id);

	// 등록
	public int announceRegist(AnnounceVO vo);

	// 공고 리스트 조회
	public ArrayList<AnnounceVO> getannounceList();

	// 검색기능
	public ArrayList<AnnounceVO> searchAnnounceByTitleAndContent(String keyword);

	public ArrayList<AnnounceVO> searchAnnounceByTitle(String keyword);

	public ArrayList<AnnounceVO> searchAnnounceByContent(String keyword);

	// 재판조력자 유형 조회하기
	public ArrayList<TrialVO> getTrial();

	// 선택된 재판조력자 데이터 불러오기
	public TrialVO getTrialVO(int trial_pn);

	// 선택한 재판조력자 pk 불러오기
	public int getTrialNum1(String st1, String st2);

	public int getTrialNum2(String st1, String st2, String st3);

	// 공고 등록 TB_002
	public int announceRegistTB002(AnnounceVO vo);

	// 공고 등록 TB_015
	public int adminRegistTB015(AnnounceVO vo);

}
