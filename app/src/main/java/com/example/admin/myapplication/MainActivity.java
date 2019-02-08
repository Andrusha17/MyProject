package com.example.admin.myapplication;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        Profile profile  = new Profile(3)
//                .setFirstName("Олег")
//                .setLastName("Хорош")
//                .setEmail("oleg@gmail.com");

//        DatabaseHelper.insert(this, profile);

        List<Profile> profiles = DatabaseHelper.getProfiles(this);
        Profile p = profiles.get(0);
        Toast.makeText(MainActivity.this, "Profile: " + p.getFirstName() + " " + p.getLastName(), Toast.LENGTH_LONG).show();

        System.out.println("Commit");
    }
}
