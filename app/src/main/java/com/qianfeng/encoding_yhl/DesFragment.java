package com.qianfeng.encoding_yhl;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
/**
 * A simple {@link Fragment} subclass.
 */
public class DesFragment extends Fragment implements View.OnClickListener {
    private EditText des_key;
    private EditText des_rlt;
    private EditText des_src;
    private Cipher cipher;
    private SecretKeySpec secretKey;

    public DesFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_des, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        des_key = ((EditText) view.findViewById(R.id.des_key));
        des_src = ((EditText) view.findViewById(R.id.des_src));
        des_rlt = ((EditText) view.findViewById(R.id.des_rlt));
        view.findViewById(R.id.des_encode).setOnClickListener(this);
        view.findViewById(R.id.des_decode).setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        //密钥
        String key = des_key.getText().toString();
        if (TextUtils.isEmpty(key)) {
            Toast.makeText(getActivity(), "密钥不可为空", Toast.LENGTH_SHORT).show();
        } else {
            try {
                byte[] bytes = key.getBytes("UTF-8");
                byte[] keys = new byte[8];

                System.arraycopy(bytes, 0, keys, 0, Math.min(bytes.length, keys.length));
                secretKey = new SecretKeySpec(keys, "DES");
                cipher = Cipher.getInstance("DES");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            } catch (NoSuchPaddingException e) {
                e.printStackTrace();
            }
            try {
            switch (v.getId()) {
                case R.id.des_encode://加密
                    String src = des_src.getText().toString();
                    if (TextUtils.isEmpty(src)) {
                        Toast.makeText(getActivity(), "加密文件不可为空", Toast.LENGTH_SHORT).show();
                    } else {

                        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
                        byte[] aFinal = cipher.doFinal(src.getBytes("UTF-8"));
                        des_rlt.setText(Base64.encodeToString(aFinal, Base64.DEFAULT));
                    }
                    break;
                case R.id.des_decode:
                    String rlt = des_rlt.getText().toString();
                    if (TextUtils.isEmpty(rlt)) {
                        Toast.makeText(getActivity(), "解密文件不可为空", Toast.LENGTH_SHORT).show();
                    } else {
                        cipher.init(Cipher.DECRYPT_MODE, secretKey);
                        byte[] aFinal = cipher.doFinal(Base64.decode(rlt, Base64.DEFAULT));
                        des_src.setText(new String(aFinal,"UTF-8"));
                    }
                    break;
            }
                } catch (InvalidKeyException e) {
                    e.printStackTrace();
                } catch (BadPaddingException e) {
                    e.printStackTrace();
                } catch (IllegalBlockSizeException e) {
                    e.printStackTrace();
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }

        }

}
