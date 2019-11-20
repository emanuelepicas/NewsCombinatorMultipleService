package com.sourcesense.emanuelepicariello.demo.model;


import java.util.Date;

public class NyTimesArticle  {

    private String title;

    private Date created_date;

    private String source;

    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setCreated_date(Date created_date) {
        this.created_date = created_date;
    }


    public Date getData() {
        return created_date;
    }

    public void setSource(String source) {
        this.source = "NyTimes";
    }

    public String getSource() {
        return "NyTimes";
    }

}
