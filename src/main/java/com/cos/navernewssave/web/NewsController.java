package com.cos.navernewssave.web;

import java.util.List; 

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cos.navernewssave.domain.News;
import com.cos.navernewssave.domain.NewsRepository;
import com.cos.navernewssave.web.dto.CMRespDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class NewsController {

	
	private final NewsRepository newsRepository;
	
<<<<<<< HEAD
	@CrossOrigin
	@GetMapping( value = "/news" , produces = MediaType.TEXT_EVENT_STREAM_VALUE)
	public Flux<News> findAll(){
      return newsRepository.mFindAll().subscribeOn(Schedulers.boundedElastic());
	}
	
	
	
=======
	@GetMapping("/news")
	public CMRespDto<List<News>> findAll(){
	      return new CMRespDto<>(1, "성공", newsRepository.findAll());
	   } 
>>>>>>> 061364adc96a18dfdb09b3d9519c01a76e2f2e4d
	
}