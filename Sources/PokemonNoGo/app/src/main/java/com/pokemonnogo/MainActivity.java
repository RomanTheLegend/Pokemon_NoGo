package com.pokemonnogo;

import android.content.Intent;
//import android.support.v7.app.AppCompatActivity;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.TextView;


public class MainActivity extends Activity  {

    public static Handler mUiHandler = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Environment.mainContext=this;

        final TextView logWindow=(TextView) findViewById(R.id.textView);
        logWindow.setMovementMethod(new ScrollingMovementMethod());

        mUiHandler = new Handler() // Receive messages from service class
        {
            public void handleMessage(Message msg)
            {

                        //Toast.makeText(MainActivity.this, msg.obj.toString(), Toast.LENGTH_LONG).show();
                logWindow.append( "\n" + msg.obj.toString());

            }
        };



    }


    public void startService(View view) {

        startService(new Intent(getBaseContext(), CommandCenter.class));

        //Toast.makeText(this, "Service Started Local", Toast.LENGTH_LONG).show();
    }

    public void stopService(View view) {

        stopService(new Intent(getBaseContext(), CommandCenter.class));
        //finish();
    }

    public void exitApp(View view){
        finish();
    }


}
