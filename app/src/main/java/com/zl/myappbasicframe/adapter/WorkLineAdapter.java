package com.zl.myappbasicframe.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.zl.myappbasicframe.beans.WorkLine;
import com.zl.myappbasicframe.databinding.ActivityDatabindingItemBinding;

import java.util.List;

/**
 * Created by Ray on 2017/5/23.
 */

public class WorkLineAdapter extends BaseAdapter {

    private List<WorkLine> workLines;
    private LayoutInflater inflater;

    public WorkLineAdapter(List<WorkLine> workLines, Context context) {
        this.workLines = workLines;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return workLines == null ? 0 : workLines.size();
    }

    @Override
    public Object getItem(int position) {
        return workLines.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final WorkLine workLine = workLines.get(position);

        ActivityDatabindingItemBinding binding;

        if (convertView == null) {
            binding = ActivityDatabindingItemBinding.inflate(inflater, parent, false);
            convertView = binding.getRoot();
            binding.setWorkline(workLine);
            convertView.setTag(binding);
        } else {
            binding = (ActivityDatabindingItemBinding) convertView.getTag();
        }
        binding.setWorkline(workLine);
        return convertView;
    }

}
