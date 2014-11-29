package com.estrumetal.jsf;

public class GalleriController {

    private String img;
    private String title;
    private String desc;

    public GalleriController(String img, String title, String desc) {
        this.img = img;
        this.title = title;
        this.desc = desc;
    }

    public GalleriController() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

}
