package com.example.guessmaster;//Valerie So 20291603

public class Person extends Entity{

    private String gender;

    public String getGender() {
        return this.gender;
    }
    public Person(String name, Date born, String gender, double difficulty) {

        super(name, born, difficulty);
        this.gender = gender;

    }

    public Person(Person newPerson){
        super(newPerson.getName(), newPerson.getBorn(), newPerson.getDifficulty());
        this.gender = newPerson.gender;
    }


    public Person clone() {
        return new Person(this);
    }

    public String toString(){
        return super.toString() + "Their gender is " +this.gender + "\n";
    }

    public String entityType(){
        return "person!";
    }
}

