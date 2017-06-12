package wuwei.client.data;

public class HotKeyData {
	//热键自定义类，目前只有热键的名称以及对应的命令两个成员
	private String  hotkeyName="";
	private String  hotkeyCmd="";
	public HotKeyData(String   hotkeyName,  String hotkeyCmd)  {
	super();
	this.hotkeyName  = hotkeyName;
	this.hotkeyCmd  = hotkeyCmd;
	}
	public String  getHotkeyName()  {
	return hotkeyName;
	}
	public void  setHotkeyName(String  hotkeyName){
		this.hotkeyName  = hotkeyName;
	}
	public String  getHotkeyCmd()  {
		return hotkeyCmd;
	}
	public void  setHotkeyCmd(String  hotkeyCmd)  {
		this.hotkeyCmd  = hotkeyCmd;
	}

}
