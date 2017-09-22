package com.example.tt.tailoredtech.Fragments;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.tt.tailoredtech.Constants;
import com.example.tt.tailoredtech.R;
import com.example.tt.tailoredtech.TTMgmtUtils;

public class PageFragment extends Fragment {
    private String title;

    public static PageFragment newInstance(String title) {
        PageFragment fragment = new PageFragment();
        Bundle args = new Bundle();
        args.putString(Constants.FRAGMENT_MSG_TITLE, title);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments().containsKey(Constants.FRAGMENT_MSG_TITLE))
            title = getArguments().getString(Constants.FRAGMENT_MSG_TITLE);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.page_item, container, false);
        TextView message = (TextView) view.findViewById(R.id.messages);
        TTMgmtUtils.setTypeface(getContext(),new TextView[]{message}, Constants.KARLA_REGULAR);
        message.setText(title);
        return view;
    }
}