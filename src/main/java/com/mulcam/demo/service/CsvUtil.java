package com.mulcam.demo.service;

import java.util.List;

public interface CsvUtil {
	List<List<String>> readCsv(String filename);

	List<List<String>> readCsv(String filename, String separator);

	List<List<String>> readCsv(String filename, String separator, int skipLine);
	
	public void writeCSV(String filename, List<List<String>> dataList);
	
	public void writeCSV(String filename, List<List<String>> dataList, String separator);
}
