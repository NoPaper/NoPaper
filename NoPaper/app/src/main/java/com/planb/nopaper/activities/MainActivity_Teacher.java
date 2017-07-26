package com.planb.nopaper.activities;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.androidquery.AQuery;
import com.androidquery.callback.AjaxCallback;
import com.androidquery.callback.AjaxStatus;
import com.planb.nopaper.R;
import com.planb.nopaper.activities.base.BaseActivity;
import com.planb.nopaper.support.account.AccountManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by dsm2016 on 2017-07-26.
 */

public class MainActivity_Teacher extends BaseActivity {
    private RecyclerView recyclerView;
    private AQuery aq;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_teacher);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        String[] idList = AccountManager.getWishList(getApplicationContext());

        final JSONArray data = new JSONArray();
        for(int i = 0; i < idList.length; i++) {
            aq.ajax("http://52.79.134.200:3434/item?id=" + idList[i], String.class, new AjaxCallback<String>() {
                @Override
                public void callback(String url, String response, AjaxStatus status) {
                    try {
                        JSONObject obj = new JSONObject(response);

                        data.put(obj);
                    } catch(JSONException e) {

                    }
                }
            });
        }
    }
}

class Adapter_Teacher extends RecyclerView.Adapter<Adapter_Teacher.ViewHolder> {
    JSONArray wishList;

    public Adapter_Teacher(JSONArray wishList) {
        this.wishList = wishList;
    }

    @Override
    public Adapter_Teacher.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_item_main_teacher, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(Adapter_Teacher.ViewHolder holder, int position) {
        String fileId = wishList[position];
    }

    @Override
    public int getItemCount() {
        return wishList.length;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView titleView;
        private TextView fileNameView;
        private TextView dateView;

        public ViewHolder(final View itemView) {
            super(itemView);
        }
    }
}
