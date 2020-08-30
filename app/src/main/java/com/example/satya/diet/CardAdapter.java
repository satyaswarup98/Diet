package com.example.satya.diet;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class CardAdapter extends RecyclerView.Adapter<CardAdapter.ViewHolder> {
    private static final String TAG = "CardAdapter";
    public Boolean lun[] = new Boolean[31];
    public Boolean din[] = new Boolean[31];
    public Context mContext;
    CheckBox bookmark;
    List<User> list = new ArrayList<>();
    AlertDialog.Builder builder;

    public CardAdapter(Context mContext, List<User> list) {
        this.mContext = mContext;
        this.list = list;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_view, parent, false);
        return new ViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        Arrays.fill(lun, Boolean.FALSE);
        Arrays.fill(din, Boolean.FALSE);

        final SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(mContext);
        if (prefs.getBoolean("firstTime", false)) {
            // <---- run your one time code here

            lun= loadArray("lunch",mContext);
            din= loadArray("dinner",mContext);


        }


        holder.User = getItem(position);
        holder.cardtitle.setText(list.get(position).name);
        holder.cardrank.setChecked(lun[position]);
        holder.cardimage.setChecked(din[position]);

        holder.cardrank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (holder.cardrank.isChecked() == true) {
                    builder = new AlertDialog.Builder(mContext, android.R.style.Theme_Material_Light_Dialog_Alert);
                    builder.setCancelable(false);
                    builder.setMessage("Confirm to ENTRY the Diet ...")
                            .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    lun[position] = true;
                                    storeArray(lun, "lunch", mContext);
                                    SharedPreferences.Editor editor = prefs.edit();
                                    editor.putBoolean("firstTime", true);
                                    editor.commit();

                                }
                            })
                            .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    //lun[position] = false;
                                    holder.cardrank.setChecked(false);
                                }
                            })
                            .show();
                } else {
                    builder = new AlertDialog.Builder(mContext, android.R.style.Theme_Material_Light_Dialog_Alert);
                    builder.setCancelable(false);
                    builder.setMessage("Confirm to DELETE the Diet ...")
                            .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    lun[position] = false;
                                    storeArray(lun, "lunch", mContext);
                                    SharedPreferences.Editor editor = prefs.edit();
                                    editor.putBoolean("firstTime", true);
                                    editor.commit();
                                }
                            })
                            .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    //lun[position] = true;
                                    holder.cardrank.setChecked(true);

                                }
                            })
                            .show();
                }

            }
        });

        holder.cardimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (holder.cardimage.isChecked() == true) {

                    builder = new AlertDialog.Builder(mContext, android.R.style.Theme_Material_Light_Dialog_Alert);
                    builder.setCancelable(false);
                    builder.setMessage("Confirm to ENTRY the Diet ...")
                            .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    din[position] = true;
                                    storeArray(din, "dinner", mContext);
                                    SharedPreferences.Editor editor = prefs.edit();
                                    editor.putBoolean("firstTime", true);
                                    editor.commit();

                                }
                            })
                            .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    //din[position] = false;
                                    holder.cardimage.setChecked(false);
                                }
                            })
                            .show();
                } else {
                    builder = new AlertDialog.Builder(mContext, android.R.style.Theme_Material_Light_Dialog_Alert);
                    builder.setCancelable(false);
                    builder.setMessage("Confirm to DELETE the Diet ...")
                            .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    din[position] = false;
                                    storeArray(din, "dinner", mContext);
                                    SharedPreferences.Editor editor = prefs.edit();
                                    editor.putBoolean("firstTime", true);
                                    editor.commit();

                                }
                            })
                            .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    //lun[position] = true;
                                    holder.cardimage.setChecked(true);

                                }
                            })
                            .show();
                }

            }
        });
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public User getItem(int i) {
        return list.get(i);
    }

    public boolean storeArray(Boolean[] array, String arrayName, Context mContext) {

        SharedPreferences prefs = mContext.getSharedPreferences("preferencename", 0);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt(arrayName + "_size", array.length);

        for (int i = 0; i < array.length - 1; i++)
            editor.putBoolean(arrayName + "_" + i, array[i]);
        return editor.commit();
    }

    public Boolean[] loadArray(String arrayName, Context mContext) {

        SharedPreferences prefs = mContext.getSharedPreferences("preferencename", 0);
        int size = prefs.getInt(arrayName + "_size", 0);
        Boolean array[] = new Boolean[size];
        for (int i = 0; i < size; i++)
            array[i] = prefs.getBoolean(arrayName + "_" + i, false);

        return array;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {


        TextView cardtitle;
        CheckBox cardrank;
        CheckBox cardimage;
        User User;

        public ViewHolder(View itemView) {
            super(itemView);
            cardtitle = (TextView) itemView.findViewById(R.id.card_user_title);
            cardrank = (CheckBox) itemView.findViewById(R.id.cb1);
            cardimage = (CheckBox) itemView.findViewById(R.id.cb2);
        }
    }
}

