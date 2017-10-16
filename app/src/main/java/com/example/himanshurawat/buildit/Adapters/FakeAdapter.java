package com.example.himanshurawat.buildit.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.himanshurawat.buildit.R;

import java.util.ArrayList;

/**
 * Created by himanshurawat on 15/10/17.
 */



    public class FakeAdapter extends RecyclerView.Adapter<FakeAdapter.ProjectViewHolder> {

        public Context mContext;
        public ArrayList<ProjectClass> mList;
        public ProjectsClickListener mListener;

        public interface ProjectsClickListener {
            void onProjectClick(View view, int position);
        }
        public FakeAdapter(Context context, ArrayList<ProjectClass> list,ProjectsClickListener listener){
            mContext=context;
            mList=list;
            mListener=listener;
        }
        @Override
        public ProjectViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(mContext).inflate(R.layout.image_item,parent,false);

            return new ProjectViewHolder(itemView,mListener);
        }
        @Override
        public void onBindViewHolder(ProjectViewHolder holder, int position) {
            ProjectClass p = mList.get(position);
            holder.dateTextView.setText(p.date);
            holder.titleTextView.setText(p.name);


        }
        @Override
        public int getItemCount() {

            return mList.size();
        }

        public static class ProjectViewHolder extends RecyclerView.ViewHolder {


            ProjectsClickListener projectClickListener;
            TextView titleTextView;
            TextView dateTextView;

            public ProjectViewHolder(View itemView, ProjectsClickListener listener) {
                super(itemView);
                projectClickListener = listener;
                dateTextView = itemView.findViewById(R.id.dateTextView);
                titleTextView = itemView.findViewById(R.id.titleTextView);
                //itemView.setOnClickListener(this);

            }

        }


    }




