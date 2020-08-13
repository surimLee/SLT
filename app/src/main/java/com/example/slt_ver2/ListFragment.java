package com.example.slt_ver2;

import android.content.Context;
import android.content.Intent;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import androidx.fragment.app.Fragment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import java.util.ArrayList;
import java.util.List;

import com.example.slt_ver2.R;
import com.example.slt_ver2.SignLanguageListAdapter;

import cn.pedant.SweetAlert.Constants;
import cn.pedant.SweetAlert.SweetAlertDialog;


public class ListFragment extends Fragment  {
    View fragmentView;
    RecyclerView signLanguageListRecyclerView;
    SignLanguageListAdapter signLanguageListAdapter;
    List<SignLanguageList> signLanguageList;


    public ListFragment()
    {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Add dummy data in SignLanguage Fragment here
        signLanguageList = new ArrayList<>();
        signLanguageList.add(new SignLanguageList("ㄱ",R.drawable.fingerlanguage_icon));
        signLanguageList.add(new SignLanguageList("ㄴ",R.drawable.fingerlanguage_icon));
        signLanguageList.add(new SignLanguageList("ㄷ",R.drawable.fingerlanguage_icon));
        signLanguageList.add(new SignLanguageList("ㄹ",R.drawable.fingerlanguage_icon));
        signLanguageList.add(new SignLanguageList("ㅁ",R.drawable.fingerlanguage_icon));
        signLanguageList.add(new SignLanguageList("ㅂ",R.drawable.fingerlanguage_icon));
        signLanguageList.add(new SignLanguageList("ㅅ",R.drawable.fingerlanguage_icon));
        signLanguageList.add(new SignLanguageList("ㅇ",R.drawable.fingerlanguage_icon));
        signLanguageList.add(new SignLanguageList("ㅈ",R.drawable.fingerlanguage_icon));
        signLanguageList.add(new SignLanguageList("ㅊ",R.drawable.fingerlanguage_icon));
        signLanguageList.add(new SignLanguageList("ㅋ",R.drawable.fingerlanguage_icon));
        signLanguageList.add(new SignLanguageList("ㅌ",R.drawable.fingerlanguage_icon));
        signLanguageList.add(new SignLanguageList("ㅍ",R.drawable.fingerlanguage_icon));
        signLanguageList.add(new SignLanguageList("ㅎ",R.drawable.fingerlanguage_icon));

    }

    Button btn_Add;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fragmentView = inflater.inflate(R.layout.fragment_list, container, false);
        signLanguageListRecyclerView = fragmentView.findViewById(R.id.signlanguage_recycler);
        signLanguageListRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        signLanguageListRecyclerView.setHasFixedSize(true);

        signLanguageListAdapter = new SignLanguageListAdapter(getActivity(), signLanguageList);
        signLanguageListRecyclerView.setAdapter(signLanguageListAdapter);


        btn_Add = fragmentView.findViewById(R.id.btn_Add);

        btn_Add.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                new SweetAlertDialog(getActivity(), SweetAlertDialog.NORMAL_TYPE)
                        .setTitleText("수화데이터 수집중")
                        .setContentText("지금부터 수화를 1회 사용해주세요.")
                        .setConfirmText("완료")
                        .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(SweetAlertDialog sweetAlertDialog) {
                                sweetAlertDialog.setTitleText("동작 완료!")
                                        .setContentText("수화 등록이 완료되었습니다.")
                                        .setConfirmText("완료")
                                        .setConfirmClickListener(null)
                                        .changeAlertType(SweetAlertDialog.SUCCESS_TYPE);
                            }
                        })
                        .show();
            }
        });

        return fragmentView;

    }

}
