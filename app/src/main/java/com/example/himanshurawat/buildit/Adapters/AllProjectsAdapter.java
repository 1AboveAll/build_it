package com.example.himanshurawat.buildit.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.himanshurawat.buildit.PojoClass.NewProject;
import com.example.himanshurawat.buildit.Project;
import com.example.himanshurawat.buildit.R;
import com.example.himanshurawat.buildit.Stakeholder;

import java.util.List;

/**
 * Created by himanshurawat on 15/10/17.
 */

public class AllProjectsAdapter extends RecyclerView.Adapter<AllProjectsAdapter.AllProjectsViewHolder> {

    private Context context;
    private List<NewProject> projectList;

    public AllProjectsAdapter(Context context, List<NewProject>projectList){
        this.context=context;
        this.projectList=projectList;

    }

    @Override
    public AllProjectsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView= LayoutInflater.from(context).inflate(R.layout.activity_stakeholder_project_item_view,parent,false);


        return new AllProjectsViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(AllProjectsViewHolder holder, int position) {
        NewProject project=projectList.get(position);

        holder.projectNameTextView.setText(project.getName());
        holder.projectDateTextView.setText(project.getDate());


    }

    @Override
    public int getItemCount() {
        return projectList.size();
    }

    public class AllProjectsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView projectNameTextView,projectDateTextView;

        public AllProjectsViewHolder(View itemView) {
            super(itemView);
            projectDateTextView= itemView.findViewById(R.id.activity_stakeholder_project_item_view_project_date_text_view);
            projectNameTextView= itemView.findViewById(R.id.activity_stakeholder_project_item_view_project_name_text_view);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Intent i=new Intent(context, Project.class);
            i.putExtra("project",projectList.get(getAdapterPosition()));
            context.startActivity(i);
        }
    }



}
