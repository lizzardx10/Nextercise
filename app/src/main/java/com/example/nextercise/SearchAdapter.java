package com.example.nextercise;

import static com.parse.Parse.getApplicationContext;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.nextercise.ui.ExerciseActivity;
import com.parse.ParseFile;

import java.util.ArrayList;
import java.util.List;


public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.SearchViewHolder> {

    private static final String TAG = "SearchAdapter";
    private Context context;
    private List<Exercise> exerciseList;

    public SearchAdapter(Context context, List<Exercise> exercises) {
        this.context = context;
        this.exerciseList = exercises;
    }




    @NonNull
    @Override
    public SearchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.example_searchresult, parent, false);
        SearchViewHolder svh = new SearchViewHolder(v);
        return svh;
    }

    @Override
    public void onBindViewHolder(@NonNull SearchViewHolder holder, int position) {
        Exercise currentItem = exerciseList.get(position);
        holder.bind(currentItem);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Navigate to the Exercise activity associated with the item clicked
                Intent i = new Intent(getApplicationContext(), ExerciseActivity.class);
                Log.i(TAG, String.valueOf(currentItem.getExerciseId()));
                i.putExtra("int_value", currentItem.getExerciseId());
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return exerciseList.size();
    }

    public void filterList(ArrayList<Exercise> filteredList) {
        exerciseList = filteredList;
        notifyDataSetChanged();
    }

    public class SearchViewHolder extends RecyclerView.ViewHolder {

        private ImageView ivSearchImage;
        private TextView tvSearchTitle;
        private TextView tvSearchDesc;

        public SearchViewHolder(@NonNull View itemView) {
            super(itemView);
            ivSearchImage = itemView.findViewById(R.id.ivSearchImage);
            tvSearchTitle = itemView.findViewById(R.id.tvSearchTitle);
            tvSearchDesc = itemView.findViewById(R.id.tvSearchDesc);
        }

        public void bind(Exercise currentItem) {
            //Bind the exercise data to the view of elements
            tvSearchTitle.setText(currentItem.getExerciseName());
            tvSearchDesc.setText(currentItem.getExerciseDescription());
            ParseFile image = currentItem.getExerciseImage();
            if (image != null) {
                Glide.with(context).load(currentItem.getExerciseImage().getUrl()).into(ivSearchImage);
            }

        }
    }
}

