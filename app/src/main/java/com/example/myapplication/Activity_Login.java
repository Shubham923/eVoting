package com.example.myapplication;


/*  THIS IS LOGIN ACTIVITY
*   IT WILL TAKE USER NAME AS AN INPUT(LATER ONE, WILL CHANGE IT TO VOTER ID )
*
* */

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import org.w3c.dom.Text;

public class Activity_Login extends AppCompatActivity {

    RelativeLayout rellay1;
    Button button;
    User user;
    EditText et_name;
    Handler handler = new Handler();
    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            rellay1.setVisibility(View.VISIBLE);

        }
    };
    public  Session session;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__login);


        rellay1 = (RelativeLayout) findViewById(R.id.rellay1);


        /*FLASH SCREEN*/
        handler.postDelayed(runnable, 2000); //2000 is the timeout for the splash


        button=(Button)findViewById(R.id.id_login);
        et_name=(EditText)findViewById(R.id.id_tvname);
        final FetchUserInfo fetchUserInfo=new FetchUserInfo();
        session=new Session(this);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String tvInput=et_name.getText().toString();
                session=new Session(getApplicationContext());
                SharedPreferences prefs = getApplicationContext().getSharedPreferences("MyPref", 0); // 0 - for private mode
                SharedPreferences.Editor editor = prefs.edit();
                editor.clear().commit();

                /*CALLING METHOD WHICH FETCHES USER INFORMATION FROM FIREBASE*/

                user=fetchUserInfo.getUserInfo(session,tvInput,getApplicationContext());

                /*USER NOT FOUND, GET BACK TO LOGIN ACTIVITY*/
                if(session.getUsername()=="") {
                    Toast.makeText(getApplicationContext(), "Voter ID Not Found", Toast.LENGTH_SHORT);
                    intent=new Intent(getApplicationContext(), Activity_Login.class);
                    startActivity(intent);

                }
            }
        });







    }

}
