package wuwei.client.data;

import java.util.ArrayList;

public abstract class BaseHotKey {
	//��Ϊ�����ȼ��ĸ��࣬�ṩһ�������ķ��������ȼ�����
	//���󷽷�generateData()    ��Ҫ�������ȥʵ��
	abstract  ArrayList<HotKeyData>  generateData();
	public BaseHotKey()  {
	// TODO Auto-generated   constructor stub
	}
	public  ArrayList<HotKeyData>   getHotkeyList(){
	return generateData();
	}

}
