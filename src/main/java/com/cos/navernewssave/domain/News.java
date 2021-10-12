package com.cos.navernewssave.domain;

import java.sql.Timestamp;
import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@AllArgsConstructor
@Data
@Document( collection = "naver_realtime")
public class News {

	@Id
	private String _id;
	
	private String company;
	private String title;
<<<<<<< HEAD
	private Date createdAt;
=======
	private String createdAt;
>>>>>>> 061364adc96a18dfdb09b3d9519c01a76e2f2e4d
	
}
