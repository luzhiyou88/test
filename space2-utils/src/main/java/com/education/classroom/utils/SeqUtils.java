package com.education.classroom.utils;

import java.text.DecimalFormat;

public class SeqUtils {

	static String concatStr(String s1,String s2){
		StringBuffer sb=new StringBuffer();
		sb.append(s1).append(s2);
		return sb.toString();
	} 
	
	public static boolean putReposite(String key){
		
		return true;
	}
    public static boolean isChar(String   validString){
    	
    	 byte[]   tempbyte=validString.getBytes(); 
         for(int   i=0;i <validString.length();i++)   {                
            if((tempbyte[i] <48)||((tempbyte[i]> 57)&(tempbyte[i] <65))||(tempbyte[i]> 122)||((tempbyte[i]> 90)&(tempbyte[i] <97)))   { 
                return   false; 
            } 
         } 
         return   true;     	
    }
    
	public static char nextCharVal(char c){		
		int i=c;		
		int nextI=i+1;
		char nextChar=(char)nextI;
		
		return nextChar;
	}
	public static String formatNum(long num,String format) {
	       
	        DecimalFormat myformat = new DecimalFormat();
	        myformat.applyPattern(format);
	        return myformat.format(num);
	 }
	 
	 public static String genNextSeq(char prefix,long num,String format){
		 String retVal="";
		 String maxStr=concatStr("1",format);
		 long ll=Long.parseLong(maxStr);
		 ll--;	
		 
		 //System.out.println("max-->>"+ll+" num-->>"+num);
		 if (num==ll){
			 prefix=nextCharVal(prefix);
			 num=1;
		 }else{			 
			 num++;
		 } 
		 
		 retVal=concatStr(String.valueOf(prefix),formatNum(num,format));
		 return retVal;
	 }
	
}
