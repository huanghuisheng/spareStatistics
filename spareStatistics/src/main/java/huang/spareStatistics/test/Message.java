package huang.spareStatistics.test;

import huang.statistics.util.HttpRequest;

import java.util.HashMap;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.tonetime.commons.codec.MD5;

public class Message {
	public static String message(String number) {

		String url = " http://www.kuaidi100.com/autonumber/auto?num="+number+"&key=QOiHKsjc5372";
		String resp1=null;
		try {
			resp1 = new HttpRequest().postData(url, "utf-8");
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		if(resp1==null)
		{
			return null;
		}
		JSONArray msg1111 = JSONArray.fromObject(resp1);
		String comCode = msg1111.getJSONObject(0).getString("comCode");
		
		JSONObject a = new JSONObject();
		a.put("com", comCode);
		a.put("num", number);
		String param = a.toString();
		String customer = "9352C0E02F4093F78ED63A6FB2BEDB62";
		String key = "QOiHKsjc5372";
		String sign = MD5.encode(param + key + customer);
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("param", param);
		params.put("sign", sign);
		params.put("customer", customer);
		String resp;
		try {
			resp = new HttpRequest().postData("http://poll.kuaidi100.com/poll/query.do", params, "utf-8").toString();
			JSONObject msg = JSONObject.fromObject(resp);
			JSONArray msg1 = msg.getJSONArray("data");
			JSONObject msg2 = msg1.getJSONObject(msg1.size() - 1);
			String time = msg2.getString("time");
			String content = msg2.getString("context");
			return time + " " + content;

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
