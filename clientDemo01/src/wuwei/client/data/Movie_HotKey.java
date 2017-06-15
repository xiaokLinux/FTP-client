package wuwei.client.data;

import java.util.ArrayList;

public class Movie_HotKey extends BaseHotKey {

	@Override
	ArrayList<HotKeyData> generateData() {
		// TODO Auto-generated method stub
		ArrayList<HotKeyData>  hotKeyList=new   ArrayList<HotKeyData>();
		hotKeyList.add(new  HotKeyData("  ���", "key:VK_ENTER"));
		hotKeyList.add(new  HotKeyData("  ��ͣ����", "key:VK_space"));
		hotKeyList.add(new  HotKeyData("  ���", "key:VK_right"));
		hotKeyList.add(new  HotKeyData("  ����", "key:VK_left"));
		hotKeyList.add(new  HotKeyData("  ����+", "key:VK_UP"));
		hotKeyList.add(new  HotKeyData("  ����-", "key:vk_down"));
		hotKeyList.add(new  HotKeyData("  ����", "key:vk_m"));
		hotKeyList.add(new  HotKeyData("  �˳�", "key:vk_alt+vk_f4"));
		hotKeyList.add(new  HotKeyData("  �л�����", "key:vk_alt+vk_shift+vk_tab"));
		hotKeyList.add(new  HotKeyData("  ��ʾ����", "key:vk_windows+vk_D"));
		return hotKeyList;

	}

}
