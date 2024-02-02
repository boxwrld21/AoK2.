package com.example.areaofknowledge;


import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.areaofknowledge.models.User;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Registration extends AppCompatActivity {

    FirebaseAuth auth;

    FirebaseDatabase db;
    DatabaseReference users;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.auth);
         EditText eT1 = findViewById(R.id.eT1);
         EditText eT2 = findViewById(R.id.eT2);
       Button ready1 = findViewById(R.id.ready1);
       TextView auth_in = findViewById(R.id.auth_in);
        ConstraintLayout root = findViewById(R.id.root_element);


        ready1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(eT1.getText().toString())) {
                    Snackbar.make(root, "Введите вашу почту", Snackbar.LENGTH_SHORT).show();
                    return;
                }
                if (eT2.getText().toString().length() < 5) {
                    Snackbar.make(root, "Введите ваш пароль, который более 5 символов", Snackbar.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isDigitsOnly(eT1.getText().toString())){
                    Snackbar.make(root, "Ошибка", Snackbar.LENGTH_SHORT).show();
                    return;
                }

                auth.createUserWithEmailAndPassword(eT1.getText().toString(), eT2.getText().toString())
                        .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                            @Override
                            public void onSuccess(AuthResult authResult) {
                                User user = new User();
                                user.seteT1(eT1.getText().toString());
                                user.seteT2(eT2.getText().toString());

                                users.child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                        .setValue(user)
                                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                                            @Override
                                            public void onSuccess(Void aVoid) {
                                                Snackbar.make(root, "Пользователь добавлен", Snackbar.LENGTH_SHORT).show();
                                                return;
                                            }

                                        });
                            }


                        });
            }
        });

        auth_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showSignInWindow();
            }
        });



    }
    public void showSignInWindow () {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        LayoutInflater inflater = LayoutInflater.from(this);
        View sign_in_window = inflater.inflate(R.layout.sign_in_window, null);
        dialog.setView(sign_in_window);
        Button ready2 = sign_in_window.findViewById(R.id.ready2);
        EditText eT1 = sign_in_window.findViewById(R.id.eT1);
        EditText eT2 = sign_in_window.findViewById(R.id.eT2);
        ConstraintLayout root = findViewById(R.id.root_element);





        dialog.setNegativeButton("Закрыть", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int which) {
                dialogInterface.dismiss();
            }
        });
        ready2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(eT1.getText().toString())) {
                    Snackbar.make(root, "Введите вашу почту", Snackbar.LENGTH_SHORT).show();
                    return;
                }
                if (eT2.getText().toString().length() < 5) {
                    Snackbar.make(root, "Введите ваш пароль, который более 5 символов", Snackbar.LENGTH_SHORT).show();
                    return;
                }
                auth.signInWithEmailAndPassword(eT1.getText().toString(), eT2.getText().toString())
                        .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                            @Override
                            public void onSuccess(AuthResult authResult) {

                                User user = new User();
                                user.seteT1(eT1.getText().toString());
                                user.seteT2(eT2.getText().toString());




                                users.child(user.geteT1())
                                        .setValue(user)
                                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                                            @Override
                                            public void onSuccess(Void aVoid) {
                                               startActivity(new Intent(Registration.this, category.class));
                                               finish();
                                            }

                                        }).addOnFailureListener(new OnFailureListener() {
                                            @Override
                                            public void onFailure(@NonNull Exception e) {
                                                Snackbar.make(root, "Ошибка авторизации. " +e.getMessage(), Snackbar.LENGTH_SHORT);
                                            }
                                        });
                            }

                        });
            }

        });

        dialog.show();

    }
}


