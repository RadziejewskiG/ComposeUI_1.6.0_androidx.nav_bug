package com.example.composenavtest

import android.content.res.ColorStateList
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidViewBinding
import androidx.navigation.NavController
import androidx.navigation.createGraph
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.fragment
import com.example.composenavtest.databinding.MainActivityBinding
import com.example.composenavtest.ui.Screen1
import com.example.composenavtest.ui.Screen2
import com.example.composenavtest.ui.theme.ComposeNavTestTheme

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeNavTestTheme {
                var navController by remember {
                    mutableStateOf<NavController?>(null)
                }

                Column(modifier = Modifier.fillMaxWidth()) {
                    AndroidViewBinding(
                        modifier = Modifier
                            .background(Color.Black)
                            .fillMaxWidth()
                            .weight(1f),
                        factory = { layoutInflater, viewGroup, boolean ->
                            MainActivityBinding.inflate(layoutInflater, viewGroup, boolean).apply {
                                navHostFragment.setBackgroundColor(Color.Black.hashCode())
                                navHostFragment.backgroundTintList =
                                    ColorStateList.valueOf(Color.Black.hashCode())
                            }.also {
                                val navHost =
                                    supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
                                val nav: NavController = navHost.navController

                                val graph = nav.createGraph(
                                    id = R.id.main_nav_graph,
                                    startDestination = R.id.screen1,
                                ) {
                                    fragment<Screen1>(R.id.screen1) {
                                        label = "screen1"
                                    }
                                    fragment<Screen2>(R.id.screen2) {
                                        label = "screen2"
                                    }
                                }

                                nav.setGraph(graph, null)

                                navController = nav
                            }
                        },
                    )
                    Box(
                        modifier = Modifier
                            .background(Color.Black)
                            .fillMaxWidth()
                            .padding(16.dp),
                        contentAlignment = Alignment.Center,
                    ) {
                        Button(
                            onClick = {
                                navController?.navigate(
                                    resId = R.id.screen2,
                                    args = null,
                                    navOptions = androidx.navigation.navOptions {
                                        anim {
                                            enter = R.anim.slide_in_from_right
                                            exit = R.anim.slide_out_to_left
                                            popEnter = R.anim.slide_in_from_left
                                            popExit = R.anim.slide_out_to_right
                                        }
                                    }
                                )
                            }
                        ) {
                            Text(
                                text = "Open screen 2",
                            )
                        }
                    }
                }
            }
        }
    }
}
