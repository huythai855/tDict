package com.charlie.tdict;

import static com.charlie.tdict.Add.wordcount;
import static com.charlie.tdict.Add.wordlist;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class AddToGroup extends AppCompatActivity {

    EditText edtWord2, edtGroup2;
    Button btnAdd2;
    TextView txtAdd2;

    void init(){
        edtWord2 = findViewById(R.id.edtWord2);
        edtGroup2 = findViewById(R.id.edtGroup2);
        btnAdd2 = findViewById(R.id.btnAdd2);
        txtAdd2 = findViewById(R.id.txtAdd2);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_to_group);

        wordcount = AddToGroup.this.getSharedPreferences("wc", AddToGroup.this.MODE_PRIVATE);
        wordlist = AddToGroup.this.getSharedPreferences("count", AddToGroup.this.MODE_PRIVATE);
        init();

        btnAdd2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean status = true;
                String word = edtWord2.getText().toString();
                String group = edtGroup2.getText().toString();

                int length = word.length();
                if(length == 0) status = false;
                for(int i = 0; i < length; i++){
                    if(word.charAt(i) == ' ')
                        status = false;
                }

                length = group.length();
                if(length == 0) status = false;
                for(int i = 0; i < length; i++){
                    if(group.charAt(i) == ' ')
                        status = false;
                }

                if(status == true){
                    // TODO action

                    int current = wordcount.getInt("count", 0);
                    int pos = -1;
                    for(int i = 1; i<= current; i++){
                        String all = wordlist.getString(String.valueOf(i), "null");
                        boolean ok = true;

                        length = all.length();
                        int last = 0;
                        for(int j = 0; j < length; ++j)
                            if(all.charAt(j) == '-') {
                                String tam2 = all.substring(last, j);

                                if(tam2.equals(group)){
                                    pos = i;
                                    ok = false;
                                    break;
                                }
                                last = j+1;
                            }
                        if(ok == false) break;
                    }

                    if(pos == -1){
                        txtAdd2.setTextColor(getResources().getColor(R.color.red));
                        txtAdd2.setText("Không có nhóm từ thỏa mãn");
                        txtAdd2.setVisibility(View.VISIBLE);
                    }
                    else {
                        String gr = String.valueOf(pos);
                        String initial = wordlist.getString(gr, "null");
                        initial = initial + word + '-';
                        SharedPreferences.Editor eword = wordlist.edit();
                        eword.putString(gr, initial);
                        eword.commit();


                        System.out.println(pos);
                        System.out.println(gr);
                        System.out.println(initial);


                        txtAdd2.setTextColor(getResources().getColor(R.color.green));
                        txtAdd2.setText("Đã thêm từ thành công");
                        txtAdd2.setVisibility(View.VISIBLE);
                    }

                }
                else{
                    txtAdd2.setTextColor(getResources().getColor(R.color.red));
                    txtAdd2.setText("Từ không hợp lệ");
                    txtAdd2.setVisibility(View.VISIBLE);
                }


            }
        });

    }
}