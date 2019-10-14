package ca.bcit.toonland;

import java.io.Serializable;

public class Book implements Serializable {

    private String title;
    private String ISB_10;
    private String description;
    private String publishedDate;
    private String publisher;
    private String[] authors;
    private String pictureUrl;

    public Book(String ISB, String title, String desc, String pubDate, String publisher, String[] authors, String url){
        setTitle(title);
        setISB_10(ISB);
        setAuthors(authors);
        setDescription(desc);
        setPublishedDate(pubDate);
        setPictureUrl(url);
        setPublisher(publisher);
    }
    public Book(){

    }

    // Getter Methods

    public String getTitle() {
        return title;
    }

    public String getISB_10() {
        return ISB_10;
    }

    public String getDescription() {
        return description;
    }

    public String getPublishedDate() {
        return publishedDate;
    }

    public String getPublisher() {
        return publisher;
    }

    public String[] getAuthors() {
        return authors;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    // Setter Methods

    public void setTitle(String title) {
        this.title = title;
    }

    public void setISB_10(String ISB_10) {
        this.ISB_10 = ISB_10;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPublishedDate(String publishedDate) {
        this.publishedDate = publishedDate;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public void setAuthors(String[] authors) {
        authors = authors;
    }

    public void setPictureUrl(String url) {
        this.pictureUrl = url;
    }
}
