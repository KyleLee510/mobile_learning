package com.example.gameanimals;

import android.app.Activity;
import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.String;
import java.util.*;

public class MainActivity extends AppCompatActivity {

    String animals_nams[] = {"bear", "bird", "cat", "fish", "flower", "honey", "house", "elephant", "giraffe", "hippo", "kangaroo",
        "leo", "lion", "rhino", "wolf", "tiger", "pig"}; //总计17个
    static String four_images[] = {"","","",""};
    static int score = 0;
    static String score_String = String.valueOf(score);
    static int logout = 0;
    static String answer = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        chooseLayout();
    }

    public void chooseLayout() {
        int orientation = getResources().getConfiguration().orientation;
        switch (logout) {
            case 0: {
                if (orientation == Configuration.ORIENTATION_PORTRAIT) {
                    setContentView(R.layout.log_layout);
                } else if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
                    setContentView(R.layout.log_landscape_layout);
                }
                break;
            }
            case 1: {
                if (orientation == Configuration.ORIENTATION_PORTRAIT) {
                    setContentView(R.layout.activity_main);
                    maintain();
                } else if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
                    setContentView(R.layout.activity_main_landscpae_layout);
                    maintain();
                }
                break;
            }
            case 2: {
                if (orientation == Configuration.ORIENTATION_PORTRAIT) {
                    setContentView(R.layout.over_layout);
                    ((TextView) findViewById(R.id.score_TextView)).setText(score_String);

                } else if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
                    setContentView(R.layout.over_landscape_layout);
                    ((TextView) findViewById(R.id.score_TextView)).setText(score_String);
                }
                break;
            }
        }
    }


    //选择四个作为游玩项目
    public String[] chooseImages() {
        String animals[] = {"","","",""};
        int len = 16-0+1;

        //初始化给定范围的待选数组
        int[] source = new int[len];
        for (int i = 0; i < 0+len; i++){
            source[i-0] = i;
        }

        int[] result = new int[4];
        Random rd = new Random();
        int index = 0;
        for (int i = 0; i < result.length; i++) {
            //待选数组0到(len-2)随机一个下标
            index = Math.abs(rd.nextInt() % len--);
            //将随机到的数放入结果集
            result[i] = source[index];
            //将待选数组中被随机到的数，用待选数组(len-1)下标对应的数替换
            source[index] = source[len];
        }

        //Random random = new Random();
        int i = 0, j = 0;
        while (j < 4) {
            i = result[j];
            //i = random.nextInt(16);
            animals[j] = animals_nams[i];
            j++;
        }
        return animals;
    }
    //选择一个作为本次游戏的正确结果
    public String chooseAnswer(String[] animals) {
        Random random = new Random();
        answer = animals[random.nextInt(3)];
        return answer;
    }
    //检查玩家提交的答案是否与正确结果相同
    public boolean checkAnswer(int i) {
        if (four_images[i] == answer) {
            Toast.makeText(this, "OK", Toast.LENGTH_SHORT).show();
            return true;
        }
        else {
            return false;
        }
    }
    public void upDate(ImageView iconImageView, String animale_name) {
        switch(animale_name) {
            case "bear":
                iconImageView.setImageResource(R.drawable.bear);
                break;
            case "bird":
                iconImageView.setImageResource(R.drawable.bird);
                break;
            case "cat":
                iconImageView.setImageResource(R.drawable.cat);
                break;
            case "fish":
                iconImageView.setImageResource(R.drawable.fish);
                break;
            case "flower":
                iconImageView.setImageResource(R.drawable.flower);
                break;
            case "honey":
                iconImageView.setImageResource(R.drawable.honey);
                break;
            case "house":
                iconImageView.setImageResource(R.drawable.house);
                break;
            case "elephant":
                iconImageView.setImageResource(R.drawable.elephant);
                break;
            case "giraffe":
                iconImageView.setImageResource(R.drawable.giraffe);
                break;
            case "hippo":
                iconImageView.setImageResource(R.drawable.hippo);
                break;
            case "kangaroo":
                iconImageView.setImageResource(R.drawable.kangaroo);
                break;
            case "leo":
                iconImageView.setImageResource(R.drawable.leo);
                break;
            case "lion":
                iconImageView.setImageResource(R.drawable.lion);
                break;
            case "rhino":
                iconImageView.setImageResource(R.drawable.rhino);
                break;
            case "wolf":
                iconImageView.setImageResource(R.drawable.wolf);
                break;
            case "tiger":
                iconImageView.setImageResource(R.drawable.tiger);
                break;
            case "pig":
                iconImageView.setImageResource(R.drawable.pig);
                break;
            default:
                break;
        }
    }

    public void maintain() {
        upDate((ImageView)findViewById(R.id.First_image),four_images[0]);
        upDate((ImageView)findViewById(R.id.Second_image),four_images[1]);
        upDate((ImageView)findViewById(R.id.Third_image),four_images[2]);
        upDate((ImageView)findViewById(R.id.Fourth_image),four_images[3]);
        ((TextView) findViewById(R.id.name_TextView)).setText(answer);
        ((TextView) findViewById(R.id.score_TextView)).setText(score_String);

    }

    public void refresh() {
        four_images = chooseImages(); //四个图片
        upDate((ImageView)findViewById(R.id.First_image),four_images[0]);
        upDate((ImageView)findViewById(R.id.Second_image),four_images[1]);
        upDate((ImageView)findViewById(R.id.Third_image),four_images[2]);
        upDate((ImageView)findViewById(R.id.Fourth_image),four_images[3]);
        answer = chooseAnswer(four_images); //答案
        ((TextView) findViewById(R.id.name_TextView)).setText(answer);
    }

    public void dealResult(boolean result) {
        //String score_String = String.valueOf(score);
        if (result == true) {
            score += 1;
            score_String = String.valueOf(score);
            ((TextView) findViewById(R.id.score_TextView)).setText(score_String);
            //logout = 1;
            //chooseLayout();
            refresh();
        }
        else {
            logout = 2;
            chooseLayout();
            ((TextView) findViewById(R.id.score_TextView)).setText(score_String);

        }
    }


    public void btnClick(View view) {
        logout = 1;
        chooseLayout();
        score = 0;
        score_String = String.valueOf(score);
        ((TextView) findViewById(R.id.score_TextView)).setText(score_String);
        refresh();
    }

    public void btnExitClick(View view) {
        this.finish();
        System.exit(0);
    }

    public void click_First(View view) {
        boolean result = checkAnswer(0);
        dealResult(result);
    }

    public void click_Second(View view) {
        boolean result = checkAnswer(1);
        dealResult(result);

    }

    public void click_Third(View view) {
        boolean result = checkAnswer(2);
        dealResult(result);

    }

    public void click_Fourth(View view) {
        boolean result = checkAnswer(3);
        dealResult(result);
    }

}
