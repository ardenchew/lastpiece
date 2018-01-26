package io.github.ardenchew.lastpiece;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class PlayActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);

        Button multiBtn = (Button)findViewById(R.id.multiBtn);
        multiBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startIntent = new Intent(getApplicationContext(), GameActivity.class);
                //TODO pass player data to game activity set up
                startActivity(startIntent);
            }
        });

        Button singleBtn = (Button)findViewById(R.id.singleBtn);
        singleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent startIntent = new Intent(getApplicationContext(), GameActivity.class);
                //TODO pass player data to game activity set up
                startActivity(startIntent);

            }
        });
    }
}
