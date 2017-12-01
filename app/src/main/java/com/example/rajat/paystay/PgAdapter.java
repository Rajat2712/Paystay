package com.example.rajat.paystay;

import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import DatabaseHelpers.PgContact;
import DatabaseHelpers.Utils;

/**
 * Created by Rajat on 11/1/2016.
 */
public class PgAdapter extends RecyclerView.Adapter<PgAdapter.MyViewHolder>{

    private List<PgContact> pgContactList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name, location, address,phone,type,rent;
        public ImageView photo;

        public MyViewHolder(View view) {
            super(view);
            name = (TextView) view.findViewById(R.id.tvpgname);
            location = (TextView) view.findViewById(R.id.tvpglocation);
            address = (TextView) view.findViewById(R.id.tvpgaddress);
            phone = (TextView) view.findViewById(R.id.tvphone);
            type = (TextView) view.findViewById(R.id.tvpgtype);
            rent = (TextView) view.findViewById(R.id.tvpgrent);
            photo = (ImageView) view.findViewById(R.id.tvpgphoto);
        }
    }


    public PgAdapter(List<PgContact> pgContactList) {
        this.pgContactList = pgContactList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.pg_adapter, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        PgContact pgContact = pgContactList.get(position);
        holder.name.setText("Name :  " +pgContact.getPgName());
        holder.location.setText("Location:  "+pgContact.getpgLocation());
        holder.address.setText("Address:  "+ pgContact.getPgAddress());
        holder.phone.setText("Phone:  "+pgContact.getPgPhone());
        holder.type.setText("Type:  "+pgContact.getpgType());
        holder.rent.setText("Rent :  " + pgContact.getpgRent());
        if(pgContact.getPhoto()!=null)
        holder.photo.setImageBitmap(BitmapFactory.decodeByteArray(pgContact.getPhoto(),0, pgContact.getPhoto().length));
        //holder.photo.setImageBitmap(Utils.getImage(pgContact.getPhoto()));

    }

    @Override
    public int getItemCount() {
        return pgContactList.size();
    }

}

