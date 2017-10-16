package com.example.himanshurawat.buildit.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.himanshurawat.buildit.PojoClass.Contractors;
import com.example.himanshurawat.buildit.R;

import java.util.ArrayList;

public class ContractorAdapter extends RecyclerView.Adapter<ContractorAdapter.ContractorViewHolder> {

    public Context mContext;
    public ArrayList<Contractors> mList;


    public ContractorAdapter(Context context, ArrayList<Contractors> list){
        mContext=context;
        mList=list;
    }
    @Override
    public ContractorViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(mContext).inflate(R.layout.contractor_item,parent,false);

        return new ContractorViewHolder(itemView);
    }
    @Override
    public void onBindViewHolder(ContractorViewHolder holder, int position) {
        Contractors c= mList.get(position);

        holder.titleTextView.setText(c.getName());



    }
    @Override
    public int getItemCount() {

        return mList.size();
    }

    public static class ContractorViewHolder extends RecyclerView.ViewHolder {

        TextView titleTextView;
        CheckBox selectionCheckBox;

        public ContractorViewHolder(View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.contractor_item_title_text_view);
            selectionCheckBox = itemView.findViewById(R.id.contractor_item_check_box);

        }

    }


}
