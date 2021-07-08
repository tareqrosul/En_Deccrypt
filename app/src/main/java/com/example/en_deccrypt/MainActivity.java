package com.example.en_deccrypt;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {
    EditText edt,num;
    TextView out;
    Button btn,btn1,btn2;
    ConstraintLayout CL;
    ImageButton copy;
    InputMethodManager imm;
    private Bundle savedInstanceState;
    String ciphertext;

    {
        ciphertext = "";
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.savedInstanceState = savedInstanceState;
        //requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edt=findViewById(R.id.editText);
        out=findViewById(R.id.output);
        num=findViewById(R.id.editTextNumberSigned);
        btn=(Button) findViewById(R.id.button);
        btn1=(Button) findViewById(R.id.button2);
        btn2=(Button) findViewById(R.id.button5);
        copy=findViewById(R.id.imageButton);

        btn.setOnClickListener(new View.OnClickListener(){
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v){
                // Get your layout set up, this is just an example
                CL = (ConstraintLayout)findViewById(R.id.CL);
                // Then just use the following
                InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(CL.getWindowToken(), 0);
                String input;
                input=edt.getText().toString();
                String number;
                number=num.getText().toString();
                int shift;
                if(number.matches("")) {
                    shift = 1;
                }
                else{
                    shift = Integer.valueOf(number);
                }

                char alphabet;
                for(int i=0; i < input.length();i++) {
                    // Shift one character at a time
                    alphabet = input.charAt(i);

                    // if alphabet lies between a and z
                    if (alphabet >= 'a' && alphabet <= 'z') {
                        // shift alphabet
                        alphabet = (char) (alphabet + shift);
                        // if shift alphabet greater than 'z'
                        if (alphabet > 'z') {
                            // reshift to starting position
                            alphabet = (char) (alphabet + 'a' - 'z' - 1);
                        }
                        ciphertext = ciphertext + alphabet;
                    }

                    // if alphabet lies between 'A'and 'Z'
                    else if (alphabet >= 'A' && alphabet <= 'Z') {
                        // shift alphabet
                        alphabet = (char) (alphabet + shift);

                        // if shift alphabet greater than 'Z'
                        if (alphabet > 'Z') {
                            //reshift to starting position
                            alphabet = (char) (alphabet + 'A' - 'Z' - 1);
                        }
                        ciphertext = ciphertext + alphabet;
                    } else {
                        ciphertext = ciphertext + alphabet;
                    }
                }
                    out.setText(ciphertext);
                    ciphertext="";
                    out.setMovementMethod(new ScrollingMovementMethod());

            }
        });
        //Screen Clear
        btn1.setOnClickListener(new View.OnClickListener(){
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v){
                // Get your layout set up, this is just an example
                CL = (ConstraintLayout)findViewById(R.id.CL);
                // Then just use the following:
                InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(CL.getWindowToken(), 0);
                edt.setText("");
                num.setText("");
                out.setText("");
                Toast.makeText(MainActivity.this, "This is first Button", Toast.LENGTH_SHORT).show();

                Snackbar.make(v, "Mod kha manus ho", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();


            }
        });
        //Got To Decrytion
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, second.class);
                startActivity(intent);
            }
        });
        //Copy the output_text in the clipboard
        copy.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText(ciphertext,ciphertext);
                clipboard.setPrimaryClip(clip);
                Snackbar.make(v, "Text Copied", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }

        });
    }
}