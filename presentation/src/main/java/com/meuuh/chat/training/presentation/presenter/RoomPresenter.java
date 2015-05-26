/**
 * Copyright (C) 2015 Fernando Cejas Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.meuuh.chat.training.presentation.presenter;

import android.support.annotation.NonNull;


import com.meuuh.chat.training.presentation.db.Message;
import com.meuuh.chat.training.presentation.internal.di.PerActivity;

import com.meuuh.chat.training.presentation.view.RoomView;

import java.util.List;

import javax.inject.Inject;

/**
 * {@link Presenter} that controls communication between views and models of the presentation
 * layer.
 */
@PerActivity
public class RoomPresenter implements Presenter {
  private RoomView viewDetailsView;

  @Inject
  public RoomPresenter() {

  }

  public void setView(@NonNull RoomView view) {
    this.viewDetailsView = view;
  }

  @Override public void resume() {}

  @Override public void pause() {}

  @Override public void destroy() {

  }

  /**
   * Initializes the presenter by start retrieving user details.
   */
  public void initialize(String roomId) {

  }

  /**
   * Loads user details.
   */
  private void loadMessage() {
    this.hideViewRetry();
    this.showViewLoading();
    this.refreshMessages();
  }

  private void showViewLoading() {
    this.viewDetailsView.showLoading();
  }

  private void hideViewLoading() {
    this.viewDetailsView.hideLoading();
  }

  private void showViewRetry() {
    this.viewDetailsView.showRetry();
  }

  private void hideViewRetry() {
    this.viewDetailsView.hideRetry();
  }


  private void showUserDetailsInView(List<Message> messages) {
    this.viewDetailsView.renderMessages(messages);
  }

  public void sendMessage(final String message) {

  }

  public void refreshMessages() {

  }


  public void onCompleted() {
    this.hideViewLoading();
  }


}
