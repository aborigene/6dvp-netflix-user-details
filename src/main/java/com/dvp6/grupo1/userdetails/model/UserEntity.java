package com.dvp6.grupo1.userdetails.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/*
  Classe responsável por criar uma entidade vinculada com o banco de dados.
*/
@Entity
@Table(name = "user")
public class UserEntity {

    /*
     * Vinculando a variaveis as colunas do banco de dados.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "username")
    private String username;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "gender")
    private String gender;

    @Column(name = "birth_date")
    private String birth_date;

    @Column(name = "country")
    private String country;

    protected UserEntity() {
    }

    public UserEntity(String username, String name, String surname, String gender, String birth_date, String country)
            throws Exception {
        this.username = username;
        this.name = name;
        this.surname = surname;
        this.gender = gender;
        this.birth_date = birth_date;
        this.country = country;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsernameame(String username) {
        this.username = username;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return this.surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getGender() {
        return this.gender;
    }
    
    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBirthDate() {
        return this.birth_date;
    }

    public void setBirthDate(String birth_date) {
        this.birth_date = birth_date;
    }

    public String getCountry() {
        return this.country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
