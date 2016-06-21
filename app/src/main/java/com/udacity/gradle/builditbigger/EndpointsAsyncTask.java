package com.udacity.gradle.builditbigger;

import android.os.AsyncTask;

import com.abhi.udacity.jokebackend.myApi.MyApi;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;

import java.io.IOException;

/**
 * Created by Abhishek on 6/20/2016.
 */
public class EndpointsAsyncTask extends AsyncTask<JokeListener, Void, String> {
    private static MyApi myApiService = null;
    JokeListener callback;

    @Override
    protected String doInBackground(JokeListener... params) {
        if (myApiService == null) {  // Only do this once
            MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(), null)
                    // options for running against local devappserver
                    // - 10.0.2.2 is localhost's IP address in Android emulator
                    // - turn off compression when running against local devappserver
//                    .setRootUrl("http://10.0.2.2:8080/_ah/api/")
                    .setRootUrl("http://192.168.43.213:8080/_ah/api/")
                    .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                        @Override
                        public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                            abstractGoogleClientRequest.setDisableGZipContent(true);
                        }
                    });
            // end options for devappserver

            myApiService = builder.build();
        }

        callback = params[0];

        try {
            return myApiService.getJoke().execute().getData();
        } catch (IOException e) {
            return "";
        }
    }

    @Override
    protected void onPostExecute(String joke) {
        super.onPostExecute(joke);

        callback.onJokeReceivedListener(joke);
    }
}
