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

import java.util.ArrayList;
import java.util.Iterator;

public class ShowGroup extends AppCompatActivity {

    EditText edtWord3;
    Button btnSearch3;
    TextView txtStatus3;
    TextView word1;
    TextView word2;
    TextView word3;
    TextView word4;
    TextView word5;
    TextView word6;
    TextView word7;
    ArrayList<String> a = new ArrayList<String>();


    void init(){
        edtWord3 = findViewById(R.id.edtWord3);
        btnSearch3 = findViewById(R.id.btnSearch3);
        txtStatus3 = findViewById(R.id.txtAddStatus3);




        word1 = findViewById(R.id.word1);
        word2 = findViewById(R.id.word2);
        word3 = findViewById(R.id.word3);
        word4 = findViewById(R.id.word4);
        word5 = findViewById(R.id.word5);
        word6 = findViewById(R.id.word6);
        word7 = findViewById(R.id.word7);


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        wordcount = ShowGroup.this.getSharedPreferences("wc", ShowGroup.this.MODE_PRIVATE);
        wordlist = ShowGroup.this.getSharedPreferences("count", ShowGroup.this.MODE_PRIVATE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_group);
        init();

        TextView [] t = {word1, word2, word3, word4, word5, word6, word7};

        btnSearch3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String group = edtWord3.getText().toString();
                boolean status = true;

                int length = group.length();
                if (length == 0) status = false;
                for (int i = 0; i < length; i++) {
                    if (group.charAt(i) == ' ')
                        status = false;
                }

                if (status == false) {
                    txtStatus3.setTextColor(getResources().getColor(R.color.red));
                    txtStatus3.setText("Từ không hợp lệ");
                    txtStatus3.setVisibility(View.VISIBLE);
                }
                else {
                    int current = wordcount.getInt("count", 0);
                    int pos = -1;
                    System.out.println(current);

                    for (int i = 1; i <= current; i++) {
                        String all = wordlist.getString(String.valueOf(i), "null");
                        boolean ok = true;

                        System.out.println(all + "\n");

                        length = all.length();
                        int last = 0;
                        for (int j = 0; j < length; ++j)
                            if (all.charAt(j) == '-') {
                                String tam2 = all.substring(last, j);


                                if (tam2.equals(group)) {
                                    pos = i;
                                    ok = false;
                                    break;
                                }
                                last = j + 1;
                            }
                        if (ok == false) break;
                    }

                    if (pos == -1) {
                        txtStatus3.setTextColor(getResources().getColor(R.color.red));
                        txtStatus3.setText("Không có nhóm từ thỏa mãn");
                        System.out.println("Không có nhóm từ thỏa mãn");
                        txtStatus3.setVisibility(View.VISIBLE);

                    } else {

                        txtStatus3.setTextColor(getResources().getColor(R.color.green));
                        txtStatus3.setText("Nhóm từ đồng nghĩa cần tìm là: ");
                        System.out.println("Nhóm từ đồng nghĩa cần tìm là: ");
                        txtStatus3.setVisibility(View.VISIBLE);

                        String all1 = wordlist.getString(String.valueOf(pos), "null");

                        length = all1.length();
                        int last = 0;
                        int dem = 0;
                        for (int j = 0; j < length; ++j)
                            if (all1.charAt(j) == '-') {
                                String tam2 = all1.substring(last, j);
                                ++dem;
                                last = j + 1;

                                t[dem-1].setText(tam2);
                                t[dem-1].setVisibility(View.VISIBLE);
                            }

                        System.out.println(dem);
                        System.out.println("\n");
                        System.out.println(pos);
                    }
                }

            }


        });
    }
}