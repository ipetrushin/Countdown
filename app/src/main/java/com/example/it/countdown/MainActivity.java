package com.example.it.countdown;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {

    Button button;

    class MyTask extends AsyncTask<Void, Void, Void> {
        // начинаем обратный отсчёт с 5
        int count = 5;

        @Override
        protected Void doInBackground(Void... voids) {
            // выполняется в отдельном потоке
            for (int i = 0; i < 5; i++) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {}
                // вызвать onProgressUpdate, который поменяет надпись на кнопке
                publishProgress();
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
            count --;
            button.setText(Integer.toString(count));
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            // выполняется в основном потоке
            button.setText("Count finished");
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = (Button) findViewById(R.id.button);
    }

    public void onClick(View v) {
        MyTask mt  = new MyTask();
        mt.execute();

    }
}
