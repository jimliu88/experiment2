package common;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;

public class findStringInFile {
	
	private static String fileFoldPath="C:\\buildservices\\marvin\\application";
	private static String findStr="";
	private static String logPath="C:\\log.txt";
	private static ArrayList<File> targetFilesList=new ArrayList<File>();
	private HashMap<String, String> suffixlist = new HashMap<String, String>();
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
			findStringInFile finder=new findStringInFile();
			finder.runMe();
			
			
			
	}
	
	private void runMe(){
		
		ArrayList<File> filesList ;		
		filesList=getFilesPathInFold(fileFoldPath);
		
		int count=0;
		for(File file : filesList){
			count++;
			getAllSuffixInFiles(file);
			println("file "+count+": "+file);						
		    
		}
		
		/*for(File file: targetFilesList){
			
			String fileName=file.getName();
			println(fileName);
			String newFilePath=recoverSuffix(file.getAbsolutePath(),".java.txt",".txt"); 
			file.renameTo(new File(newFilePath));	
			println(newFilePath);
		}*/
	}
	
	
	/***
	 * 获取文件夹下所有文件路径
	 * @param foldPath
	 * @return
	 */
	private  ArrayList<File> getFilesPathInFold(String foldPath){
		File file=new File(foldPath);
		File[] array=file.listFiles();
		ArrayList<File> allFiles=new ArrayList<File>();
		StringBuffer logger=new StringBuffer("\n Start reading file from all folds \n");
		for(File file_it:array){
			if(file_it.isFile()){
				allFiles.add(file_it);
			}
			else if(file_it.isDirectory()){
				getFilesPathInFold(file_it.getPath());
			}
			logger.append(file_it.getAbsolutePath()+"\n");
			
		}
		
		logger.append("\n Read file from all folds has been completed! \n");
		logToTxt(logger.toString(),logPath);
		
		return allFiles;
}		
	
	private void getAllSuffixInFiles(File file){
		StringBuffer logger=new StringBuffer("\n Start collect file suffix from all folds \n");
		if(file!=null){
			String  fileName=file.getName();
			int dot=fileName.lastIndexOf('.');
			String suffix=fileName.substring(dot+1);
			if(!suffixlist.containsKey(suffix)){
				suffixlist.put(suffix, suffix);
				logger.append(suffix+" \t");
			}
		}
		logger.append("\n Complete collect file suffix from all folds \n");
		logToTxt(logger.toString(), logPath);
	}
		
		
		
/*		for(File file_it : array){
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
		
		return filesPath;*/		
	//}	

	private static void println(String string){
		System.out.println(string);
	}
	
	private void println(String string, boolean start){
		StringBuffer output=new StringBuffer();
		output.append(string+"  ");
		if(start){
			println(output.toString());
		}
	}

	/***
	 * 读文件
	 * @param filePath
	 * @return
	 */
	private String readFile(String filePath){
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

	private String recoverSuffix(String curFileName, String curSuffix, String newSuffix){
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

	private void getAllFilelist(File file){
		targetFilesList.add(file);
	}
	
	private void logToTxt(String string, String logPath){
		File logger=null;
		FileWriter fw=null;
		try{
		logger =new File(logPath);
		if(!logger.exists()){
			logger.createNewFile();
		}
		
		fw=new FileWriter(logger, true);
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
}