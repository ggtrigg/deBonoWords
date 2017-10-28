package net.glenntrigg.debonowords;

import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private WordList dbWordList;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        dbWordList = new WordList(getResources());
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView) findViewById(R.id.textView);
        if (savedInstanceState != null) {
            textView.setText(savedInstanceState.getString("currentWord"));
        } else {
            textView.setText(dbWordList.GetRandomWord());
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putString("currentWord", textView.getText().toString());
    }

    public void updateWord(View v){
        textView.setText(dbWordList.GetRandomWord());
    }

    private class WordList {
        private String[] dbWords;
        private Integer wordCount;

        public WordList (Resources res) {
            dbWords = res.getStringArray(R.array.debonowords);
            wordCount = Array.getLength(dbWords);
        }

        public String GetRandomWord()
        {
            return dbWords[new Random().nextInt(wordCount)];
        }
    }
}
