package nhitruong.com.dictionary;

import android.content.Intent;
        import android.speech.tts.TextToSpeech;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.text.Html;
        import android.view.View;
        import android.webkit.WebView;
        import android.widget.Button;
        import android.widget.TextView;

        import java.util.Locale;

public class DefinitionActivity extends AppCompatActivity {
    private TextView tvWord;
    private WebView  tvDefinition;
    private TextToSpeech tts1;
    private Button Speak;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_definition);

        tvWord = (TextView) findViewById(R.id.tv_word);
        tvDefinition = (WebView) findViewById(R.id.tv_definition);
        Speak = (Button) findViewById(R.id.btn_speak);

        Intent intent = getIntent();
        String word = intent.getStringExtra("word");
        tvWord.setText(word);

        DatabaseAccess dbAccess = DatabaseAccess.getInstance(this);
        dbAccess.open();
        String definition = dbAccess.getDefinition(word);
        dbAccess.close();
        tvDefinition.loadData(definition,"text/html; charset = utf-8","utf-8");

        tts1 = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status != TextToSpeech.ERROR){
                    tts1.setLanguage(Locale.UK);
                }
            }
        });
    }
    public void onClick(View v){

        tts1.speak(tvWord.getText().toString(), TextToSpeech.QUEUE_FLUSH, null);
    }
}
