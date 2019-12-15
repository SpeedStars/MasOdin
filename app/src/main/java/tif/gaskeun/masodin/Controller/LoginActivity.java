package tif.gaskeun.masodin.Controller;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import tif.gaskeun.masodin.R;

public class LoginActivity extends AppCompatActivity {

    FirebaseAuth dbAuth;
    EditText iUname,iPswd;
    Button btnSignin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        iUname = (EditText)findViewById(R.id.et_uname);
        iPswd = (EditText)findViewById(R.id.et_pswd);
        btnSignin = (Button)findViewById(R.id.btn_signin);
        dbAuth = FirebaseAuth.getInstance();

        btnSignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = iUname.getText().toString().trim();
                String passwd = iPswd.getText().toString().trim();
                if(TextUtils.isEmpty(email)){
                    iUname.setError("Please Fill The Email");
                    return;
                }if(passwd.length()<6){
                    iPswd.setError("Minimum Password Length is 6 Characters");
                }

                dbAuth.signInWithEmailAndPassword(email,passwd).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText( LoginActivity.this, "SignIn Success", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), Dashboard.class));
                        }else{
                            Toast.makeText(LoginActivity.this, "Error" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }
}