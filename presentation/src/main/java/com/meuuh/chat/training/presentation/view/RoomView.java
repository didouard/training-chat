/**
 * Copyright (C) 2014 android10.org. All rights reserved.
 * @author Fernando Cejas (the android10 coder)
 */
package com.meuuh.chat.training.presentation.view;

import com.meuuh.chat.training.presentation.db.Message;

import java.util.List;

/**
 * Interface representing a View in a model view presenter (MVP) pattern.
 * In this case is used as a view representing a user profile.
 */
public interface RoomView extends LoadDataView {
  void renderMessages(List<Message> messages);
}
