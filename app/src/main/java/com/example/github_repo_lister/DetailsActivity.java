package com.example.github_repo_lister;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.github_repo_lister.Database.DataBaseHelper;

import java.util.ArrayList;
import java.util.Objects;

public class DetailsActivity extends AppCompatActivity {

    private static DataBaseHelper mydb;
    private static String getName;
    private Button bookmark_btn, remove_btn;
    private ImageButton already_bookmakred_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        mydb = new DataBaseHelper(this);
        Intent intent = getIntent();
        TextView name = findViewById(R.id.txtName);
        TextView stargazers_count = findViewById(R.id.txtStargazers_count);
        name.setText(intent.getStringExtra("name"));
        getName = name.getText().toString().trim();
        stargazers_count.setText(intent.getStringExtra("stargazers_count"));
        bookmark_btn = findViewById(R.id.bookmark_btn);
        already_bookmakred_btn = findViewById(R.id.alread_bookmark_btn);
        remove_btn = findViewById(R.id.remove_btn);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Repositories Details");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                DetailsActivity.super.onBackPressed();
                finish();
            }
        });

        if (mydb.ifExists(getName)) {
            already_bookmakred_btn.setVisibility(View.VISIBLE);
            bookmark_btn.setVisibility(View.GONE);
            remove_btn.setVisibility(View.VISIBLE);
            remove_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    removeBookmark(v);
                }
            });
        }

        bookmark_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addToBookmark(v);
            }
        });
    }

    public void addToBookmark(View view) {

        if (!mydb.ifExists(getName)) {
            mydb.insertBookmark(getName);
            already_bookmakred_btn.setVisibility(View.VISIBLE);
            bookmark_btn.setVisibility(View.GONE);
            remove_btn.setVisibility(View.VISIBLE);
        }
    }

    public void removeBookmark(View view) {
        mydb.deleteBookmark(getName);
        already_bookmakred_btn.setVisibility(View.GONE);
        remove_btn.setVisibility(View.GONE);
        bookmark_btn.setVisibility(View.VISIBLE);
    }


    @Override
    protected void onStart() {

        super.onStart();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}