package wuwei.client.data;

public class HotKeyData {
	//�ȼ��Զ����࣬Ŀǰֻ���ȼ��������Լ���Ӧ������������Ա
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
