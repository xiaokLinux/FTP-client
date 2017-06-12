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
//	boolean isDebug=true;					//	����debugģʽ
//	Handler handler;
//	Socket socket;
//	private BufferedReader bufferedReader;
//	private OutputStreamWriter writer;
//	public static final String KEY_SERVER_ACK_MSG="KEY_SERVER_ACK_MSG";
//	public static final int SERVER_MSG_ERROR=1;			//�쳣����ֵ
//	public static final int SERVER_MSG_OK=0;			//��������
	public static  int SERVER_MSG_OK=0;//   ���ڷ��͸��������Ϣ����,������Ϣ��arg2�У���ʾ���������
	public static  int SERVER_MSG_ERROR=1;//   ��ʾ����˳���
	public static  String STATUS_OK="ok";//   ���������ʱ�����ص���Ϣʶ���ַ���
	public static  final String  KEY_SERVER_ACK_MSG  = "KEY_SERVER_ACK_MSG";
	public static  boolean isDebug=true;
	private int  port;
	private String  ip;
	private int  connect_timeout  = 2000;//   ����socket ���ӵĳ�ʱʱ�䣬��λ��ms
	private int  transfer_timeout  = 10000;//   ����socket ���ӵĳ�ʱʱ�䣬��λ��ms
	private Handler  handler;//  �����Message ����
	private Socket  socket;
	private int  msgType=SERVER_MSG_ERROR;//msgType=0,     ���������ִ�У�msgType=1 �������ִ�г���
	private BufferedReader   bufferedReader;
	private OutputStreamWriter   writer;

	
	public CmdClientSocket(String ip, int port, Handler handler) {
		super();
		this.port = port;
		this.ip = ip;
		this.handler = handler;
	}
		//�ر�socket����
		private void close() {
			// TODO Auto-generated method stub
			try {
				socket.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	//����
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
			connect();				// ���ӷ���ˣ������쳣������׽
			writeCmd(cmd);			//  �����˷������δ�ر������
			msgList=readSocketMsg();//  ��ȡsocket ��������Ϣ�������������msgList �б�
			//������˷�����Ϣ��״̬Ϊ"ok"����msgType  ����Ϊ�Զ��峣��SERVER_MSG_OK ��ʵ��ֵΪ0��
			//����˷�����Ϣ״̬����"ok"����msgType  ΪSERVER_MSG_ERROR  ��ʵ��ֵΪ1��
			close();// �ر�Socket ���������������
//			MsgType=SERVER_MSG_OK;
		}catch(IOException e){
			msgType=SERVER_MSG_ERROR;//   ����׽���쳣��������msgType ΪSERVER_MSG_ERROR   ��ʵ��ֵΪ1��
			//SERVER_MSG_ERROR  ��SERVER_MSG_OK  Ϊ�Զ��峣��
			//public  static int SERVER_MSG_OK=0;//   ���ڷ��͸��������Ϣ����,������Ϣ��arg2�У���ʾ���������
			//public  static int SERVER_MSG_ERROR=1;//    ��ʾ����˳���
			msgList.add(e.toString());//    ��msgList �б��з��������Ϣ
			e.printStackTrace();
		}
		Message message = handler.obtainMessage();
		Bundle bundle  = new Bundle();
		bundle.putStringArrayList(KEY_SERVER_ACK_MSG,    msgList);//  �ش�������Ҫ��msgList ��size�����жϣ�����0��Ϊ��Ч
		message.arg2=msgType;
		//���bundle ��handleMessage(Message   msg)���������ȶ���Ϣ��arg2�����жϣ�����SERVER_MSG_ERROR  ���ͣ��򲻸����б�Toast ��ʾ������Ϣ
		//��message.arg2  ��SERVER_MSG_OK ��������б�UI
		message.setData(bundle);
		handler.sendMessage(message);//      ͨ�����֪ͨ��UI���ݴ�����ϣ����ش�����

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
			msgList.add(status);//   ������˵Ĵ�����Ϣ������Ϣ�б�
		}
		//	    ����״̬�Ѷ����ʴ�1��ʼ����
		for(int i=1;i<lineNum;i++){
			String str=bufferedReader.readLine();
			msgList.add(str);
		}
		
		return msgList;
	}
	private void writeCmd(String cmd) throws IOException {
		// TODO Auto-generated method stub
		//socket.getOutputStream()          ���������BufferedOut
		//
		//OutputStreamWriter  writer=new  OutputStreamWriter(os);//    Ĭ�ϵ��ַ����룬�п�����GB2312 Ҳ�п�����UTF-8 ��ȡ����ϵͳ
		//���鲻Ҫ��Ĭ���ַ����룬����ָ��UTF-8 ���Ա�֤���ͽ����ַ�����һ�£������ڳ�����
		//��������ֽڴ���ģ������߱��ַ���ֱ��д�빦�ܣ�����ٽ����װ��OutputStreamWriter   ��ʹ��֧���ַ���ֱ��д��

		BufferedOutputStream os=new BufferedOutputStream(socket.getOutputStream());
		writer=new OutputStreamWriter(os,"gb2312");
		writer.write("1\n");//   δ����д�����������������ڴ���
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
