package wuwei.client.view;

import java.util.List;

import com.example.clientdemo01.R;

import wuwei.client.data.HotKeyData;
import wuwei.client.socket.CmdClientSocket;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class HotKeyGridAdapter extends ArrayAdapter<HotKeyData> {
	Context context;
	List<HotKeyData>  list;
	CmdClientSocket  cmdClientSocket;
	String  cmd;
	public HotKeyGridAdapter(Context   context,   List<HotKeyData>  list,CmdClientSocket   cmdClientSocket)  {
		super(context,  android.R.layout.simple_list_item_1,    list);
		this.context=context;
		this.list=list;
		this.cmdClientSocket=cmdClientSocket;
		// TODO Auto-generated   constructor stub
	}
	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ViewHolder  viewHolder;
		if(convertView==null){
			convertView  = LayoutInflater.from(context).inflate(R.layout.hotkey_row_view,     null,  false);
			viewHolder=new  ViewHolder();
			viewHolder.textView=(TextView)   convertView.findViewById(R.id.textView1);
			convertView.setTag(viewHolder);
		}
		viewHolder  = (ViewHolder)  convertView.getTag();
		TextView  tv=viewHolder.textView;
		tv.setText(list.get(position).getHotkeyName());
		
		tv.setOnClickListener(new   TextView.OnClickListener() {
			@Override
			public void  onClick(View  v) {
			// TODO Auto-generated   method stub
			cmd=list.get(position).getHotkeyCmd();
			cmdClientSocket.work(cmd);
			}

		});
		return convertView;
	}
	class ViewHolder  {
		TextView   textView;
	}

}
