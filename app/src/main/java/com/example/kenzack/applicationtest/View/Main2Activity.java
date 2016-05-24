package com.example.kenzack.applicationtest.View;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.kenzack.applicationtest.R;
import com.example.kenzack.applicationtest.model.MyApplication;
import com.example.kenzack.applicationtest.model.Session;
import com.example.kenzack.applicationtest.model.Utilisateur;
import com.example.kenzack.applicationtest.service.AuthentificationService;

public class Main2Activity extends AppCompatActivity {
    String Extra_login="user_login",Extra_pass="";
    EditText login , pass;


    private void initSession(Session session){
        MyApplication myApplication = (MyApplication)this.getApplication();
        myApplication.setSession(session);
    }
    Button cnx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        cnx =(Button)findViewById(R.id.button2);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        login=(EditText)findViewById(R.id.editText);
        pass=(EditText)findViewById(R.id.editText2);
        final AlertDialog.Builder abd = new AlertDialog.Builder(this);
        abd.setTitle("erreur");
        abd.setPositiveButton("ok", null);
        cnx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Extra_login = login.getText().toString();
                Extra_pass = pass.getText().toString();
                AuthentificationService authentificationService = new AuthentificationService();
                String erreurs = authentificationService.authentifier(Extra_login, Extra_pass);
                if (erreurs.isEmpty()) {
                    Session session = new Session();
                    Utilisateur u = new Utilisateur();
                    u.setLogin(Extra_login);
                    session.setUtilisateur(u);
                    initSession(session);
                    Intent intent = new Intent(Main2Activity.this, MainActivity.class);
                    intent.putExtra(Extra_login, login.getText().toString());
                    startActivity(intent);
                }else {
                    abd.setMessage("login ou mot de passe erroné");
                    abd.show();
                    //afficher un message d'erreur
                }

            }
        });


    }
}
