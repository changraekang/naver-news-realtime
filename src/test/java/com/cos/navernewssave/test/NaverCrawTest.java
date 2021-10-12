package com.cos.navernewssave.test;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.junit.jupiter.api.Test;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.cos.navernewssave.domain.News;

import ch.qos.logback.core.recovery.ResilientSyslogOutputStream;


public class NaverCrawTest {
int aidNum = 800;
	
	@Test
	public void collect() {
		RestTemplate rt = new RestTemplate();
		List<News> newsList = new ArrayList<>();
		for (int i = 1; i < 3; i++) {
			
			String aid = String.format("%010d", aidNum);
			String url = "https://news.naver.com/main/read.naver?mode=LSD&mid=shm&sid1=103&oid=437&aid=0000277493";
			try {
			String html = rt.getForObject(url, String.class);
			Document doc = null;
			doc = Jsoup.parse(html);

				Element titleelem = doc.selectFirst("#articleTitle");
				Element timeelem = doc.selectFirst(".t11");
				String title = titleelem.text();
				String time = timeelem.text();
				
				DateFormat dateParser = new SimpleDateFormat("yyyy.MM.dd. a KK:mm");
				SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

				String time2 =  transFormat.format(dateParser.parse(time));
				
				Timestamp createdAt = Timestamp.valueOf(time2);
				
				News news = News.builder()
						.title(title)
						.build();

				System.out.println(title);
				System.out.println(createdAt);
				System.out.println("테스트");
				
				
				newsList.add(news);

			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		
			aidNum++;

		}
		
		
		
	}

}