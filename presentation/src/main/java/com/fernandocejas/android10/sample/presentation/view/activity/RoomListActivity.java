/**
 * Copyright (C) 2014 android10.org. All rights reserved.
 * @author Fernando Cejas (the android10 coder)
 */
package com.fernandocejas.android10.sample.presentation.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import com.fernandocejas.android10.sample.presentation.R;
import com.fernandocejas.android10.sample.presentation.db.Room;
import com.fernandocejas.android10.sample.presentation.internal.di.HasComponent;
import com.fernandocejas.android10.sample.presentation.internal.di.components.DaggerRoomComponent;
import com.fernandocejas.android10.sample.presentation.internal.di.components.RoomComponent;
import com.fernandocejas.android10.sample.presentation.view.fragment.RoomListFragment;

/**
 * Activity that shows a list of Users.
 */
public class RoomListActivity extends BaseActivity implements HasComponent<RoomComponent>,
        RoomListFragment.RoomListListener {

  public static Intent getCallingIntent(Context context) {
    return new Intent(context, RoomListActivity.class);
  }

  private RoomComponent roomComponent;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
    setContentView(R.layout.activity_room_list);

    this.initializeInjector();
  }

  private void initializeInjector() {
    this.roomComponent = DaggerRoomComponent.builder()
        .applicationComponent(getApplicationComponent())
        .activityModule(getActivityModule())
        .build();
  }

  @Override public RoomComponent getComponent() {
    return roomComponent;
  }

  @Override public void onRoomClicked(Room room) {
    this.navigator.navigateToUserDetails(this, room.getObjectId());
  }

}
