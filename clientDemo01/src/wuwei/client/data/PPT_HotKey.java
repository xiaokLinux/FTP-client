package wuwei.client.data;

import java.util.ArrayList;

public class PPT_HotKey extends BaseHotKey {

	@Override
	ArrayList<HotKeyData> generateData() {
		// TODO Auto-generated method stub
		ArrayList<HotKeyData>  hotKeyList=new   ArrayList<HotKeyData>();
		hotKeyList.add(new  HotKeyData("  �л�����", "key:vk_alt+vk_shift+vk_tab"));
		hotKeyList.add(new  HotKeyData("ESC",   "key:VK_ESCAPE"));
		hotKeyList.add(new  HotKeyData("  ��һҳ", "key:VK_PAGE_DOWN"));
		hotKeyList.add(new  HotKeyData("  ��һҳ", "key:VK_PAGE_UP"));
		hotKeyList.add(new  HotKeyData("  ��ͷ��ӳ", "key:VK_F5"));
		hotKeyList.add(new  HotKeyData("  ��ǰҳ��ӳ", "key:vk_shift+vk_f5"));
		hotKeyList.add(new  HotKeyData("  �˳�����", "key:vk_alt+vk_f4"));
		hotKeyList.add(new  HotKeyData("  ����/����", "key:vk_b"));
		return hotKeyList;

	}

}
