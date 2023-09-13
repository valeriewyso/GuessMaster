package com.example.guessmaster;//Valerie So 20291603

public class Politician extends Person{

    private String party;

    public Politician(String name, Date born, String gender, String party, double difficulty) {
        super(name, born, gender, difficulty);
        this.party = party;

    }

    public Politician(Politician newPolitician) {
        super(newPolitician.getName(), newPolitician.getBorn(), newPolitician.getGender(), newPolitician.getDifficulty());
        this.party = newPolitician.party;
    }

    public Politician clone() {

        return new Politician(this);
    }

    public String toString() {
        return super.toString() + ". \n They are a member of the "+this.party+" party.";
    }

    public String personType() {
        return "Politician!";
    }

}
