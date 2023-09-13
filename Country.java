package com.example.guessmaster;//Valerie So 20291603

public class Country extends Entity{

    private String capital;

    public Country(String name, Date born, String capital, double difficulty) {

        super(name, born, difficulty);
        this.capital = capital;

    }

    public Country(Country newCountry){

        super(newCountry.getName(), newCountry.getBorn(), newCountry.getDifficulty());
        this.capital = newCountry.capital;
    }


    public Country clone() {
        return new Country(this);
    }

    public String toString(){
        return super.toString() + "Their capital city is " + this.capital;
    }

    public String entityType(){
        return "country!";
    }
}
