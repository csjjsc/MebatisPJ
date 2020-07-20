package org.rockyJie.v2.pojo;

import java.io.Serializable;

/**
 * RockeyJie
 * 2020/7/12
 */
public class Blog implements Serializable {

    private Integer bid;
    private String name;
    private String authorId;

    public Blog(Integer bid, String name, String authorId) {
        this.bid = bid;
        this.name = name;
        this.authorId = authorId;
    }

    public Blog() {
    }

    public Integer getBid() {
        return bid;
    }

    public void setBid(Integer bid) {
        this.bid = bid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthorId() {
        return authorId;
    }

    public void setAuthorId(String authorId) {
        this.authorId = authorId;
    }

    @Override
    public String toString() {
        return "Blog{" +
                "bid=" + bid +
                ", name='" + name + '\'' +
                ", authorId='" + authorId + '\'' +
                '}';
    }
}
