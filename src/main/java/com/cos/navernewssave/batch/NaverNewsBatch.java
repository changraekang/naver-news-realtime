package com.cos.navernewssave.batch;

import java.util.List;  

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.cos.navernewssave.domain.News;
import com.cos.navernewssave.domain.NewsRepository;
import com.cos.navernewssave.util.NaverCraw;

import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@Component
public class NaverNewsBatch {
	
	
		private final NewsRepository newsRepository;
		private final NaverCraw naverCraw;
	
	
	
		@Scheduled(fixedDelay = 1000 * 60 * 1)
		public void newsCrawAndSave() {

			List<News> newsList = naverCraw.collect();
			newsRepository.saveAll(newsList);
			
		}
	
	
}
