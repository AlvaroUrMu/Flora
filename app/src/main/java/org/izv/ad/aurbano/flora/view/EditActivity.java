package org.izv.ad.aurbano.flora.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import org.izv.ad.aurbano.flora.R;
import org.izv.ad.aurbano.flora.model.entity.Flora;
import org.izv.ad.aurbano.flora.viewmodel.MainActivityViewModel;

import java.util.ArrayList;

public class EditActivity extends AppCompatActivity {

    private EditText etEditNombre;
    private Button btEditConfirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        init();

        btEditConfirm.setOnClickListener(view -> {

        });
    }

    private void init() {
        etEditNombre = findViewById(R.id.etEditNombre);
        btEditConfirm = findViewById(R.id.btEditConfirm);

        MainActivityViewModel mavm = new ViewModelProvider(this).get(MainActivityViewModel.class);
        LiveData<ArrayList<Flora>> floraList = mavm.getFloraLiveData();

        floraList.observe(this, floraPlural -> {
            
        });

        mavm.getFlora();
    }
}