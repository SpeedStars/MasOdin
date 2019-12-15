package tif.gaskeun.masodin.Controller;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.toptoche.searchablespinnerlibrary.SearchableSpinner;
import java.util.ArrayList;
import java.util.List;

import tif.gaskeun.masodin.Model.RestaurantData;
import tif.gaskeun.masodin.R;

public class RestSelector extends AppCompatActivity implements IFirebaseLoadDone {

    //Database
        //Init Database
    DatabaseReference dbRef;
    IFirebaseLoadDone iFirebaseLoadDone;
    //Model
        //Init Model
    List<RestaurantData> list;
    //Toosl
    SearchableSpinner restSpinner;

    TextView about;
    Button button;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        iFirebaseLoadDone = this;

        //Component
        restSpinner = (SearchableSpinner)findViewById(R.id.spnResto);

        //DB Reference
        dbRef = FirebaseDatabase.getInstance().getReference("RestaurantData");
        dbRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                List<RestaurantData> restaurantData = new ArrayList<>();
                for(DataSnapshot restaurantDataSnapshot:dataSnapshot.getChildren()){
                    restaurantData.add(restaurantDataSnapshot.getValue(RestaurantData.class));
                }
                iFirebaseLoadDone.onFirebaseLoadSuccess(restaurantData);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                iFirebaseLoadDone.onFirebaseLoadFailed(databaseError.getMessage());
            }
        });

        about = (TextView) findViewById(R.id.about);
        about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
            }
        });

        button = (Button)findViewById(R.id.buttonOK);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), CustomrMenuListActivity.class));
            }
        });


    }

    @Override
    public void onFirebaseLoadSuccess(List<RestaurantData> rList) {
        list= rList;
        List<String> rest_list = new ArrayList<>();
        for(RestaurantData restaurantData:rList ){
            rest_list.add(restaurantData.getName());
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,rest_list);
        restSpinner.setAdapter(adapter);
    }

    @Override
    public void onFirebaseLoadFailed(String Message) {
        Message = "Database Load Failed";
    }
}
