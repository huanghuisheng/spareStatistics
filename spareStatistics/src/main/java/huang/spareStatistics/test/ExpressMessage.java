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
public class ExpressMessage {
	public String centextMessage(String expressId,String expressStore) throws HttpException, IOException {
		HttpClient client = new HttpClient();
		//882685725342809237
		String url="http://www.kuaidi100.com/query?type="+expressStore+"&postid="+expressId+"&id=1&valicode=&temp=0.3625311172377822";
		HttpMethod method = new GetMethod(url);
		client.executeMethod(method);
		// 打印返回的信息
		String text =method.getResponseBodyAsString();
	    System.out.println("--------"+text);
		// 释放连接
		method.releaseConnection();
		//获取快递信息
		JSONObject jsonObject = JSONObject.fromObject(text);
		String data =jsonObject.getString("data");
		JSONArray jsonObject1=jsonObject.getJSONArray("data");
		JSONObject jsonObject2=jsonObject1.getJSONObject(0);
		String content=jsonObject2.getString("context");
		return content;
	}
	//判断是那间快递；
	public String expressStore(String expressId) throws Throwable, IOException{
		HttpClient client = new HttpClient();
		String url="http://www.kuaidi100.com/autonumber/autoComNum?text="+expressId;
		HttpMethod method = new GetMethod(url);
		client.executeMethod(method);
		// 打印返回的信息
		String text =method.getResponseBodyAsString();
	//	{"comCode":"","num":"779051715056","auto":[{"comCode":"zhongtong","id":"","noCount":6491,"noPre":"779","startTime":""}]}
		System.out.println("-----------"+text);
		JSONObject jsonObject = JSONObject.fromObject(text);
		JSONArray jsonObject1 = jsonObject.getJSONArray("auto");
		JSONObject data =jsonObject1.getJSONObject(0);
		String data1=data.getString("comCode");
		return data1;
	}
	
	public static void main(String[] args) throws Throwable {
		ExpressMessage expressMessage = new ExpressMessage();
		expressMessage.updateExpressMassage();
	}
	//刷新快递信息；
	public void updateExpressMassage() throws Throwable, IOException{
		//获取所有的imei号；
		BasicDao dao = new BasicDao();
		// 获取快递单号；
		List<Map<String, Object>> list = dao.expressMessage();
		for(int i=0;i<list.size();i++)
		{
			String expId=String.valueOf(list.get(i).get("c_express_id"));
			String sendId=String.valueOf(list.get(i).get("n_id"));
			String content=Message.message(expId);
			System.out.println("-------"+content);
			//更新快递信息；
			Map<String,Object>  map=new HashMap<String,Object>();
			map.put("c_express_message", content);
			dao.basicUpdate("iov_spare_send", map, "n_id="+sendId);
			//获取交换机快递信息
			dao.basicUpdate("iov_spare_change", map, "c_express_id="+expId);
			
			
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
