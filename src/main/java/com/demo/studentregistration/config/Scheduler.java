package com.demo.studentregistration.config;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import com.demo.studentregistration.service.StudentService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
@EnableScheduling
@RequiredArgsConstructor
public class Scheduler {

	private final StudentService studentService;

	@Scheduled(cron = "0 0 */1 * * *") // every one hour
	public void scheduleTaskUsingCronExpression() {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String strDate = sdf.format(new Date());
		System.out.println("Scheduler start " + strDate);
		log.info("schedule - count students " + studentService.countStudent());

	}
}
