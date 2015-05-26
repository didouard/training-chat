package com.fernandocejas.android10.sample.presentation.db;

import com.parse.ParseClassName;
import com.parse.ParseObject;

/**
 * Created by Edouard on 25/05/2015.
 */
@ParseClassName("Room")
public class Room extends ParseObject {
    public void setName(String name) {
        put("name", name);
    }

    public String getName() {
        return getString("name");
    }

    public void setId(String id) {
        setObjectId(id);
    }

    public String getId() {
        return getObjectId();
    }

    public void setPosition(Integer position) {
        put("position", position);
    }

    public Integer getPosition() {
        return getInt("position");
    }
}
