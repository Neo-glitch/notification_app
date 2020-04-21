package com.neo.notification_app;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyCustomAdapter extends RecyclerView.Adapter<MyCustomAdapter.MyViewHolder> {

    private ArrayList<TasksList> tasks;
    private Context mContext;
    private RecyclerViewClickInterface recyclerViewClickInterface;

    public MyCustomAdapter(Context context, ArrayList<TasksList> tasks, RecyclerViewClickInterface recyclerViewClickInterface) {
        this.tasks = tasks;
        mContext = context;
        this.recyclerViewClickInterface = recyclerViewClickInterface;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.task_list, parent, false);
        return new MyViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        TasksList tasksList = tasks.get(position);

        // applies animation effect to the taskContainer view
        holder.taskContainer.setAnimation(AnimationUtils.loadAnimation(mContext, R.anim.fade_animation_transition));

        holder.taskTitle.setText(tasksList.getTaskName());
        holder.taskDetails.setText("click to get task details..");


    }

    @Override
    public int getItemCount() {
        if (tasks != null) {
            return tasks.size();
        } else {
            return 1;
        }
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        RelativeLayout taskContainer;
        TextView taskTitle;
        TextView taskDetails;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            taskContainer = itemView.findViewById(R.id.taskContainer);
            taskTitle = (TextView) itemView.findViewById(R.id.taskTitle);
            taskDetails = (TextView) itemView.findViewById(R.id.taskDetails);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    recyclerViewClickInterface.onClick(getAdapterPosition());

                }
            });
        }

    }


}
