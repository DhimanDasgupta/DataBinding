package com.dhiman.databinding.ui.activities;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.CompoundButton;

import com.dhiman.databinding.R;
import com.dhiman.databinding.databinding.ActivityActivityDataBindingBinding;
import com.dhiman.databinding.model.ActivityModel;
import com.dhiman.databinding.utils.AnimatorUtils;

public class ActivityDataBindingActivity extends BaseAppcompatActivity {
    private ActivityActivityDataBindingBinding mBinding;
    private ActivityModel mActivityModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_activity_data_binding);

        setupToolbarWithText((Toolbar) findViewById(R.id.toolbar), "Activity Data binding");

        if (savedInstanceState == null) {
            mActivityModel = new ActivityModel();
        } else {
            mActivityModel = savedInstanceState.getParcelable(ActivityModel.ACTIVITY_MODEL_TAG);
        }

        mBinding.setActivityModel(mActivityModel);
        mBinding.firstNameCheckbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mActivityModel.setShowFirstName(isChecked);
            }
        });
        mBinding.lastNameCheckbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mActivityModel.setShowLastName(isChecked);
            }
        });
        mBinding.editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                mActivityModel.setFirstName(s.toString());
            }
        });

        mBinding.editText2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                mActivityModel.setLastName(s.toString());
            }
        });

        doRevealAnimation(mBinding.containerLayout, AnimatorUtils.VERY_LONG_ANIMATION_DURATION);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putParcelable(ActivityModel.ACTIVITY_MODEL_TAG, mActivityModel);
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        outState.putParcelable(ActivityModel.ACTIVITY_MODEL_TAG, mActivityModel);
        super.onSaveInstanceState(outState, outPersistentState);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        mBinding.unbind();
    }
}
