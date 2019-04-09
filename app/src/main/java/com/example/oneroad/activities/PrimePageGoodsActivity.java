package com.example.oneroad.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.oneroad.R;

public class PrimePageGoodsActivity extends BaseActivity {

    private TextView name,introduce,more;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prime_page_goods);

        name = (TextView) findViewById(R.id.name);
        introduce = (TextView) findViewById(R.id.introduce);
        more = (TextView) findViewById(R.id.more);

        name.setText("非遗皮影戏——舞动的艺术");

        introduce.setText("    皮影，又被称作是“影子戏”，通常是用纸板做成人或者动物或者其他现状的东西，用来表演民间故事。其表演形式较为独特。表演者都在幕后，在操作皮影的同时用当地流行的曲调来讲解故事，同时用打击乐和弦乐来配合完成，有着浓厚的乡土气息。皮影戏在多个地方都有流行，由于各地曲调不同，所以各有特色。");

        more.setText("一、腾冲皮影戏的渊源\n" +
                "据了解，腾冲皮影戏是在明朝初年由江南、湖广等地的移民传入腾冲。其造型独特、古朴、夸张,形象幽默,韵味深长,将原始文化的神秘感体现的淋漓尽致。\n" +
                "二、腾冲皮影戏的艺术特色\n" +
                "皮影戏的操作方式，主要是操作艺人对头靠和身靠，同时利用灯光的照射，把皮影的投射在银幕上。皮影的做工精美，可作为工艺品，表演时即可作为剧中角色。");
    }
}
