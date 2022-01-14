package com.example.mainactivity;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.repsly.library.timelineview.LineType;
import com.repsly.library.timelineview.TimelineView;

import java.util.List;

public class TimelineAdapter extends RecyclerView.Adapter<TimelineAdapter.TimelineViewHolder> {

    private final int orientation;
    private final List<ListItem> items;

    TimelineAdapter(int orientation, List<ListItem> items) {
        this.orientation = orientation;
        this.items = items;
    }

    @Override
    public int getItemViewType(int position) {
        if (orientation == LinearLayoutManager.VERTICAL) {
            return R.layout.item_vertical;
        } else {
            return R.layout.item_horizontal;
        }
    }

    @NonNull
    @Override
    public TimelineViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(viewType, parent, false);
        return new TimelineViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull TimelineAdapter.TimelineViewHolder holder, int position) {
        holder.tvName.setText(items.get(position).getName());
        holder.tvAddress.setText(items.get(position).getAddress());
        holder.timelineView.setLineType(getLineType(position));
        holder.timelineView.setNumber(position);

        // Make first and last markers stroked, others filled
        if (position == 0 || position + 1 == getItemCount()) {
            holder.timelineView.setFillMarker(false);
        } else {
            holder.timelineView.setFillMarker(true);
        }

        if (position == 4) {
            holder.timelineView.setDrawable(AppCompatResources
                    .getDrawable(holder.timelineView.getContext(),
                            R.drawable.ic_checked));
        } else {
            holder.timelineView.setDrawable(null);
        }

        // Set every third item active
        holder.timelineView.setActive(position % 3 == 2);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    private LineType getLineType(int position) {
        if (getItemCount() == 1) {
            return LineType.ONLYONE;

        } else {
            if (position == 0) {
                return LineType.BEGIN;

            } else if (position == getItemCount() - 1) {
                return LineType.END;

            } else {
                return LineType.NORMAL;
            }
        }
    }

    public class TimelineViewHolder extends RecyclerView.ViewHolder {
        TimelineView timelineView;
        TextView tvName;
        TextView tvAddress;
        public TimelineViewHolder(@NonNull View view) {
            super(view);
            timelineView = (TimelineView) view.findViewById(R.id.timeline);
            tvName = (TextView) view.findViewById(R.id.tv_name);
            tvAddress = (TextView) view.findViewById(R.id.tv_address);
        }
    }
}
