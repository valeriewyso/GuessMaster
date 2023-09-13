package com.example.guessmaster;//Valerie So 20291603

public class Singer extends  Person{

    private String debutAlbum;
    private Date debutAlbumReleaseDate;

    public Singer(Singer newSinger){
        super(newSinger.getName(), newSinger.getBorn(), newSinger.getGender(), newSinger.getDifficulty());
        this.debutAlbum = newSinger.debutAlbum;
        this.debutAlbumReleaseDate = newSinger.debutAlbumReleaseDate;
    }

    public Singer(String name, Date born, String gender, String debutAlbum, Date debutAlbumReleaseDate, double difficulty) {
        super(name, born, gender, difficulty);
        this.debutAlbum = debutAlbum;
        this.debutAlbumReleaseDate = debutAlbumReleaseDate;

    }

    public Singer clone() {

        return new Singer(this);
    }

    public String toString() {
        return super.toString()+"\n Their debut album was "+this.debutAlbum+".\n " +
                "It was released on "+this.debutAlbumReleaseDate.getDay()+" "+this.debutAlbumReleaseDate.getMonth()+", "+this.debutAlbumReleaseDate.getYear();
    }

    public String personType() {
        return "Singer";
    }

}
