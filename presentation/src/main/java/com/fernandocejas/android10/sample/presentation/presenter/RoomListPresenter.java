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
package com.fernandocejas.android10.sample.presentation.presenter;

import android.support.annotation.NonNull;
import android.util.Log;

import com.fernandocejas.android10.sample.presentation.db.Room;
import com.fernandocejas.android10.sample.presentation.internal.di.PerActivity;
import com.fernandocejas.android10.sample.presentation.model.IRoomListModel;
import com.fernandocejas.android10.sample.presentation.model.RoomList;
import com.fernandocejas.android10.sample.presentation.view.RoomListView;

import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;

/**
 * {@link Presenter} that controls communication between views and models of the presentation
 * layer.
 */
@PerActivity
public class RoomListPresenter  implements Presenter {
  private IRoomListModel roomListModel;
  private final RoomListPresenter self = this;
  private List<Room> rooms;

  private RoomListView viewListView;

  @Inject
  public RoomListPresenter() {
    roomListModel = new RoomList();
    rooms = new ArrayList<Room>();
  }

  public void setView(@NonNull RoomListView view) {
    this.viewListView = view;
  }

  /**
   * Initializes the presenter by start retrieving the user list.
   */
  public void initialize() {
    this.loadUserList();
  }


  public void resume() {
    refreshRooms();
  }

  public void pause() {}

  public void destroy() {

  }

  /**
   * Loads all users.
   */
  private void loadUserList() {
    this.hideViewRetry();
    this.showViewLoading();
    this.getUserList();
  }

  public void refreshRooms() {
    roomListModel.fetchRooms(new RoomList.FetchRoomListModelCallback() {
      @Override
      public void done(List<Room> rooms) {
        onCompleted();
        self.rooms.clear();
        self.rooms.addAll(rooms);
        self.showUsersCollectionInView(rooms);
      }
    });
  }

  public void createRoom(String name) {
    roomListModel.createRoom(name, new RoomList.CreateRoomListModelCallback() {
      @Override
      public void done(Room room) {
        self.rooms.add(room);
        self.viewListView.viewRoom(room);
      }
    });
  }

  public void onRoomClicked(Room room) {
    this.viewListView.viewRoom(room);
  }

  private void showViewLoading() {
    this.viewListView.showLoading();
  }

  private void hideViewLoading() {
    this.viewListView.hideLoading();
  }

  private void showViewRetry() {
    this.viewListView.showRetry();
  }

  private void hideViewRetry() {
    this.viewListView.hideRetry();
  }


  private void showUsersCollectionInView(List<Room> rooms) {
    this.viewListView.renderRoomList(rooms);
  }

  private void getUserList() {
    this.refreshRooms();
  }

  public void onCompleted() {
    this.hideViewLoading();
  }

  public void onError(Throwable e) {
    this.hideViewLoading();
    Log.e("beelee", e.getMessage());
    this.showViewRetry();
  }

//  @Override public void onNext(List<RoomList> users) {
//    this.showUsersCollectionInView(users);
//  }
}
