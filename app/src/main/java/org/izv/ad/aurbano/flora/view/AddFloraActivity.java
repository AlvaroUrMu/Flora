package org.izv.ad.aurbano.flora.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.Observer;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.izv.ad.aurbano.flora.R;
import org.izv.ad.aurbano.flora.model.entity.Flora;
import org.izv.ad.aurbano.flora.viewmodel.AddFloraViewModel;
import org.izv.ad.aurbano.flora.viewmodel.MainActivityViewModel;

import java.util.List;

public class AddFloraActivity extends AppCompatActivity {

    private EditText etNombre;
    private Button btAdd;
    AddFloraViewModel avm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_flora);

        initialize();

        btAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Flora flora = new Flora();
                flora.setNombre(etNombre.getText().toString());
                avm.createFlora(flora);
            }
        });
    }

    private void initialize() {
        etNombre = findViewById(R.id.etNombreImagen);
        btAdd = findViewById(R.id.btAdd);

        avm = new ViewModelProvider(this).get(AddFloraViewModel.class);
        avm.getAddFloraLiveData().observe(this, new Observer<Long>() {
            @Override
            public void onChanged(Long aLong) {
                if(aLong > 0) {
                    finish();
                } else {
                    Toast.makeText(AddFloraActivity.this, "Error", Toast.LENGTH_LONG).show();
                }
            }
        });

        /*MainActivityViewModel mavm = new ViewModelProvider(this).get(MainActivityViewModel.class);
        MutableLiveData<List<Flora>> floraList = mavm.getFloraLiveData();
        mavm.getFlora();*/
    }
}