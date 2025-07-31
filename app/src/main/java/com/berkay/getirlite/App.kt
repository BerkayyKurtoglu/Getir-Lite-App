package com.berkay.getirlite

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ScaffoldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.berkay.common.NavigationCommand
import com.berkay.common.Navigator
import com.berkay.getirlite.navigation.NavigationHandler
import com.berkay.ui.theme.GetirLiteColorScheme
import com.berkay.ui.theme.LocalColorScheme
import kotlinx.serialization.Serializable

@Serializable
data object TestScreenRoute : NavigationCommand.Destination

@Composable
fun App(
    navigator: Navigator,
    localColorScheme: GetirLiteColorScheme = LocalColorScheme.current,
) {
    val navController = rememberNavController()

    NavigationHandler(
        navigator = navigator,
        navController = navController
    )

    NavHost(
        navController = navController,
        startDestination = TestScreenRoute
    ) {
        composable<TestScreenRoute> {
            Scaffold(
                modifier = Modifier.fillMaxSize(),
                contentWindowInsets = ScaffoldDefaults.contentWindowInsets,
                topBar = {
                    Row(
                        modifier = Modifier
                            .height(88.dp)
                            .fillMaxWidth()
                            .background(color = localColorScheme.corePrimaryColor)
                    ) {

                    }
                }
            ) { innerPadding ->
                Column(
                    Modifier.padding(innerPadding)
                ) {
                    Text("Example Screen")
                }
            }
        }
    }

}
