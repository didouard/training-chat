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
package com.fernandocejas.android10.sample.test.view.activity;

import android.app.Fragment;
import android.content.Intent;
import android.test.ActivityInstrumentationTestCase2;
import com.meuuh.chat.training.presentation.R;
import com.meuuh.chat.training.presentation.view.activity.RoomActivity;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.not;

public class RoomActivityTest extends ActivityInstrumentationTestCase2<RoomActivity> {

  private static final String FAKE_USER_ID = "10";

  private RoomActivity roomActivity;

  public RoomActivityTest() {
    super(RoomActivity.class);
  }

  @Override protected void setUp() throws Exception {
    super.setUp();
    this.setActivityIntent(createTargetIntent());
    this.roomActivity = getActivity();
  }

  @Override protected void tearDown() throws Exception {
    super.tearDown();
  }

  public void testContainsUserDetailsFragment() {
    Fragment userDetailsFragment =
        roomActivity.getFragmentManager().findFragmentById(R.id.fl_fragment);
    assertThat(userDetailsFragment, is(notNullValue()));
  }

  public void testContainsProperTitle() {
    String actualTitle = this.roomActivity.getTitle().toString().trim();

    assertThat(actualTitle, is("RoomList Details"));
  }

  public void testLoadUserHappyCaseViews() {
    onView(withId(R.id.rl_retry)).check(matches(not(isDisplayed())));
    onView(withId(R.id.rl_progress)).check(matches(not(isDisplayed())));

    onView(withId(R.id.tv_fullname)).check(matches(isDisplayed()));
    onView(withId(R.id.tv_email)).check(matches(isDisplayed()));
    onView(withId(R.id.tv_description)).check(matches(isDisplayed()));
  }

  public void testLoadUserHappyCaseData() {
    onView(withId(R.id.tv_fullname)).check(matches(withText("John Sanchez")));
    onView(withId(R.id.tv_email)).check(matches(withText("dmedina@katz.edu")));
    onView(withId(R.id.tv_followers)).check(matches(withText("4523")));
  }

  private Intent createTargetIntent() {
    Intent intentLaunchActivity =
        RoomActivity.getCallingIntent(getInstrumentation().getTargetContext(), FAKE_USER_ID);

    return intentLaunchActivity;
  }
}
