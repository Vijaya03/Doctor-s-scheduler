package com.example.sana.medicalapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.sana.medicalapp.dbutil.MedicalConstant;
import com.example.sana.medicalapp.dbutil.MedicalManager;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

public class MainActivity extends AppCompatActivity {
    EditText username,password;
    SharedPreferences sp;
    SharedPreferences.Editor se;
    MedicalManager medicalManager;
    SQLiteDatabase sqLiteDatabase;
    String email1,pass1;
    SignInButton signInButton;
    FirebaseAuth mAuth;
    private final static int RC_SIGN_IN=2;
    GoogleApiClient mGoogleApiClient;
    FirebaseAuth.AuthStateListener mAuthListener;

    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        username=findViewById(R.id.username);
        password=findViewById(R.id.password);
        signInButton=findViewById(R.id.google_signIn);
        mAuth = FirebaseAuth.getInstance();
        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signIn();
            }
        });
        mAuthListener=new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if(firebaseAuth.getCurrentUser()!=null)
                {
                    Intent intent=new Intent(MainActivity.this,InfoCenter.class);
                    startActivity(intent);
                }
            }
        };
        sp=getSharedPreferences("info",MODE_PRIVATE);
        se=sp.edit();
        medicalManager=new MedicalManager(this);
        sqLiteDatabase=medicalManager.openDB();

        GoogleSignInOptions gso=new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestIdToken(getString(R.string.default_web_client_id)).build();
        mGoogleApiClient=new GoogleApiClient.Builder(this).enableAutoManage(this, new GoogleApiClient.OnConnectionFailedListener() {
            @Override
            public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
                Toast.makeText(MainActivity.this,"Something went wrong",Toast.LENGTH_SHORT).show();
            }
        }).addApi(Auth.GOOGLE_SIGN_IN_API,gso).build();

    }
//    GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
//            .requestIdToken(getString(R.string.default_web_client_id))
//            .requestEmail()
//            .build();
    private void signIn() {
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(signInIntent,RC_SIGN_IN);
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            GoogleSignInAccount account = result.getSignInAccount();
            firebaseAuthWithGoogle(account);
        }
        else{
            Toast.makeText(MainActivity.this,"Auth went wrong",Toast.LENGTH_SHORT).show();
        }
    }

    private void firebaseAuthWithGoogle(GoogleSignInAccount account) {
        AuthCredential credential = GoogleAuthProvider.getCredential(account.getIdToken(), null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("TAG", "signInWithCredential:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            //updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("TAG", "signInWithCredential:failure", task.getException());
                            Toast.makeText(MainActivity.this,"Authentication Failed",Toast.LENGTH_SHORT).show();
                            //updateUI(null);
                        }

                        // ...
                    }
                });
    }

    public void signIn(View view) {
        String name=username.getText().toString();
        String pass=password.getText().toString();
        if(TextUtils.isEmpty(name)|| TextUtils.isEmpty(pass))
        {
            if(TextUtils.isEmpty(name)){
                username.setError("Enter name");
                username.requestFocus();
        }
            if(TextUtils.isEmpty(pass)){
                password.setError("Enter name");
                password.requestFocus();
            }
        }
        se.putString("name",name);
        se.putString("pass",pass);
        se.commit();
        String nm=sp.getString("name",null);
        String ps=sp.getString("pass",null);
//        Cursor c=sqLiteDatabase.query(MedicalConstant.TABLE_NAME,null,null,null,null,null,null);
//        if(c!=null && c.moveToFirst()) {
//            do {
//                email1 = c.getString(c.getColumnIndex(MedicalConstant.EMAIL));
//                pass1 = c.getString(c.getColumnIndex(MedicalConstant.PASSWORD));
//                //Toast.makeText(this, "email=" + email1 + "pass=" + pass1, Toast.LENGTH_SHORT).show();
//            }
//            while (c.moveToNext());
//        }
        if(nm.equals("sanashahin2225@gmail.com") && ps.equals("Sana@2225vishal")) {
            Toast.makeText(this, "Successful", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(this, InfoCenter.class);
            startActivity(intent);
        }
        else
        {
            Toast.makeText(this,"Please Enter correct Email and Password",Toast.LENGTH_SHORT).show();
        }
    }
}
