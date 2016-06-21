package com.udacity.gradle.builditbigger;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.abhi.jokedisplay.JokeActivity;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;


public class MainActivity extends AppCompatActivity implements JokeListener {

    InterstitialAd mInterstitialAd;
    String mJoke = null;
    Boolean isAdShown = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId("ca-app-pub-3940256099942544/1033173712");

        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                isAdShown = false;
                requestNewInterstitial();
                launchJokeActivity();
            }
        });

        requestNewInterstitial();
    }


    private void requestNewInterstitial() {
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .addTestDevice("ABEF917CDEBA1427C27D5E68B870748D")
                .build();

        mInterstitialAd.loadAd(adRequest);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void tellJoke(View view) {
        showLoadingSpinner(true);
        new EndpointsAsyncTask().execute(this);

        if (mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
            isAdShown = true;
        }
    }

    public void onJokeReceivedListener(String joke) {
        showLoadingSpinner(false);
        mJoke = joke == null ? "No Joke Received!" : joke;
        if (!isAdShown)
            launchJokeActivity();
    }

    private void launchJokeActivity() {
        if (mJoke == null)
            return;

        Intent jokeActivityIntent = new Intent(this, JokeActivity.class);
        jokeActivityIntent.putExtra(JokeActivity.EXTRA_JOKE_KEY, mJoke);
        startActivity(jokeActivityIntent);
    }

    public void showLoadingSpinner(boolean show) {
        try {
            if (show)
                findViewById(R.id.jokeLoadingProgressBar).setVisibility(View.VISIBLE);
            else
                findViewById(R.id.jokeLoadingProgressBar).setVisibility(View.GONE);
        } catch (NullPointerException e) {
            Toast.makeText(this, R.string.loading_joke_msg, Toast.LENGTH_SHORT).show();
        }
    }
}
