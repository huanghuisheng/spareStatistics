package huang.spareStatistics.test;

import huang.spareStatistics.dao.BasicDao;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.methods.GetMethod;
//http://www.kuaidi100.com/query?type=yuantong&postid=882685725342809237&id=1&valicode=&temp=0.3625311172377822
public class AsExpressMessage {

	
	public static void main(String[] args) throws Throwable {
		AsExpressMessage expressMessage = new AsExpressMessage();
		expressMessage.updateExpressMassage();
	}
	//刷新快递信息；
	public void updateExpressMassage() throws Throwable, IOException{
		//获取所有的imei号；
		BasicDao dao = new BasicDao();
		// 获取快递单号；
		List<Map<String, Object>> list = dao.expressMessage1();
		for(int i=0;i<list.size();i++)
		{
			String expId=String.valueOf(list.get(i).get("c_express_id"));
			String sendId=String.valueOf(list.get(i).get("n_id"));
		
			//String content = centextMessage(expId,contentStore);
			String content=Message.message(expId);
			System.out.println("-------"+content);
			//更新快递信息；
			Map<String,Object>  map=new HashMap<String,Object>();
			map.put("c_express_message", content);
			dao.basicUpdate("iov_spare_as_send", map, "n_id="+sendId);
			
			//获取交换机快递信息
			dao.basicUpdate("iov_spare_as_change", map, "c_express_id="+expId);
			
			
			
			
		}
//		//获取交换机快递单号
//		List<Map<String, Object>> list1 = dao.expressMessage2();
//		for(int j=0;j<list1.size();j++)
//		{
//			String expId=String.valueOf(list.get(j).get("c_express_id"));
//			String sendId=String.valueOf(list.get(j).get("n_id"));
//			String content=Message.message(expId);
//			Map<String,Object>  map1=new HashMap<String,Object>();
//			map1.put("c_express_message", content);
//			dao.basicUpdate("iov_spare_as_change", map1, "n_id="+sendId);	
//		}
		
		
	    
		
		
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
