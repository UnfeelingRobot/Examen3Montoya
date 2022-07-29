package com.example.examen3montoya;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.ArrayList;


public class PictureFragment extends Fragment {

    private Button photoButton;
    private RecyclerView recyclerView;
    private PictureAdapter pictureAdapter;
    private ArrayList<PictureModel> pictureModel;
    View vista;
    public PictureFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        getData();
        vista=inflater.inflate(R.layout.fragment_picture, container, false);
        recyclerView = vista.findViewById(R.id.picture_recycler_view);
        pictureAdapter = new PictureAdapter(pictureModel);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(pictureAdapter);

        // Inflate the layout for this fragment
        return vista;
    }

    private void getData(){
        pictureModel = new ArrayList<>();
        pictureModel.add(new PictureModel("Flowers", "https://images.unsplash.com/photo-1575995872537-3793d29d972c?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=724&q=80"));
        pictureModel.add(new PictureModel("City", "https://images.unsplash.com/photo-1579762593155-42faee39d0b4?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1229&q=80"));
        pictureModel.add(new PictureModel("Beach", "https://images.unsplash.com/photo-1580196923194-ddad5b516c88?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1311&q=80"));
        pictureModel.add(new PictureModel("Couple of birds", "https://images.unsplash.com/photo-1578762915100-7a2d602a09bc?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1062&q=80"));
        pictureModel.add(new PictureModel("Pig", "https://images.unsplash.com/photo-1584446922609-d494912c35c5?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=759&q=80"));
        pictureModel.add(new PictureModel("Girl with notebook", "https://i.pinimg.com/564x/49/9b/5f/499b5f0743bad66022fc9bcdb365da95.jpg"));
        pictureModel.add(new PictureModel("Guitar", "https://i.pinimg.com/564x/6e/f0/07/6ef007f14505fe74758cc401479b9217.jpg"));
        pictureModel.add(new PictureModel("Eyes", "https://i.pinimg.com/564x/c7/b4/e6/c7b4e6af8869bf41a1fe986d2d48b6fa.jpg"));
        pictureModel.add(new PictureModel("Cats", "https://i.pinimg.com/564x/f4/e5/d2/f4e5d27e06403e344d6bd07710ac5169.jpg"));
        pictureModel.add(new PictureModel("Apple Cat", "https://i.pinimg.com/564x/57/00/71/57007131f7c54918a9d3bb7cfe479031.jpg"));
        pictureModel.add(new PictureModel("Girls", "https://i.pinimg.com/564x/cc/45/08/cc45081d0a1f897952ab71cc007b4c51.jpg"));


    }
}