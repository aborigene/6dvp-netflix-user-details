package com.dvp6.grupo1.userdetails.model;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import com.google.gson.*;

import org.aspectj.weaver.EnumAnnotationValue;

@Entity
public class ListEntry {
    @Id
    private String imdbId;
    private String userId;
    private Date dateAdded;
    private String entryType;

    protected ListEntry() {}

    public ListEntry(String imdbId, String userId, Date dateAdded, String entryType){
        this.imdbId=imdbId;
        this.userId=userId;
        this.dateAdded=dateAdded;
        this.entryType=entryType.toString();
    }

    @Override
    public String toString(){
        return String.format("ListItem[imdbid='%s', userid='%s', dateAdded='%tY-%tm-%td', entryType='%s']", imdbId, userId, dateAdded, entryType);
    }

    public String toJson(){
        return new Gson().toJson(this);
    }

    public String getImdbId(){
        return this.imdbId;
    }
    
    public String getUserId(){
        return this.userId;
    }

    public Date getDateAdded(){
        return this.dateAdded;
    }

    public String getEntryType(){
        return this.entryType;
    }
}
