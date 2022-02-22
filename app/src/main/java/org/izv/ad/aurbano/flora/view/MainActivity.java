package org.izv.ad.aurbano.flora.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.izv.ad.aurbano.flora.R;
import org.izv.ad.aurbano.flora.model.entity.CreateResponse;
import org.izv.ad.aurbano.flora.model.entity.RowsResponse;
import org.izv.ad.aurbano.flora.model.entity.Flora;
import org.izv.ad.aurbano.flora.model.api.FloraClient;
import org.izv.ad.aurbano.flora.view.adapter.FloraAdapter;
import org.izv.ad.aurbano.flora.viewmodel.MainActivityViewModel;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private FloraClient floraClient;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        initialize();
    }

    private void initialize() {

        FloatingActionButton fabAdd = findViewById(R.id.fabAdd);
        fabAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openAddActivity();
            }
        });

        FloatingActionButton fabImagen = findViewById(R.id.fabImagen);
        fabImagen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openAddImagenActivity();
            }
        });

        RecyclerView rvFlora = findViewById(R.id.rvFlora);
        rvFlora.setLayoutManager(new LinearLayoutManager(this));

        FloraAdapter floraAdapter = new FloraAdapter(this);
        rvFlora.setAdapter(floraAdapter);

        MainActivityViewModel mavm = new ViewModelProvider(this).get(MainActivityViewModel.class);
        LiveData<ArrayList<Flora>> floraList = mavm.getFloraLiveData();

        floraList.observe(this, floraPlural -> {
            floraAdapter.setFloraList(mavm.getFloraLiveData().getValue());
            rvFlora.setAdapter(floraAdapter);
        });

        mavm.getFlora();

        /*Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://informatica.ieszaidinvergeles.org:10018/ad/felixRDLFApp/public/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        floraClient = retrofit.create(FloraClient.class);

        Call<ArrayList<Flora>> call = floraClient.getFlora();
        call.enqueue(new Callback<ArrayList<Flora>>() {
            @Override
            public void onResponse(Call<ArrayList<Flora>> call, Response<ArrayList<Flora>> response) {
                Log.v("abc", response.body().toString());
            }

            @Override
            public void onFailure(Call<ArrayList<Flora>> call, Throwable t) {
                Log.v("abc", t.getLocalizedMessage());
            }
        });

        Call<Flora> call2 = floraClient.getFlora(12);
        call2.enqueue(new Callback<Flora>() {
            @Override
            public void onResponse(Call<Flora> call, Response<Flora> response) {
                Log.v("bcd", response.body().toString());
            }

            @Override
            public void onFailure(Call<Flora> call, Throwable t) {
                Log.v("bcd", t.getLocalizedMessage());
            }
        });

        Flora flora = new Flora();
        flora.setNombre("flor");
        Call<CreateResponse> call3 = floraClient.createFlora(flora);
        call3.enqueue(new Callback<CreateResponse>() {
            @Override
            public void onResponse(Call<CreateResponse> call, Response<CreateResponse> response) {
                Log.v("cde", response.body().toString());
            }

            @Override
            public void onFailure(Call<CreateResponse> call, Throwable t) {
                Log.v("cde", t.getLocalizedMessage());
            }
        });

        flora.setNombre("pepe");
        flora.setAltitud("alta altitud");
        Call<RowsResponse> call4 = floraClient.editFlora(14, flora);
        call4.enqueue(new Callback<RowsResponse>() {
            @Override
            public void onResponse(Call<RowsResponse> call, Response<RowsResponse> response) {
                Log.v("def", response.body().toString());
            }

            @Override
            public void onFailure(Call<RowsResponse> call, Throwable t) {
                Log.v("def", t.getLocalizedMessage());
            }
        });

        Call<RowsResponse> call5 = floraClient.deleteFlora(15);
        call5.enqueue(new Callback<RowsResponse>() {
            @Override
            public void onResponse(Call<RowsResponse> call, Response<RowsResponse> response) {
                Log.v("efg", response.body().toString());
            }

            @Override
            public void onFailure(Call<RowsResponse> call, Throwable t) {
                Log.v("efg", t.getLocalizedMessage());
            }
        });*/
    }

    private void openAddActivity(){
        Intent intent = new Intent(this, AddFloraActivity.class);
        startActivity(intent);
    }

    private void openAddImagenActivity(){
        Intent intent = new Intent(this, AddImagenActivity.class);
        startActivity(intent);
    }
}