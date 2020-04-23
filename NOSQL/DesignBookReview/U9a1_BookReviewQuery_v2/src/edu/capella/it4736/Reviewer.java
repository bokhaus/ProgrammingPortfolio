/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.capella.it4736;

import java.util.ArrayList;

/**
 *
 * @author BrianBok
 */

class Reviewer {
    private String book_id;
    private String user_id;
    private String user_name;
    private int book_review_rating;
    private int num_of_reviews;
    private int reviewer_rating;
    ArrayList<Reviewer> reviewer;

    public Reviewer(String book_id, String user_id, String user_name, int num_of_reviews, int reviewer_rating) {
        this.book_id = book_id;
        this.user_id = user_id;
        this.user_name = user_name;
        this.num_of_reviews = num_of_reviews;
        this.reviewer_rating = reviewer_rating;
    }

    public String getBook_id() {
        return book_id;
    }

    public void setBook_id(String book_id) {
        this.book_id = book_id;
    }
    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public int getBookReviewRating() {
        return book_review_rating;
    }

    public void setBookReviewRating(int book_review_rating) {
        this.book_review_rating = book_review_rating;
    }
    public String getUserName() {
        return user_name;
    }

    public void setPageCount(String user_name) {
        this.user_name = user_name;
    }

    public int getNumOfReviews() {
        return num_of_reviews;
    }

    public void setNumOfReviews(int num_of_reviews) {
        this.num_of_reviews = num_of_reviews;
    }
    
    public void setReviewRating(int reviewer_rating){
        this.reviewer_rating = reviewer_rating;
    }

    public int getReviewRating() {
        return reviewer_rating;
    } 
        public ArrayList<Reviewer> getReviewer() {
        return reviewer;
    }

    public void addReviewer(String book_id, String user_id, String user_name,  int num_of_reviews, int reviewer_rating) {
        this.reviewer.add(new Reviewer(book_id, user_id, user_name, num_of_reviews, reviewer_rating));
    }
   
}
