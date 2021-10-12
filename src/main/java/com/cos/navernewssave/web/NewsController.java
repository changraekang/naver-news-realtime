package com.cos.navernewssave.web;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cos.navernewssave.domain.News;
import com.cos.navernewssave.domain.NewsRepository;
import com.cos.navernewssave.web.dto.CMRespDto;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

@RequiredArgsConstructor
@RestController
public class NewsController {

	
	private final NewsRepository newsRepository;
	
	@CrossOrigin
	@GetMapping( value = "/news" , produces = MediaType.TEXT_EVENT_STREAM_VALUE)
	public Flux<News> findAll(){
      return newsRepository.mFindAll().subscribeOn(Schedulers.boundedElastic());
	}
	
	
	
	
}