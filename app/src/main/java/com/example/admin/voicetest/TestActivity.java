package com.example.admin.voicetest;

import android.content.DialogInterface;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.*;

import java.lang.reflect.Array;
import java.util.ArrayList;
import android.app.Activity;
import android.content.Intent;
import android.speech.RecognizerIntent;
import android.view.View.OnClickListener;



public class TestActivity extends ActionBarActivity implements OnClickListener {

    protected static final int REQUEST_OK = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        findViewById(R.id.imageButton).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        contSpeak();
    }

    public void contSpeak() {
        Intent i = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        i.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, "en-US");
        try {
            startActivityForResult(i, REQUEST_OK);
        } catch (Exception e) {
            Toast.makeText(this, "Error initializing speech to text engine.", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==REQUEST_OK  && resultCode==RESULT_OK) {
            ArrayList<String> thingsYouSaid = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
            ((TextView)findViewById(R.id.fillMe)).setText(thingsYouSaid.get(0));
            shownHide(thingsYouSaid);
            contSpeak();
        }
    }

    public void shownHide (ArrayList<String> command) {
        if (command.get(0).equals("show")){
            showButton();
        }
        else if (command.get(0).equals("hyde")){
            hideButton();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_test, menu);
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

    public void visaKnappen(View V) {
        showButton();

    }
    public void showButton() {
        TextView button1 = (TextView) findViewById(R.id.button1);
        button1.setVisibility(View.VISIBLE);
    }

    public void gomKnappen(View V) {
        hideButton();

    }
    public void hideButton() {
        TextView button1 = (TextView) findViewById(R.id.button1);
        button1.setVisibility(View.INVISIBLE);
    }

}

