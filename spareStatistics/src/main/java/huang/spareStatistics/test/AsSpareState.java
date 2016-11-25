package huang.spareStatistics.test;

import huang.spareStatistics.dao.BasicDao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AsSpareState {
	public static void main(String[] args) throws Throwable {
		// TODO Auto-generated method stub
		BasicDao dao=new BasicDao();
		List<Map<String, Object>> listImei=	dao.searchAllImei();
		for(int i=0;i<listImei.size();i++)
		{
		
			String imei=String.valueOf(listImei.get(i).get("c_spare_imei"));
			//安装状态；
			List<Map<String, Object>> list2=dao.searchInstallImei(imei);
			if(list2.size()>0)
			{
				Map<String,Object> map2=new HashMap<String,Object>();
				map2.put("c_install_message", list2.get(0).get("c_sc_message"));
				dao.basicUpdate("iov_spare_as_change", map2, "c_spare_imei="+imei);
				//安装信息c_sc_message
			}
			
		}
	}

}
