package org.coursera.petagram2;

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
public class MyPetAdapter extends RecyclerView.Adapter<MyPetAdapter.MyPetViewHolder> {

    ArrayList<Pet> myPet;

    public MyPetAdapter(ArrayList<Pet> myPet){
        this.myPet = myPet;
    }

    @Override
    public MyPetViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_pet, parent, false);
        return new MyPetViewHolder(v);
    }

    @Override
    public void onBindViewHolder(MyPetViewHolder myPetViewHolder, final int position) {
        Pet pet = myPet.get(position);
        myPetViewHolder.ivPhoto.setImageResource(pet.getFoto());
        myPetViewHolder.tvName.setText(pet.getName());
        myPetViewHolder.tvRate.setText(Integer.toString(pet.getRate()));

        myPetViewHolder.btnRate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myPet.get(position).setRate(myPet.get(position).getRate() + 1);
                notifyItemChanged(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return myPet.size();
    }

    public static class MyPetViewHolder extends RecyclerView.ViewHolder{

        private ImageView ivPhoto;
        private TextView tvName;
        private TextView tvRate;
        private ImageButton btnRate;

        public MyPetViewHolder(View itemView) {
            super(itemView);
            ivPhoto = (ImageView) itemView.findViewById(R.id.ivPhoto);
            tvName = (TextView) itemView.findViewById(R.id.tvName);
            tvName.setTextSize(13);
            tvRate = (TextView) itemView.findViewById(R.id.tvRate);
            btnRate = (ImageButton) itemView.findViewById(R.id.btnRate);
        }
    }

}
