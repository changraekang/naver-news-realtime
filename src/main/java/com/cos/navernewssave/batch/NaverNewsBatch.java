package com.cos.navernewssave.batch;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class NaverNewsBatch {

	
	
		@Scheduled( cron = "0 0 1 * * *" )
		public void newsCrawAndSave() {
			
			
		}
	
	
}
