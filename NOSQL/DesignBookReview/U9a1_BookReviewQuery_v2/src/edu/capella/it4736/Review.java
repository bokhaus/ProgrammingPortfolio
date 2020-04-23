/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.capella.it4736;

import java.util.Date;

/**
 *
 * @author BrianBok
 */

public class Review {
    private Date date;
    private String book_rating;
    private String review;
    private String reviewer_id;

    public Review(Date date, String reviewer_id, String book_rating, String review) {
        this.date = date;
        this.book_rating = book_rating;
        this.review = review;
        this.reviewer_id = reviewer_id;
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
    
    public String getReviewerID() {
        return reviewer_id;
    }

    public void setReviewerID(String reviewer_id) {
        this.reviewer_id = reviewer_id;
    }
    
    @Override
    public String toString() {
        // Use StringBuilder to create string efficiently
        StringBuilder reviewInfo = new StringBuilder();
        reviewInfo.append("Review Date: ").append(String.format("%tD", date)).append(" ");
        reviewInfo.append("\n Book Rating: ").append(book_rating).append(" \n");
        reviewInfo.append(" Review: \n").append(String.format(" " + review));
        
        
        return reviewInfo.toString();
    }
}
