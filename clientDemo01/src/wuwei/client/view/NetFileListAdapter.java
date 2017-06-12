package wuwei.client.view;

import java.util.ArrayList;

import com.example.clientdemo01.R;

import wuwei.client.data.NetFileData;
import android.R.animator;
import android.content.Context;
import android.graphics.drawable.LayerDrawable;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class NetFileListAdapter extends ArrayAdapter<NetFileData> {

	public NetFileListAdapter(Context context, ArrayList<NetFileData> netFileList) {
		super(context, android.R.layout.simple_list_item_1,netFileList);
		// TODO Auto-generated constructor stub
		this.context=context;
		this.netFileList=netFileList;
	}
	private ArrayList<NetFileData> netFileList;
	private Context context;
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		int image_folder_id=R.drawable.folder;//ÎÄ¼þ¼ÐÍ¼Æ¬
		int image_file_id=R.drawable.folder;
		if(convertView==null){
			convertView=LayoutInflater.from(context).inflate(
					R.layout.item, null,false);
		}
		ImageView iv=(ImageView) convertView.findViewById(R.id.imageView1);
		TextView tv1=(TextView) convertView.findViewById(R.id.textView1);
		TextView tv2=(TextView) convertView.findViewById(R.id.textView2);
		TextView tv3=(TextView) convertView.findViewById(R.id.textView3);
		
		NetFileData fileData=netFileList.get(position);
		if(fileData.getFileType()>=1){
			iv.setImageResource(image_folder_id);
			tv2.setText("");
		}else{
			iv.setImageResource(image_file_id);
			tv3.setText(fileData.getFileSizeStr());
		}
		tv1.setText(fileData.getFileName());
		tv2.setText(fileData.getFileModifiedDate());
		return convertView;
	}
	
	
}
