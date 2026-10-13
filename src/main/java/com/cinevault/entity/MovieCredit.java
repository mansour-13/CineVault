package com.cinevault.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.GenerationType;
import jakarta.persistence.JoinColumn;

@Entity 
@Table(name = "movie_credit")
public class MovieCredit {

    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column (name = "character_name", length = 100)
    private String characterName;

    @Column (name = "credit_order")
    private Integer creditOrder;

    @ManyToOne
    @JoinColumn(name = "movie_id", referencedColumnName = "id")
    private Movie movie;

    @ManyToOne
    @JoinColumn(name = "person_id", referencedColumnName = "id")
    private Person person;

    @ManyToOne 
    @JoinColumn (name = "role_id", referencedColumnName = "id")
    private CreditRole role; 

    public MovieCredit() {
    }

    public Integer getId() {
        return id;
    }

    public String getCharacterName() {
        return characterName;
    }

    public void setCharacterName(String characterName) {
        this.characterName = characterName;
    }

    public Integer getCreditOrder() {
        return creditOrder;
    }

    public void setCreditOrder(Integer creditOrder) {
        this.creditOrder = creditOrder;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public CreditRole getRole() {
        return role;
    }

    public void setRole(CreditRole role) {
        this.role = role;
    }
    
}   
