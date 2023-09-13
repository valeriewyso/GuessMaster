package com.example.guessmaster;//Valerie So 20291603

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;
import java.util.Scanner;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class GuessMaster{

    private static int numberOfCandidateEntities = 0;
    private static Entity[ ] entities = new Entity[5];

    private int tickets = 0;
    public void addEntity(Entity entity){
        if(numberOfCandidateEntities == 0){
            Entity[] newEntity = {entity.clone()};
            entities[numberOfCandidateEntities] = entity;
            numberOfCandidateEntities++;
        } else if (equals(entity)){
            System.out.println("Existing Entity");
        }
        else{
            Entity[] newEntity = {entity.clone()};
            entities[numberOfCandidateEntities] = entity;
            numberOfCandidateEntities++;
        }
    }

    public void playGame(Entity entity){

        System.out.println(entity.welcomeMessage());
        System.out.println("Guess " + entity.getName() + "'s birthday.");
        System.out.println("(mm/dd/yyyy)");

        boolean repeat = true;

        while(repeat){

            Scanner keyboard = new Scanner(System.in);
            String line = keyboard.nextLine().replace("\n", "").replace("\r", "");


            if(line.equals("quit")) {
                System.exit(0);
            }

            Date dateEntered = new Date(line);
            Date test = entity.getBorn();

            //Check the date for before, after, or correct value
            if(dateEntered.suceeds(test)) {
                System.out.println("Incorrect. Try an earlier date.");
            }
            if(dateEntered.precedes(test)) {
                System.out.println("Incorrect. Try a later date.");
            }
            if(dateEntered.equals(test)) {
                System.out.println("*************Bingo!***************");
                tickets += entity.getAwardedTicketNumber();
                System.out.println("You won " + entity.getAwardedTicketNumber() + " tickets in this round.");
                System.out.println("The total number of your tickets is " + tickets);
                System.out.println("**********************************");
                System.out.println(entity.closingMessage());
                entity.toString();
                break;
            }
        }
    }

    public void playGame(int entityInd){

        if(entities[entityInd] == null){
            playGame();
        }
        else{
            playGame(entities[entityInd]);
        }
    }

    public void playGame() {

        genRandomEntityInd();

        while(true) {
            playGame(genRandomEntityInd());
        }

    }

    int genRandomEntityInd(){
        Random num = new Random();

        return num.nextInt(numberOfCandidateEntities);
    }

}

