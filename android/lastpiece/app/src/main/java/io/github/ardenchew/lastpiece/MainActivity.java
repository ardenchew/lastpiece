package io.github.ardenchew.lastpiece;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button playBtn = (Button)findViewById(R.id.playBtn);
        playBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startIntent = new Intent(getApplicationContext(), PlayActivity.class);
                //TODO
                startActivity(startIntent);
            }
        });

        Button instructionsBtn = (Button)findViewById(R.id.instructionsBtn);
        instructionsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent startIntent = new Intent(getApplicationContext(), InstructionsActivity.class);
                //TODO
                startActivity(startIntent);

            }
        });


    }
}
