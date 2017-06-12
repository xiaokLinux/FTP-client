package wuwei.client.operator;

import java.util.ArrayList;

import wuwei.client.data.Default_HotKey;
import wuwei.client.data.HotKeyData;
import wuwei.client.data.Movie_HotKey;
import wuwei.client.data.NetFileData;
import wuwei.client.data.PPT_HotKey;

public class HotKeyGenerator {

	public HotKeyGenerator(){
		
	}
	public static  ArrayList<HotKeyData>   getHotkeyList(NetFileData  fileData){
	//  ����fileType ��չ��������������Щ�ȼ��б�
	//  �˴�ֻ����ppt���ȼ���Ĭ�ϵ��ȼ�
		String fileType  = getFileType(fileData);
		if(fileType.equalsIgnoreCase("ppt")|fileType.equalsIgnoreCase("pptx")|fileType.equalsIgnoreCase("pps")){
				return new  PPT_HotKey().getHotkeyList();
		}
		if(checkIsMovie(fileType)){
			return new  Movie_HotKey().getHotkeyList();
		}
		ArrayList<HotKeyData>  list=new  Default_HotKey().getHotkeyList();
		return list;
	}
	
	public static  String getFileType(NetFileData   fileData){
		String fileType="";
		if(fileData.getFileType()==0){//    �����ļ���
		String fileName  = fileData.getFileName();
		int idx =  fileName.lastIndexOf(".");
		if(idx>0){// ����չ��
		fileType=fileName.substring(idx+1);
		}
		}
		return fileType;//    �����ļ��л�������չ������fileType=""
	}
	private static  boolean  checkIsMovie(String  fileType){
		if(fileType.equalsIgnoreCase("avi")|fileType.equalsIgnoreCase("mpeg")|fileType.equalsIgnoreCase("mov")){
				return true;
		}
		if(fileType.equalsIgnoreCase("flv")|fileType.equalsIgnoreCase("flac")|fileType.equalsIgnoreCase("mkv")){
				return true;
		}
		if(fileType.equalsIgnoreCase("mpg")|fileType.equalsIgnoreCase("wmv")|fileType.equalsIgnoreCase("rmvb")){
				return true;
		}
		return false;
	}


}
