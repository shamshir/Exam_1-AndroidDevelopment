package com.example.javi.firstexamandroid;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button toastB;
    private Button zeroB;
    private Button countB;
    private TextView counter;
    private int count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        leerValorContador();
        asignarListenersBotones();
    }

    protected void leerValorContador() {
        this.counter = (TextView) findViewById(R.id.countView);

        SharedPreferences sharedP = this.getPreferences(Context.MODE_PRIVATE);
        count = sharedP.getInt("savedCounter", 0);
        this.counter.setText(String.valueOf(count));
    }

    protected void asignarListenersBotones() {

        this.toastB = (Button) findViewById(R.id.toastButton);
        this.toastB.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Toast toast = Toast.makeText(v.getContext(), String.valueOf(count), Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER_VERTICAL|Gravity.CENTER_HORIZONTAL, 0, 0);
                toast.show();
            }

        });

        this.zeroB = (Button) findViewById(R.id.zeroButton);
        this.zeroB.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                count = 0;
                guardarValor();
                counter.setText(String.valueOf(count));
            }

        });

        this.countB = (Button) findViewById(R.id.countButton);
        this.countB.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                count++;
                guardarValor();
                counter.setText(String.valueOf(count));
            }

        });
    }

    protected void guardarValor() {
        SharedPreferences sharedP = this.getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedP.edit();
        editor.putInt("savedCounter", count);
        editor.commit();
    }
}
