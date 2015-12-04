package com.dhiman.databinding.ui.adapter;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dhiman.databinding.R;
import com.dhiman.databinding.databinding.AdapterLauncherBinding;
import com.dhiman.databinding.model.LauncherModel;

import java.util.List;

/**
 * Created by dhiman_da on 12/2/2015.
 */
public class LauncherAdapter extends RecyclerView.Adapter<LauncherAdapter.LauncherViewHolder> {
    private List<LauncherModel> mLaunchers;
    private OnItemClickListener mOnItemClickListener;

    public LauncherAdapter(final List<LauncherModel> launcherModels) {
        mLaunchers = launcherModels;
    }

    @Override
    public LauncherViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        final AdapterLauncherBinding binding = DataBindingUtil.inflate(inflater,
                R.layout.adapter_launcher,
                parent,
                false);
        return new LauncherViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(final LauncherViewHolder holder, final int position) {
        holder.getBinding().setLauncher(mLaunchers.get(position));
        holder.getBinding().setClicklistener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnItemClickListener != null) {
                    mOnItemClickListener.onItemClick(position, mLaunchers.get(position), holder.getBinding().adapterLauncherTextView);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mLaunchers == null ? 0 : mLaunchers.size();
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener {
        void onItemClick(final int id, final LauncherModel model, final TextView textView);
    }

    public static class LauncherViewHolder extends RecyclerView.ViewHolder {
        private AdapterLauncherBinding mBinding;

        public LauncherViewHolder(final AdapterLauncherBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }

        public AdapterLauncherBinding getBinding() {
            return mBinding;
        }
    }
}
