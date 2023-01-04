package com.mulcam.demo.service;

import java.util.List;

public class CsvTest {

	public static void main(String[] args) {
		CsvUtil cu = new CsvUtil();
		List<List<String>> list = cu.readCsv("c:/Temp/sample.csv", ",", 1);
		for (List<String> row : list) {
			row.forEach(x -> System.out.print(x + " "));
			System.out.println();
		}
		cu.writeCSV("c:/Temp/sample2.csv", list, ",");
	}

}
