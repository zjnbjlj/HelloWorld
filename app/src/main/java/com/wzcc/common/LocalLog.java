package com.wzcc.common;


import com.wzcc.toollibary_jlj.IOHelper;

import java.io.PrintWriter;
import java.io.StringWriter;

import wzcc.com.helloworld.CommonString;

public class LocalLog {

    private static String LOG_FILE_PATH = CommonString.FoldName;
    private static final String LOG_FILE_NAME = "/error.txt";

    public LocalLog(String foldname){
    	LOG_FILE_PATH = foldname;
    }
    
    public LocalLog(){
    }
    
    public static final String nvl(String val){
        if(val == null){
            return "null";
        }
        else if(val.equalsIgnoreCase("")){
            return "blank";
        }
        else{
            return val;
        }
    }
    
    public static final void writeLog(Exception e) {

        try {
        	
            e.printStackTrace();
            StringWriter sw = new StringWriter();    
            PrintWriter pw = new PrintWriter(sw);    
            e.printStackTrace(pw);    

            IOHelper.createESDir(LOG_FILE_PATH);
            IOHelper.writeFile(LOG_FILE_PATH + LOG_FILE_NAME, sw.toString());
        } catch (Exception ex){
            // 屏蔽其他异常
        }

    }
    
    public static final void writeLog(String strLogText) {

        try {
        	IOHelper.createESDir(LOG_FILE_PATH);
        	IOHelper.writeFile(LOG_FILE_PATH + LOG_FILE_NAME, strLogText);
        }catch (Exception ex){
            // 屏蔽其他异常
        }
    }
    
    public static final void writeLogAppend(String strLogText) {
        try {
        	IOHelper.createESDir(LOG_FILE_PATH);        
        	if(!strLogText.equals("")){
        		IOHelper.writeStreamAppend("/"+LOG_FILE_PATH + LOG_FILE_NAME, strLogText);
        	}
        	
        }catch (Exception ex){
            // 屏蔽其他异常
        }
    }
}
