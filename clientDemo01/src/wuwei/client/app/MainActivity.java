package wuwei.client.app;

import java.io.File;

import com.example.clientdemo01.R;

import wuwei.client.data.NetFileData;
import wuwei.client.operator.HotKeyGenerator;
import wuwei.client.operator.ShowNonUiUpdateCmdHandler;
import wuwei.client.operator.ShowRemoteFileHandler;
import wuwei.client.socket.CmdClientSocket;
import wuwei.client.view.HotKeyDialog;
import android.R.integer;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends Activity {

	
	TextView  tv2;
	EditText  et1, et2;
	ListView  listView;
	ShowRemoteFileHandler  showRemoteFileHandler;
	ShowNonUiUpdateCmdHandler   showNonUiUpdateCmdHandler;
	String ip;
	Integer port;
	EditText test;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        Button bt  = (Button) findViewById(R.id.button1);//y
        tv2 = (TextView)  findViewById(R.id.textView2);
        et1 = (EditText)  findViewById(R.id.editText1);//y
        et2 = (EditText)  findViewById(R.id.editText2);//y
        test=(EditText) findViewById(R.id.editText3);
        
        Button bt1=(Button) findViewById(R.id.button2);
        Button bt2=(Button) findViewById(R.id.button3);
        Button bt3=(Button) findViewById(R.id.button4);
        Button bt4=(Button) findViewById(R.id.button5);
        Button bt5=(Button) findViewById(R.id.button6);
        Button bt6=(Button) findViewById(R.id.button7);
		bt5.setOnClickListener(new Button.OnClickListener() {
					
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						String cmd="clk:left";
						CmdClientSocket  cmdClientSocket  = new  CmdClientSocket(ip,  port,showRemoteFileHandler);
			        	cmdClientSocket.work(cmd);
					}
				});
		bt6.setOnClickListener(new Button.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String cmd="clk:right";
				CmdClientSocket  cmdClientSocket  = new  CmdClientSocket(ip,  port,showRemoteFileHandler);
		    	cmdClientSocket.work(cmd);
			}
		});
        bt1.setOnClickListener(new Button.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String cmd="mov:0,-7";
				CmdClientSocket  cmdClientSocket  = new  CmdClientSocket(ip,  port,showRemoteFileHandler);
	        	cmdClientSocket.work(cmd);
			}
		});
		bt2.setOnClickListener(new Button.OnClickListener() {
					
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						String cmd="mov:0,7";
						CmdClientSocket  cmdClientSocket  = new  CmdClientSocket(ip,  port,showRemoteFileHandler);
			        	cmdClientSocket.work(cmd);
					}
				});
		bt3.setOnClickListener(new Button.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String cmd="mov:-7,0";
				CmdClientSocket  cmdClientSocket  = new  CmdClientSocket(ip,  port,showRemoteFileHandler);
	        	cmdClientSocket.work(cmd);
			}
		});
		bt4.setOnClickListener(new Button.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String cmd="mov:7,0";
				CmdClientSocket  cmdClientSocket  = new  CmdClientSocket(ip,  port,showRemoteFileHandler);
	        	cmdClientSocket.work(cmd);
			}
		});
        
        
        listView  = (ListView) findViewById(R.id.listView1);//y
        showRemoteFileHandler  =  new ShowRemoteFileHandler(MainActivity.this,    listView);
        showNonUiUpdateCmdHandler=new   ShowNonUiUpdateCmdHandler(MainActivity.this);
        ip = et1.getText().toString();
        String portStr  = et2.getText().toString();
        port = Integer.parseInt(portStr);
        bt.setOnClickListener(new   Button.OnClickListener()   {
 
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				ip = et1.getText().toString();
	        	String portStr  = et2.getText().toString();
	        	port=Integer.parseInt(portStr);
	        	CmdClientSocket  cmdClientSocket  = new  CmdClientSocket(ip,  port,showRemoteFileHandler);
	        	//cmdClientSocket.work("dir:...");//    列出盘符	       
	        	//debug
	        	String cmd=test.getText().toString();
	        	cmdClientSocket.work(cmd);
			}
        });
        registerForContextMenu(listView);
        listView.setOnItemClickListener(new    ListView.OnItemClickListener()   {       	            

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				Toast.makeText(MainActivity.this, "click", Toast.LENGTH_SHORT).show();
				NetFileData  fileData =  (NetFileData)  arg0.getItemAtPosition(arg2);
	        	String pwd=fileData.getFilePath();
	        	Log.e("ww1", pwd);
	        	String filePath="";
	        	if(pwd.endsWith("/")|pwd.endsWith("\\")){
	        		filePath=pwd+fileData.getFileName();
	        	}else{
	        		filePath=pwd+"\\"+fileData.getFileName();
	        	}
	        	//如果是目录
	        	if(fileData.getFileType()>=1){
	            	if(fileData.getFileName().equals("...")){
	            		filePath="...";
	            	}
	            	CmdClientSocket  cmdClientSocket  = new  CmdClientSocket(ip,  port,showRemoteFileHandler);
	            	cmdClientSocket.work("dir:"+filePath);
	            	Toast.makeText(MainActivity.this, "dir:"+filePath, Toast.LENGTH_SHORT).show();
	            }else{
	            	CmdClientSocket  cmdClientSocket  = new  CmdClientSocket(ip,  port,showNonUiUpdateCmdHandler);
	            	cmdClientSocket.work("open:"+filePath);
	            	Toast.makeText(MainActivity.this, "open:"+filePath, Toast.LENGTH_SHORT).show();
	            }
			}
        });

        
    }


    @Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		// TODO Auto-generated method stub
    	getMenuInflater().inflate(R.menu.file_list_context_menu, menu);//R.menu.file_list_context_menu为上下文菜单
        super.onCreateContextMenu(menu, v, menuInfo);
	}


	@Override
	public boolean onContextItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		AdapterContextMenuInfo contextMenuInfo = (AdapterContextMenuInfo) item.getMenuInfo();
	    int pos=contextMenuInfo.position;
	    NetFileData netFileData=(NetFileData) listView.getAdapter().getItem(pos);//其中listView为显示文件列表的视图
	    switch(item.getItemId()){
	    case R.id.item1:// 弹出热键对话框
	        showHotKeyDialog(netFileData);//能根据netFileData类型决定弹出相应的热键对话框
	        break;
	        default :break;
	    }
		return super.onContextItemSelected(item);
	}


	private void showHotKeyDialog(NetFileData netFileData) {
		// TODO Auto-generated method stub
		Toast.makeText(MainActivity.this, "showFIleDialog", Toast.LENGTH_SHORT).show();
		CmdClientSocket cmdClientSocket = new CmdClientSocket(ip, port,showNonUiUpdateCmdHandler);
	    //showNonUiUpdateCmdHandler句柄，处理socket的接收信息，若远程服务端正确执行命令，不做任何UI更新；若远程服务端出错，Toast显示出错信息
	    new HotKeyDialog(MainActivity.this, HotKeyGenerator.getHotkeyList(netFileData), "热键操作表", cmdClientSocket).show();
	    //HotKeyDialog的构造函数为：public HotKeyDialog(Context context,ArrayList<HotKeyData> hotKeyList,String title,CmdClientSocket cmdClientSocket)
	    //其中Context context为上下文
	    //ArrayList<HotKeyData> hotKeyList,传入的热键列表，热键对象HotKeyData为自定义类，具有热键的名称以及热键对应的操作
	    //例如HotKeyData("退出程序", "key:vk_alt+vk_f4")则构造了一个名称为"退出程序"，通过命令"key:vk_alt+vk_f4"实现alt+f4的热键操作
	    //HotKeyGenerator.getHotkeyList(netFileData)为静态方法
	    //public static ArrayList<HotKeyData> getHotkeyList(NetFileData fileData),根据fileData类型决定产生什么样的热键数据
	}


	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
}
