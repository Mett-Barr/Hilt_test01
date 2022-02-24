package com.example.hilttest01

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.hilttest01.data.source.local.DataModel
import com.example.hilttest01.data.source.local.TestDataModel
import com.example.hilttest01.ui.theme.HiltTest01Theme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.init()

        setContent {
            HiltTest01Theme {
                Surface(modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background) {
                    val data by viewModel.user.collectAsState(initial = DataModel(0,
                        "null",
                        "null"))
                    val size by viewModel.size.collectAsState(initial = 0)
                    Column(modifier = Modifier.fillMaxSize()) {
                        if (data == null) {
                            repeat(3) {
                                Text(text = "null")
                            }
                        } else {
                            Text(text = data?.userID.toString())
                            Text(text = data!!.userName)
                            Text(text = data!!.userDesignation)
                        }

                        Row(modifier = Modifier.fillMaxWidth()) {
                            Button(modifier = Modifier
                                .weight(1f)
                                .padding(8.dp),
                                onClick = { viewModel.delete() },
                                enabled = size == 1) {
                                Text(text = "delete")
                            }
                            Button(modifier = Modifier
                                .weight(1f)
                                .padding(8.dp),
                                onClick = { viewModel.add() },
                                enabled = size == 0) {
                                Text(text = "add")
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    HiltTest01Theme {
        Greeting("Android")
    }
}

@Composable
fun TestDataModel() {
}