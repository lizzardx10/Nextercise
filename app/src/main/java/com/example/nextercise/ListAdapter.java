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

import java.util.List;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ListViewHolder> {

private static final String TAG = "SearchAdapter";
private final Context context;
private final List<Exercise> exerciseList;

public ListAdapter(Context context, List<Exercise> exercises) {
        this.context = context;
        this.exerciseList = exercises;
        }

@NonNull
@Override
public ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.example_list_item, parent, false);
    return new ListViewHolder(v);
        }

@Override
public void onBindViewHolder(@NonNull ListViewHolder holder, int position) {
        Exercise currentItem = exerciseList.get(position);
        holder.bind(currentItem);

        holder.itemView.setOnClickListener(v -> {
                //Navigate to the Exercise activity associated with the item clicked
                Intent i = new Intent(getApplicationContext(), ExerciseActivity.class);
                Log.i(TAG, String.valueOf(currentItem.getExerciseId()));
                i.putExtra("int_value", currentItem.getExerciseId());
                context.startActivity(i);
                });
        }

@Override
public int getItemCount() {
        return exerciseList.size();
        }

    public class ListViewHolder extends RecyclerView.ViewHolder {

    private final ImageView ivSearchImage;
    private final TextView tvSearchTitle;

        public ListViewHolder(@NonNull View itemView) {
        super(itemView);
        ivSearchImage = itemView.findViewById(R.id.ivSearchImage);
        tvSearchTitle = itemView.findViewById(R.id.tvSearchTitle);
    }

    public void bind(Exercise currentItem) {
        //Bind the exercise data to the view of elements
        tvSearchTitle.setText(currentItem.getExerciseName());
        ParseFile image = currentItem.getExerciseImage();
        if (image != null) {
            Glide.with(context).load(currentItem.getExerciseImage().getUrl()).into(ivSearchImage);
        }

    }
}
}