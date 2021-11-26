package umn.ac.id.kusalist;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Note implements Parcelable {

	@SerializedName("image")
	private String image;

	@SerializedName("checkbox")
	private boolean checkbox;

	@SerializedName("id")
	private String id;

	@SerializedName("title")
	private String title;

	@SerializedName("body")
	private String body;

	public Note(){}

	public Note(String id, String title, String body,String image, boolean checkbox) {
		this.image = image;
		this.checkbox = checkbox;
		this.id = id;
		this.title = title;
		this.body = body;
	}

	public void setImage(String image){
		this.image = image;
	}

	public String getImage(){
		return image;
	}

	public void setCheckbox(boolean checkbox){
		this.checkbox = checkbox;
	}

	public boolean isCheckbox(){
		return checkbox;
	}

	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return id;
	}

	public void setTitle(String title){
		this.title = title;
	}

	public String getTitle(){
		return title;
	}

	public void setBody(String body){
		this.body = body;
	}

	public String getBody(){
		return body;
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(this.image);
		dest.writeByte(this.checkbox ? (byte) 1 : (byte) 0);
		dest.writeString(this.id);
		dest.writeString(this.title);
		dest.writeString(this.body);
	}

	public void readFromParcel(Parcel source) {
		this.image = source.readString();
		this.checkbox = source.readByte() != 0;
		this.id = source.readString();
		this.title = source.readString();
		this.body = source.readString();
	}

	protected Note(Parcel in) {
		this.image = in.readString();
		this.checkbox = in.readByte() != 0;
		this.id = in.readString();
		this.title = in.readString();
		this.body = in.readString();
	}

	public static final Parcelable.Creator<Note> CREATOR = new Parcelable.Creator<Note>() {
		@Override
		public Note createFromParcel(Parcel source) {
			return new Note(source);
		}

		@Override
		public Note[] newArray(int size) {
			return new Note[size];
		}
	};
}