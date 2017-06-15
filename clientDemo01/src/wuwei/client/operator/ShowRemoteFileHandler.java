package wuwei.client.operator;

import java.util.ArrayList;
import java.util.Collections;

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
	 * ͨ���������UI�������ģ�context����listView����
	 * ����UIͨ��dir����͵�server��ʱ
	 * server�������д��Ϣ�����þ����Ϊc��socket�ľ������
	 * ����ͨ���ͻ��˵ľ����Ϣ����handlemessage������������Ϣ
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
		//0λ��Ϊ��ǰ����·��
		String filePath=ack.get(0);
		for(int i=0;i<ack.size();i++){
			String fileInfo=ack.get(i);
			NetFileData data=new NetFileData(fileInfo, filePath);
			netFileList.add(data);
		}
		/**
         * �ļ�����
         */
        ArrayList<NetFileData> fileData_final = new ArrayList<NetFileData>();
        ArrayList<NetFileData> fileData_dir = new ArrayList<NetFileData>();
        ArrayList<NetFileData> fileData_file = new ArrayList<NetFileData>();

        for (NetFileData item : netFileList) {
            if (item.getFileType()>=1)
                fileData_dir.add(item);
            else
                fileData_file.add(item);

        }
        Collections.sort(fileData_dir);
        Collections.sort(fileData_file);
        fileData_final.addAll(fileData_dir);
        fileData_final.addAll(fileData_file);
		NetFileListAdapter adapter=new NetFileListAdapter(context, fileData_final);
		
		listView.setAdapter(adapter);
		super.handleMessage(msg);
	}
	
	
}
