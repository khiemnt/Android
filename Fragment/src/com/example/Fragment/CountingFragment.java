package com.example.Fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created with IntelliJ IDEA.
 * User: Dell 3360
 * Date: 10/22/13
 * Time: 9:36 AM
 * To change this template use File | Settings | File Templates.
 */
public class CountingFragment extends Fragment
{
    int mNum;

    static CountingFragment newInstance()
    {
        CountingFragment f = new CountingFragment();

        return f;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View v = inflater.inflate(R.layout.fragment, container, false);
        View tv = v.findViewById(R.id.text);
        ((TextView) tv).setText("Hello.This is fragment example #" + mNum);
        tv.setBackgroundDrawable(getResources().getDrawable(android.R.drawable.gallery_thumb));
        return v;

    }
}