package com.dvp6.grupo1.userdetails.model;

public class LikeOrDislikeEntity {
    
    private String imbdid;
    private String likeOrDislike;

    public LikeOrDislikeEntity(String imdbid, String likeOrDislike){
        this.imbdid = imdbid;
        this.likeOrDislike = likeOrDislike;
    }

    public String getImbdid() {
        return this.imbdid;
    }

    public void setImbdid(String imbdid) {
        this.imbdid = imbdid;
    }
    public String getLikeOrDislike() {
        return this.likeOrDislike;
    }

    public void setLikeOrDislike(String likeOrDislike) {
        this.likeOrDislike = likeOrDislike;
    }

}
