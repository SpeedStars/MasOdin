package tif.gaskeun.masodin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import tif.gaskeun.masodin.Controller.GetDataAdmin;
import tif.gaskeun.masodin.Controller.RestSelector;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        startActivity(new Intent(getApplicationContext(), RestSelector.class));
    };
}
