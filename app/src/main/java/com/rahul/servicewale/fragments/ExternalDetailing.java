package com.rahul.servicewale.fragments;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.ArrayList;

import com.rahul.servicewale.ExternalData;
import com.rahul.servicewale.R;


public class ExternalDetailing extends Fragment {

Activity context;
    public String k;
    public static Context mcontext;
    private ArrayList<ExternalData> eventsList = new ArrayList<>();

    public ExternalDetailing() {

    }

    Button cnt;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
context=getActivity();

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_external_detailing, container, false);

        return v;
    }





    }





