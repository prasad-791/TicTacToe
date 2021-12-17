package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    // 0-> o 1->x
    int activeplayer = 0;
    int [][] winpos = { {0,1,2}, {3,4,5}, {6,7,8}, {0,3,6}, {1,4,7}, {2,5,8}, {0,4,8}, {2,4,6} };
    int []ispresent = {-1,-1,-1,-1,-1,-1,-1,-1,-1};

    boolean conti = true;

    public int iswin()
    {
        int i,j;
        int win = 0,draw = 1;
        for(i=0;i<winpos.length;i++)
        {
            if(ispresent[winpos[i][0]] == ispresent[winpos[i][1]]
                    && ispresent[winpos[i][1]]== ispresent[winpos[i][2]]
                    && ispresent[winpos[i][0]]!=-1){
                win = 1;
            }
        }
        if(win==1)
        {
            return 1;
        }
        for(i=0;i<ispresent.length;i++)
        {
            if(ispresent[i]==-1) {
                draw = 0;
            }
        }
        return draw == 0 ? 0 : -1 ;
    }
    public void showwinner(int winner)
    {
        TextView winplay= (TextView) findViewById(R.id.WinnerPLayer);

        String champ = "";

        if(winner == 0)
        {
            champ += "PLAYER 1 WINS :)";
        }
        else if(winner == 1)
        {
            champ += "PLAYER 2 WINS :)";
        }
        else if(winner == -1)
        {
            champ += "Its a DRAW :o";
        }

        winplay.setText(champ);
        winplay.setAlpha(1f);
        Toast.makeText(this,"You Can Click on Play Again!",Toast.LENGTH_LONG).show();
    }
    public void playAgain(View v)
    {
        TextView winplay= (TextView) findViewById(R.id.WinnerPLayer);
        winplay.setAlpha(0f);
        GridLayout ourgrid = (GridLayout)findViewById(R.id.gridLayout);

        activeplayer = 0;
        conti = true;
        for(int i=0;i<ispresent.length;i++)
        {
            ispresent[i] = -1;
        }
        for(int i=0;i<ourgrid.getChildCount();i++)
        {
            ((ImageView) ourgrid.getChildAt(i)).setImageResource(0);
        }
        TextView player1 = (TextView) findViewById(R.id.player1);
        TextView player2 = (TextView) findViewById(R.id.player2);
        player1.setAlpha(1f);
        player2.setAlpha(0.3f);
    }
    public void moveplayed(View view)
    {
        ImageView clickedimg = (ImageView)view;

        TextView player1 = (TextView) findViewById(R.id.player1);
        TextView player2 = (TextView) findViewById(R.id.player2);

        int clickedno = Integer.parseInt(clickedimg.getTag().toString());
        int won;
        int winner = -1;
        if(activeplayer==0 && ispresent[clickedno]==-1 && conti)
        {
            clickedimg.setTranslationY(-1000f);
            clickedimg.setImageResource(R.drawable.o);
            clickedimg.animate().translationYBy(1000f).rotationBy(720).setDuration(700);
            ispresent[clickedno] = 0;

            won = iswin();
            if(won==1) {
                winner = activeplayer;
                showwinner(winner);
                conti = false;
            }else if(won == -1)
            {
                showwinner(-1);
                conti = false;
            }
            activeplayer = 1;
            player1.setAlpha(0.3f);
            player2.setAlpha(1f);
        }
        else if(activeplayer==1 && ispresent[clickedno]==-1 && conti) {
            clickedimg.setTranslationY(-1000f);
            clickedimg.setImageResource(R.drawable.x);
            clickedimg.animate().translationYBy(1000f).rotationBy(720).setDuration(700);
            ispresent[clickedno] = 1;
            won = iswin();
            if(won==1) {
                winner = activeplayer;
                showwinner(winner);
                conti = false;
            }else if(won == -1)
            {
                showwinner(-1);
                conti = false;
            }
            activeplayer = 0;
            player1.setAlpha(1f);
            player2.setAlpha(0.3f);
        }

        if(winner!=-1)
        {
            System.out.println("Winner: "+winner);
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView player1 = (TextView) findViewById(R.id.player1);
        TextView player2 = (TextView) findViewById(R.id.player2);

        player1.setAlpha(1f);
        player2.setAlpha(0.3f);

        TextView winner = (TextView) findViewById(R.id.WinnerPLayer);
        winner.setAlpha(0.0f);
    }
}
