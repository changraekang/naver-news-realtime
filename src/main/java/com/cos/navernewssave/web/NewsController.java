package com.cos.navernewssave.web;

import java.util.List; 

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
	
	@GetMapping("/news")
	public CMRespDto<List<News>> findAll(){
	      return new CMRespDto<>(1, "성공", newsRepository.findAll());
	   } 
	
}