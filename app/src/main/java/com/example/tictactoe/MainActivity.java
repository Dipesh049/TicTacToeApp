package com.example.tictactoe;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    // 0 - X
    // 1 - o
    int active_player = 0;
    int[] gamestate = new int[]{2,2,2,2,2,2,2,2,2};  //2 - Null

    int[][] Winpos = new int[][]{{0,1,2},{3,4,5},{6,7,8},
                                 {0,4,8},{2,4,6},{0,3,6},
                                 {1,4,7},{2,5,8}};
    int gameactive = 1;
    int gameMode;



    public void playertap (View view){
        ImageView img = (ImageView) view;
        int tappedImage = Integer.parseInt(img.getTag().toString());
        if(gameactive!=1){
            reset(view);
        }
        if(gamestate[tappedImage] == 2 && gameactive == 1){
            gamestate[tappedImage] = active_player;
            img.setTranslationY(-1000f);
            if(active_player == 0){
                img.setImageResource(R.drawable.cross);
                active_player = 1;
                TextView status = findViewById(R.id.status);
                status.setText("o's Turn ");
            }else
            {
                img.setImageResource(R.drawable.zero);
                active_player = 0;
                TextView status = findViewById(R.id.status);
                status.setText("x's Turn ");

            }
            img.animate().translationYBy(1000f).setDuration(300);
        }
        //if all the state are filled and no one won
        for (int i = 0; i < gamestate.length; i++) {
            if(gamestate[i]==2){
                break;
            }else{
                if(i==gamestate.length-1){
                    TextView status = findViewById(R.id.status);
                    status.setText("No One has Won");

                    TextView reset = findViewById(R.id.reset);
                    reset.setText("Tap to reset");
                }
            }
        }

        //checking if win position hit

        for(int i=0;i<Winpos.length;i++){

                if( gamestate[Winpos[i][0]]!=2 && gamestate[Winpos[i][0]]==gamestate[Winpos[i][1]] &&  gamestate[Winpos[i][1]] == gamestate[Winpos[i][2]]){
                    TextView status = findViewById(R.id.status);
                    if(gamestate[Winpos[i][0]]==1) {

                        status.setText("o has Won");
                    }else{
                        status.setText("x has Won");
                    }


                    gameactive = 0;
                    TextView reset = findViewById(R.id.reset);
                    reset.setText("Tap to reset");
                }


            }

        }

        public void reset (View view){
        gameactive = 1;
        for(int i=0;i<gamestate.length;i++){
            gamestate[i] = 2;
        }
            ((ImageView) findViewById(R.id.iv1)).setImageResource(0);
            ((ImageView) findViewById(R.id.iv2)).setImageResource(0);
            ((ImageView) findViewById(R.id.iv3)).setImageResource(0);
            ((ImageView) findViewById(R.id.iv4)).setImageResource(0);
            ((ImageView) findViewById(R.id.iv5)).setImageResource(0);
            ((ImageView) findViewById(R.id.iv6)).setImageResource(0);
            ((ImageView) findViewById(R.id.iv7)).setImageResource(0);
            ((ImageView) findViewById(R.id.iv8)).setImageResource(0);
            ((ImageView) findViewById(R.id.iv9)).setImageResource(0);

            TextView reset = findViewById(R.id.reset);
            reset.setText("");

            TextView status = findViewById(R.id.status);
            status.setText("Tap to play");
        }






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SharedPreferences sharedPreferences = getSharedPreferences("mode",MODE_PRIVATE);
        gameMode = sharedPreferences.getInt("mode",0);
        Log.d("mode",String.valueOf(gameMode));


    }

}