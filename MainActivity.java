package com.example.paudeljenishspeedtactoe;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Starts the timer for the buttons from the start ---------------
        timerforbutton();

    }

    InstructionScreen insScr = new InstructionScreen();

//Board set-up ------------------------
    int board[][] = new int[3][3];
    int turn =0;
    //Important for turns ---------------------
    boolean ready=false;
    boolean leftplayerfirst=false;
    boolean rightplayerfirst=false;

// Method for the timer -------------------------------------
    public void timerforbutton() {
        int timer = (int) (Math.random() * 5000 + 500);
        new CountDownTimer(timer, 100) {

            public void onTick(long millisUntilFinished) {
                TextView notice = (TextView) findViewById(R.id.notices);
                notice.setText("Wait till it's green");
            }
            public void onFinish() {
                TextView notice = (TextView) findViewById(R.id.notices);
                ImageView left = (ImageView) findViewById(R.id.left);
                ImageView right = (ImageView) findViewById(R.id.right);

                left.setImageResource(R.drawable.greenleftturn);
                right.setImageResource(R.drawable.greenrightturn);
                notice.setText("It's green!");
                ready=true;
            }

        }.start();
    }

    //Left player's turn --------------------------------------------
    public void leftClick(View view){
        ImageView zeldaturn = (ImageView) findViewById(R.id.zeldaturn);
        ImageView sonicturn = (ImageView) findViewById(R.id.sonicturn);
        TextView notice = (TextView) findViewById(R.id.notices);
        if(ready==false) {
            //Dialog box -------------------------------------------------------
            new AlertDialog.Builder(this)

                    .setTitle("Careful!")
                    .setMessage("Do not press the button when it's red." +
                            "\nyour opponent gets a free turn.")
                    .setPositiveButton("Close", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            turn = 2;
                            zeldaturn.setAlpha(50);



                        }
                    }).show();
            leftplayerfirst=false;
            rightplayerfirst=true;
        }
        else {
            //they are first
            ready =false;
            turn =1;
            sonicturn.setAlpha(100);
            leftplayerfirst=true;
            rightplayerfirst=false;
            notice.setText("Left player's turn");

        }
    }

    //Right player's turn ---------------------------------------------------------------
    public void rightClick(View view){
        ImageView zeldaturn = (ImageView) findViewById(R.id.zeldaturn);
        ImageView sonicturn = (ImageView) findViewById(R.id.sonicturn);
        if(ready==false) {
            new AlertDialog.Builder(this)
                    //The title on the Dialog
                    .setTitle("Warning!")
                    //The message that will appear
                    .setMessage("Do not press the button when it's red." +
                            "\nyour opponent gets a free turn.")
                    //What to do if the button is pressed
                    .setPositiveButton("Close", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            turn = 1;
                            sonicturn.setAlpha(50);
                            ImageView left = (ImageView) findViewById(R.id.left);
                            ImageView right = (ImageView) findViewById(R.id.right);
                            TextView notice = (TextView) findViewById(R.id.notices);

                        }
                    }).show();
            leftplayerfirst=true;
            rightplayerfirst=false;
        }
        else {
            //they are first
            ready =false;
            turn =2;
            zeldaturn.setAlpha(50);
            leftplayerfirst=false;
            rightplayerfirst=true;

        }
    }

