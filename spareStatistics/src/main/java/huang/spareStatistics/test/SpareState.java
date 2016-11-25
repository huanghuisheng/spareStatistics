package huang.spareStatistics.test;

import huang.spareStatistics.dao.BasicDao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SpareState {
	public static void main(String[] args) throws Throwable {
		// TODO Auto-generated method stub
		BasicDao dao=new BasicDao();
		List<Map<String, Object>> listImei=	dao.searchAllImei();
		for(int i=0;i<listImei.size();i++)
		{
			//是否已激活；
			String imei=String.valueOf(listImei.get(i).get("c_spare_imei"));
			List<Map<String, Object>> list1=dao.userGetActiveMobileImei(imei);
			if(list1.size()>0)
			{
				Map<String,Object> map1=new HashMap<String,Object>();
				map1.put("c_active_state", "1");
				dao.basicUpdate("iov_spare_change", map1, "c_spare_imei="+imei);
			}
			//是否安装；
			List<Map<String, Object>> list2=dao.searchInstallImei(imei);
			if(list2.size()>0)
			{
				Map<String,Object> map2=new HashMap<String,Object>();
				map2.put("c_install_state", "1");
				dao.basicUpdate("iov_spare_change", map2, "c_spare_imei="+imei);
			}
			//是否已经绑定；
			List<Map<String, Object>> list3=dao.searchBindingImei(imei);
			if(list3.size()>0)
			{
				Map<String,Object> map3=new HashMap<String,Object>();
				map3.put("c_binding_state", "1");
				dao.basicUpdate("iov_spare_change", map3, "c_spare_imei="+imei);
			}
		}
	}

}
