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

import tif.gaskeun.masodin.Model.Menu;
import tif.gaskeun.masodin.Model.RestaurantData;
import tif.gaskeun.masodin.R;

public class AddMenu extends AppCompatActivity {
    // variable yang merefers ke Firebase Realtime Database
    private DatabaseReference database;

    // variable fields EditText dan Button
    private Button btSubmitMakanan;
    private EditText etNamaMakanan;
    private EditText etKategori;
    private EditText etHargaMakanan;
    private EditText etDesc;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        // inisialisasi fields EditText dan Button
        etNamaMakanan = (EditText) findViewById(R.id.et_namamenu);
        etKategori = (EditText) findViewById(R.id.spn_kategori);
        etDesc = (EditText) findViewById(R.id.et_desc);
        etHargaMakanan = (EditText) findViewById(R.id.et_harga);
        btSubmitMakanan = (Button) findViewById(R.id.bt_submit);

        // mengambil referensi ke Firebase Database
        database = FirebaseDatabase.getInstance().getReference();

        // kode yang dipanggil ketika tombol Submit diklik
        btSubmitMakanan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!isEmpty(etNamaMakanan.getText().toString()) && !isEmpty(etKategori.getText().toString()) && !isEmpty(etDesc.getText().toString()) && !isEmpty(etHargaMakanan.getText().toString()) )
                    addmenu(new Menu(etNamaMakanan.getText().toString(), etKategori.getText().toString(), etDesc.getText().toString(), etHargaMakanan.getText().toString()));
                else
                    Snackbar.make(findViewById(R.id.bt_submit), "Data tidak boleh kosong", Snackbar.LENGTH_LONG).show();

                InputMethodManager imm = (InputMethodManager)
                        getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(
                        etNamaMakanan.getWindowToken(), 0);
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

    private void addmenu(Menu menu) {
        /**
         * Ini adalah kode yang digunakan untuk mengirimkan data ke Firebase Realtime Database
         * dan juga kita set onSuccessListener yang berisi kode yang akan dijalankan
         * ketika data berhasil ditambahkan
         */
        database.child("RestaurantData").child("Menu").child("-Lw72jaUvhCWKx_sIrFn").push().setValue(menu).addOnSuccessListener(this, new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                etNamaMakanan.setText("");
                etKategori.setText("");
                etDesc.setText("");
                etHargaMakanan.setText("");
                Snackbar.make(findViewById(R.id.bt_submit), "Data berhasil ditambahkan", Snackbar.LENGTH_LONG).show();
            }
        });
    }

    public static Intent getActIntent(Activity activity) {
        // kode untuk pengambilan Intent
        return new Intent(activity, AddMenu.class);
    }
}
