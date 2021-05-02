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
@Table(name = "favorities")
public class FavoritesEntity {

    /*
     * Vinculando a variaveis as colunas do banco de dados.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "username")
    private String username;

    @Column(name = "imdbid")
    private String imdbid;

    @Column(name = "dateadded")
    private String dateadded;

    /*
     * Método construtor da classe.
     */
    public FavoritesEntity() {
    }

    public FavoritesEntity(String username, String imdbid, String dateadded) {
        this.username = username;
        this.imdbid = imdbid;
        this.dateadded = dateadded;
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getImdbId() {
        return this.imdbid;
    }

    public void setImdbid(String imdbid) {
        this.imdbid = imdbid;
    }

    public String getDateAdded() {
        return this.dateadded;
    }

    public void setDateadded(String dateadded) {
        this.dateadded = dateadded;
    }

}
