/**
 * Copyright (C) 2014 android10.org. All rights reserved.
 * @author Fernando Cejas (the android10 coder)
 */
package com.fernandocejas.android10.sample.presentation.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.ButterKnife;
import butterknife.InjectView;
import com.fernandocejas.android10.sample.presentation.R;
import com.fernandocejas.android10.sample.presentation.db.Room;

import java.util.Collection;
import java.util.List;

/**
 * Adaptar that manages a collection of {@link Room}.
 */
public class RoomsAdapter extends RecyclerView.Adapter<RoomsAdapter.RoomViewHolder> {

  public interface OnItemClickListener {
    void onRoomItemClicked(Room rooms);
  }

  private List<Room> rooms;
  private final LayoutInflater layoutInflater;

  private OnItemClickListener onItemClickListener;

  public RoomsAdapter(Context context, Collection<Room> rooms) {
    this.validateRoomsCollection(rooms);
    this.layoutInflater =
        (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    this.rooms = (List<Room>) rooms;
  }

  @Override public int getItemCount() {
    return (this.rooms != null) ? this.rooms.size() : 0;
  }

  @Override public RoomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View view = this.layoutInflater.inflate(R.layout.row_room, parent, false);
    RoomViewHolder roomViewHolder = new RoomViewHolder(view);

    return roomViewHolder;
  }

  @Override public void onBindViewHolder(RoomViewHolder holder, final int position) {
    final Room roomListModel = this.rooms.get(position);
    holder.textViewTitle.setText(roomListModel.getName());
    holder.itemView.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        if (RoomsAdapter.this.onItemClickListener != null) {
          RoomsAdapter.this.onItemClickListener.onRoomItemClicked(roomListModel);
        }
      }
    });
  }

  @Override public long getItemId(int position) {
    return position;
  }

  public void setRoomsCollection(Collection<Room> rooms) {
    this.validateRoomsCollection(rooms);
    this.rooms = (List<Room>) rooms;
    this.notifyDataSetChanged();
  }

  public void setOnItemClickListener (OnItemClickListener onItemClickListener) {
    this.onItemClickListener = onItemClickListener;
  }

  private void validateRoomsCollection(Collection<Room> rooms) {
    if (rooms == null) {
      throw new IllegalArgumentException("The list cannot be null");
    }
  }

  static class RoomViewHolder extends RecyclerView.ViewHolder {
    @InjectView(R.id.title) TextView textViewTitle;

    public RoomViewHolder(View itemView) {
      super(itemView);
      ButterKnife.inject(this, itemView);
    }
  }
}
