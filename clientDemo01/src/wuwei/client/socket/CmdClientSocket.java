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

//	int port;
//	String ip;
//	int MsgType;
//	int connect_timeout=2000;
//	boolean isDebug=true;					//	处于debug模式
//	Handler handler;
//	Socket socket;
//	private BufferedReader bufferedReader;
//	private OutputStreamWriter writer;
//	public static final String KEY_SERVER_ACK_MSG="KEY_SERVER_ACK_MSG";
//	public static final int SERVER_MSG_ERROR=1;			//异常返回值
//	public static final int SERVER_MSG_OK=0;			//正常结束
	public static  int SERVER_MSG_OK=0;//   用于发送给句柄的消息类型,放在消息的arg2中，表示服务端正常
	public static  int SERVER_MSG_ERROR=1;//   表示服务端出错
	public static  String STATUS_OK="ok";//   服务端正常时，返回的消息识别字符串
	public static  final String  KEY_SERVER_ACK_MSG  = "KEY_SERVER_ACK_MSG";
	public static  boolean isDebug=true;
	private int  port;
	private String  ip;
	private int  connect_timeout  = 2000;//   设置socket 连接的超时时间，单位：ms
	private int  transfer_timeout  = 10000;//   设置socket 连接的超时时间，单位：ms
	private Handler  handler;//  句柄的Message 对象
	private Socket  socket;
	private int  msgType=SERVER_MSG_ERROR;//msgType=0,     服务端正常执行，msgType=1 ，服务端执行出错
	private BufferedReader   bufferedReader;
	private OutputStreamWriter   writer;

	
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
			connect();				// 连接服务端，若有异常，被捕捉
			writeCmd(cmd);			//  向服务端发送命令，未关闭输出流
			msgList=readSocketMsg();//  读取socket 输入流信息，并将结果存入msgList 列表
			//若服务端返回信息的状态为"ok"，则将msgType  设置为自定义常量SERVER_MSG_OK （实际值为0）
			//服务端返回信息状态不是"ok"，则将msgType  为SERVER_MSG_ERROR  （实际值为1）
			close();// 关闭Socket 的输入流、输出流
//			MsgType=SERVER_MSG_OK;
		}catch(IOException e){
			msgType=SERVER_MSG_ERROR;//   若捕捉到异常，则设置msgType 为SERVER_MSG_ERROR   （实际值为1）
			//SERVER_MSG_ERROR  和SERVER_MSG_OK  为自定义常量
			//public  static int SERVER_MSG_OK=0;//   用于发送给句柄的消息类型,放在消息的arg2中，表示服务端正常
			//public  static int SERVER_MSG_ERROR=1;//    表示服务端出错
			msgList.add(e.toString());//    在msgList 列表中放入错误信息
			e.printStackTrace();
		}
		Message message = handler.obtainMessage();
		Bundle bundle  = new Bundle();
		bundle.putStringArrayList(KEY_SERVER_ACK_MSG,    msgList);//  回传数据需要对msgList 的size进行判断，大于0才为有效
		message.arg2=msgType;
		//句柄bundle 在handleMessage(Message   msg)函数中首先对消息的arg2进行判断，若是SERVER_MSG_ERROR  类型，则不更新列表，Toast 显示错误信息
		//若message.arg2  是SERVER_MSG_OK ，则更新列表UI
		message.setData(bundle);
		handler.sendMessage(message);//      通过句柄通知主UI数据传输完毕，并回传数据

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
		msgType=SERVER_MSG_ERROR;
		if(lineNum<1){
			msgList.add("Receive  empty  message");
			return msgList;
		}
		String status  = bufferedReader.readLine();
		if(status.equalsIgnoreCase(STATUS_OK)){
			msgType=SERVER_MSG_OK;
		}else{
			msgList.add(status);//   将服务端的错误信息放入消息列表
		}
		//	    反馈状态已读，故从1开始索引
		for(int i=1;i<lineNum;i++){
			String str=bufferedReader.readLine();
			msgList.add(str);
		}
		
		return msgList;
	}
	private void writeCmd(String cmd) throws IOException {
		// TODO Auto-generated method stub
		//socket.getOutputStream()          是输出流，BufferedOut
		//
		//OutputStreamWriter  writer=new  OutputStreamWriter(os);//    默认的字符编码，有可能是GB2312 也有可能是UTF-8 ，取决于系统
		//建议不要用默认字符编码，而是指定UTF-8 ，以保证发送接收字符编码一致，不至于出乱码
		//输出流是字节传输的，还不具备字符串直接写入功能，因此再将其封装入OutputStreamWriter   ，使其支持字符串直接写入

		BufferedOutputStream os=new BufferedOutputStream(socket.getOutputStream());
		writer=new OutputStreamWriter(os,"gb2312");
		writer.write("1\n");//   未真正写入的输出流，仅仅在内存中
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
