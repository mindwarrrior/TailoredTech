package com.example.tt.tailoredtech.Adapters;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.tt.tailoredtech.Constants;
import com.example.tt.tailoredtech.TTMgmtUtils;

import java.util.List;

public class SpinnerAdapter extends ArrayAdapter<String> {
    private Context context;

    public SpinnerAdapter(@NonNull Context context, @LayoutRes int resource, List<String> items) {
        super(context, resource, items);
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView textView = (TextView) super.getView(position, convertView, parent);
        TTMgmtUtils.setTypeface(context, new View[]{textView}, Constants.KARLA_REGULAR);
        return textView;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        TextView textView = (TextView) super.getDropDownView(position, convertView, parent);
        TTMgmtUtils.setTypeface(context, new View[]{textView}, Constants.KARLA_REGULAR);
        return textView;
    }
}
