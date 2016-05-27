package org.coursera.petagram2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Created by rodomualdo on 26/05/2016.
 */
public class PetsFragment extends Fragment {

    public ArrayList<Pet> pets;
    private RecyclerView rvPets;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);
        View v = inflater.inflate(R.layout.fragment_pets, container, false);

        // RecyclerView
        rvPets = (RecyclerView) v.findViewById(R.id.rvPets);

        //Layout Manager
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);

        //StaggeredGridLayoutManager sglm = new StaggeredGridLayoutManager(2,1);

        // Set Layout Manager
        rvPets.setLayoutManager(llm);

        // Hardcoded data
        pets = new ArrayList<>();
        pets.add(new Pet(R.drawable.dog, "Matías", 0));
        pets.add(new Pet(R.drawable.dog2, "Nisha", 0));
        pets.add(new Pet(R.drawable.dog, "Sebastián", 0));
        pets.add(new Pet(R.drawable.dog2, "Pirata", 0));
        pets.add(new Pet(R.drawable.dog, "Señora Gorda", 0));
        pets.add(new Pet(R.drawable.dog2, "Lola", 0));
        pets.add(new Pet(R.drawable.dog, "Rocky", 0));
        pets.add(new Pet(R.drawable.dog2, "Fifí", 0));
        pets.add(new Pet(R.drawable.dog, "Doby", 0));

        // Adapter instantiation
        PetAdapter petAdapter = new PetAdapter(pets);
        rvPets.setAdapter(petAdapter);


        return v;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.mContact:
                break;
            case R.id.mAbout:
                break;
            case R.id.mStar:
                Intent intent = new Intent(getActivity(), BestFive.class);
                intent = AddBestFive(intent);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public Intent AddBestFive(Intent intent){
        int[] ordered_by_rating = new int[pets.size()];
        for (int i=0;i<ordered_by_rating.length;i++){
            ordered_by_rating[i] = i;
        }
        for (int i=0;i<pets.size();i++){
            for (int j=0;j<pets.size();j++){
                if (pets.get(ordered_by_rating[i]).getRate() >= pets.get(ordered_by_rating[j]).getRate()){
                    int temp = ordered_by_rating[i];
                    ordered_by_rating[i] = ordered_by_rating[j];
                    ordered_by_rating[j] = temp;
                }
            }
        }
        for (int i=0;i<5;i++){
            intent.putExtra("foto" + i, pets.get(ordered_by_rating[i]).getFoto());
            intent.putExtra("name" + i, pets.get(ordered_by_rating[i]).getName());
            intent.putExtra("rate" + i, pets.get(ordered_by_rating[i]).getRate());
        }
        return intent;
    }
}
