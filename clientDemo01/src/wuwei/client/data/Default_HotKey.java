package wuwei.client.data;

import java.util.ArrayList;

public class Default_HotKey extends BaseHotKey {

	@Override
	ArrayList<HotKeyData> generateData() {
		// TODO Auto-generated method stub
		ArrayList<HotKeyData>  hotKeyList=new   ArrayList<HotKeyData>();
		hotKeyList.add(new  HotKeyData("  �л�����","key:vk_alt+vk_shift+vk_tab"   ));
		hotKeyList.add(new  HotKeyData("  �˳�","key:vk_alt+vk_f4"   ));
		hotKeyList.add(new  HotKeyData("  ���","key:vk_windows+vk_up"   ));
		hotKeyList.add(new  HotKeyData("  ��ԭ/��С��","key:vk_windows+vk_down"   ));
		return hotKeyList;

	}

}
