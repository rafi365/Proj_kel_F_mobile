package umn.ac.id.kusalist;

import java.io.Serializable;

public class NoteArray  implements Serializable {
    private String Title;
    private String BodyText;
    private String image64;
    private boolean checkbox;

    public boolean isCheckbox() {
        return checkbox;
    }

    public void setCheckbox(boolean checkbox) {
        this.checkbox = checkbox;
    }

    public NoteArray(String Title, String BobyText, String image64,boolean checkbox){
        this.Title = Title;
        this.BodyText = BobyText;
        this.image64 = image64;
        this.checkbox = checkbox;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getBodyText() {
        return BodyText;
    }

    public void setBodyText(String bodyText) {
        BodyText = bodyText;
    }

    public String getImage64() {
        return image64;
    }

    public void setImage64(String image64) {
        this.image64 = image64;
    }
}
