package com.cos.navernewssave.batch;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.cos.navernewssave.domain.News;
import com.cos.navernewssave.domain.NewsRepository;
import com.cos.navernewssave.util.NaverCraw;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;


@RequiredArgsConstructor
@Component
public class NaverNewsBatch {
	
	
		private final NewsRepository newsRepository;
		private final NaverCraw naverCraw;
	
	
		long aid = 278000;
		@Scheduled(cron = "0 0 1 * * *", zone = "Asia/Seoul")
		public void newsCrawAndSave() {

			List<News> naverNewsList = new ArrayList<>();
			int successCount =0;
			int errorCount =0;
			
			while ( true ) {
				
			String aidStr = String.format("%010d", aid);
			System.out.println("aidStr : " + aidStr );
			
			String url =  "https://news.naver.com/main/read.naver?mode=LSD&mid=shm&sid1=103&oid=437&aid=" + aidStr;
		
			try {
				Document doc = Jsoup.connect(url).get();
				//System.out.println(doc);
				String title = doc.selectFirst("#articleTitle").text();
				String company = doc.selectFirst(".press_logo img").attr("alt");
				String createdAt = doc.selectFirst(".t11").text();
				createdAt = createdAt.substring(0,10);
				createdAt = createdAt.replace(".","-");
				LocalDate today = LocalDate.now();
				if (today.toString().equals(createdAt)) {
					System.out.println("createdAt : " + createdAt);
					System.out.println("===========");
					break;
				}
				
				LocalDate yesterday = today.minusDays(1);		
				if(yesterday.toString().equals(createdAt)) {
					naverNewsList.add(News.builder()
							.title(title)
							.company(company)
							.createdAt(Timestamp.valueOf(LocalDateTime.now().minusDays(1).plusHours(9)))
							.build()
							);
					
					System.out.println("어제 기사 입니다");
				}
				successCount++;
			} catch (Exception e) {
				System.out.println("해당 주소 페이지를 찾을 수 없습니다 오류:" + e.getMessage());
				errorCount++;
			} 
			aid++;
			
		} //while문 끝
			System.out.println("성공횟수 " + successCount);
			System.out.println("실패횟수 " + errorCount);
			System.out.println("배치프로그램종료");
			System.out.println("마지막 aid : " + aid);
			System.out.println("컬렉션에 담은크기 : " + naverNewsList.size());

			Flux.fromIterable(naverNewsList)
			.flatMap(newsRepository::save)
			.subscribe();
			
		}
	
	
}
