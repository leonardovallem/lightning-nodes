package com.vallem.lightningnodes.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.vallem.lightningnodes.di.NetworkModule
import com.vallem.lightningnodes.di.RepositoryModule
import com.vallem.lightningnodes.di.UtilModule
import com.vallem.lightningnodes.di.ViewModelModule
import com.vallem.lightningnodes.presentation.theme.LightningNodesTheme
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        startKoin {
            androidContext(this@MainActivity)
            modules(NetworkModule, UtilModule, RepositoryModule, ViewModelModule)
        }

        setContent {
            LightningNodesTheme {
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        stopKoin()
    }
}