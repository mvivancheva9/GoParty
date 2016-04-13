package com.example.admin.goparty.views.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.admin.goparty.R;
import com.example.admin.goparty.models.Party;

import java.util.List;

public class ListDemoAdapter extends ArrayAdapter<Party> {

    public ListDemoAdapter(Context context, List<Party> items) {
        super(context, R.layout.party_list_row, items);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;

        if(convertView == null) {
            // inflate the GridView item layout
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.party_list_row, parent, false);

            // initialize the view holder
            viewHolder = new ViewHolder();
            viewHolder.tvTitle = (TextView) convertView.findViewById(R.id.tvTitle);
            convertView.setTag(viewHolder);
        } else {
            // recycle the already inflated view
            viewHolder = (ViewHolder) convertView.getTag();
        }

        // update the item view
        Party item = getItem(position);
        viewHolder.tvTitle.setText(item.getTitle().toString());

        return convertView;
    }
    private static class ViewHolder {
        TextView tvTitle;
    }
}