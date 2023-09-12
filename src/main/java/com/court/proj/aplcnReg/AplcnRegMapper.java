package com.court.proj.aplcnReg;

import com.court.proj.announce.AnnounceVO;
import com.court.proj.user.UserVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;

@Mapper
public interface AplcnRegMapper {

    // 기본정보 불러오기
    public UserVO getInfo(String userId);

    // 법원 조회하기
    public ArrayList<CourtVO> getCourt();

    // 재판조력자 유형 조회하기
    public ArrayList<TrialVO> getTrial();

    // 모집중 공고 불러오기
    public ArrayList<AnnounceVO> getAnnounce(String date);

    // 선택된 재판조력자 데이터 불러오기
    public TrialVO getTrialVO(int trial_pn);

    // 선택한 재판조력자 pk 불러오기
    public int getTrialNum1(String st1, String st2);
    public int getTrialNum2(String st1, String st2, String st3);

    // 상세정보 테이블에 유저 정보가 있는지 확인하기
    public int getDetailInfo(int user_num);

    // 상세정보 테이블에 데이터 넣기
    public void setDetailInfo(InfoVO ivo);

    // 상세정보 테이블 업데이트하기
    public void updateDetailInfo(InfoVO ivo);

    // 상세정보 테이블값 불러오기
    public InfoVO getAllDetailInfo(int user_num);

    // 기본정보 업데이트하기
    public void updateInfo(UserVO uvo);

    // 경력사항 불러오기
    public ArrayList<AddInfoVO> getCareer(int reg_num);

    // 활동기록 불러오기
    public AddInfoVO getSpecial(int reg_num);

    // 경력 테이블에 유저 정보가 있는지 확인하기
    public int getCareerInfo(int reg_num);

    // 특기사항 테이블에 유저 정보가 있는지 확인하기
    public int getSpecialInfo(int reg_num);

    // 경력정보 업데이트하기
    public void updateCareer007(AddInfoVO aivo);
    public void updateCareer007_2(AddInfoVO aivo);

    // 경력정보 저장하기
    public void setCareerTB007(AddInfoVO aivo);
    public void setCareerTB007_2(AddInfoVO aivo);



//    void setCerti(CertiVO vo);
//    public ArrayList<CertiVO> getCerti(@Param("vo") CertiVO vo);

}
