package common;

import java.io.File;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MyLogger {

	private File logger;
	
	public MyLogger(String logPath){
		this.logger= new File(logPath);
	}
	
	public void logToTxt(String string){
		FileWriter fw=null;
		SimpleDateFormat currentDate=new SimpleDateFormat("yyyy-MM-dd hh-mm-ss"+"\n");
		try{
		if(!logger.exists()){
			logger.createNewFile();
		}
		
		fw=new FileWriter(logger, true);
		fw.write(currentDate.format(new Date()).toString());
		fw.write(string);
		}catch(Exception exception){
			exception.printStackTrace();
		}finally{
			try{
				fw.close();
			}catch(Exception exception){
				exception.printStackTrace();
			}
			
		}
		
	}

	public void clearLog(){
		if(logger.exists()){
			logger.delete();
			try{
				logger.createNewFile();
			}catch(Exception exception){
				exception.printStackTrace();
			}
			
		}
	}
}
