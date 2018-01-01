package com.example.eli.testtab;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Eli on 27/12/2017.
 */

    public class Events extends Fragment {
    int mNum;
    //declaraciones
    Activity mActivity;
    ListView events;
    Timestamp timeStamp = new Timestamp(1);

    Evento[] eventos = {new Evento(1, "ejemplo", "ejemplo", "ejemplo", timeStamp, timeStamp)};

    static Events newInstance(int num) {
        Events e = new Events();

        // Supply num input as an argument.
        Bundle args = new Bundle();
        args.putInt("num", num);
        e.setArguments(args);

        return e;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mNum = getArguments() != null ? getArguments().getInt("num") : 1;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.events, container, false);
        events = (ListView) view.findViewById(R.id.event_list);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle state) {
        super.onActivityCreated(state);
        MyAdapter adapter = new MyAdapter(getActivity(), eventos,"event");
        events.setAdapter(adapter);

    }
}