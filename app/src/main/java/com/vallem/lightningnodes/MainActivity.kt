package com.vallem.lightningnodes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.vallem.lightningnodes.di.NetworkModule
import com.vallem.lightningnodes.di.RepositoryModule
import com.vallem.lightningnodes.di.UtilModule
import com.vallem.lightningnodes.ui.theme.LightningNodesTheme
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        startKoin {
            androidContext(this@MainActivity)
            modules(NetworkModule, UtilModule, RepositoryModule)
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