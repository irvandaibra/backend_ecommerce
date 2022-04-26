package com.database.ecommerfix.model;

import javax.persistence.*;
import java.sql.Date;


@Entity
@Table( name = "wishlist")
public class WishList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;

    @OneToOne(targetEntity = User.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
        private User user;


    @Column(name = "created_date")
    private Date createdDate;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    public WishList() {
    }

    public WishList(Integer id, User user, Date createdDate, Product product) {
        Id = id;
        this.user = user;
        this.createdDate = createdDate;
        this.product = product;
    }

    public WishList(User user, Product product) {

        this.user = user;
        this.createdDate = createdDate;
        this.product = product;
    }


    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
