package com.cos.navernewssave.util;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.cos.navernewssave.domain.News;

@Component
public class NaverCraw {
int aidNum = 277493;
	
	public List<News> collect() {
		
		RestTemplate rt = new RestTemplate();
		List<News> newsList = new ArrayList<>();

		for (int i = 1; i < 3; i++) {
			int aid =  aidNum;
			String url = "https://news.naver.com/main/read.naver?mode=LSD&mid=shm&sid1=103&oid=437&aid=0000"+ aid;
			String html = rt.getForObject(url, String.class);
			try {
			Document doc = null;
			doc = Jsoup.parse(html);

				Element titleelem = doc.selectFirst("#articleTitle");
				Element timeelem = doc.selectFirst(".t11");
				Element compant = doc.selectFirst("#wrap > table > tbody > tr > td.aside > div > div:nth-child(1) > h4 > em");
				String title = titleelem.text();
				String createdAt = timeelem.text();
				// 오전오후를 yyyy-mm-dd로 parsing하기
				//DateFormat dateParser = new SimpleDateFormat("yyyy.MM.dd. a KK:mm");
				//  date type을 string으로 변환
				//SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				
				//   2021.10.04. 오후 4:44 -> 2021-10-04 16:44:00.0 바꾸는 code
				//String time2 =  transFormat.format(dateParser.parse(time));
				
				//Timestamp createdAt = Timestamp.valueOf(time2);
				
				News news = News.builder()
						.title(title)
						.createdAt(createdAt)
						.build();
				System.out.println(title);
				System.out.println(createdAt);
				newsList.add(news);
				
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}

			aidNum++;

		}
		
		return newsList;
		
		
	}

}
