package com.example.Fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created with IntelliJ IDEA.
 * User: Dell 3360
 * Date: 10/22/13
 * Time: 10:39 AM
 * To change this template use File | Settings | File Templates.
 */
public class HoodFragment extends Fragment
{
    static HoodFragment instance()
    {
        HoodFragment fragment = new HoodFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View v = inflater.inflate(R.layout.fragment2, container, false);
        return v;
    }
}