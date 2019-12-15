package tif.gaskeun.masodin.Controller;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import tif.gaskeun.masodin.R;

public class Dashboard extends AppCompatActivity{

        Button btn_vrestAdd,btn_vmenuAdd, btn_adminAdd;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        btn_vrestAdd = (Button)findViewById(R.id.btn_restadd);
        btn_vmenuAdd = (Button)findViewById(R.id.btn_menuadd);
        btn_adminAdd = (Button) findViewById(R.id.bt_addadmin);

        btn_vrestAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),RestAdder.class));
            }
        });
        btn_vmenuAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),AddMenu.class));
            }
        });
        btn_adminAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),ActivityAddAcount.class));
            }
        });

    }
}
