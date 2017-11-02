package com.srd.ljzd.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.util.ResourceUtils;

public class ReadTxtUtils {
	
	protected static Logger logger = LogManager.getLogger(JerseyUtil.class.getName());
	
	public static String[] splitt(String stocks){
        String strr = stocks;
        String[] stocksData = strr.split("\t");
        return stocksData;
	}
	
	public Map<String,String[]> readTxt(){
		String[] stocksData = null;
		Map<String,String[]> keyDataSources = null;
		
		String encoding="UTF-8";
		String filePath="classpath:resources"+File.separator+"data"+File.separator+"stocks.txt";
		
		File file = null;
		InputStreamReader read = null;
		String lineTxt = null;
		
        try {
			file=ResourceUtils.getFile(filePath);
		    read = new InputStreamReader(new FileInputStream(file),encoding);//考虑到编码格式
		    
		    BufferedReader bufferedReader = new BufferedReader(read);
		    
		    keyDataSources=new HashMap<String,String[]>();
		    
		    while((lineTxt = bufferedReader.readLine()) != null){
		    	stocksData=splitt(lineTxt);
		    	if(null!=stocksData[stocksData.length-1]&&!"".equals(stocksData[stocksData.length-1])){
		    		keyDataSources.put(stocksData[stocksData.length-1],stocksData);
		    	}
		    	else if(null!=stocksData[stocksData.length-2]&&!"".equals(stocksData[stocksData.length-2])){
		    		keyDataSources.put(stocksData[stocksData.length-2],stocksData);
		    	}
		    }
		    
		    read.close();
		} catch (FileNotFoundException e) {
			logger.error("舆情关键字文件，找不到指定的文件，" + e.getMessage(), e);
		} catch (UnsupportedEncodingException e) {
			logger.error("舆情关键字文件，文件编码不支持，" + e.getMessage(), e);
		} catch (IOException e) {
			logger.error("舆情关键字文件，IO读写错误，" + e.getMessage(), e);
		} catch (Exception e) {
			logger.error("舆情关键字文件，读取文件失败，" + e.getMessage(), e);
		}
	    
		return keyDataSources;
	}
}
