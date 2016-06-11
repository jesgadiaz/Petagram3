package org.coursera.petagram2;

import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by rodomualdo on 26/05/2016.
 */
public class PetAdapter extends RecyclerView.Adapter<PetAdapter.PetViewHolder>{

    ArrayList<Pet> pets;
    PetsFragment fragment;

    public PetAdapter(ArrayList<Pet> pets, PetsFragment fragment){
        this.pets = pets;
        this.fragment = fragment;
    }

    public PetAdapter(ArrayList<Pet> pets){
        this.pets = pets;
    }

    @Override
    public PetViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_pet, parent, false);
        return new PetViewHolder(v);
    }

    @Override
    public void onBindViewHolder(PetViewHolder petViewHolder, final int position) {
        Pet pet = pets.get(position);
        petViewHolder.ivPhoto.setImageResource(pet.getFoto());
        petViewHolder.tvName.setText(pet.getName());
        petViewHolder.tvRate.setText(Integer.toString(pet.getRate()));

        petViewHolder.btnRate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pets.get(position).setRate(pets.get(position).getRate() + 1);
                notifyItemChanged(position);

                fragment.onClickLikes(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return pets.size();
    }

    public static class PetViewHolder extends RecyclerView.ViewHolder{

        private ImageView ivPhoto;
        private TextView tvName;
        private TextView tvRate;
        private ImageButton btnRate;

        public PetViewHolder(View itemView) {
            super(itemView);
            ivPhoto = (ImageView) itemView.findViewById(R.id.ivPhoto);
            tvName = (TextView) itemView.findViewById(R.id.tvName);
            tvRate = (TextView) itemView.findViewById(R.id.tvRate);
            btnRate = (ImageButton) itemView.findViewById(R.id.btnRate);
        }
    }

}