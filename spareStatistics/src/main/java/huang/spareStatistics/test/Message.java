package huang.spareStatistics.test;

import huang.statistics.util.HttpRequest;

import java.util.HashMap;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.tonetime.commons.codec.MD5;

public class Message {
	public static String message(String number) {
		JSONObject a = new JSONObject();
		a.put("com", "shunfeng");
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
			String msg2 = msg1.getString(msg1.size() - 1);
			return msg2;

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
