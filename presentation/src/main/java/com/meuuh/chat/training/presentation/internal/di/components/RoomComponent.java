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
package com.meuuh.chat.training.presentation.internal.di.components;

import com.meuuh.chat.training.presentation.internal.di.PerActivity;
import com.meuuh.chat.training.presentation.internal.di.modules.ActivityModule;
import com.meuuh.chat.training.presentation.view.fragment.RoomFragment;
import com.meuuh.chat.training.presentation.view.fragment.RoomListFragment;

import dagger.Component;

/**
 * A scope {@link com.meuuh.chat.training.presentation.internal.di.PerActivity} component.
 * Injects user specific Fragments.
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = {ActivityModule.class})
public interface RoomComponent extends ActivityComponent {
  void inject(RoomListFragment userListFragment);
  void inject(RoomFragment roomFragment);
}
