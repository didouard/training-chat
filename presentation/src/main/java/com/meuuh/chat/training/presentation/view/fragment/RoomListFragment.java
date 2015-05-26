/**
 * Copyright (C) 2014 android10.org. All rights reserved.
 * @author Fernando Cejas (the android10 coder)
 */
package com.meuuh.chat.training.presentation.view.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import com.meuuh.chat.training.presentation.R;
import com.meuuh.chat.training.presentation.db.Room;
import com.meuuh.chat.training.presentation.internal.di.components.RoomComponent;
import com.meuuh.chat.training.presentation.presenter.RoomListPresenter;
import com.meuuh.chat.training.presentation.view.RoomListView;
import com.meuuh.chat.training.presentation.view.adapter.RoomsAdapter;
import com.meuuh.chat.training.presentation.view.adapter.roomsLayoutManager;

import java.util.Collection;
import javax.inject.Inject;

/**
 * Fragment that shows a list of rooms.
 */
public class RoomListFragment extends BaseFragment implements RoomListView {

  /**
   * Interface for listening room list events.
   */
  public interface RoomListListener {
    void onRoomClicked(final Room roomListModel);
  }

  @Inject
  RoomListPresenter roomListPresenter;

  @InjectView(R.id.rv_rooms) RecyclerView rv_rooms;
  @InjectView(R.id.rl_progress) RelativeLayout rl_progress;
  @InjectView(R.id.rl_retry) RelativeLayout rl_retry;
  @InjectView(R.id.bt_retry) Button bt_retry;

  private RoomsAdapter roomsAdapter;
  private roomsLayoutManager roomsLayoutManager;

  private RoomListListener roomListListener;

  public RoomListFragment() { super(); }

  @Override public void onAttach(Activity activity) {
    super.onAttach(activity);
    if (activity instanceof RoomListListener) {
      this.roomListListener = (RoomListListener) activity;
    }
  }

  @Override public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {

    View fragmentView = inflater.inflate(R.layout.fragment_room_list, container, true);
    ButterKnife.inject(this, fragmentView);
    setupUI();

    return fragmentView;
  }

  @Override public void onActivityCreated(Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);
    this.initialize();
    this.loadRoomList();
  }

  @Override public void onResume() {
    super.onResume();
    this.roomListPresenter.resume();
  }

  @Override public void onPause() {
    super.onPause();
    this.roomListPresenter.pause();
  }

  @Override public void onDestroy() {
    super.onDestroy();
    this.roomListPresenter.destroy();
  }

  private void initialize() {
    this.getComponent(RoomComponent.class).inject(this);
    this.roomListPresenter.setView(this);
  }

  private void setupUI() {
    this.roomsLayoutManager = new roomsLayoutManager(getActivity());
    this.rv_rooms.setLayoutManager(roomsLayoutManager);
  }

  @Override public void showLoading() {
    this.rl_progress.setVisibility(View.VISIBLE);
    this.getActivity().setProgressBarIndeterminateVisibility(true);
  }

  @Override public void hideLoading() {
    this.rl_progress.setVisibility(View.GONE);
    this.getActivity().setProgressBarIndeterminateVisibility(false);
  }

  @Override public void showRetry() {
    this.rl_retry.setVisibility(View.VISIBLE);
  }

  @Override public void hideRetry() {
    this.rl_retry.setVisibility(View.GONE);
  }

  @Override public void renderRoomList(Collection<Room> rooms) {
    if (rooms != null) {
      if (this.roomsAdapter == null) {
        this.roomsAdapter = new RoomsAdapter(getActivity(), rooms);
      } else {
        this.roomsAdapter.setRoomsCollection(rooms);
      }
      this.roomsAdapter.setOnItemClickListener(onItemClickListener);
      this.rv_rooms.setAdapter(roomsAdapter);
    }
  }

  @Override public void viewRoom(Room room) {
    if (this.roomListListener != null) {
      this.roomListListener.onRoomClicked(room);
    }
  }

  @Override public void showError(String message) {
    this.showToastMessage(message);
  }

  @Override public Context getContext() {
    return this.getActivity().getApplicationContext();
  }

  /**
   * Loads all rooms.
   */
  private void loadRoomList() {
    this.roomListPresenter.initialize();
  }

  @OnClick(R.id.bt_retry) void onButtonRetryClick() {
    RoomListFragment.this.loadRoomList();
  }

  private RoomsAdapter.OnItemClickListener onItemClickListener =
      new RoomsAdapter.OnItemClickListener() {
        @Override public void onRoomItemClicked(Room rooms) {
            if (RoomListFragment.this.roomListPresenter != null && rooms != null) {
              RoomListFragment.this.roomListPresenter.onRoomClicked(rooms);
            }
        }
      };

}
