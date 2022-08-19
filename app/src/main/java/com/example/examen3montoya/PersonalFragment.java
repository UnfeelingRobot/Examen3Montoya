package com.example.examen3montoya;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.ArrayList;

public class PersonalFragment extends Fragment {

    private Button photoButton;
    private RecyclerView recyclerView;
    private PictureAdapter pictureAdapter;
    private ArrayList<PictureModel> pictureModel;
    View vista;

    public PersonalFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        getData();
        vista=inflater.inflate(R.layout.fragment_personal, container, false);
        recyclerView = vista.findViewById(R.id.picture_recycler_view);
        pictureAdapter = new PictureAdapter(pictureModel);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(pictureAdapter);

        return vista;
    }


    private void getData(){
        pictureModel = new ArrayList<>();
        pictureModel.add(new PictureModel("Alejandra Torres", "https://i.pinimg.com/564x/26/0c/10/260c102ea8ce941387ab5b5fdc4d7797.jpg"));
        pictureModel.add(new PictureModel("Fernanda Nole", "https://i.pinimg.com/564x/b0/b4/e3/b0b4e3f5d153ba8d070d072228faf8bb.jpg"));
        pictureModel.add(new PictureModel("Iris Paredes", "https://i.pinimg.com/564x/99/e5/5d/99e55d6e6f5e32472a72d28a4a593f0c.jpg"));
        pictureModel.add(new PictureModel("Laura Santillen", "https://i.pinimg.com/564x/f5/cf/89/f5cf89ffc4ef31f7cedba3fcdc9f79c1.jpg"));

    }
}