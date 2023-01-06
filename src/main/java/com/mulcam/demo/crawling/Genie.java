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
public class Genie {
	private int rank;
	private String title;
	private String artist;
	private String album;
	private String img;
}
