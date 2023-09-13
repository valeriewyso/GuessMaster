package com.example.guessmaster;
//Valerie So 20291603

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import java.util.Random;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    private TextView entityName;
    private TextView ticketsum;
    private Button guessButton;
    private EditText userIn;
    private Button btnclearContent;
    private ImageView entityImage;
    String answer;

    private int numberOfCandidateEntities;
    private Entity[ ] entities;
    String entName;
    int entNum;
    private Entity ent;
    private int tickets;
    Country usa = new Country("United States", new Date("July", 4, 1776), "Washinton D.C.", 0.1);
    Person myCreator = new Person("myCreator", new Date("September", 1, 2000),"Female", 1);
    Politician trudeau = new Politician("Justin Trudeau", new Date("December", 25, 1971), "Male", "Liberal", 0.25);
    Singer dion = new Singer("Celine Dion", new Date("March", 30, 1961), "Female", "La voix du bon Dieu", new Date("November", 6, 1981), 0.5);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guess_activity);
        guessButton = (Button) findViewById(R.id.btnGuess);
        userIn = (EditText) findViewById(R.id.guessinput);
        ticketsum = (TextView) findViewById(R.id.ticket);
        btnclearContent =(Button) findViewById(R.id.btnClear);
        entityImage = (ImageView) findViewById(R.id.entityImage);
        entityName = (TextView) findViewById(R.id.entityName);


        //new MainActivity();
        addEntity(trudeau);
        addEntity(dion);
        addEntity(myCreator);
        addEntity(usa);

        changeEntity();
        welcomeToGame(ent);

        btnclearContent.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeEntity();
            }

        }));

        guessButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playGame(ent);
            }
        });

    }

    public MainActivity() {
        numberOfCandidateEntities = 0;
        entities = new Entity[100];
        tickets = 0;
    }
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



    public void playGame(Entity entity) {
        answer = userIn.getText().toString();
        answer = answer.replace("\n", "").replace("\r", "");
        Date date = new Date(answer);

        if (date.precedes(entity.getBorn())) {
            AlertDialog.Builder laterAlert = new AlertDialog.Builder(MainActivity.this);
            laterAlert.setTitle("Incorrect");
            laterAlert.setMessage("Try a later date");
            laterAlert.setCancelable(false);
            laterAlert.setNegativeButton("Ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Toast.makeText(getBaseContext(), "Continuing", Toast.LENGTH_SHORT).show();
                }
            });
            laterAlert.show();
            userIn.getText().clear();

        } else if (entity.getBorn().precedes(date)) {
            AlertDialog.Builder earlyalert = new AlertDialog.Builder(MainActivity.this);
            earlyalert.setTitle("Incorrect");
            earlyalert.setMessage("Try an earlier date");
            earlyalert.setCancelable(false);
            earlyalert.setNegativeButton("Ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Toast.makeText(getBaseContext(), "Continuing", Toast.LENGTH_SHORT).show();
                }
            });
            earlyalert.show();
            userIn.getText().clear();
        } else {

            tickets += entity.getAwardedTicketNumber();
            ticketsum.setText("Tickets: " + tickets);
            AlertDialog.Builder correctalert = new AlertDialog.Builder(MainActivity.this);
            correctalert.setTitle("You Won!");
            correctalert.setMessage("Bingo!" + entity.closingMessage());
            correctalert.setCancelable(false);
            correctalert.setNegativeButton("Continue", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Toast.makeText(MainActivity.this, "Tickets won: " + entity.getAwardedTicketNumber(), Toast.LENGTH_SHORT).show();
                    ContinueGame();
                }
            });
            correctalert.show();
        }

    }

    public void playGame(int entityInd){

     Entity entity = entities[entityInd];
     playGame(entity);
    }

    public void playGame() {

        genRandomEntityInd();
        playGame(genRandomEntityInd());
    }

    int genRandomEntityInd(){
        Random num = new Random();

        return num.nextInt(numberOfCandidateEntities);
    }
    public void changeEntity() {
        int entNum = genRandomEntityInd();
        Entity entity = entities[entNum];
        entityName.setText(entity.getName());
        ImageSetter(entity);
        ent = entity;
    }

    public void ImageSetter(Entity entity){
        if (entity.toString().equals(trudeau.toString())){
            entityImage.setImageResource(R.drawable.justint);
        } else if (entity.toString().equals(usa.toString())){
            entityImage.setImageResource(R.drawable.usaflag);
        } else if (entity.toString().equals(myCreator.toString())){
            entityImage.setImageResource(R.drawable.img);
        } else if (entity.toString().equals(dion.toString())){
            entityImage.setImageResource(R.drawable.celidion);
        }

    }

    public void welcomeToGame(Entity entity){
        AlertDialog.Builder welcomeAlert = new AlertDialog.Builder(MainActivity.this);
        welcomeAlert.setTitle("GuessMaster Game v3");
        welcomeAlert.setMessage(entity.welcomeMessage());
        welcomeAlert.setCancelable(false);

        welcomeAlert.setNegativeButton("START GAME", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int which) {
                Toast.makeText(getBaseContext(), "Game is Starting... Enjoy", Toast.LENGTH_SHORT).show();
            }
        });
        welcomeAlert.show();
    }

    public void ContinueGame(){
        entNum = genRandomEntityInd();
        Entity entity = entities[entNum];
        userIn.getText().clear();
        entName = entity.getName();

        ImageSetter(entity);
        ent = entity;
        entityName.setText(entName);

    }
}