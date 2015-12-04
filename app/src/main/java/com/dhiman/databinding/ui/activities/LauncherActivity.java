package com.dhiman.databinding.ui.activities;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;
import android.widget.Toast;

import com.dhiman.databinding.R;
import com.dhiman.databinding.databinding.ActivityLauncherBinding;
import com.dhiman.databinding.model.LauncherGenerator;
import com.dhiman.databinding.model.LauncherModel;
import com.dhiman.databinding.ui.adapter.LauncherAdapter;
import com.dhiman.databinding.utils.AnimatorUtils;

public class LauncherActivity extends BaseAppcompatActivity {
    private ActivityLauncherBinding mBinding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_launcher);

        setupToolbarWithText((Toolbar) findViewById(R.id.toolbar), "Awesome Data binding");
        setupRecyclerView();
    }

    private void setupRecyclerView() {
        final RecyclerView recyclerView = mBinding.recylerView;
        if (recyclerView != null) {
            recyclerView.setLayoutManager(new LinearLayoutManager(getBaseContext()));
            final LauncherAdapter adapter = new LauncherAdapter(LauncherGenerator.getAllLaunchers());
            adapter.setOnItemClickListener(new LauncherAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(int id, LauncherModel model, final TextView textView) {
                    onItemClicked(id, model, textView);
                }
            });
            recyclerView.setAdapter(adapter);
        }

        doRevealAnimation(recyclerView, AnimatorUtils.VERY_LONG_ANIMATION_DURATION);
    }

    private void onItemClicked(int id, LauncherModel model, final TextView textView) {
        try {
            final Class clazz = Class.forName(getPackageName() + ".ui.activities" + "." + model.getClassName());
            final Intent intent = new Intent(getBaseContext(), clazz);

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                ActivityOptionsCompat options = ActivityOptionsCompat.
                        makeSceneTransitionAnimation(LauncherActivity.this,
                                textView,
                                getString(R.string.app_transition_list_to_toolbar));
                startActivity(intent, options.toBundle());
            } else {
                startActivity(intent);
            }
        } catch (ClassNotFoundException e) {
            Toast.makeText(getBaseContext(),
                    "Class Not found",
                    Toast.LENGTH_SHORT).show();
        } catch (ActivityNotFoundException e) {
            Toast.makeText(getBaseContext(),
                    "Is Activity registered in Manifest? Please check.",
                    Toast.LENGTH_SHORT).show();
        }
    }
}
