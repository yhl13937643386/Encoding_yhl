package com.qianfeng.encoding_yhl;

import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * A simple {@link Fragment} subclass.
 */
public class Md5ragment extends Fragment implements View.OnClickListener {


    private EditText md5_et;
    private TextView md5_tv;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_md5, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        md5_et = ((EditText) view.findViewById(R.id.md5_et));
        md5_tv = ((TextView) view.findViewById(R.id.md5_tv));
        ((Button) view.findViewById(R.id.md5_bt)).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
       String  edit=md5_et.getText().toString();
      if(TextUtils.isEmpty(edit)){
          Toast.makeText(getActivity(),"摘要数据不可为空",Toast.LENGTH_SHORT).show();

      }else {
          try {
              MessageDigest instance=MessageDigest.getInstance("Md5");
              byte[] digest=instance.digest(edit.getBytes("UTF-8"));
              StringBuilder builder=new StringBuilder();
              for (byte b:digest){
                  builder.append(String.format("%2x",b&0xff));
              }
              String replace=builder.toString().toUpperCase();
               md5_tv.setText(replace);
          } catch (NoSuchAlgorithmException e) {
              e.printStackTrace();
          } catch (UnsupportedEncodingException e) {
              e.printStackTrace();
          }

      }

    }
}
