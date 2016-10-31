package com.cristianrd.finalmovil;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import fcm.CoursePushNotification;

/**
 * Created by estemanp on 24/10/16.
 */

public class PushNotificationsAdapter extends RecyclerView.Adapter<PushNotificationsAdapter.ViewHolder> {

    ArrayList<CoursePushNotification> pushNotifications = new ArrayList<>();


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View itemView = inflater.inflate(R.layout.item_notification, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        CoursePushNotification coursePushNotification = pushNotifications.get(position);

        holder.title.setText(coursePushNotification.getTitle());
        holder.description.setText(coursePushNotification.getDescription());
        holder.discount.setText(String.format("%d%%", (int) (coursePushNotification.getDiscount() * 100)));
    }

    public void replaceData(ArrayList<CoursePushNotification> items) {
        setList(items);
        notifyDataSetChanged();
    }

    public void setList(ArrayList<CoursePushNotification> list) {
        this.pushNotifications = list;
    }
    public void addItem(CoursePushNotification pushMessage) {
        pushNotifications.add(0, pushMessage);
        notifyItemInserted(0);
    }

    @Override
    public int getItemCount() {
        return pushNotifications.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView title;
        public TextView description;
        public TextView discount;

        public ViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.tv_title);
            description = (TextView) itemView.findViewById(R.id.tv_description);
            discount = (TextView) itemView.findViewById(R.id.tv_discount);
        }
    }
}
