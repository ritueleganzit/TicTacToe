package com.eleganzit.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button[][] button=new Button[3][3];
    boolean playeroneturn=true;
    int roundcount=0;
    int playeronepoints=0;
    int playertwopoints=0;
    TextView playerone,playertwo;
    Button resetbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        playerone=findViewById(R.id.textViewPlayer1);
        playertwo=findViewById(R.id.textViewPlayer2);
        resetbtn=findViewById(R.id.button_reset);

        button[0][0]=findViewById(R.id.button_00);
        button[0][1]=findViewById(R.id.button_01);
        button[0][2]=findViewById(R.id.button_02);
        button[1][0]=findViewById(R.id.button_10);
        button[1][1]=findViewById(R.id.button_11);
        button[1][2]=findViewById(R.id.button_12);
        button[2][0]=findViewById(R.id.button_20);
        button[2][1]=findViewById(R.id.button_21);
        button[2][2]=findViewById(R.id.button_22);

        for (int i=0;i<3;i++)
        {
            for (int j=0;j<3;j++)
            {
                button[i][j].setOnClickListener(this);
            }
        }
        resetbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetgame();
            }
        });

    }

    @Override
    public void onClick(View v) {
        Button b=(Button)v;
        if (!(b.getText().toString().equalsIgnoreCase("")))
        {
            return;
        }  if (playeroneturn)
        {
            b.setText("X");
        }
        else
        {
            b.setText("O");
        }
        roundcount++;

        if (checkForWin())
        {
            if (playeroneturn)
            {
                playeronewins();
            }
            else
            {
                playertwowins();
            }
        }
        else
        {
            if (roundcount==9)
            {
                draw();
            }
            else
            {
                playeroneturn=!playeroneturn;
            }
        }
    }



    private boolean checkForWin()
{
    String[][] field=new String[3][3];
    for (int i=0;i<3;i++)
    {
        for (int j=0;j<3;j++)
        {
            field[i][j]=button[i][j].getText().toString();

        }
    }
    for (int i=0;i<3;i++)
    {
        if (field[i][0].equals(field[i][1]) && field[i][0].equals(field[i][2]) && !field[i][0].equals(""))
        {
            return true;
        }
    }



    for (int i=0;i<3;i++)
    {
        if (field[0][i].equals(field[1][i]) && field[0][i].equals(field[2][i]) && !field[0][i].equals(""))
        {
            return true;
        }
    }
    if (field[0][0].equals(field[1][1]) && field[0][0].equals(field[2][2]) && !field[0][0].equals(""))
    {
        return true;
    }
     if (field[0][2].equals(field[1][1]) && field[0][2].equals(field[2][0]) && !field[0][2].equals(""))
    {
        return true;
    }

     return false;


}
    private void playeronewins() {
        playeronepoints++;
        Toast.makeText(this, "Player One Wins", Toast.LENGTH_SHORT).show();
        updatepointtext();
        resetboard();

    }   private void playertwowins() {
        playertwopoints++;
        Toast.makeText(this, "Player two Wins", Toast.LENGTH_SHORT).show();
        updatepointtext();
        resetboard();

    }

    private void updatepointtext() {
        playerone.setText("Player 1 "+playeronepoints);
        playertwo.setText("Player 2 "+playertwopoints);
    }
private void resetboard() {
        for(int i=0;i<3;i++)
        {
            for(int j=0;j<3;j++)
            {
                button[i][j].setText("");
            }

        }
    roundcount=0;
    playeroneturn=true;
    }

    private void resetgame(){
        playertwopoints=0;
        playeronepoints=0;
        updatepointtext();
        resetboard();

    }
    private void draw() {

        Toast.makeText(this, "Match Tie", Toast.LENGTH_SHORT).show();
        resetboard();

    }
}
