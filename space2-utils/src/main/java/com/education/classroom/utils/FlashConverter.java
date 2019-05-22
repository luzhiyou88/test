package com.education.classroom.utils;

import java.io.File;


public class FlashConverter extends Thread{

//	static{
//		if(Utils.testStringEmpty(CommonConfig.SWF_FILE_PATH))
//			SWF_FILE_PATH=System.getenv("TEMP");
//		else
//			SWF_FILE_PATH=CommonConfig.SWF_FILE_PATH;
//		
//		if(SWF_FILE_PATH==null)
//			SWF_FILE_PATH="c:\\";
//		
//		if(!SWF_FILE_PATH.endsWith("\\"))
//			SWF_FILE_PATH=SWF_FILE_PATH+"\\";
//	}
	
	public static File converter(File inputFile) throws Exception{
		
		int flag=2048+1024+512+256+128+64+16+8;
		
		String swfFileName="";
		
		if(inputFile.getName().lastIndexOf(".")>=0)
			swfFileName=inputFile.getName().substring(0,inputFile.getName().lastIndexOf("."))+".swf";
		else
			swfFileName=inputFile.getName()+".swf";
		
		swfFileName=inputFile.getAbsolutePath().replace(inputFile.getName(),swfFileName);		
		File swfFile=new File(swfFileName);
		
		if(swfFile.exists()){
			return swfFile;
		}
		
		//cmd /c start javac

		String converter = "cmd /c start p2fserver.exe " + inputFile.getAbsolutePath()+ " "+swfFileName+" /InterfaceOptions:"+flag;
		
		System.out.println("Running..."+converter);
		Runtime pro = Runtime.getRuntime();
		pro.exec(converter);

		long waitTimeTotal=0;
		
		while(true){
			if(!swfFile.exists()){
				Thread.sleep(1000);
				waitTimeTotal+=1000;
				if(waitTimeTotal>20*60*1000)
					break;
				else
					continue;
			}
			break;
		}
		
		return swfFile;
	}
	
	public static void main(String[] args) {
		
		try {
			FlashConverter.converter(new File("C:\\bbb.doc"));

		} catch (Exception e) {
			e.printStackTrace();
		}
		//PDFManipulator.addWaterMarkToPDF("E:\\IBM_Test.pdf", "E:\\IBM_Test_Mark.pdf", "我们来测试水印！！");
		//PDFManipulator.signPDF("C:\\IBM_Test.pdf", "C:\\IBM_Test_Sign.pdf", "19986054");
	}	
}