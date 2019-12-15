package tif.gaskeun.masodin.Controller;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import tif.gaskeun.masodin.Model.Account;
import tif.gaskeun.masodin.Model.RestaurantData;
import tif.gaskeun.masodin.R;

public class ActivityAddAcount extends AppCompatActivity {

    private DatabaseReference database;

    // variable fields EditText dan Button
    private Button btAddAdmin;
    private EditText etNamaAdmin;
    private EditText etUsername;
    private EditText etPassword;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_account);

        // inisialisasi fields EditText dan Button
        etNamaAdmin = (EditText) findViewById(R.id.et_namadtaff);
        etUsername = (EditText) findViewById(R.id.et_username);
        etPassword = (EditText) findViewById(R.id.et_password);
        btAddAdmin = (Button) findViewById(R.id.bt_submitadmin);

        // mengambil referensi ke Firebase Database
        database = FirebaseDatabase.getInstance().getReference();

        // kode yang dipanggil ketika tombol Submit diklik
        btAddAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isEmpty(etNamaAdmin.getText().toString()) && !isEmpty(etUsername.getText().toString()) && !isEmpty(etPassword.getText().toString()))
                    addAdmin(new Account(etNamaAdmin.getText().toString(), etUsername.getText().toString(), etPassword.getText().toString()));
                else
                    Snackbar.make(findViewById(R.id.bt_submitadmin), "Data tidak boleh kosong", Snackbar.LENGTH_LONG).show();

                InputMethodManager imm = (InputMethodManager)
                        getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(
                        etNamaAdmin.getWindowToken(), 0);
            }
        });

    }

    private boolean isEmpty(String s) {
        // Cek apakah ada fields yang kosong, sebelum disubmit
        return TextUtils.isEmpty(s);
    }

    private void updateBarang(RestaurantData barang) {
        // kodingan untuk next tutorial ya :p
    }

    private void addAdmin(Account account) {
        /**
         * Ini adalah kode yang digunakan untuk mengirimkan data ke Firebase Realtime Database
         * dan juga kita set onSuccessListener yang berisi kode yang akan dijalankan
         * ketika data berhasil ditambahkan
         */
        database.child("RestaurantData").child("-Lw72jaUvhCWKx_sIrFn").child("UserId").push().setValue(account).addOnSuccessListener(this, new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                etNamaAdmin.setText("");
                etUsername.setText("");
                etPassword.setText("");
                Snackbar.make(findViewById(R.id.bt_submitadmin), "Data berhasil ditambahkan", Snackbar.LENGTH_LONG).show();
            }
        });
    }

    public static Intent getActIntent(Activity activity) {
        // kode untuk pengambilan Intent
        return new Intent(activity, ActivityAddAcount.class);
    }
}
