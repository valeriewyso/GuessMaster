package com.example.guessmaster;

import org.w3c.dom.ls.LSOutput;
//Valerie So 20291603
public abstract class Entity {

    private String name;
    private Date born;

    private double difficulty;
    public Entity() {
        this.name = "";
        this.born = new Date();
        this.difficulty = 0.0;
    }

    public Entity(String name, Date born, double difficulty) {
        this.name = name;
        this.born = new Date(born);
        this.difficulty = difficulty;
    }

    //copy constructor
    public Entity(Entity newEntity) {
        this.name = newEntity.name;
        this.born = new Date(newEntity.born);
        this.difficulty = newEntity.difficulty;
    }
    public String getName() {
        return this.name;
    }

    public Date getBorn(){
        return new Date(this.born);
    }
    public void setName(String name) {
        this.name = name;
    }

    public void setBorn(Date born) {
        this.born = new Date(born);
    }

    public double getDifficulty() {
        return this.difficulty;
    }
    public String toString() {

        return ("\nName: " + this.name + "\nBorn at: " +this.born.toString() +"\n");
    }

    public int getAwardedTicketNumber(){

        double numOfTickets = this.difficulty*100;
        return (int) numOfTickets;
    }

    public abstract String entityType();

    public abstract Entity clone();

    public String welcomeMessage() {
        return "Welcome! Let’s start the game! This entity is a " +this.entityType()+"\n";
    }

    public String closingMessage(){
        return "Congratulations! The detailed information of the entity you guess is: " + this.toString();
    }
    public boolean equals(Entity entity) {

        return entity != null;

    }

}

