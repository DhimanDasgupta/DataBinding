package com.dhiman.databinding.model;

import com.dhiman.databinding.ui.activities.ActivityDataBindingActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dhiman_da on 12/2/2015.
 */
public class LauncherGenerator {
    public static List<LauncherModel> getAllLaunchers() {
        final int size = 1;
        final List<LauncherModel> launcherModels = new ArrayList<>(size);

        launcherModels.add(new LauncherModel("Activity Data Binding Demo",
                ActivityDataBindingActivity.class.getSimpleName()));

        return launcherModels;
    }
}
