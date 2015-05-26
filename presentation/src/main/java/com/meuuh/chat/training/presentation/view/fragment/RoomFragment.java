/**
 * Copyright (C) 2014 android10.org. All rights reserved.
 * @author Fernando Cejas (the android10 coder)
 */
package com.meuuh.chat.training.presentation.view.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;

import com.meuuh.chat.training.presentation.R;
import com.meuuh.chat.training.presentation.db.Message;
import com.meuuh.chat.training.presentation.internal.di.components.RoomComponent;
import com.meuuh.chat.training.presentation.presenter.RoomPresenter;
import com.meuuh.chat.training.presentation.view.RoomView;

import java.util.List;

import javax.inject.Inject;

/**
 * Fragment that shows details of a certain user.
 */
public class RoomFragment extends BaseFragment implements RoomView {

  private static final String ARGUMENT_KEY_ROOM_ID = "org.android10.ARGUMENT_USER_ID";

  private String roomId;

  @Inject
  RoomPresenter roomPresenter;



  public RoomFragment() { super(); }

  public static RoomFragment newInstance(String roomId) {
    RoomFragment roomFragment = new RoomFragment();

    Bundle argumentsBundle = new Bundle();
    argumentsBundle.putString(ARGUMENT_KEY_ROOM_ID, roomId);
    roomFragment.setArguments(argumentsBundle);

    return roomFragment;
  }

  @Override public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {

    View fragmentView = inflater.inflate(R.layout.fragment_room, container, false);
    ButterKnife.inject(this, fragmentView);

    /**/

    return fragmentView;
  }

  @Override public void onActivityCreated(Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);
    this.initialize();
  }

  @Override public void onResume() {
    super.onResume();
    this.roomPresenter.resume();
  }

  @Override public void onPause() {
    super.onPause();
    this.roomPresenter.pause();
  }

  @Override public void onDestroy() {
    super.onDestroy();
    this.roomPresenter.destroy();
  }

  private void initialize() {
    this.getComponent(RoomComponent.class).inject(this);
    this.roomPresenter.setView(this);
    this.roomId = getArguments().getString(ARGUMENT_KEY_ROOM_ID);
    this.roomPresenter.initialize(this.roomId);
  }

  @Override public void renderMessages(List<Message> messages) {

  }

  @Override public void showLoading() {
    //this.rl_progress.setVisibility(View.VISIBLE);
    this.getActivity().setProgressBarIndeterminateVisibility(true);
  }

  @Override public void hideLoading() {
    //this.rl_progress.setVisibility(View.GONE);
    this.getActivity().setProgressBarIndeterminateVisibility(false);
  }

  @Override public void showRetry() {
    //this.rl_retry.setVisibility(View.VISIBLE);
  }

  @Override public void hideRetry() {
    //this.rl_retry.setVisibility(View.GONE);
  }

  @Override public void showError(String message) {
    this.showToastMessage(message);
  }

  @Override public Context getContext() {
    return getActivity().getApplicationContext();
  }


}
