package com.meuuh.chat.training.presentation.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import butterknife.InjectView;
import butterknife.OnClick;
import com.meuuh.chat.training.presentation.R;
import com.parse.ParseUser;
import com.parse.ui.ParseLoginBuilder;

/**
 * Main application screen. This is the app entry point.
 */
public class MainActivity extends BaseActivity {

  private ParseLoginBuilder parseLoginBuilder;
  private ParseUser user;

  @InjectView(R.id.btn_LoadData) Button btn_LoadData;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    if (parseLoginBuilder == null) {
      parseLoginBuilder = new ParseLoginBuilder(MainActivity.this);
    }
    startActivityForResult(parseLoginBuilder.build(), 0);
  }

  @Override
  protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    super.onActivityResult(requestCode, resultCode, data);

    this.navigator.navigateToRoomList(this);
  }

  @OnClick(R.id.btn_LoadData)
  void navigateToUserList() {
    this.navigator.navigateToRoomList(this);
  }
}
