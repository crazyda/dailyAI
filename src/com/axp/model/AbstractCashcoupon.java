package com.axp.model;

import java.sql.Timestamp;


/**
 * AbstractCashcoupon entity provides the base persistence definition of the Cashcoupon entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractCashcoupon  implements java.io.Serializable {


    // Fields    

     private Integer id;
     private Double price;
     private String imgUrl;
     private Boolean isValid;
     private Timestamp createtime;


    // Constructors

    /** default constructor */
    public AbstractCashcoupon() {
    }

	/** minimal constructor */
    public AbstractCashcoupon(Double price) {
        this.price = price;
    }
    
    /** full constructor */
    public AbstractCashcoupon(Double price, String imgUrl, Boolean isValid, Timestamp createtime) {
        this.price = price;
        this.imgUrl = imgUrl;
        this.isValid = isValid;
        this.createtime = createtime;
    }

   
    // Property accessors

    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }

    public Double getPrice() {
        return this.price;
    }
    
    public void setPrice(Double price) {
        this.price = price;
    }

    public String getImgUrl() {
        return this.imgUrl;
    }
    
    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public Boolean getIsValid() {
        return this.isValid;
    }
    
    public void setIsValid(Boolean isValid) {
        this.isValid = isValid;
    }

    public Timestamp getCreatetime() {
        return this.createtime;
    }
    
    public void setCreatetime(Timestamp createtime) {
        this.createtime = createtime;
    }
   








}