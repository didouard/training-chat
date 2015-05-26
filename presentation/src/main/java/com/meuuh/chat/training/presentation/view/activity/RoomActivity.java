/**
 * Copyright (C) 2014 android10.org. All rights reserved.
 * @author Fernando Cejas (the android10 coder)
 */
package com.meuuh.chat.training.presentation.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import com.meuuh.chat.training.presentation.R;
import com.meuuh.chat.training.presentation.internal.di.HasComponent;
import com.meuuh.chat.training.presentation.internal.di.components.DaggerRoomComponent;
import com.meuuh.chat.training.presentation.internal.di.components.RoomComponent;
import com.meuuh.chat.training.presentation.view.fragment.RoomFragment;

/**
 * Activity that shows details of a certain user.
 */
public class RoomActivity extends BaseActivity implements HasComponent<RoomComponent> {

  private static final String INTENT_EXTRA_PARAM_USER_ID = "org.android10.INTENT_PARAM_USER_ID";
  private static final String INSTANCE_STATE_PARAM_USER_ID = "org.android10.STATE_PARAM_USER_ID";

  private String roomId;
  private RoomComponent roomComponent;

  public static Intent getCallingIntent(Context context, String objectId) {
    Intent callingIntent = new Intent(context, RoomActivity.class);
    callingIntent.putExtra(INTENT_EXTRA_PARAM_USER_ID, objectId);

    return callingIntent;
  }

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
    setContentView(R.layout.activity_room);

    this.initializeActivity(savedInstanceState);
    this.initializeInjector();
  }

  @Override protected void onSaveInstanceState(Bundle outState) {
    if (outState != null) {
      outState.putString(INSTANCE_STATE_PARAM_USER_ID, this.roomId);
    }
    super.onSaveInstanceState(outState);
  }

  /**
   * Initializes this activity.
   */
  private void initializeActivity(Bundle savedInstanceState) {
    if (savedInstanceState == null) {
      String empty = "";
      this.roomId = getIntent().getStringExtra(INTENT_EXTRA_PARAM_USER_ID);
      addFragment(R.id.fl_fragment, RoomFragment.newInstance(this.roomId));
    } else {
      this.roomId = savedInstanceState.getString(INSTANCE_STATE_PARAM_USER_ID);
    }
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
}
