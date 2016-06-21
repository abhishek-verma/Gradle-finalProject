package com.abhi.jokedisplay;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class JokeActivity extends AppCompatActivity {

    public static final java.lang.String EXTRA_JOKE_KEY = "joke key";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joke);

        Bundle extras = getIntent().getExtras();
        String joke = extras != null && extras.containsKey(EXTRA_JOKE_KEY)
                ? extras.getString(EXTRA_JOKE_KEY) : getString(R.string.no_joke_available);

        TextView jokeTxtV = (TextView) findViewById(R.id.jokeTxtV);
        jokeTxtV.setText(joke);
    }
}
