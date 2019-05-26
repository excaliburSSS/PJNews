package com.scujcc.androidxtest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView CLS;
    private ChannelListAdapter LSA;
    private List<Channel> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initData();
        CLS =  findViewById(R.id.channelList);

        LSA = new ChannelListAdapter(this.data, new ChannelClickListener() {
            @Override
            public void onClick(View view, int position) {
                Log.i("FFPLAY", "Clicked "+view+" on "+position);
                if (position < data.size()) {
                    Channel c = data.get(position);
                    Intent intent = new Intent(MainActivity.this, LiveActivity.class);
                    intent.putExtra("channel", c);
                    startActivity(intent);
                } else {
                    Toast.makeText(MainActivity.this, "无效的频道",Toast.LENGTH_SHORT);
                }
            }
        });
        CLS.setAdapter(LSA);
        CLS.setLayoutManager(new LinearLayoutManager(this));
    }

    private void initData(){
        Data lab = new Data(this);
        this.data = lab.getChannels("data.json");
    }

}
