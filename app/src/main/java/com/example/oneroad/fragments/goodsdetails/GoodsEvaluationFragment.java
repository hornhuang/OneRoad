 package com.example.oneroad.fragments.goodsdetails;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.oneroad.R;
import com.example.oneroad.activities.GoodsDetails;

import java.util.Objects;

import es.dmoral.toasty.Toasty;

 public class GoodsEvaluationFragment extends Fragment implements View.OnClickListener {

    /*
    控件
     */
    private LinearLayout addLinearLayout;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public GoodsEvaluationFragment() {
        // Required empty public constructor
    }

    public static GoodsEvaluationFragment newInstance(String param1, String param2) {
        GoodsEvaluationFragment fragment = new GoodsEvaluationFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_goods_evalation, container, false);
        addLinearLayout = view.findViewById(R.id.goods_add_evaluation);
        addLinearLayout.setOnClickListener(this);
        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.goods_add_evaluation:
//                startActivity(new Intent());
                Toasty.success((GoodsDetails) Objects.requireNonNull(getActivity()), "跳转至评论发布界面").show();
                break;
        }
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

}
