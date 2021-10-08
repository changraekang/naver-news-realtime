package com.cos.navernewssave.util;

import java.sql.Timestamp;
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
int aidNum = 800;
	
	public List<News> collect() {
		
		RestTemplate rt = new RestTemplate();
		List<News> newsList = new ArrayList<>();

		for (int i = 1; i < 3; i++) {
			String aid = String.format("%010d", aidNum);
			String url = "https://news.naver.com/main/read.naver?mode=LSD&mid=shm&sid1=103&oid=437&aid=0000277493";
			String html = rt.getForObject(url, String.class);
			Document doc = null;
			doc = Jsoup.parse(html);
			try {

				Element titleelem = doc.selectFirst("#articleTitle");
				Element timeelem = doc.selectFirst(".t11");
				String title = titleelem.text();
				String time = timeelem.text();
				Timestamp createdAt = Timestamp.valueOf(time);
			//System.out.println(title); // jsoup로 id: articleTitle를 파싱해야함
			//System.out.println(time);
				News news = News.builder()
						.title(title)
						.createdAt(createdAt)
						.build();

				newsList.add(news);

			} catch (Exception e) {
				System.out.println(e.getMessage());
			}

			aidNum++;

		}
		
		return newsList;
		
		
	}

}
