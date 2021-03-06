package rs.systempro.igramemorije;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Timer;
import java.util.TimerTask;

import static rs.systempro.igramemorije.R.id.glMreza;



public class GameActivity extends AppCompatActivity {

    int height;
    int width;
    int h;
    int w;
    int solved=0;
    ArrayList<Drawable> images;
    ArrayList<ImageButton> lista = new ArrayList<ImageButton>();
    int time=0;
    java.util.Timer ti = new java.util.Timer();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        final TextView tv = (TextView) findViewById(R.id.tvtime);

        ti.schedule(new TimerTask()
        {
            @Override
            public void run()
            {
                time++;
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        tv.setText(time+"s");
                    }
                });

            }
        },1000,1000);


        Intent myIntent = getIntent();
        h = myIntent.getIntExtra("height", 4);
        w = myIntent.getIntExtra("width", 3);
        images= new ArrayList<>();
        for(int i=0; i<(h*w)/2;i++)
        {

            int resID= getResources().getIdentifier("slicica"+(i+1),"drawable",getPackageName());
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
            {
                images.add(getDrawable(resID));
                images.add(getDrawable(resID));
            }
            else
            {
                images.add(getResources().getDrawable(resID));
                images.add(getResources().getDrawable(resID));
            }
        }
        Collections.shuffle(images);
        final RelativeLayout rlRoot = (RelativeLayout) findViewById(R.id.rlRoot);
        final GridLayout glMreza = (GridLayout) findViewById(R.id.glMreza);
        glMreza.setColumnCount(w);
        rlRoot.post(new Runnable() {
            @Override
            public void run() {
                height = rlRoot.getHeight();
                width = rlRoot.getWidth();

                Toast.makeText(getApplicationContext(), height+" "+width, Toast.LENGTH_SHORT).show();

                for (int i = 0; i < h * w; i++) {
                    final int image=i;
                    final ImageButton b = new ImageButton(getApplicationContext());
                    b.setBackgroundColor(Color.RED);
                    //b.setImageDrawable(images.get(i));
                    b.setScaleType(ImageView.ScaleType.FIT_CENTER);
                    b.setPadding(0,0,0,0);
                    glMreza.addView(b);
                    GridLayout.LayoutParams param = new GridLayout.LayoutParams();
                    if (height/h < width/w) {
                        param.height = height / h - 20;
                        param.width = height / h - 20;
                    }
                    else
                    {
                        param.height = width / w - 20;
                        param.width = width / w - 20;
                    }

                        param.setMargins(10,10,10,10);
                    b.setLayoutParams(param);
                    b.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            if(lista.contains(b))
                                return;




                            if(lista.size()==1)
                            {
                                b.setImageDrawable(images.get(image));
                                lista.add(b);
                                if(lista.get(0).getDrawable().getConstantState().equals(lista.get(1).getDrawable().getConstantState()))
                                {
                                    solved+=2;
                                    Toast.makeText(GameActivity.this, solved + " " + (h*w), Toast.LENGTH_SHORT);
                                    for(ImageButton but : lista)
                                    {
                                        but.setClickable(false);
                                    }
                                    lista.clear();
                                    if(solved == h*w) {
                                        ti.cancel();
                                        Intent intent = new Intent(getApplicationContext() ,GameOver.class);
                                        intent.putExtra("time",time+"s");
                                        startActivity(intent);
                                        finish();
                                    }

                                }
                            }
                            else




                            if(lista.size()==2)
                            {

                                    lista.get(0).setImageDrawable(null);
                                    lista.get(1).setImageDrawable(null);
                                    lista.clear();

                                b.setImageDrawable(images.get(image));
                                lista.add(b);
                            }else
                            if(lista.size()==0)
                            {
                                b.setImageDrawable(images.get(image));
                                lista.add(b);

                            }


                        }

                    });

                }
            }

        });


    }

//    @Override
//    protected void onResume() {
//
//
//        super.onResume();
//
//        DisplayMetrics displayMetrics = new DisplayMetrics();
//        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
//        //int height = displayMetrics.heightPixels;
//        //int width = displayMetrics.widthPixels;
//        //height=findViewById(R.id.rlRoot).getHeight();
//        //width=findViewById(R.id.rlRoot).getWidth();
//        //Toast.makeText(this, height+" "+width, Toast.LENGTH_SHORT).show();
//
//
//        if(glMreza.getChildCount()!=0) {
//            return;
//        }
//
//
//
//    }
}