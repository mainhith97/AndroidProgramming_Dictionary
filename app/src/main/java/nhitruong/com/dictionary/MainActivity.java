package nhitruong.com.dictionary;

import android.content.Intent;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private EditText Editsearch;
    private ListView Lv_words;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Editsearch = (EditText) findViewById(R.id.edt_search);
        Lv_words = (ListView) findViewById(R.id.lv_words);

        Intent intent = getIntent();

        final DatabaseAccess dbAcess = DatabaseAccess.getInstance(this);
        dbAcess.open();
        final ArrayList<String> words = dbAcess.getwords();

        dbAcess.close();
        //final ArrayList<String> words_2 = dbAcess.getwords();
        //load du lieu len list view
        final ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,words);
        Lv_words.setAdapter(adapter);

        /*Editsearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                dbAcess.open();
                words_2.clear();
                words_2.addAll(dbAcess.getwords(s.toString()));
                adapter.clear();
                adapter.addAll(words_2);
                dbAcess.close();
               runOnUiThread(new Runnable() {
                   @Override
                   public void run() {
                       adapter.notifyDataSetChanged();
                   }
               });
            }
            @Override
            public void afterTextChanged(Editable s) {
            }
        });*/
        Lv_words.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedWord = Lv_words.getItemAtPosition(position).toString();
                Intent intent = new Intent(MainActivity.this, DefinitionActivity.class);
                intent.putExtra("word", selectedWord);
                startActivity(intent);
            }
        });
    }
}
