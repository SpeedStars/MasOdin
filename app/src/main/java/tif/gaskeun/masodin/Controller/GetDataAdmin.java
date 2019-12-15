package tif.gaskeun.masodin.Controller;

import android.os.Bundle;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import tif.gaskeun.masodin.Model.RestaurantData;
import tif.gaskeun.masodin.R;

public class GetDataAdmin extends AppCompatActivity {

    ListView listView;
    FirebaseDatabase database;
    DatabaseReference reff;
    ArrayList<RestaurantData> list;
    ArrayAdapter<RestaurantData> adapter;
    RestaurantData resto;

    @Override
    public void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_getdataadmin);

        listView = (ListView) findViewById(R.id.listviewadmin);
        database = FirebaseDatabase.getInstance();
        reff = database.getReference("RestaurantData");
        list = new ArrayList<>();
        resto = new RestaurantData();
        adapter = new ArrayAdapter<RestaurantData>(this, R.layout.datalayout,R.id.datalayout, list);
        reff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot ds: dataSnapshot.getChildren()){
                    resto = ds.getValue(RestaurantData.class);
                    list.add(resto);
                }
                listView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
