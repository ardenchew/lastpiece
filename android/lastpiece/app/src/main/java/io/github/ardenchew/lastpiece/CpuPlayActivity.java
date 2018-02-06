package io.github.ardenchew.lastpiece;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class CpuPlayActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cpu_play);

        Button easyBtn = (Button)findViewById(R.id.easyBtn);
        Button mediumBtn = (Button)findViewById(R.id.mediumBtn);
        Button hardBtn = (Button)findViewById(R.id.hardBtn);
        Button machineBtn = (Button)findViewById(R.id.machineBtn);

        easyBtn.setOnClickListener(this);
        mediumBtn.setOnClickListener(this);
        hardBtn.setOnClickListener(this);
        machineBtn.setOnClickListener(null);
    }

    @Override
    public void onClick(View v) {
        Intent startIntent = new Intent(getApplicationContext(), GameActivity.class);
        switch (v.getId()) {
            case R.id.easyBtn:
                startIntent.putExtra("io.github.ardenchew.lastpiece.easy", "Computer");
                startActivity(startIntent);
                break;
            case R.id.mediumBtn:
                startIntent.putExtra("io.github.ardenchew.lastpiece.medium", "Computer");
                startActivity(startIntent);
                break;
            case R.id.hardBtn:
                startIntent.putExtra("io.github.ardenchew.lastpiece.hard", "Computer");
                startActivity(startIntent);
                break;
            case R.id.machineBtn:
                startIntent.putExtra("io.github.ardenchew.lastpiece.machine", "Computer_Machine");
                startActivity(startIntent);
                break;
            default:
                break;
        }
    }

}
