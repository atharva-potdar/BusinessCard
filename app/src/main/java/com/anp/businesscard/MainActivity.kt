package com.anp.businesscard

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.anp.businesscard.ui.theme.BusinessCardTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BusinessCardTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    BusinessCard(
                        name = "Atharva Potdar",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun BusinessCard(name: String, modifier: Modifier = Modifier) {

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Hey. I'm $name.",
            fontSize = 24.sp,
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Welcome to my business card app.",
            modifier = Modifier.background(color = Color(
                when (isSystemInDarkTheme()) {
                    false -> 0xFFDDFBEE
                    else -> 0xFF527768
                }
            )),
            color = Color(when (isSystemInDarkTheme()) {
                false -> 0xFF999999
                else -> 0xFFF3F3F3
            })
        )
        SocialHandle(
            "github",
            "atharva-potdar",
            "https://github.com/atharva-potdar",
            Color(0xFFB1EA55),
            R.drawable.github,
            80.dp
        )
        SocialHandle(
            "instagram",
            "atharva___potdar",
            "https://instagram.com/atharva___potdar",
            Color(0xFFEC34A8),
            R.drawable.instagram,
            80.dp
        )
    }
}

@Composable
fun SocialHandle(
    site: String,
    handle: String,
    url: String,
    color: Color,
    id: Int,
    iconSize: Dp,
    modifier: Modifier = Modifier
) {
    val uriHandler = LocalUriHandler.current
    Spacer(modifier = Modifier.height(24.dp))
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(color = color)
            .clickable(
                enabled = true,
                onClick = { uriHandler.openUri(url) }),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly,
    ) {
        Image(
            painter = painterResource(id = id),
            contentDescription = "$site Logo",
            modifier = modifier
                .padding(vertical = 8.dp)
                .size(iconSize)
        )
        Text(
            text = "/$site/$handle"
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun BusinessCardPreview() {
    BusinessCardTheme {
        BusinessCard(name = "Atharva Potdar")
    }
}