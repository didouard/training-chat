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
package com.fernandocejas.android10.sample.test.mapper;


import com.meuuh.chat.training.presentation.model.RoomList;

import java.util.ArrayList;
import java.util.List;
import junit.framework.TestCase;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.mock;

public class RoomListModelDataMapperTest extends TestCase {

  private static final String FAKE_USER_ID = "123";
  private static final String FAKE_FULLNAME = "Tony Stark";

  //private RoomModelDataMapper roomModelDataMapper;

  @Override protected void setUp() throws Exception {
    super.setUp();
    //roomModelDataMapper = new RoomModelDataMapper();
  }

  public void testTransformUser() {
    RoomList user = createFakeUser();
    //RoomList roomListModel = roomModelDataMapper.transform(user);

    //assertThat(roomListModel, is(instanceOf(RoomList.class)));
    //assertThat(roomListModel.getRoomId(), is(FAKE_USER_ID));
    //assertThat(roomListModel.getFullName(), is(FAKE_FULLNAME));
  }

  public void testTransformUserCollection() {
    RoomList mockUserOne = mock(RoomList.class);
    RoomList mockUserTwo = mock(RoomList.class);

    List<RoomList> userList = new ArrayList<RoomList>(5);
    userList.add(mockUserOne);
    userList.add(mockUserTwo);

    //Collection<RoomList> roomListModelList = roomModelDataMapper.transform(userList);

    //assertThat(roomListModelList.toArray()[0], is(instanceOf(RoomList.class)));
    //assertThat(roomListModelList.toArray()[1], is(instanceOf(RoomList.class)));
    //assertThat(roomListModelList.size(), is(2));
  }

  private RoomList createFakeUser() {
    RoomList user = new RoomList();
    //user.setFullName(FAKE_FULLNAME);

    return user;
  }
}
