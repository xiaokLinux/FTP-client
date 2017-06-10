package wuwei.client.operator;

import java.util.ArrayList;

import wuwei.client.data.NetFileData;
import wuwei.client.socket.CmdClientSocket;
import wuwei.client.view.NetFileListAdapter;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.ListView;

public class ShowRemoteFileHandler extends Handler {

	/**
	 * 通过传入的主UI的上下文（context）和listView对像
	 * 当主UI通过dir命令发送到server端时
	 * server处理玩回写信息，而该句柄作为c的socket的句柄参数
	 * 进而通过客户端的句柄消息触发handlemessage函数，解析消息
	 */
	private Context context;
	private ListView listView;
	public ShowRemoteFileHandler(Context context, ListView listView) {
		super();
		this.context = context;
		this.listView = listView;
	}
	@Override
	public void handleMessage(Message msg) {
		// TODO Auto-generated method stub
		ArrayList<NetFileData> netFileList=new ArrayList<NetFileData>();
		Bundle bundle=msg.getData();
		ArrayList<String> ack=bundle.getStringArrayList(CmdClientSocket.KEY_SERVER_ACK_MSG);
		//0位置为当前绝对路径
		String filePath=ack.get(0);
		for(int i=0;i<ack.size();i++){
			String fileInfo=ack.get(i);
			NetFileData data=new NetFileData(fileInfo, filePath);
			netFileList.add(data);
		}
		NetFileListAdapter adapter=new NetFileListAdapter(context, netFileList);
		listView.setAdapter(adapter);
		super.handleMessage(msg);
	}
	
	
}
