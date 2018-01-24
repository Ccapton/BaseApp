package com.capton.baseapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.blankj.utilcode.util.ToastUtils;
import com.capton.baseapp.databinding.FragmentUpdateBinding;
import com.capton.common.base.BaseFragment;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by capton on 2018/1/19.
 */

public class UpdateFragment extends BaseFragment<FragmentUpdateBinding> {
    @Override
    public int getLayoutId() {
        return R.layout.fragment_update;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        binding.update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String url = getString(R.string.bmob_function_base_url)
                        + getString(R.string.bmob_function_update);
                JSONObject jsonObject = new JSONObject();
                try {
                    jsonObject.put("face",binding.face.getText().toString());
                    jsonObject.put("birthday",binding.birthday.getText().toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                OkGo.<String>post(url)
                        .tag(UpdateFragment.this.getActivity())
                        .params("username",binding.phone.getText().toString())
                        .params("password",binding.psw.getText().toString())
                        .params("userinfo",jsonObject.toString())
                        .execute(new StringCallback() {
                            @Override
                            public void onSuccess(Response<String> response) {
                                System.out.println("UpdateFragment.onSuccess");
                                ToastUtils.showShort(response.body());
                            }
                        });

            }
        });
    }
}
