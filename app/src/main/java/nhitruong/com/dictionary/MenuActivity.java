package nhitruong.com.dictionary;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MenuActivity extends AppCompatActivity {

    private Button btnDictionary, btnLearn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        btnDictionary = (Button) findViewById(R.id.btn_dictionary);


    }
    public void onClickDictionary(View v){
        Intent intent = new Intent(MenuActivity.this, MainActivity.class);

        startActivity(intent);
    }
    public void onClickLearn(View v){
        Intent intent = new Intent(MenuActivity.this, LearnActivity.class);

        startActivity(intent);
    }
}