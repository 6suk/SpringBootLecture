package com.mulcam.demo.crawling;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Interpark {
	private int rank;
	private String title;
	private String author;
	private String company;
	private int price;
	private String img;
}
