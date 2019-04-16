package com.runnimal.app.android;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class NotificacionListViewAdapter extends BaseAdapter {

    Context mContext;
    LayoutInflater inflater;
    List<ModelNotificaciones> modelslist;
    ArrayList<ModelNotificaciones> arrayList;


    public NotificacionListViewAdapter(Context Context, List<ModelNotificaciones> modelslist) {
        mContext = Context;
        this.modelslist = modelslist;
        inflater = LayoutInflater.from(mContext);
        this.arrayList = new ArrayList<ModelNotificaciones>();
        this.arrayList.addAll(modelslist);
    }

    public class ViewHolder {
        TextView mTitleTv;
        TextView mMailTv;
        ImageView mIconIv;
        Button aceptBtn;
        Button rechazarBtn;

    }

    @Override
    public int getCount() {
        return modelslist.size();
    }

    @Override
    public Object getItem(int position) {
        return modelslist.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        NotificacionListViewAdapter.ViewHolder holder;
        if (convertView == null){
            holder = new NotificacionListViewAdapter.ViewHolder();
            convertView = inflater.inflate(R.layout.row_notificaciones, null);

            holder.mTitleTv = convertView.findViewById(R.id.mainTitle);
            holder.mIconIv = convertView.findViewById(R.id.fotoUser);
            holder.mMailTv = convertView.findViewById(R.id.mailUsers);
            holder.aceptBtn = convertView.findViewById(R.id.aceptar);
            holder.rechazarBtn = convertView.findViewById(R.id.rechazar);

            convertView.setTag(holder);
        }
        else{
            holder = (NotificacionListViewAdapter.ViewHolder)convertView.getTag();
        }

        holder.mTitleTv.setText(modelslist.get(position).getTitle());
        holder.mIconIv.setImageResource(modelslist.get(position).getIcon());
        holder.mMailTv.setText(modelslist.get(position).getMail());
        holder.aceptBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //programar la llamada  a la api para aceptar amistad
            }
        });
        holder.rechazarBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //programar la llamada a la api para rechazara la amistad
            }
        });




        return convertView;
    }
}