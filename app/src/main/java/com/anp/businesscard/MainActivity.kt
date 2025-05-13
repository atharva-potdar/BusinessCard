package com.anp.businesscard

import android.content.Intent
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
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Call
import androidx.compose.material.icons.rounded.Email
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.net.toUri
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
                        phoneNumber = "+919987325869",
                        email = "atharvapotdar07@gmail.com",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun BusinessCard(name: String, email: String, phoneNumber: String, modifier: Modifier = Modifier) {
    val context = LocalContext.current
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Hey. I'm $name.",
//            fontSize = 32.sp,
            style = MaterialTheme.typography.displaySmall
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Welcome to my business card app.",
//            modifier = Modifier
////                .background(
////                    color =
////                        when (isSystemInDarkTheme()) {
////                            false -> Color(0xFFDDFBEE)
////                            else -> Color(0xFFA4BAB1)
////                        }
////                )
//                .padding(4.dp),
            style = MaterialTheme.typography.titleLarge
            )
        Row(
            modifier = Modifier
                .padding(top = 12.dp)
                .clickable(enabled = true, onClick = {
                    context.startActivity(Intent(Intent.ACTION_DIAL, "tel:$phoneNumber".toUri()))
                })
        ) {
            Icon(Icons.Rounded.Call, contentDescription = "Phone number")
            Spacer(modifier = Modifier.width(4.dp))
            Text(text = phoneNumber)
        }
        Row(
            modifier = Modifier
                .padding(top = 8.dp)
                .clickable(enabled = true, onClick = {
                    context.startActivity(Intent(Intent.ACTION_SENDTO, "mailto:$email".toUri()))
                })) {
            Icon(Icons.Rounded.Email, contentDescription = "Email address")
            Spacer(modifier = Modifier.width(4.dp))
            Text(text = email)
        }
        SocialHandle(
            "github",
            "atharva-potdar",
            "https://github.com/atharva-potdar",
            Color(0xFF83AE3D),
            Color(0xFFB1EA55),
            R.drawable.github,
            80.dp
        )

        // Add Instagram handle if needed, not needed for professional settings
        SocialHandle(
            "instagram",
            "atharva___potdar",
            "https://instagram.com/atharva___potdar",
            Color(0xFFAF247C),
            Color(0xFFEC34A8),
            R.drawable.instagram,
            80.dp
        )
        SocialHandle(
            "linkedin",
            "atharva-potdar-90ba45364",
            "https://www.linkedin.com/in/atharva-potdar-90ba45364/",
            Color(0xFF798FBE),
            Color(0xFFA4C1FF),
            R.drawable.linkedin,
            80.dp
        )
    }
}

@Composable
fun SocialHandle(
    site: String,
    handle: String,
    url: String,
    darkColor: Color,
    lightColor: Color,
    id: Int,
    iconSize: Dp,
    modifier: Modifier = Modifier
) {
    val uriHandler = LocalUriHandler.current
    Spacer(modifier = Modifier.height(24.dp))
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(
                color = when (isSystemInDarkTheme()) {
                    false -> lightColor
                    else -> darkColor
                }
            )
            .clickable(
                enabled = true, onClick = { uriHandler.openUri(url) })
            .padding(horizontal = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
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
        BusinessCard(
            name = "Atharva Potdar",
            phoneNumber = "+919987325869",
            email = "atharvapotdar07@gmail.com"
        )
    }
}