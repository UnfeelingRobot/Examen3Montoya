package com.example.examen3montoya;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class PictureAdapter extends RecyclerView.Adapter<PictureAdapter.ViewHolder> {

    private ArrayList<PictureModel> pictureModel;
    public PictureAdapter(ArrayList<PictureModel> pictureModel) {
        this.pictureModel = pictureModel;
    }
    @NonNull
    @Override
    public PictureAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.picture_item, parent, false);
        ViewHolder vh = new ViewHolder(view);
        return vh;
    }
    @Override
    public void onBindViewHolder(@NonNull PictureAdapter.ViewHolder holder, int position) {
        holder.pictureName.setText(pictureModel.get(position).getPictureName());
        Glide.with(holder.itemView.getContext()).load(pictureModel.get(position).getPicturePhoto()).into(holder.picturePhoto);;
    }

    @Override
    public int getItemCount() {
        return pictureModel.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView pictureName;
        ImageView picturePhoto;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            pictureName = itemView.findViewById(R.id.picture_name);
            picturePhoto = itemView.findViewById(R.id.picture_photo);
        }
    }
}






