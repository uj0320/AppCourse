package com.pu.guess_number;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button bt,btPush ;
    TextView tv, tvHint, tvHint2;
    EditText ed, edTopic ;
    String ans ;
    int count;
    String hint;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bt = findViewById(R.id.bt);
        tv = findViewById(R.id.tv);
        ed = findViewById(R.id.ed);
        //ans = "8524";
        btPush = findViewById(R.id.btPush);
        edTopic = findViewById(R.id.edTopic);
        count = 0;
        hint = "";
        tvHint = findViewById(R.id.tvHint);
      //  tvHint2 = findViewById(R.id.tvHint2);

        //設定題目
        btPush.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ans = edTopic.getText().toString();
                edTopic.setTextSize(5);
                //btPush.setTextColor(0);
                edTopic.setTextColor(0);
            }
        });
        //猜數字
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String guess = ed.getText().toString();
             //   tvHint2.setText("");
                hint = "";//清空提示欄
                count++;//數猜了幾次
                int nA = 0, nB = 0;
                for(int i = 0; i < 4; i++){
                    if(guess.charAt(i) == ans.charAt(i)) {
                        nA++;
                        hint += "Hint: "+guess.charAt(i) + " "; //提示哪個數字是A
                      //  tvHint2.setText("Hint: ");
                    }
                    else
                        for(int j = 0; j < 4; j++)
                            if(guess.charAt(i) == ans.charAt(j)) {
                                nB++;
                                continue;
                            }
                    tvHint.setText(hint); //顯示提示
                }
                tv.setText(nA+"A"+nB+"B"); //顯示幾A幾B
                //
                if(nA == 4) {
                    End("You Win!");

                }
                //限制猜的次數
                else if(count == 4)
                    Toast.makeText(MainActivity.this, "剩下最後一次機會", Toast.LENGTH_SHORT).show();
                if(count == 5) {
                    End("You Lose...");
                    return;
                }

            }
        });

    }
    //跑出小視窗看是贏了還輸
    public void End(String str){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(str+"\n 答案:"+ans);
        builder.setPositiveButton("OK", null);
        builder.create().show();
        re();
    }
    //還原所有設定
    public void re(){
        edTopic.setText("");
        ed.setText("");
        tv.setText("");
        tvHint.setText("");
        tvHint2.setText("");
        count = 0;
        edTopic.setTextSize(25);
        //btPush.setTextColor(256);
        edTopic.setTextColor(256);
    }
}