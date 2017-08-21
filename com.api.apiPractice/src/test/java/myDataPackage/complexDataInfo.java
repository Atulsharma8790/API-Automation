package myDataPackage;

public class complexDataInfo {

	private String id;
	private String title;
	private String author;
	private myDataInfo info;
	
	public String getID(){
		return id;
	}
	public void setID(String id){
		this.id=id;
	}
	
	public String getTitle(){
		return title;
	}
	public void setTitle(String title){
		this.title=title;
	}
	
	public String getAuthor(){
		return author;
	}
	public void setAuthor(String author){
		this.author=author;
	}
	
	public myDataInfo getInfo(){
		return info;
	}
	
	public void setInfo(myDataInfo info){
		this.info=info;
	}
	
}
