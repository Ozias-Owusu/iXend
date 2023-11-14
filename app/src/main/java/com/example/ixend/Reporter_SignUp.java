package com.example.ixend;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthSettings;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.regex.Pattern;

public class Reporter_SignUp extends AppCompatActivity {
    TextInputLayout regfname, regphone;
    Button btnregister;
    FirebaseDatabase rootNode;
    FirebaseAuth.AuthStateListener FirebaseAuthListener;
    DatabaseReference reference;
    long UserID = 0;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.reporter_signup);
        regfname = findViewById(R.id.fname);
        regphone = findViewById(R.id.phone);
        btnregister = findViewById(R.id.register);

        mAuth = FirebaseAuth.getInstance();


        if (mAuth.getCurrentUser() != null) {
            startActivity(new Intent(getApplicationContext(), ReporterHome.class));
            finish();
        }


        reference = FirebaseDatabase.getInstance().getReference().child("Reporter");

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    UserID = (dataSnapshot.getChildrenCount());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        btnregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String fname = regfname.getEditText().getText().toString();
                final String phone = regphone.getEditText().getText().toString();



                if (fname.isEmpty()) {
                    regfname.setError("Full name Required");
                    return;

                } else {
                    regfname.setError(null);
                    regfname.setErrorEnabled(false);
                }


                if (phone.isEmpty()) {
                    regphone.setError("Phone Number Required");
                    return;

                } else {
                    regphone.setError(null);
                    regphone.setErrorEnabled(false);
                }

                isValidMobile(phone);


                Intent intent = new Intent(getApplicationContext(), Reporter_Verify.class);
                intent.putExtra("phone", phone);
                intent.putExtra("fname", fname);
                startActivity(intent);

                Intent i = new Intent(getApplicationContext(),MainAction.class);
                i.putExtra("fname", fname);
                i.putExtra("phone", phone);

               Intent A = new Intent(getApplicationContext(),SendAudio.class);
                A.putExtra("fname", fname);
                A.putExtra("phone", phone);

                Intent T = new Intent(getApplicationContext(),Text_page.class);
                T.putExtra("fname", fname);
                T.putExtra("phone", phone);


                /*UserHelperClass helperClass = new UserHelperClass(fname, phone,userid);


                reference.child(String.valueOf(UserID + 1)).setValue(helperClass);*/

            }
        });


    }

    private boolean isValidMobile(String phone) {
        if (!Pattern.matches("[a-zA-Z]+", phone)) {
            return phone.length() == 10;
        }
        return false;
    }

}