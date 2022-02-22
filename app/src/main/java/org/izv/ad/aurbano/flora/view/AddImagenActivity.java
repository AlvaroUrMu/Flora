package org.izv.ad.aurbano.flora.view;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.izv.ad.aurbano.flora.R;
import org.izv.ad.aurbano.flora.model.entity.Imagen;
import org.izv.ad.aurbano.flora.viewmodel.AddImagenViewModel;

public class AddImagenActivity extends AppCompatActivity {

    private ActivityResultLauncher<Intent> launcher;
    private Intent resultadoImagen = null;
    private EditText etIdFlora, etNombre, etDescripcion;
    private AddImagenViewModel aivm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_imagen);

        initialize();
    }

    private void initialize() {
        launcher = getLauncher();

        etIdFlora = findViewById(R.id.etIdFlora);
        etNombre = findViewById(R.id.etNombreImagen);
        etDescripcion = findViewById(R.id.etDescripcion);

        Button btSelectImage = findViewById(R.id.btSelectImage);
        btSelectImage.setOnClickListener(view -> {
            selectImage();
        });

        Button btAddImage = findViewById(R.id.btAddImage);
        btAddImage.setOnClickListener(view -> {
            uploadDataImage();
        });

        aivm = new ViewModelProvider(this).get(AddImagenViewModel.class);
    }

    private void uploadDataImage() {
        String idFlora = etIdFlora.getText().toString();
        String nombre = etNombre.getText().toString();
        String descripcion = etDescripcion.getText().toString();

        if(!(nombre.trim().isEmpty() || idFlora.trim().isEmpty() || resultadoImagen == null)) {
            Imagen imagen = new Imagen();
            imagen.idflora = Long.parseLong(idFlora);
            imagen.nombre = nombre;
            imagen.descripcion = descripcion;
            aivm.saveImagen(resultadoImagen, imagen);
        }
    }

    ActivityResultLauncher<Intent> getLauncher() {
        return registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    // Respuesta al resultado de haber seleccionado una imagen
                    if(result.getResultCode() == Activity.RESULT_OK) {
                        //copyData(result.getData());
                        resultadoImagen = result.getData();
                    }
                }
        );
    }

    Intent getContentIntent() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setType("image/*");
        return intent;
    }

    void selectImage() {
        Intent intent = getContentIntent();
        launcher.launch(intent);
        //getLauncher().launch(getContentIntent());
    }
}