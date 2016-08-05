/***
 * @author liuj78
 */
package common;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class Configuration {
	private String configFilePath;
	private String trainDataFold;
	private String linkDataFold;
	private String KnowledgeDataFold;
	
	/**
	 * Constructor without no parameter;
	 */
	public Configuration(){
		
	}
	
	/***
	 * 构造函数，初始化配置文件路径，以及各数据所在文件夹路径
	 * @param filePath
	 * @throws Exception
	 */
	public Configuration(String filePath) throws Exception{
		this.configFilePath=filePath;
		try{
			this.setDataFilePath();
		}
		catch(Exception e){
			System.out.println(e.getMessage());
			throw e;
		}
	}
	
	/***
	 * 获取训练数据文件路径
	 * @return
	 */
	public  ArrayList<String> getTrainDataFilesPath(){
		if(!trainDataFold.isEmpty()){
			return this.getFilesPathInFold(trainDataFold);
		}
		return null;
	}
	
	/***
	 * 获取训练链接数据文件路径
	 * @return
	 */
	public  ArrayList<String> getLinkDataFilesPath(){
		if(!trainDataFold.isEmpty()){
			return this.getFilesPathInFold(linkDataFold);
		}
		return null;
	}
	
	/***
	 * 获取知识库文件路径
	 * @return
	 */
	public  ArrayList<String> getKnowledgeDataFilesPath(){
		if(!trainDataFold.isEmpty()){
			return this.getFilesPathInFold(KnowledgeDataFold);
		}
		return null;
	}
	
	/***
	 * 从配置文件中获取数据文件夹路径
	 * @throws Exception
	 */
	private void setDataFilePath() throws Exception{
		SAXReader sax=new SAXReader();
		File configFile=new File(configFilePath);
		Document doc=sax.read(configFile);
		
		Element root=doc.getRootElement();
		Iterator<?> iterator=root.elementIterator("datasource");
		
		Element data=(Element)iterator.next();
		
		this.trainDataFold=data.elementText("trainingdata").trim();
		this.linkDataFold=data.elementText("linkdata").trim();
		this.KnowledgeDataFold=data.elementText("knowledgebase").trim();
		
	}
	
	/***
	 * 获取文件夹下所有文件路径
	 * @param foldPath
	 * @return
	 */
	private ArrayList<String> getFilesPathInFold(String foldPath){
		File file=new File(foldPath);
		File[] array=file.listFiles();
		ArrayList<String> filesPath=new ArrayList<String>();
		for(File file_it : array){
			if(file_it.isFile()){
				filesPath.add(foldPath+"//"+file_it.getName());
				System.out.println(file_it.getPath());
			}				
			else if(file_it.isDirectory()){
				getFilesPathInFold(file_it.getPath());
			}
		}
		
		return filesPath;		
	}	
	
}
