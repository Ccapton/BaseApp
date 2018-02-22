package com.capton.common.demo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.capton.common.R;
import com.capton.common.base.BaseFragment;
import com.capton.common.databinding.FragmentUpdateBinding;

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



            }
        });
    }
}
