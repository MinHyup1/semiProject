package com.kh.semi.notice;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.kh.semi.common.mail.MailSender;
import com.kh.semi.notice.dto.Notice;
import com.kh.semi.schedule.model.service.ScheduleService;

//@DisallowConcurrentExecution
public class NoticeJob implements Job{
	
	private ScheduleService scheduleService = new ScheduleService();
	private MailSender mailSender = new MailSender();

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		Calendar calendar = Calendar.getInstance();
		List<Notice> nowNotice = new ArrayList<Notice>();
		List<Notice> nowDoseNoticeList = new ArrayList<Notice>();
		List<Notice> nowVisitNoticeList = new ArrayList<Notice>();
		System.out.println(calendar.get(Calendar.HOUR_OF_DAY) + " : " + calendar.get(Calendar.MINUTE) + "execute 함수를 실행합니다.");
		
		//현재날짜와 시간을 생성한다.(초단위 제외)
		
		//DB에서  알림 여부가 N인 사용자 이메일, 스케줄 타이틀, 알림시간을 가져온다.
		List<Notice> doseNoticeList = scheduleService.selectEmailAndTimeInDoseNotice();
		List<Notice> visitNoticeList = scheduleService.selectEmailAndTimeInVisitNotice();
		System.out.println("db에서 가져온 알림 : " + doseNoticeList);
		
		
		if(doseNoticeList.size() == 0 && visitNoticeList.size() == 0) return;
		
		for (Notice notice : doseNoticeList) {
			Timestamp noticeTime = notice.getNoticeTime();
			System.out.println("db에서 가져온 알림 : " + notice);
			System.out.println("현재 시 : " + calendar.get(Calendar.HOUR_OF_DAY));
			System.out.println("현재 분 : " + calendar.get(Calendar.MINUTE));
			if(noticeTime.getHours() == calendar.get(Calendar.HOUR_OF_DAY) && noticeTime.getMinutes() == calendar.get(Calendar.MINUTE)) {
				System.out.println("지금 보낼 알림 : " + notice);
				System.out.println("알림 시간입니다!!!");
				nowDoseNoticeList.add(notice);
				nowNotice.add(notice);
			}
		}
		
		for (Notice notice : visitNoticeList) {
			Timestamp noticeTime = notice.getNoticeTime();
			System.out.println(notice);
			if(noticeTime.getHours() == calendar.get(Calendar.HOUR_OF_DAY) && noticeTime.getMinutes() == calendar.get(Calendar.MINUTE)) {
				System.out.println("알림 시간입니다!!!");
				nowVisitNoticeList.add(notice);
				nowNotice.add(notice);
			}
		}
		
		//알림시간이 현재시간과 동일하면 해당 이메일로 메일을 보낸다.
		//해당 알림을 Y로 바꾼다.
		for (Notice notice : nowNotice) {
			System.out.println("메일 전송할 알림 : " + notice);
			mailSender.sendEmail(notice.getEmail(), "medibook 알림", notice.getNoticeName());
		}

		for (Notice notice : nowDoseNoticeList) {
			System.out.println("업데이트 알림 : " + notice);
			scheduleService.updateDoseNotice(notice.getNoticeCode());
		}

		for (Notice notice : nowVisitNoticeList) {
			scheduleService.updateVisitNotice(notice.getNoticeCode());
		}

	}

}
