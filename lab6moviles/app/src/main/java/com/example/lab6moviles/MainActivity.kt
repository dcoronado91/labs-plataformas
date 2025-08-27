package com.example.lab6moviles

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CounterApp()
        }
    }
}

@Composable
fun CounterApp() {
    var counter by remember { mutableStateOf(0) }
    var increments by remember { mutableStateOf(0) }
    var decrements by remember { mutableStateOf(0) }
    var maxValue by remember { mutableStateOf(0) }
    var minValue by remember { mutableStateOf(0) }
    var totalChanges by remember { mutableStateOf(0) }
    var history by remember { mutableStateOf(listOf<Pair<Int, Boolean>>()) }
    // Pair(valor, true=incremento, false=decremento)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // TÍTULO
        Text(
            text = "Derek Coronado",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        // CONTADOR
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            // BOTÓN DECREMENTO
            Button(
                onClick = {
                    counter--
                    decrements++
                    totalChanges++
                    if (counter < minValue) minValue = counter
                    history = history + (counter to false)
                },
                shape = RoundedCornerShape(50),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFD32F2F),
                    contentColor = Color.White
            )
            ) {
                Text(text = "–", fontSize = 24.sp)
            }

            Text(
                text = counter.toString(),
                fontSize = 36.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(horizontal = 24.dp)
            )

            // BOTÓN INCREMENTO
            Button(
                onClick = {
                    counter++
                    increments++
                    totalChanges++
                    if (counter > maxValue) maxValue = counter
                    history = history + (counter to true)
                },
                shape = RoundedCornerShape(50),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF388E3C),
                    contentColor = Color.White
                )
            ) {
                Text(text = "+", fontSize = 24.sp)
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        // ESTADÍSTICAS
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Total incrementos: $increments",
                fontSize = 25.sp)
            Text("Total decrementos: $decrements",
                fontSize = 25.sp)
            Text("Valor máximo: $maxValue",
                fontSize = 25.sp)
            Text("Valor mínimo: $minValue",
                fontSize = 25.sp)
            Text("Total cambios: $totalChanges",
                fontSize = 25.sp)
            Text("Historial:", fontWeight = FontWeight.Bold,
                fontSize = 25.sp)
        }

        Spacer(modifier = Modifier.height(10.dp))

        // HISTORIAL
        LazyVerticalGrid(
            columns = GridCells.Fixed(5),
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth(),
            content = {
                items(history) { (value, isIncrement) ->
                    Box(
                        modifier = Modifier
                            .padding(4.dp)
                            .size(40.dp)
                            .background(
                                color = if (isIncrement) Color(0xFF388E3C) else Color(0xFFD32F2F),
                                shape = RoundedCornerShape(8.dp)
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = value.toString(),
                            color = Color.White,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
        )

        // BOTÓN REINICIAR
        Button(
            onClick = {
                counter = 0
                increments = 0
                decrements = 0
                maxValue = 0
                minValue = 0
                totalChanges = 0
                history = emptyList()
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
                .height(60.dp),
            shape = RoundedCornerShape(50),
                    colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF3F51B5),
            contentColor = Color.White
        )
        ) {
            Text("Reiniciar", fontSize = 20.sp)
        }
    }
}
