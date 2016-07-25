package common;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

public class FileIO {

	/***
	 * 读文件
	 * @param filePath
	 * @return
	 */
	public String readFile(String filePath){
		String file_string="";
		BufferedReader bufferReader=null;
		try{
			FileInputStream fileInputStream=new FileInputStream(filePath);
			InputStreamReader inputStreamReader=new InputStreamReader(fileInputStream,"UTF-8");
			bufferReader=new BufferedReader(inputStreamReader);
			String  readLineString=bufferReader.readLine();
			while(readLineString!=null){
				file_string+=readLineString;
			}
			bufferReader.close();
			
		}catch(IOException e){
			e.printStackTrace();
		}finally{ //防止发生异常，导致的bufferReader没有关闭
			try{
				if(bufferReader!=null)
					bufferReader.close(); 
			}catch(IOException e){
				e.printStackTrace();
			}
		}		
		return file_string;
	}
	
	/***
	 * 写文件
	 * @param filePath
	 * @param content
	 * @return
	 */
	public boolean writeFile(String filePath, String content){
		try{
		File file=new File(filePath);
		if(!file.exists()){
			file.createNewFile();
		}
		
		FileWriter fileWriter=new FileWriter(file.getAbsolutePath());
		BufferedWriter bufferWriter=new BufferedWriter(fileWriter);
		bufferWriter.write(content);
		bufferWriter.close();
		return true;
		}catch(IOException e){
			e.printStackTrace();
		}
		return false;
	}
}
