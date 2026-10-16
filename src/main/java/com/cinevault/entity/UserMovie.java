package com.cinevault.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "user_movie")
public class UserMovie {

    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String status;

    @Column 
    private Integer priority;

    @Column(name = "personal_rating")
    private BigDecimal personalRating;

    @Column(name = "personal_review", columnDefinition = "TEXT")
    private String personalReview;

    @Column(name = "added_at", insertable = false, updatable = false)
    private LocalDateTime addedAt;

    @UpdateTimestamp 
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @ManyToOne(optional = false)
    @JoinColumn(name = "movie_id", referencedColumnName = "id")
    private Movie movie;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private AppUser appUser;

    public UserMovie() {
    }

    public Integer getId() {
        return id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public BigDecimal getPersonalRating() {
        return personalRating;
    }

    public void setPersonalRating(BigDecimal personalRating) {
        this.personalRating = personalRating;
    }

    public String getPersonalReview() {
        return personalReview;
    }

    public void setPersonalReview(String personalReview) {
        this.personalReview = personalReview;
    }

    public LocalDateTime getAddedAt() {
        return addedAt;
    }

    public void setAddedAt(LocalDateTime addedAt) {
        this.addedAt = addedAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public AppUser getAppUser() {
        return appUser;
    }

    public void setAppUser(AppUser appUser) {
        this.appUser = appUser;
    }    
}