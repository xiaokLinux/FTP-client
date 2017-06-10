package wuwei.client.data;

public class NetFileData {
	private long fileSize = 0;// �ļ�����Ӧ��long�����ݣ��������2GB���ļ���С�޷����
	private String fileName = "$error";// �ļ����ƣ�����Ŀ¼��Ϣ,Ĭ��ֵ���ڱ�ʾ�ļ�����
	private String filePath = ".\\";// ���ļ�����������Ŀ¼��Ĭ��ֵΪ��ǰ���Ŀ¼
	private String fileSizeStr = "0";// �ļ��Ĵ�С�����ַ�����ʾ�������ܵ�ѡ��B��KB��MB��GB�����
	private boolean isDirectory = false;// trueΪ�ļ��У�falseΪ�ļ�
	private String fileModifiedDate = "1970-01-01 00:00:00";// �ļ�����޸����ڣ�Ĭ��ֵΪ1970���׼ʱ��
	public NetFileData(String fileInfo, String filePath) {
		super();
		try{
			String array[]=fileInfo.split(">");
			this.filePath=filePath;
			this.fileName=array[0];
			this.fileModifiedDate=array[1];
			this.fileSize=new Long(array[2]);
			this.isDirectory=(array[3].equals("1"));
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
	public boolean isDirectory() {
		return isDirectory;
	}
	public void setDirectory(boolean isDirectory) {
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

}
