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

package com.example.a211275_nursyafiqahfarzana_drnazatul_lab04

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.a211275_nursyafiqahfarzana_drnazatul_lab04.ui.theme.FoodAppTheme
import com.example.a211275_nursyafiqahfarzana_drnazatul_lab04.ui.AppViewModelProvider // FIXED: Added import for centralized provider

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        setContent {
            FoodAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                ) {
                    // FIXED: Now safely initialized via the centralized AppViewModelProvider Factory
                    val viewModel: FoodViewModel = viewModel(factory = AppViewModelProvider.Factory)

                    FoodAppNavigation(viewModel = viewModel)
                }
            }
        }
    }
}