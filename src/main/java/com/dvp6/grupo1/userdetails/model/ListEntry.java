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
    private String imdbid;
    private String userid;
    private Date dateadded;
    private String entrytype;

    protected ListEntry() {}

    public ListEntry(String imdbid, String userid, Date dateadded, String entrytype){
        this.imdbid=imdbid;
        this.userid=userid;
        this.dateadded=dateadded;
        this.entrytype=entrytype.toString();
    }

    @Override
    public String toString(){
        return String.format("ListItem[imdbid='%s', userid='%s', dateAdded='%tY-%tm-%td', entryType='%s']", imdbid, userid, dateadded, entrytype);
    }

    public String toJson(){
        return new Gson().toJson(this);
    }

    public String getImdbId(){
        return this.imdbid;
    }
    
    public String getUserId(){
        return this.userid;
    }

    public Date getDateAdded(){
        return this.dateadded;
    }

    public String getEntryType(){
        return this.entrytype;
    }
}
