package rs.systempro.igramemorije;

import android.content.Intent;
import android.graphics.Point;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.Toast;

public class GameActivity extends AppCompatActivity {

    int h;
    int w;
    DisplayMetrics displayMetrics = new DisplayMetrics();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        Intent myIntent = getIntent();
        h = myIntent.getIntExtra("height", 4);
        w = myIntent.getIntExtra("width", 3);


    }

    @Override
    protected void onResume() {


        super.onResume();

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int height = displayMetrics.heightPixels;
        int width = displayMetrics.widthPixels;
        height=findViewById(R.id.rlRoot).getHeight();
        width=findViewById(R.id.rlRoot).getWidth();
        Toast.makeText(this, height+" "+width, Toast.LENGTH_SHORT).show();
        GridLayout glMreza = (GridLayout) findViewById(R.id.glMreza);

        if(glMreza.getChildCount()!=0) {
            return;
        }

        glMreza.setColumnCount(w);
        for (int i = 0; i < h * w; i++) {
            Button b = new Button(this);
            b.setText("Dugme " + i);
            glMreza.addView(b);
            GridLayout.LayoutParams param = new GridLayout.LayoutParams();


            if(displayMetrics.heightPixels!=0) {
                param.height = height/h;
            }
            else
                param.height = 100;
            if(displayMetrics.widthPixels!=0) {
                param.width = width/w;
            }
            else
                param.width = 100;
            param.setGravity(Gravity.FILL);
            b.setLayoutParams(param);
        }
    }
}