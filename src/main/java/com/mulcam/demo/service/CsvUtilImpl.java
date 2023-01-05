package com.mulcam.demo.service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class CsvUtilImpl implements CsvUtil {

	public List<List<String>> readCsv(String filename) {
		return readCsv(filename, ",", 0);
	}

	public List<List<String>> readCsv(String filename, String separator) {
		return readCsv(filename, separator, 0);
	}

	public List<List<String>> readCsv(String filename, String separator, int skipLine) {
		List<List<String>> csvList = new ArrayList<>();
		File csv = new File(filename);
		BufferedReader br = null;
		String line = null;
		int lineNo = 0;

		try {
			br = new BufferedReader(new FileReader(csv));
			while ((line = br.readLine()) != null) {
				if (skipLine > lineNo++)
					continue;

				List<String> aLine = new ArrayList<String>();
				String[] lineArr = line.split(separator);
				aLine = Arrays.asList(lineArr);
				csvList.add(aLine);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return csvList;
	}

	public void writeCSV(String filename, List<List<String>> dataList) {
		writeCSV(filename, dataList, ",");
	}

	public void writeCSV(String filename, List<List<String>> dataList, String separator) {
		File csv = new File(filename);
		BufferedWriter bw = null;

		try {
//			bw = new BufferedWriter(new FileWriter(csv, true));
			bw = new BufferedWriter(new FileWriter(csv));
			for (List<String> data : dataList) {
				StringBuilder sb = new StringBuilder();
				data.forEach(x -> sb.append(x).append(separator));
				sb.deleteCharAt(sb.length() - 1);
				bw.write(sb.toString() + "\n");
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (bw != null) {
					bw.flush();
					bw.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}
}
