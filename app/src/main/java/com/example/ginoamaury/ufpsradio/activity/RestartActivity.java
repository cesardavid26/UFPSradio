package com.example.ginoamaury.ufpsradio.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.example.ginoamaury.ufpsradio.R;

/**
 * author carlos22ivan
 * author gino
 */
public class RestartActivity extends AppCompatActivity {

    Context context;

    /**
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restart);
        context = this;
        String msg = getIntent().getStringExtra("msg");
        TextView textView = (TextView) findViewById(R.id.restart_error);
        textView.setText(msg);
    }

    /**
     * vuelve a lanzar la aplicacion
     *
     * @param view
     */
    public void volverIntentarlo(View view) {
        Intent i = new Intent(context, SplashActivity.class);
        startActivity(i);
        finish();
    }

    /**
     * cierra la aplicacion
     *
     * @param view
     */
    public void salir(View view) {
        finish();
    }


}
