package com.mulcam.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
@ToString
public class StaticMap {
	private int width;
	private int height;
	private double lat;
	private double lng;
	private int level;
	private String maptype;
	private String format;
	private String scale;
	private String lang;
}