//Placing an image ---------------------------------------------------------
    public void flip(ImageView i) {
        // Changes buttons back to red -------------------------------------
        ImageView left = (ImageView) findViewById(R.id.left);
        ImageView right = (ImageView) findViewById(R.id.right);
        left.setImageResource(R.drawable.redleftturn);
        right.setImageResource(R.drawable.redrightturn);

        //Makes top icons back to full opacity -------------------------------
        ImageView zeldaturn = (ImageView) findViewById(R.id.zeldaturn);
        ImageView sonicturn = (ImageView) findViewById(R.id.sonicturn);
        TextView notice = (TextView) findViewById(R.id.notices);
        sonicturn.setAlpha(-10000);
        zeldaturn.setAlpha(-10000);
        if (turn == 1 && leftplayerfirst==true) {
            i.setImageResource(R.drawable.zelda);
            notice.setText("Left player's turn");

        } else if(turn==2 && rightplayerfirst==true){
            i.setImageResource(R.drawable.sonic);
            notice.setText("Right player's turn");

        } else {
            Toast.makeText(getApplicationContext(), "Can't place right now!", Toast.LENGTH_SHORT).show();
        }
        leftplayerfirst=false;
        rightplayerfirst=false;
        ready=false;


    }



    public void aClick(View view) {


        if (board[0][0] == 0) {
            ImageView i = (ImageView) findViewById(R.id.a);
            board[0][0] = turn;
            flip(i);

        } else {
            Toast.makeText(getApplicationContext(), "Place already taken", Toast.LENGTH_SHORT).show();
        }
        win();
    }

    public void bClick(View view) {
        if (board[0][1] == 0) {
            ImageView i = (ImageView) findViewById(R.id.b);
            board[0][1] = turn;
            flip(i);
        } else {
            Toast.makeText(getApplicationContext(), "Place already taken", Toast.LENGTH_SHORT).show();
        }
        win();
    }

    public void cClick(View view) {
        if (board[0][2] == 0) {
            ImageView i = (ImageView) findViewById(R.id.c);
            board[0][2] = turn;
            flip(i);
        } else {
            Toast.makeText(getApplicationContext(), "Place already taken", Toast.LENGTH_SHORT).show();
        }
        win();
    }

    public void dClick(View view) {
        if (board[1][0] == 0) {
            ImageView i = (ImageView) findViewById(R.id.d);
            board[1][0] = turn;
            flip(i);
        } else {
            Toast.makeText(getApplicationContext(), "Place already taken", Toast.LENGTH_SHORT).show();
        }
        win();
    }

    public void eClick(View view) {
        if (board[1][1] == 0) {
            ImageView i = (ImageView) findViewById(R.id.e);
            board[1][1] = turn;
            flip(i);
        } else {
            Toast.makeText(getApplicationContext(), "Place already taken", Toast.LENGTH_SHORT).show();
        }
        win();
    }


    public void fClick(View view) {
        if (board[1][2] == 0) {
            ImageView i = (ImageView) findViewById(R.id.f);
            board[1][2] = turn;
            flip(i);
        } else {
            Toast.makeText(getApplicationContext(), "Place already taken", Toast.LENGTH_SHORT).show();
        }
        win();
    }


    public void gClick(View view) {
        if (board[2][0] == 0) {
            ImageView i = (ImageView) findViewById(R.id.g);
            board[2][0] = turn;
            flip(i);
        } else {
            Toast.makeText(getApplicationContext(), "Place already taken", Toast.LENGTH_SHORT).show();
        }
        win();
    }


    public void hClick(View view) {
        if (board[2][1] == 0) {
            ImageView i = (ImageView) findViewById(R.id.h);
            board[2][1] = turn;
            flip(i);
        } else {
            Toast.makeText(getApplicationContext(), "Place already taken", Toast.LENGTH_SHORT).show();
        }
        win();
    }


    public void iClick(View view) {
        if (board[2][2] == 0) {
            ImageView i = (ImageView) findViewById(R.id.i);
            board[2][2] = turn;
            flip(i);
        } else {
            Toast.makeText(getApplicationContext(), "Place already taken", Toast.LENGTH_SHORT).show();
        }
        win();
    }

    public void win() {
        int winner = 0;
        if (board[0][0] == board[0][1] && board[0][0] == board[0][2] && board[0][0] != 0)
            winner = board[0][0];
        else if (board[1][0] == board[1][1] && board[1][0] == board[1][2] && board[1][0] != 0)
            winner = board[1][0];
        else if (board[2][0] == board[2][1] && board[2][0] == board[1][2] && board[2][0] != 0)
            winner = board[2][0];
        else if (board[0][0] == board[1][1] && board[0][0] == board[2][2] && board[0][0] != 0)
            winner = board[0][0];
        else if (board[0][2] == board[1][1] && board[0][2] == board[2][0] && board[0][2] != 0)
            winner = board[0][2];
        else if (board[0][0] == board[1][0] && board[0][0] == board[2][0] && board[0][0] != 0)
            winner = board[0][0];
        else if (board[0][1] == board[1][1] && board[0][1] == board[2][1] && board[0][1] != 0)
            winner = board[0][1];
        else if (board[0][2] == board[1][2] && board[0][2] == board[2][2] && board[0][2] != 0)
            winner = board[0][2];
        else if (board[0][0] != 0 && board[0][1] != 0 && board[0][2] != 0 &&
                board[1][0] != 0 && board[1][1] != 0 && board[1][2] != 0 &&
                board[2][0] != 0 && board[2][1] != 0 && board[2][2] != 0)
            winner = 3;
        if (winner == 1) {
            startActivity(new Intent(MainActivity.this, InstructionScreen.class));
            Toast.makeText(getApplicationContext(), "Zelda Wins!", Toast.LENGTH_SHORT).show();
            insScr.stopMusic();
        } else if (winner == 2) {
            startActivity(new Intent(MainActivity.this, InstructionScreen.class));
            Toast.makeText(getApplicationContext(), "Sonic Wins", Toast.LENGTH_SHORT).show();
            insScr.stopMusic();
        } else if (winner == 3) {
            startActivity(new Intent(MainActivity.this, InstructionScreen.class));
            Toast.makeText(getApplicationContext(), "It's a draw! game", Toast.LENGTH_SHORT).show();
            insScr.stopMusic();

        }
        timerforbutton();
    }
}
