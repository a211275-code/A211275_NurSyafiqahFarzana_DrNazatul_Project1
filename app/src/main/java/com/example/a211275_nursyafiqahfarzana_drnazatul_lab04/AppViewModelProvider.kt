/*
 * Copyright (C) 2023 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.a211275_nursyafiqahfarzana_drnazatul_lab04.ui

import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.a211275_nursyafiqahfarzana_drnazatul_lab04.FoodApplication
import com.example.a211275_nursyafiqahfarzana_drnazatul_lab04.FoodViewModel

/**
 * Provides Factory to create instances of ViewModels for the entire Food app
 */
object AppViewModelProvider {
    val Factory = viewModelFactory {
        // Initializer for your primary FoodViewModel
        initializer {
            FoodViewModel(
                foodApplication().container.foodRepository
            )
        }

        // Note: If you add more ViewModels later (e.g., HistoryViewModel), 
        // you can cleanly add their initializer blocks right here!
    }
}

/**
 * Extension function to query for [Application] object and returns an instance of
 * [FoodApplication].
 */
fun CreationExtras.foodApplication(): FoodApplication =
    (this[AndroidViewModelFactory.APPLICATION_KEY] as FoodApplication)