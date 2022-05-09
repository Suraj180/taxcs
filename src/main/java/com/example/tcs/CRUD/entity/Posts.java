package com.example.tcs.CRUD.entity;

import javax.persistence.*;
import java.sql.Blob;

@Entity
public class Posts {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(length=1000)
    private String Title;

    @Column( length=10000)
    private String text;

    @Column(length=1000)
    private String About;

    @Lob
    private byte[] photos;

    public Posts() {
        super();
    }


    public Posts(String title, String text, String about, byte[] photos) {
        super();
        Title = title;
        this.text = text;
        About = about;
        this.photos = photos;
    }

    public byte[] getPhotos() {
        return photos;
    }

    public void setPhotos(String fileName, String contentType, byte[] photos) {
        this.photos = photos;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getAbout() {
        return About;
    }

    public void setAbout(String about) {
        About = about;
    }

    @Override
    public String toString() {
        return "Posts{" +
                "id=" + id +
                ", Title='" + Title + '\'' +
                ", text='" + text + '\'' +
                ", About='" + About + '\'' +
                ", About='" + photos + '\'' +
                '}';
    }


}
