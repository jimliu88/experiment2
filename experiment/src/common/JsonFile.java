package common;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class JsonFile {

	public void readJsonFile(String JsonFilePath){
		FileIO fileIO=new FileIO();
		String jsonString=fileIO.readFile(JsonFilePath);
		JSONArray jsonArray=JSONArray.fromObject(jsonString);
		for(int index=0; index<jsonArray.size();index++){
			JSONObject jsonObject=jsonArray.getJSONObject(index);
			
			//分词处理
			jsonObject.get("movie_name");
			
			//TODO
		}		
		
	}
}
