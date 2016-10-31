package com.cristianrd.finalmovil;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import fcm.CoursePushNotification;


/**
 * Created by CristianRD on 8/21/2015.
 */
public class Fragment3 extends Fragment {

    private RecyclerView mRecyclerView;
    private LinearLayout mNoMessagesView;
    private PushNotificationsAdapter mNotificationsAdapter;

    public static final String ACTION_NOTIFY_NEW_PROMO = "NOTIFY_NEW_PROMO";
    private BroadcastReceiver mNotificationsReceiver;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.frag3, container, false);

        mNotificationsAdapter = new PushNotificationsAdapter();
        mRecyclerView = (RecyclerView) view.findViewById(R.id.rv_notifications_list);
        mNoMessagesView = (LinearLayout) view.findViewById(R.id.noMessages);
        mRecyclerView.setAdapter(mNotificationsAdapter);

        mNotificationsReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                String title = intent.getStringExtra("title");
                String description = intent.getStringExtra("description");
                String discount = intent.getStringExtra("discount");
                savePushMessage(title, description, discount);
            }
        };

        return view;

    }

    @Override
    public void onResume() {
        super.onResume();
        LocalBroadcastManager.getInstance(getActivity()).registerReceiver(mNotificationsReceiver, new IntentFilter(ACTION_NOTIFY_NEW_PROMO));
    }

    @Override
    public void onPause() {
        super.onPause();
        LocalBroadcastManager.getInstance(getActivity()).unregisterReceiver(mNotificationsReceiver);
    }

    public void savePushMessage(String title, String description, String discount){
        CoursePushNotification coursePushNotification = new CoursePushNotification();
        coursePushNotification.setTitle(title);
        coursePushNotification.setDescription(description);
        coursePushNotification.setDiscount(TextUtils.isEmpty(discount) ? 0 : Float.parseFloat(discount));

        mNotificationsAdapter.addItem(coursePushNotification);
        showEmptyState(false);
    }

    public void showEmptyState(boolean empty) {
        mRecyclerView.setVisibility(empty ? View.GONE : View.VISIBLE);
        mNoMessagesView.setVisibility(empty ? View.VISIBLE : View.GONE);
    }
}


