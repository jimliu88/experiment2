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
	
	
	public String readFile(String filePath, String charset){
		String file_string="";
		BufferedReader bufferReader=null;
		try{
			FileInputStream fileInputStream=new FileInputStream(filePath);
			InputStreamReader inputStreamReader=new InputStreamReader(fileInputStream, charset);
			bufferReader=new BufferedReader(inputStreamReader);

			String readLineString="";
			
			while(readLineString!=null){
				readLineString=bufferReader.readLine();
				if(readLineString!=null && !readLineString.isEmpty()){
					
					file_string+=readLineString;
				}
				
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
	public boolean writeFile(String filePath, String content, boolean append){
		try{
		File file=new File(filePath);
		if(!file.exists()){
			file.createNewFile();
		}
		
		FileWriter fileWriter=new FileWriter(file.getAbsolutePath(), append);
		BufferedWriter bufferWriter=new BufferedWriter(fileWriter);
		bufferWriter.write(content);
		bufferWriter.close();
		return true;
		}catch(IOException e){
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean clearFile(String filePath){
		File file =new File(filePath);
		if(file.exists()){
			file.delete();
			try{
				file.createNewFile();
			}catch(Exception exception){
				exception.printStackTrace();
			}
			return true;
		}
		
		return false;
	}
	
	public boolean isContainsString(String source, String target){

		if(source!=null && !source.isEmpty()){
			target.toLowerCase();
			source.toLowerCase();	
			int index=source.lastIndexOf(target);
			if(index >0 && index< source.length())
			 	return true;
			 return false;
		}		
		return false;
	}
	
	public String getFileSuffix(File file){
		
		String suffix="";
		if(file!=null){
			String  fileName=file.getName();
			int dot=fileName.lastIndexOf('.');
			suffix=fileName.substring(dot+1);
			return suffix;
			
		}
		return suffix;
	}

	public void changeFileSuffix(File file,  String newSuffix){
		String curFileName=file.getName();
		if(curFileName!=null && !curFileName.isEmpty()){
			int dot=curFileName.lastIndexOf(".");
			String curSuffix=curFileName.substring(dot+1);
				if(!curSuffix.equals(newSuffix)){					
					curFileName=curFileName.substring(0, dot+1)+newSuffix;
				}											
		}
		
		String newFilePath=file.getParent()+"\\"+curFileName;
		file.renameTo(new File(newFilePath));
		
}
}
