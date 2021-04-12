package com.dvp6.grupo1.userdetails.model;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import com.google.gson.*;

@Entity
public class User {
    @Id
    private long id;
    private String name;
    private String surname;
    private String gender;
    private String birth_date;
    private String country;

    protected User() {}

    public User(String name, String surname, String gender, String birth_date, long id, String country){
        this.name=name;
        this.surname=surname;
        this.gender=gender;
        this.birth_date=birth_date;
        this.id=id;
        this.country=country;
    }

    @Override
    public String toString(){
        return String.format("User[name='%s', surname='%s', gender='%s', userid='%s', birth_date='%s', country='%s']", name, surname, gender, id, birth_date, country);
    }

    public String toJson(){
        return new Gson().toJson(this);
    }

    public String getName(){
        return this.name;
    }
    
    public String getSurname(){
        return this.surname;
    }

    public String getGender(){
        return this.gender;
    }

    public long getUserId(){
        return this.id;
    }

    public String getBirthDate(){
        return this.birth_date;
    }

    public String getCountry(){
        return this.country;
    }

    public void setName(String name){
        this.name=name;
    }
    
    public void setSurname(String surname){
        this.surname=surname;
    }

    public void setGender(String gender){
        this.gender=gender;
    }

    public void setBirthDate(String birth_date){
        this.birth_date=birth_date;
    }

    public void setCountry(String country){
        this.country=country;
    }
}
