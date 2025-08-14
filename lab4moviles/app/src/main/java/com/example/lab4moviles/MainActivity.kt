package com.example.lab4moviles

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    PortadaUVG()
                }
            }
        }
    }
}

@Composable
fun PortadaUVG() {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .border(width = 10.dp, color = Color(0xFF004D00))
            .padding(16.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.uvg_logo),
            contentDescription = null,
            modifier = Modifier
                .size(250.dp)
                .align(Alignment.Center)
                .alpha(0.15f) // Opacidad ajustada para un efecto más tenue
        )

        // Contenido principal encima de la imagen
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 48.dp, bottom = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                text = "Universidad del Valle\nde Guatemala",
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
            Text(
                text = "Programación de plataformas\nmóviles, Sección 30",
                fontSize = 24.sp,
                textAlign = TextAlign.Center
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "INTEGRANTES",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.weight(1f, fill = false)
                )
                Column(
                    modifier = Modifier.weight(1f, fill = true),
                    horizontalAlignment = Alignment.End
                ) {
                    Text(
                        text = "Derek Coronado",
                        fontSize = 18.sp,
                        textAlign = TextAlign.End
                    )
                    Text(
                        text = "Antony Saz",
                        fontSize = 18.sp,
                        textAlign = TextAlign.End
                    )
                    Text(
                        text = "Jose Ovando",
                        fontSize = 18.sp,
                        textAlign = TextAlign.End
                    )
                }
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "CATEDRÁTICO",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.weight(1f, fill = false)
                )
                Column(
                    modifier = Modifier.weight(1f, fill = true),
                    horizontalAlignment = Alignment.End
                ) {
                    Text(
                        text = "Juan Carlos Durini",
                        fontSize = 18.sp,
                        textAlign = TextAlign.End
                    )
                }
            }
            Spacer(modifier = Modifier.height(32.dp))
            Text(
                text = "Nombre del alumno",
                fontSize = 16.sp,
                textAlign = TextAlign.Center
            )
            Text(
                text = "Número de carné",
                fontSize = 16.sp,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewPortadaUVG() {
    MaterialTheme {
        PortadaUVG()
    }
}