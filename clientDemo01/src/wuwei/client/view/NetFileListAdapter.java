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
		int image_folder_id=R.drawable.file;//文件夹图片
		int image_file_id=R.drawable.dir;		
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
			setTrueImage(iv,fileData.getFileName());
			tv3.setText(fileData.getFileSizeStr());
		}
		tv1.setText(fileData.getFileName());
		tv2.setText(fileData.getFileModifiedDate());
		return convertView;
	}
	private void setTrueImage(ImageView iv, String fileName) {
		// TODO Auto-generated method stub
		int image_ppt_id=R.drawable.ppt;
		int image_exe_id=R.drawable.exe;
		int imge_txt_id=R.drawable.txt;
		int image_mov_id=R.drawable.movies;
		int image_html_id=R.drawable.html;
		int image_music_id=R.drawable.music;
		String fileType="";
		int idx =  fileName.lastIndexOf(".");
		if(idx>0){// 有扩展名
			fileType=fileName.substring(idx+1);
		}
		else{
			return;
		}
		if(fileType.equalsIgnoreCase("avi")|fileType.equalsIgnoreCase("mpeg")|fileType.equalsIgnoreCase("mov")|fileType.equalsIgnoreCase("mp4")){
			iv.setImageResource(image_mov_id);
		}
		if(fileType.equalsIgnoreCase("flv")|fileType.equalsIgnoreCase("flac")|fileType.equalsIgnoreCase("mkv")){
			iv.setImageResource(image_mov_id);
		}
		if(fileType.equalsIgnoreCase("mpg")|fileType.equalsIgnoreCase("wmv")|fileType.equalsIgnoreCase("rmvb")){
			iv.setImageResource(image_mov_id);
		}
		if(fileType.equalsIgnoreCase("txt")){
			iv.setImageResource(imge_txt_id);
		}
		if(fileType.equalsIgnoreCase("ppt")|fileType.equalsIgnoreCase("pptx")){
			iv.setImageResource(image_ppt_id);
		}
		if(fileType.equalsIgnoreCase("html")){
			iv.setImageResource(image_html_id);
		}
		if(fileType.equalsIgnoreCase("mp3")){
			iv.setImageResource(image_music_id);
		}
	}
	
	
}
