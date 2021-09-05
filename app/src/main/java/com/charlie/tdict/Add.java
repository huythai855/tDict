package com.charlie.tdict;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.TextView;

public class Add extends AppCompatActivity {

    EditText edtWord;
    Button btnAdd;
    TextView txtAddStatus;
    static SharedPreferences wordcount;
    static SharedPreferences wordlist;



    void init(){
        edtWord = findViewById(R.id.edtWord);
        btnAdd = findViewById(R.id.btnAdd);
        txtAddStatus = findViewById(R.id.txtAddStatus);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        wordcount = Add.this.getSharedPreferences("wc", Add.this.MODE_PRIVATE);
        wordlist = Add.this.getSharedPreferences("count", Add.this.MODE_PRIVATE);

        init();

        btnAdd.setOnClickListener(new View.OnClickListener() {


            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View view) {
                String word = edtWord.getText().toString();
                boolean status = true;
                int length = word.length();

                if(length == 0)
                    status = false;

                for(int i = 0; i < length; i++){
                    if(word.charAt(i) == ' ')
                        status = false;
                }

                if(status == true){
                    SharedPreferences.Editor ecount = wordcount.edit();

                    int current = wordcount.getInt("count", 0);
                    current++;
                    ecount.putInt("count", current);
                    ecount.commit();

                    String key = String.valueOf(current);
                    SharedPreferences.Editor eword = wordlist.edit();
                    eword.putString(key, word + "-");
                    eword.commit();


                    txtAddStatus.setTextColor(getResources().getColor(R.color.green));
                    txtAddStatus.setText("Đã thêm từ thành công");
                    txtAddStatus.setVisibility(View.VISIBLE);
                }
                else {
                    txtAddStatus.setTextColor(getResources().getColor(R.color.red));
                    txtAddStatus.setText("Từ không hợp lệ");
                    txtAddStatus.setVisibility(View.VISIBLE);
                }
            }
        });


    }
}