package com.qianfeng.encoding_yhl;


import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.UnsupportedEncodingException;


/**
 * A simple {@link Fragment} subclass.
 */
public class BaseFragment extends Fragment implements View.OnClickListener {


    private EditText encode_et;
    private EditText decode_et;

    public BaseFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_base, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        encode_et = ((EditText) view.findViewById(R.id.encode_et));
        ((Button) view.findViewById(R.id.encode_btn)).setOnClickListener(this);
        decode_et = ((EditText) view.findViewById(R.id.decode_et));
        ((Button) view.findViewById(R.id.decode_btn)).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            //编码
            case R.id.encode_btn:
             String src=encode_et.getText().toString();
                if(TextUtils.isEmpty(src)) {
                    Toast.makeText(getActivity(), "编码摘要不可为空", Toast.LENGTH_SHORT).show();
                }else {
                    try {
                        String s=Base64.encodeToString(src.getBytes("UTF-8"), Base64.DEFAULT);
                        decode_et.setText(s);
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                }
                break;
            //解码
            case R.id.decode_btn:
                String rlt=decode_et.getText().toString();
                if(TextUtils.isEmpty(rlt)){
                    Toast.makeText(getActivity(),"解码摘要不可为空",Toast.LENGTH_SHORT).show();
                }else {
                    try {
                        byte[] b=Base64.decode(rlt.getBytes("UTF-8"),Base64.DEFAULT);
                      String s1=new String(b);
                        encode_et.setText(s1);
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                }

                break;
        }
    }
}
