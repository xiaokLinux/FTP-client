package wuwei.client.data;

import java.util.ArrayList;

public class Movie_HotKey extends BaseHotKey {

	@Override
	ArrayList<HotKeyData> generateData() {
		// TODO Auto-generated method stub
		ArrayList<HotKeyData>  hotKeyList=new   ArrayList<HotKeyData>();
		hotKeyList.add(new  HotKeyData("  最大化", "key:VK_ENTER"));
		hotKeyList.add(new  HotKeyData("  暂停播放", "key:VK_space"));
		hotKeyList.add(new  HotKeyData("  快进", "key:VK_right"));
		hotKeyList.add(new  HotKeyData("  快退", "key:VK_left"));
		hotKeyList.add(new  HotKeyData("  音量+", "key:VK_UP"));
		hotKeyList.add(new  HotKeyData("  音量-", "key:vk_down"));
		hotKeyList.add(new  HotKeyData("  静音", "key:vk_m"));
		hotKeyList.add(new  HotKeyData("  退出", "key:vk_alt+vk_f4"));
		hotKeyList.add(new  HotKeyData("  切换程序", "key:vk_alt+vk_shift+vk_tab"));
		hotKeyList.add(new  HotKeyData("  显示桌面", "key:vk_windows+vk_D"));
		return hotKeyList;

	}

}
