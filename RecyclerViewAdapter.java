package com.hafiz.quranproject;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    private Context context;
    private List<String> surahNameUList;


    public RecyclerViewAdapter(Context context, List<String> surahNameUList) {
        this.context = context;
        this.surahNameUList = surahNameUList;
    }

    //Where to get the single card as ViewHolder object.
    @NonNull
    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //Card kaha se aye ga
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row1, parent, false);
        return new ViewHolder(view);
    }

    //What will happen after we create the ViewHolder object.

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.ViewHolder holder, int position) {
        //row main kia rakhna hai
        String surahNameU = surahNameUList.get(position);
        holder.surahNameU.setText(surahNameU);

    }

    //How many items?
    @Override
    public int getItemCount() {
        return surahNameUList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView surahNameU;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            surahNameU = itemView.findViewById(R.id.surahNameU);
        }

        @Override
        public void onClick(View view) {
            Log.d("SurahNameE", "Clicked");
            int position = this.getAdapterPosition();

            Toast.makeText(itemView.getContext(), "Ths position is " + String.valueOf(position) , Toast.LENGTH_LONG).show();
            Intent intent = new Intent(context, SurahActivity.class);
            intent.putExtra("SURA_ID", position + 1);
            context.startActivity(intent);
        }
    }
}
