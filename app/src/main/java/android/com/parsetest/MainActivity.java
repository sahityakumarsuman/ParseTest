package android.com.parsetest;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import io.cloudboost.CloudApp;
import io.cloudboost.CloudException;
import io.cloudboost.CloudObject;
import io.cloudboost.CloudObjectCallback;

public class MainActivity extends AppCompatActivity {


    private Button okeyButton, resetButton;
    private EditText guessNoEt;
    private TextView showResult;


    private int TargetNo;
    private String guessData;
    private int enteredData;

    private int result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        okeyButton = (Button) findViewById(R.id.button);
        guessNoEt = (EditText) findViewById(R.id.guess_no);
        showResult = (TextView) findViewById(R.id.show_data);
        resetButton = (Button) findViewById(R.id.reset_btton);

        okeyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                guessData = guessNoEt.getText().toString();
                enteredData = Integer.parseInt(guessData);

                if (enteredData > TargetNo) {
                    result = (TargetNo * 100 / enteredData);

                } else {
                    result = (enteredData * 100 / TargetNo);
                }

                showResult.setText("You are close about " + result + "%");

            }
        });

        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guessNoEt.setText("");
                showResult.setText("Try Again");
            }
        });


        // -------------

        new NewThread().execute();


        // -------------


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

    private class NewThread extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        @Override
        protected Void doInBackground(Void... params) {

            try {
                CloudObject obj = new CloudObject("DataInfo");
                obj.set("Name", "Sahitya");
                obj.set("data", "25");
                obj.save(new CloudObjectCallback() {
                    @Override
                    public void done(CloudObject x, CloudException t) {
                        if (x != null) {

                            Log.d("in if", "x is not null");
                        }
                        if (t != null) {
                            Log.d("in if", "t is not null");
                        }
                    }
                });


                String data = "" + obj.get("data");
                TargetNo = Integer.parseInt(data);
                Log.d("data is more here ... ", data);
            } catch (Exception e) {
                e.printStackTrace();
            }


            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {

            super.onPostExecute(aVoid);
        }
    }
}
