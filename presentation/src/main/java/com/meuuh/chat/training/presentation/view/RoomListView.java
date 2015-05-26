/**
 * Copyright (C) 2014 android10.org. All rights reserved.
 * @author Fernando Cejas (the android10 coder)
 */
package com.meuuh.chat.training.presentation.view;

import com.meuuh.chat.training.presentation.db.Room;
import com.meuuh.chat.training.presentation.model.RoomList;

import java.util.Collection;

/**
 * Interface representing a View in a model view presenter (MVP) pattern.
 * In this case is used as a view representing a list of {@link RoomList}.
 */
public interface RoomListView extends LoadDataView {
  /**
   * Render a user list in the UI.
   *
   * @param roomListModelCollection The collection of {@link RoomList} that will be shown.
   */
  void renderRoomList(Collection<Room> roomListModelCollection);

  /**
   * View a {@link RoomList} profile/details.
   *
   * @param roomListModel The user that will be shown.
   */
  void viewRoom(Room roomListModel);
}
