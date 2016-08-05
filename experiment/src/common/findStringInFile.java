package common;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class findStringInFile {
	
	private static String fileFoldPath="C:\\buildservices\\marvin\\application\\marvin_webapp\\src\\main\\java\\com\\vmware\\marvin\\configuration";
	private static String findStr="";
	private static ArrayList<File> targetFilesList=new ArrayList<File>();
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
			ArrayList<String> filesList ;
			
			filesList=getFilesPathInFold(fileFoldPath);
			int count=0;
			for(String file : filesList){
				count++;
				println("file "+count+": "+file);
				//println(readFile(file));				
				
				//find 				
			    
			}
			
			for(File file: targetFilesList){
				
				String fileName=file.getName();
				println(fileName);
				String newFilePath=recoverSuffix(file.getAbsolutePath(),".java.txt",".txt"); 
				file.renameTo(new File(newFilePath));	
				println(newFilePath);
			}
			
			
			
			
	}
	
	/***
	 * 获取文件夹下所有文件路径
	 * @param foldPath
	 * @return
	 */
	private static ArrayList<String> getFilesPathInFold(String foldPath){
		File file=new File(foldPath);
		File[] array=file.listFiles();
		ArrayList<String> filesPath=new ArrayList<String>();
		String suffix_java="java";
		String suffix_txt="txt";
		for(File file_it : array){
			if(file_it.isFile()){
				String fileName=file_it.getName();
					if(fileName.endsWith(suffix_java))
					{
						println(suffix_java);
						file_it.renameTo(new File(file_it+".txt"));
						filesPath.add(file_it.getAbsolutePath());
						getAllFilelist(file_it);

					} 
					if(fileName.endsWith(suffix_txt)){
						filesPath.add(file_it.getAbsolutePath());
						getAllFilelist(file_it);
					}

			}				
			else if(file_it.isDirectory()){
				getFilesPathInFold(file_it.getPath());
			}
		}
		
		return filesPath;		
	}	

	private static void println(String string){
		System.out.println(string);
	}
	

	/***
	 * 读文件
	 * @param filePath
	 * @return
	 */
	private static String readFile(String filePath){
		String file_string="";
		BufferedReader bufferReader=null;
		try{
			FileInputStream fileInputStream=new FileInputStream(filePath);
			InputStreamReader inputStreamReader=new InputStreamReader(fileInputStream,"UTF-8");
			bufferReader=new BufferedReader(inputStreamReader);

			String readLineString="START";
			
			while(readLineString!=null){
				readLineString=bufferReader.readLine();
				if(readLineString!=null && !readLineString.isEmpty()){
					
					println(readLineString);
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

	private static String recoverSuffix(String curFileName, String curSuffix, String newSuffix){
		String newFileName="";
		if(curFileName!=null && !curFileName.isEmpty()){
			if(curFileName.contains(curSuffix)){
				int dot=curFileName.lastIndexOf(".");
				newFileName=curFileName.substring(0, dot)+newSuffix;
			}
			else{
				newFileName=curFileName;
			}
			
			return newFileName;
		}
	
		return newFileName;
}

	private static void getAllFilelist(File file){
		targetFilesList.add(file);
	}
}