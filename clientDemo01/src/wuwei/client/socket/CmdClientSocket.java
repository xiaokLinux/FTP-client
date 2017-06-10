package wuwei.client.socket;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.ArrayList;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

public class CmdClientSocket {

	int port;
	String ip;
	int MsgType;
	int connect_timeout=2000;
	boolean isDebug=true;					//	处于debug模式
	Handler handler;
	Socket socket;
	private BufferedReader bufferedReader;
	private OutputStreamWriter writer;
	public static final String KEY_SERVER_ACK_MSG="KEY_SERVER_ACK_MSG";
	public static final int SERVER_MSG_ERROR=1;			//异常返回值
	public static final int SERVER_MSG_OK=0;			//正常结束
	
	
	public CmdClientSocket(String ip, int port, Handler handler) {
		super();
		this.port = port;
		this.ip = ip;
		this.handler = handler;
	}
		//关闭socket对象
		private void close() {
			// TODO Auto-generated method stub
			try {
				socket.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	//连接
		private void connect() {
			// TODO Auto-generated method stub
			InetSocketAddress inetAddress=new InetSocketAddress(ip, port);
			socket=new Socket();
			try {
				socket.connect(inetAddress, connect_timeout);
				if(!isDebug){
					socket.setSoTimeout(connect_timeout);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	public void doCmdTask(String cmd){
		ArrayList<String> msgList=new ArrayList<String>();
		try{
			connect();
			writeCmd(cmd);
			msgList=readSocketMsg();
			MsgType=SERVER_MSG_OK;
		}catch(IOException e){
			msgList.clear();
			msgList.add(e.toString());//在msgList列表中放入错误信息
			MsgType=SERVER_MSG_ERROR;
			e.printStackTrace();
		}
		close();
		Message message = handler.obtainMessage();
		message.arg2=MsgType;
		//句柄bundle在handleMessage(Message msg)函数中首先对消息的arg2进行判断，若是SERVER_MSG_ERROR类型，则不更新列表，Toast显示错误信息
	    //若message.arg2是SERVER_MSG_OK，则更新列表UI
		Bundle bundle=new Bundle();
		bundle.putStringArrayList(KEY_SERVER_ACK_MSG, msgList);
		message.setData(bundle);
		handler.sendMessage(message);
	}
	/**
	 * 
	 * @return
	 * @throws IOException
	 */
	private ArrayList<String> readSocketMsg() throws IOException {
		// TODO Auto-generated method stub
		ArrayList<String> msgList=new ArrayList<String>();
		
		InputStream inputStream=socket.getInputStream();
		InputStreamReader reader=new InputStreamReader(inputStream,"gb2312");
		bufferedReader=new BufferedReader(reader);
		String lineNumStr=bufferedReader.readLine();
		int lineNum=Integer.parseInt(lineNumStr);
		Log.e("[CHECK]","lineNum:"+lineNum);
		for(int i=0;i<lineNum;i++){
			String str=bufferedReader.readLine();
			msgList.add(str);
		}
		
		return msgList;
	}
	private void writeCmd(String cmd) throws IOException {
		// TODO Auto-generated method stub
		BufferedOutputStream os=new BufferedOutputStream(socket.getOutputStream());
		writer=new OutputStreamWriter(os,"gb2312");
		writer.write("1\n");
		writer.write(cmd+"\n");
		Log.e("[Check]", "writeCmd:"+cmd);
		writer.flush();
	}
	public void work(final String cmd){
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				Log.e("[Check]","work");
				doCmdTask(cmd);
			}

		}).start();
	}
}
