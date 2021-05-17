package com.benny.penda;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.benny.annotation.Builder;
import com.benny.annotation.Optional;
import com.benny.annotation.Required;

@Builder
public class UserActivity extends AppCompatActivity {

    private TextView tvName,tvOwner,tvUrl,tvTime;

    @Required
    String name;

    @Required
    String owner;

    @Optional
    String url;

    @Optional
    long time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        initView();
    }

    private void initView() {
        tvName=findViewById(R.id.nameTv);
        tvOwner=findViewById(R.id.ownerTv);
        tvUrl =findViewById(R.id.urlTv);
        tvTime = findViewById(R.id.timeTv);

        tvName.setText(name);
        tvOwner.setText(owner);
        tvUrl.setText(url);
        tvTime.setText(time+"");

    }
}