package common;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class findStringInFile {
	
	private static String fileFoldPath="C:\\buildservices\\marvin\\application\\marvin_webapp\\src\\main\\java\\com\\vmware\\marvin\\New folder";
	private static String findStr="evo";
	private static String logPath="C:\\log.txt";
	private static String searchResult="C:\\searchResult.txt";
	private HashMap<String, String> fileNamelist = new HashMap<String, String>();
	private ArrayList<File> allFiles=new ArrayList<File>();
	private HashSet<String> fileSuffixSet=new HashSet<String>();
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
			findStringInFile finder=new findStringInFile();
			finder.runMe();		
	}
	
	public void runMe(){
		
		ArrayList<File> filesList ;	
		MyLogger myLogger=new MyLogger(logPath);
		int count=0;
		
		myLogger.clearLog();
		
		filesList=getFilesPathInFold(fileFoldPath);
		StringBuffer logger=new StringBuffer("Start collect file info in folds \n");
		StringBuffer suffixBuffer=new StringBuffer();
		FileIO fileInput=new FileIO();
		for(File file : filesList){
			String fileName=file.getName();
			count++;			
			logger.append("file "+count+": "+file.getAbsolutePath()+"\n");
			
			String suffix=fileInput.getFileSuffix(file);			
			
			if(!fileSuffixSet.contains(suffix)){	
				fileSuffixSet.add(suffix);
				suffixBuffer.append(suffix+"\t");
			}
			
			fileNamelist.put(fileName, suffix);
			
			println("file "+count+": "+file);	
			
			fileInput.changeFileSuffix(file,"txt"); 
			
			String fileContent=fileInput.readFile(file.getAbsolutePath(),"UTF-8");
			if(fileInput.isContainsString(fileContent, findStr)){
				
				fileInput.clearFile(searchResult);
				fileInput.writeFile(searchResult, fileName+"\n", true);
			}
			
			fileInput.changeFileSuffix(file, suffix); 			
			
		}
		
		logger.append(suffixBuffer.toString()+"\n");
		logger.append("Complete collect file info in all folds \n");
		myLogger.logToTxt(logger.toString());
	}
	
	
	/***
	 * 获取文件夹下所有文件路径
	 * @param foldPath
	 * @return
	 */
	private  ArrayList<File> getFilesPathInFold(String foldPath){
		File file=new File(foldPath);
		File[] array=file.listFiles();
		
		for(File file_it:array){
			if(file_it.isFile()){
				allFiles.add(file_it);
			}
			else if(file_it.isDirectory()){
				//println(file_it.getAbsolutePath());
				getFilesPathInFold(file_it.getAbsolutePath());
			}
			
		}
				
		return allFiles;
}		
	
	

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

}