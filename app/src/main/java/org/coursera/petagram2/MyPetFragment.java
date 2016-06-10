package org.coursera.petagram2;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Created by rodomualdo on 26/05/2016.
 */
public class MyPetFragment extends Fragment {

    ArrayList<Pet> myPet;
    private RecyclerView rvMyPet;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_my_pet, container, false);
        // RecyclerView
        rvMyPet = (RecyclerView) v.findViewById(R.id.rvMyPet);

        //Layout Manager
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);

        StaggeredGridLayoutManager sglm = new StaggeredGridLayoutManager(2,1);

        // Set Layout Manager
        rvMyPet.setLayoutManager(sglm);

        // Hardcoded data
        myPet = new ArrayList<>();
        myPet.add(new Pet(1, R.drawable.dog, "Nisha", 0));
        myPet.add(new Pet(1, R.drawable.dog2, "Nisha", 0));
        myPet.add(new Pet(1, R.drawable.dog, "Nisha", 0));
        myPet.add(new Pet(1, R.drawable.dog2, "Nisha", 0));
        myPet.add(new Pet(1, R.drawable.dog, "Nisha", 0));
        myPet.add(new Pet(1, R.drawable.dog2, "Nisha", 0));
        myPet.add(new Pet(1, R.drawable.dog, "Nisha", 0));
        myPet.add(new Pet(1, R.drawable.dog2, "Nisha", 0));
        myPet.add(new Pet(1, R.drawable.dog, "Nisha", 0));

        // Adapter instantiation
        MyPetAdapter myPetAdapter = new MyPetAdapter(myPet);
        rvMyPet.setAdapter(myPetAdapter);


        return v;
    }
}
