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

import java.security.Timestamp;
import java.time.Instant;
import java.util.Date;

/**
 * Created by Eli on 27/12/2017.
 */

    public class Comments extends Fragment {
    int mNum;
    //declaraciones
    ListView comments;

    Valoracion[] comentarios;
    Usuario [] users;

    static Comments newInstance(int num) {
        Comments c = new Comments();

        // Supply num input as an argument.
        Bundle args = new Bundle();
        args.putInt("num", num);
        c.setArguments(args);

        return c;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mNum = getArguments() != null ? getArguments().getInt("num") : 1;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.comments, container, false);
        comments = (ListView) view.findViewById(R.id.comment_list);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle state) {
        super.onActivityCreated(state);
        MyAdapter adapter = new MyAdapter(getActivity(), comentarios,"comment",users);
        comments.setAdapter(adapter);

    }
}
