package org.izv.ad.aurbano.flora.view.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import org.izv.ad.aurbano.flora.R;
import org.izv.ad.aurbano.flora.model.entity.Flora;
import org.izv.ad.aurbano.flora.view.EditActivity;
import org.izv.ad.aurbano.flora.view.MainActivity;
import org.izv.ad.aurbano.flora.view.adapter.viewholder.FloraViewHolder;

import java.util.List;

public class FloraAdapter extends RecyclerView.Adapter<FloraViewHolder>{

    private List<Flora> floraList;
    private Context context;
    private AlertDialog.Builder alertDialog;

    public FloraAdapter(Context context){
        this.context = context;
    }

    @NonNull
    @Override
    public FloraViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_flora, parent, false);
        return new FloraViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FloraViewHolder holder, int position) {

        alertDialog = new AlertDialog.Builder(context);

        Flora flora = floraList.get(position);
        holder.flora = flora;

        String urlImage = "https://informatica.ieszaidinvergeles.org:10018/ad/felixRDLFApp/public/api/imagen/" + flora.getId() + "/flora";
        String defaultImage = "https://informatica.ieszaidinvergeles.org:10018/ad/felixRDLFApp/public/storage/images/noimageflora.jpeg";

        Glide.with(context).load(urlImage).into(holder.ivImage);

        /*if(holder.ivImage.getDrawable() == null) {
            Glide.with(context).load(defaultImage).into(holder.ivImage);
        }*/

        holder.tvNombre.setText(flora.getNombre());
        holder.tvId.setText(String.valueOf(flora.getId()));

        holder.ivImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.setTitle(holder.tvNombre.getText()).setPositiveButton("edit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        context.startActivity(new Intent(context, EditActivity.class));
                    }
                }).setNegativeButton("cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                }).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        if(floraList == null) {
            return 0;
        }
        return floraList.size();
    }

    public void setFloraList(List<Flora> floraList) {
        this.floraList = floraList;
        notifyDataSetChanged();
    }
}
