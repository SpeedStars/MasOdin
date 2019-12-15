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

import tif.gaskeun.masodin.Model.RestaurantData;
import tif.gaskeun.masodin.R;

public class RestAdder extends AppCompatActivity {

    // variable yang merefers ke Firebase Realtime Database
    private DatabaseReference database;

    // variable fields EditText dan Button
    private Button btSubmit;
    private EditText etNama;
    private EditText etAlamat;
    private EditText etNotelp;
    private EditText etEmail;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rest_adder);

        // inisialisasi fields EditText dan Button
        etNama = (EditText) findViewById(R.id.et_namarestoran);
        etAlamat = (EditText) findViewById(R.id.et_alamatrestoran);
        etNotelp = (EditText) findViewById(R.id.et_telprestoran);
        etEmail = (EditText) findViewById(R.id.et_emailrestoran);
        btSubmit = (Button) findViewById(R.id.bt_submit);

        // mengambil referensi ke Firebase Database
        database = FirebaseDatabase.getInstance().getReference();

        // kode yang dipanggil ketika tombol Submit diklik
        btSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!isEmpty(etNama.getText().toString()) && !isEmpty(etAlamat.getText().toString()) && !isEmpty(etNotelp.getText().toString()) && !isEmpty(etEmail.getText().toString()))
                    submitBarang(new RestaurantData(etAlamat.getText().toString(), etEmail.getText().toString(), etNama.getText().toString(), etNotelp.getText().toString()));
                else
                    Snackbar.make(findViewById(R.id.bt_submit), "Data tidak boleh kosong", Snackbar.LENGTH_LONG).show();

                InputMethodManager imm = (InputMethodManager)
                        getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(
                        etNama.getWindowToken(), 0);
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

    private void submitBarang(RestaurantData restoran) {
        /**
         * Ini adalah kode yang digunakan untuk mengirimkan data ke Firebase Realtime Database
         * dan juga kita set onSuccessListener yang berisi kode yang akan dijalankan
         * ketika data berhasil ditambahkan
         */
        database.child("RestaurantData").push().setValue(restoran).addOnSuccessListener(this, new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                etAlamat.setText("");
                etNotelp.setText("");
                etEmail.setText("");
                etNama.setText("");
                Snackbar.make(findViewById(R.id.bt_submit), "Data berhasil ditambahkan", Snackbar.LENGTH_LONG).show();
            }
        });
    }

    public static Intent getActIntent(Activity activity) {
        // kode untuk pengambilan Intent
        return new Intent(activity, RestAdder.class);
    }
}
