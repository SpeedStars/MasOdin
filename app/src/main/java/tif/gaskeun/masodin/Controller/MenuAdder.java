package tif.gaskeun.masodin.Controller;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.toptoche.searchablespinnerlibrary.SearchableSpinner;

import tif.gaskeun.masodin.Model.RestaurantMenu;
import tif.gaskeun.masodin.R;

import static android.text.TextUtils.isEmpty;

public class MenuAdder extends AppCompatActivity {
    private DatabaseReference database;
    private Button btnsubmit,btnchs;
    private ImageView imgprv;
    private EditText etNama,etDesc,etHarga;
    private Spinner spnKategori;
    private SearchableSpinner spnRestID;
    private Uri filePath;
    private final int PICK_IMAGE_REQUEST = 71;
    private String restKey;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_adder);

        etNama = (EditText)findViewById(R.id.et_namamenu);
        etDesc = (EditText)findViewById(R.id.et_desc);
        etHarga = (EditText)findViewById(R.id.et_harga);
        spnKategori = (Spinner)findViewById(R.id.spn_kategori);
        spnRestID = (SearchableSpinner)findViewById(R.id.spnResto);
        btnsubmit = (Button)findViewById(R.id.bt_submit);
        btnchs = (Button)findViewById(R.id.btn_chsfile);
        imgprv = (ImageView)findViewById(R.id.imgprv);

        database = FirebaseDatabase.getInstance().getReference("RestaurantData");
        FirebaseStorage firebaseStorage;
        StorageReference reference;

        btnsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                database.child("RestaurantData").orderByChild("Name").equalTo(spnRestID.getSelectedItem().toString()).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for(DataSnapshot childSnapshot: dataSnapshot.getChildren()){
                            restKey = childSnapshot.getKey().toString();
                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
                if(!isEmpty(spnRestID.getSelectedItem().toString()) && !isEmpty(etNama.getText().toString()) && !isEmpty(spnKategori.getSelectedItem().toString()) && !isEmpty(etDesc.getText().toString()) && !isEmpty(etHarga.getText().toString()))
                    submit(new RestaurantMenu(spnRestID.getSelectedItem().toString(),etNama.getText().toString(),spnKategori.getSelectedItem().toString(),etDesc.getText().toString(),etHarga.getText().toString()));
                else
                    Snackbar.make(findViewById(R.id.bt_submit), "Data tidak boleh kosong", Snackbar.LENGTH_LONG).show();

                InputMethodManager imm = (InputMethodManager)
                        getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(
                        etNama.getWindowToken(), 0);
            }
        });
//        btnchs.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                imgchoose();
//            }
//        });

    }

//    private void imgchoose() {
//        Intent intent = new Intent();
//        intent.setType("image/*");
//        intent.setAction(Intent.ACTION_GET_CONTENT);
//        startActivityForResult(Intent.createChooser(intent, "Select Picture"));
//    }
//
//    private void onActivityResult(nt requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if(requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK
//                && data != null && data.getData() != null ){
//            filePath = data.getData();
//            try {
//                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
//                imgprv.setImageBitmap(bitmap);
//            }
//            catch (IOException e)
//            {
//                e.printStackTrace();
//            }
//        };
//    }


    private void submit(RestaurantMenu restaurantMenu) {
        database.child(restKey).child("RestaurantMenu").push().setValue(restaurantMenu).addOnSuccessListener(this, new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                spnRestID.getSelectedItemPosition();
                etNama.setText("");
                spnKategori.getSelectedItemPosition();
                etDesc.setText("");
                etHarga.setText("");
                Snackbar.make(findViewById(R.id.bt_submit), "Data Berhasil Di Tambah", Snackbar.LENGTH_LONG).show();
            }
        });
    }
}
