package com.fernandocejas.android10.sample.presentation.model;


import com.fernandocejas.android10.sample.presentation.db.Message;
import com.fernandocejas.android10.sample.presentation.presenter.RoomModel;

import java.util.List;

/**
 * Created by Edouard on 25/05/2015.
 */
public interface IRoomModel {
    void setName(String name);
    String getName();
    void setId(String id);
    String getId();
    void setMessages(List<Message> messages);
    List<Message> getMessages();
    void setPosition(Integer position);
    Integer getPosition();

    void fetchRoom(final RoomModel.RoomModelFetchRoomCallback callback);
    void sendMessage(String message, final RoomModel.RoomModelSendMessageCallback callback);
    void fetchMessages(final RoomModel.RoomModelFetchMessageCallback callback);
}
