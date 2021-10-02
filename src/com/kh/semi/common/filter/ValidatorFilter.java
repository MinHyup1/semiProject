package com.kh.semi.common.filter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.semi.member.validator.ChangeForm;
import com.kh.semi.member.validator.JoinForm;
import com.kh.semi.schedule.validator.DoseNoticeForm;
import com.kh.semi.schedule.validator.MedicalForm;
import com.kh.semi.schedule.validator.ScheduleForm;
import com.kh.semi.schedule.validator.VisitNoticeForm;


//모든 validator처리(우리의 실수로 나타나는 오류 처리?)를 여기서 하도록 코드 작성
public class ValidatorFilter implements Filter {

    /**
     * Default constructor. 
     */
    public ValidatorFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		String[] uriArr = httpRequest.getRequestURI().split("/");  //넘어온 요청 url을 가져와서 파싱
		
		//  /member/join => [ , member, join]
		
		if(uriArr.length != 0) {
			
			String redirectUrl = null;
			String dispatcherUrl = null;
			String msg = null;
			
			switch (uriArr[1]) {
			//uriArr(넘어온 요청 url)의 1번 인덱스에 들어있는 값이 member 라면,
			case "member":
				redirectUrl = memberValidation(httpRequest, uriArr); //redirectUrl을 받아와서
				break;
			case "schedule":
				Map<String, String> resMap = scheduleValidation(httpRequest, uriArr);
				redirectUrl = resMap.get("redirectUrl");
				dispatcherUrl = resMap.get("dispatcherUrl");
				msg = resMap.get("msg");
				break;
			}
			
			if(dispatcherUrl != null) {
				httpRequest.setAttribute("url", dispatcherUrl);
				httpRequest.setAttribute("msg", msg);
				httpRequest.getRequestDispatcher("/error/result").forward(httpRequest, httpResponse);
				return;
			}
			
			if(redirectUrl != null) { //redirectUrl이 null이 아니라면,
				httpResponse.sendRedirect(redirectUrl);  //그 주소로 재요청
				return;
			}
		}
		
		chain.doFilter(request, response);
	}

	private Map<String, String> scheduleValidation(HttpServletRequest httpRequest, String[] uriArr) {
		ScheduleForm scheduleForm = new ScheduleForm(httpRequest);
		DoseNoticeForm doseNoticeForm = new DoseNoticeForm(httpRequest);
		VisitNoticeForm visitNoticeForm = new VisitNoticeForm(httpRequest);
		MedicalForm medicalForm = new MedicalForm(httpRequest);
		Map<String, String> resMap = new HashMap<String, String>();
		String redirectUrl = null;
		String dispatcherUrl = null;
		String msg = null;
		
		switch (uriArr[2]) {
		case "schedule-register":
			if (!scheduleForm.testHasValue()) {
				dispatcherUrl = "/schedule/schedule-main";
				msg = "진료 날짜를 제외한 입력값이 없어 스케줄 등록이 취소되었습니다.";
			}else if(!scheduleForm.standardDateTest()) {
				dispatcherUrl = "/schedule/schedule-main";
				msg = "진료 날짜가 잘못 입력되어 스케줄 등록이 취소되었습니다.";
			}else if(!scheduleForm.test()) {
				redirectUrl = "/schedule/schedule-record-form?err=1";
			}
			break;
		case "dose-notice-register":
			if(!doseNoticeForm.testHasValue()) {
				dispatcherUrl = "/schedule/schedule-main";
				msg = "입력값이 없어 스케줄 등록이 취소되었습니다.";
			}else if(!doseNoticeForm.standardDateTest()) {
				dispatcherUrl = "/schedule/schedule-main";
				msg = "복용 시작일이 잘못 입력되어 스케줄 등록이 취소되었습니다.";
			}else if(!doseNoticeForm.test()) {
				redirectUrl = "/schedule/medicine-notice-form?err=1";
			}
			break;
		case "visit-notice-register":
			if(!visitNoticeForm.testHasValue()) {
				dispatcherUrl = "/schedule/schedule-main";
				msg = "알림 날짜를 제외한 입력값이 없어 스케줄 등록이 취소되었습니다.";
			}else if(!visitNoticeForm.standardDateTest()) {
				dispatcherUrl = "/schedule/schedule-main";
				msg = "알림 일자가 잘못 입력되어 스케줄 등록이 취소되었습니다.";
			}else if(!visitNoticeForm.test()) {
				redirectUrl = "/schedule/visit-notice-form?err=1";
			}
			break;
		case "renew-prescription":
			if(!doseNoticeForm.testHasValue()) {
				dispatcherUrl = "/schedule/schedule-main";
				msg = "입력값이 없어 스케줄 등록이 취소되었습니다.";
			}else if(!doseNoticeForm.standardDateTest()) {
				dispatcherUrl = "/schedule/schedule-main";
				msg = "복용 시작일이 잘못 입력되어 스케줄 등록이 취소되었습니다.";
			}else if(!doseNoticeForm.test()) {
				redirectUrl = "/schedule/medicine-notice-form?err=1&edit=1";
			}
			break;
		case "renew-visit":
			if(!visitNoticeForm.testHasValue()) {
				dispatcherUrl = "/schedule/schedule-main";
				msg = "알림 날짜를 제외한 입력값이 없어 스케줄 등록이 취소되었습니다.";
			}else if(!visitNoticeForm.standardDateTest()) {
				dispatcherUrl = "/schedule/schedule-main";
				msg = "알림 일자가 잘못 입력되어 스케줄 등록이 취소되었습니다.";
			}else if(!visitNoticeForm.test()) {
				redirectUrl = "/schedule/visit-notice-form?err=1&edit=1";
			}
			break;
		case "renew-medical":
			if(!medicalForm.standardDateTest()) {
				dispatcherUrl = "/schedule/schedule-main";
				msg = "알림 일자가 잘못 입력되어 스케줄 등록이 취소되었습니다.";
			}else if(!medicalForm.test()) {
				redirectUrl = "/schedule/schedule-edit/medical?err=1";
			}
			break;
		}
	
		resMap.put("redirectUrl", redirectUrl);
		resMap.put("dispatcherUrl", dispatcherUrl);
		resMap.put("msg", msg);
		return resMap;
	}

	private String memberValidation(HttpServletRequest httpRequest, String[] uriArr) {

		//재요청 할 주소를 넣기 위한 변수 선언
		String redirectUrl = null;
		
		switch (uriArr[2]) {
		case "join":
			JoinForm joinForm = new JoinForm(httpRequest);
			if (!joinForm.test()) { //joinForm의 테스트의 결과가 false라면(validator를 통과하지 못했을 때), 밑의 경로로 리다이렉트 하도록 지정
				redirectUrl = "/member/joinPage";
			}
			break;
		case "memberInfo":
			ChangeForm changeForm = new ChangeForm(httpRequest);
			if (!changeForm.test()) { //joinForm의 테스트의 결과가 false라면(validator를 통과하지 못했을 때), 밑의 경로로 리다이렉트 하도록 지정
				redirectUrl = "/member/memberInfo";
			}
			break;
		case "kakaoMemberInfo":
			ChangeForm changeForm2 = new ChangeForm(httpRequest);
			if (!changeForm2.test()) { //joinForm의 테스트의 결과가 false라면(validator를 통과하지 못했을 때), 밑의 경로로 리다이렉트 하도록 지정
				redirectUrl = "/member/kakaoMemberInfo";
			}
			break;
		}
		return redirectUrl;
	}
	
	
	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}

