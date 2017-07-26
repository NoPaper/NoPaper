package com.planb.nopaper.activities;

import android.content.Context;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_teacher);

//        AccountManager.addWishList(getApplicationContext(), "path1");
//        AccountManager.addWishList(getApplicationContext(), "path2");
//        AccountManager.addWishList(getApplicationContext(), "path3");

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        String[] idList = AccountManager.getWishList(getApplicationContext());
        System.out.println(idList);

        recyclerView.setAdapter(new Adapter_Teacher(idList, getApplicationContext()));
    }
}

class Adapter_Teacher extends RecyclerView.Adapter<Adapter_Teacher.ViewHolder> {
    String[] idList;
    private AQuery aq;

    public Adapter_Teacher(String[] idList, Context context) {
        this.idList = idList;
        aq = new AQuery(context);
    }

    @Override
    public Adapter_Teacher.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_item_main_teacher, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final Adapter_Teacher.ViewHolder holder, int position) {
        String id = idList[position];

        aq.ajax("http://52.79.134.200:3434/item?id=" + id, String.class, new AjaxCallback<String>() {
            @Override
            public void callback(String url, String response, AjaxStatus status) {
                try {
                    JSONObject obj = new JSONObject(response);
                    holder.titleView.setText(obj.getString("title"));
                    holder.fileNameView.setText(obj.getString("file_name"));
                    holder.dateView.setText(obj.getString("date"));
                } catch(JSONException e) {

                }
            }
        }.method(AQuery.METHOD_GET));
    }

    @Override
    public int getItemCount() {
        return idList.length;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView titleView;
        private TextView fileNameView;
        private TextView dateView;

        public ViewHolder(final View itemView) {
            super(itemView);
            titleView = (TextView) itemView.findViewById(R.id.titleView);
            fileNameView = (TextView) itemView.findViewById(R.id.fileNameView);
            dateView = (TextView) itemView.findViewById(R.id.dateView);
        }
    }
}
