/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.capella.it4736;

import java.util.Date;

/**
 *
 * @author User
 */
public class Review {
    private Date date;
    private String book_rating;
    private String review;
    

    public Review(Date date, String book_rating, String review) {
        this.date = date;
        this.book_rating = book_rating;
        this.review = review;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getBookRating() {
        return book_rating;
    }

    public void setBookRating(String book_rating) {
        this.book_rating = book_rating;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    @Override
    public String toString() {
        // Use StringBuilder to create string efficiently
        StringBuilder reviewInfo = new StringBuilder();
        reviewInfo.append("\nDate: ").append(String.format("%tD", date)).append(" ");
        reviewInfo.append("\nBook Rating: ").append(book_rating).append(" \n");
        reviewInfo.append("Review: \n").append(String.format(review));
        return reviewInfo.toString();
    }
}
