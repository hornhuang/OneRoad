package com.example.oneroad.text;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.oneroad.R;

public class RoutesRoutesActivity extends AppCompatActivity {

    private TextView name,introduce,more;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_routes_routes);

        name = (TextView) findViewById(R.id.name);
        introduce = (TextView) findViewById(R.id.introduce);
        more = (TextView) findViewById(R.id.more);

        name.setText("海沧东屿中元宫庙会，国家级非遗精彩亮相");

        introduce.setText("    东屿中元宫始建于明代末期，距今已有四百多年历史，主要供奉从青礁慈济宫分灵而来的保生大帝。每年农历正月二十，宫庙都要举行盛大的谒祖进香活动。");

        more.setText("一、民俗文化祭庙会\n\n" +
                "    几十节长、气势不凡的蜈蚣阁参加了巡游。蜈蚣阁是国家级非物质文化遗产项目，它的出现再次展示了其特有的迷人魅力。\n\n\n\n" +
                "二、艺队表演\n\n" +
                "    村民出资邀请的几十队艺阵也在广场进行了轮番表演：他们中有舞龙、舞狮、乐队、表演队、号队、鼓队等，精彩的表演更增添了民俗文化祭庙会的热闹气氛。庙前广场顿时锣鼓喧天、鞭炮连连，人声鼎沸……。");
    }



}
