package com.example.evenline

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.datastore.core.DataStore
import androidx.datastore.dataStore
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.evenline.ui.theme.EvenlineTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    //Writing to a proto data store
//    suspend fun saveEmployeeInfo(eName: String, eDesignation: String) {
//        this.settingsDataStore.updateData { employeeData ->
//            employeeData.toBuilder()
//                .setEmpName(eName)
//                .setEmpDesignation(eDesignation)
//                .build()
//
//        }
//    }

    //Reading employee object from a proto data store
//    val employeeInfo: Flow<Appsettings> = this.settingsDataStore.data
//        .map { it }

    //Reading employee object property empName from a proto data store
//    val empName: Flow<String> = this.settingsDataStore.data
//        .map {
//            it.empName
//        }

    //Reading employee object property empDesignation from a proto data store
//    val empDesignation: Flow<String> = this.employeeProtoDataStore.data
//        .map {
//            it.empDesignation
//        }

    val viewModel: MainActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()
        super.onCreate(savedInstanceState)

        var uiState: MainActivityUiState by mutableStateOf(MainActivityUiState.Loading)

        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED){
                viewModel.uiState.onEach {
                    uiState = it
                }.collect()
            }
        }

        splashScreen.apply {
            // adding loading condition
            setKeepOnScreenCondition {
                when (uiState) {
                    MainActivityUiState.Loading -> true
                    is MainActivityUiState.Success -> false
                }
            }
        }
        setContent {

        }
    }
}
