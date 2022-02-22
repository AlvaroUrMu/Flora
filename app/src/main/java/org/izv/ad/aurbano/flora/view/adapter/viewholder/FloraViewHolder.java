package org.izv.ad.aurbano.flora.view.adapter.viewholder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.izv.ad.aurbano.flora.R;
import org.izv.ad.aurbano.flora.model.entity.Flora;

public class FloraViewHolder extends RecyclerView.ViewHolder {

    public Flora flora;
    public ImageView ivImage;
    public TextView tvNombre, tvId;

    public FloraViewHolder(@NonNull View itemView) {
        super(itemView);

        ivImage = itemView.findViewById(R.id.ivImage);
        tvNombre = itemView.findViewById(R.id.tvNombre);
        tvId = itemView.findViewById(R.id.tvId);
    }
}
