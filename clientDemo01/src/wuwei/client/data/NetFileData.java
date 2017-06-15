package wuwei.client.data;

public class NetFileData implements Comparable {
	private long fileSize = 0;// 文件长度应该long型数据，否则大于2GB的文件大小无法表达
	private String fileName = "$error";// 文件名称，不含目录信息,默认值用于表示文件出错
	private String filePath = ".\\";// 该文件对象所处的目录，默认值为当前相对目录
	private String fileSizeStr = "0";// 文件的大小，用字符串表示，能智能地选择B、KB、MB、GB来表达
	private int isDirectory = 0;// "0"为文件，"1"为目录，"2"为根目录
	private String fileModifiedDate = "1970-01-01 00:00:00";// 文件最近修改日期，默认值为1970年基准
	//fileName+fileDate+fileSize+idDir
	public NetFileData(String fileInfo, String filePath) {
		super();
		try{
			String array[]=fileInfo.split(">");
			this.filePath=filePath;
			this.fileName=array[0];
			this.fileModifiedDate=array[1];
			this.fileSize=new Long(array[2]);
			this.isDirectory=Integer.parseInt(array[3]);
			this.fileSizeStr=parseFileSize(this.fileSize);
		}catch(Exception e){
			
		}
		this.filePath = filePath;
	}
	public long getFileSize() {
		return fileSize;
	}
	public void setFileSize(long fileSize) {
		this.fileSize = fileSize;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public String getFileSizeStr() {
		return fileSizeStr;
	}
	public void setFileSizeStr(String fileSizeStr) {
		this.fileSizeStr = fileSizeStr;
	}
	public int getFileType() {
		return isDirectory;
	}
	public void setDirectory(int isDirectory) {
		this.isDirectory = isDirectory;
	}
	public String getFileModifiedDate() {
		return fileModifiedDate;
	}
	public void setFileModifiedDate(String fileModifiedDate) {
		this.fileModifiedDate = fileModifiedDate;
	}
	private String parseFileSize(long fileSize2) {
		// TODO Auto-generated method stub
		String sizeStr="";
		double KB=(double) 1024.0;
		double MB=(double) (KB*1024.0);
		double GB=(double) (MB*1024.0);
		double sizef=(double) (fileSize2);
		if(sizef>=GB){
			sizeStr=new String().format("%.3fGB", sizef/GB);
			return sizeStr;
		}
		if(sizef>=MB){
			sizeStr=new String().format("%.3fMB", sizef/GB);
			return sizeStr;
		}
		if(sizef>=KB){
			sizeStr=new String().format("%.3fKB", sizef/GB);
			return sizeStr;
		}
		sizeStr=new String().format("%dB", (int)fileSize2);
		return sizeStr;
	}
	@Override
	public int compareTo(Object another) {
		// TODO Auto-generated method stub
		return 0;
	}


}
