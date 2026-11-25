package com.example.littlelemon.ui.screens

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.littlelemon.R
import com.example.littlelemon.ui.theme.LittleLemonColor.Black
import com.example.littlelemon.ui.theme.LittleLemonColor.DisabledState
import com.example.littlelemon.ui.theme.LittleLemonColor.Yellow

@Composable
fun ProfileScreen(onHomeClick: () -> Unit,
                  onLogoutClick: () -> Unit,
                  modifier: Modifier) {

    val context = LocalContext.current

    val sharedPreferences = remember {
        context.getSharedPreferences(
            "LittleLemonPrefs",
            Context.MODE_PRIVATE
        )
    }

    val firstName = sharedPreferences.getString("firstName", null)
    val lastName = sharedPreferences.getString("lastName", null)
    val email = sharedPreferences.getString("email", null)
    Column(modifier = modifier.fillMaxSize()
        .statusBarsPadding()
        .navigationBarsPadding()
        .imePadding()
        .padding(24.dp)
        .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Row(
            Modifier
                .fillMaxWidth()
                .height(80.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painterResource(id = R.drawable.logo),
                contentDescription = "Logo Image",
                Modifier.size(width=120.dp, height = 40.dp)
            )
        }
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text(
                text="Personal Information",
                fontWeight = FontWeight.Medium,
                modifier=Modifier.padding(vertical = 24.dp)
            )

                Text(
                    text="First Name",
                )
                Text(text = firstName ?: "",
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(
                            width = 1.dp,
                            color = Color.Gray,
                            shape = RoundedCornerShape(8.dp)
                        )
                        .padding(12.dp)
                )

            Text(
                text="Last Name"
            )
            Text(text = lastName ?: "",
                modifier = Modifier
                    .fillMaxWidth()
                    .border(
                        width = 1.dp,
                        color = Color.Gray,
                        shape = RoundedCornerShape(8.dp)
                    )
                    .padding(12.dp)
            )

            Text(
                text="Email"
            )
            Text(text = email ?: "",
                modifier = Modifier
                    .fillMaxWidth()
                    .border(
                        width = 1.dp,
                        color = Color.Gray,
                        shape = RoundedCornerShape(8.dp)
                    )
                    .padding(12.dp)
            )

            Spacer(modifier = Modifier.height(20.dp))

        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.Bottom,
            horizontalArrangement = Arrangement.Center
        ) {
            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = onLogoutClick,
                shape = MaterialTheme.shapes.medium,
                colors = ButtonDefaults.buttonColors(
                    containerColor = Yellow,
                    contentColor = Black,
                    disabledContainerColor = DisabledState,
                    disabledContentColor = Color.DarkGray
                )
            ) {
                Text(text="Logout",
                    color=Color.Black
                )
            }
        }
    }
    IconButton(
        onClick = onHomeClick
    ) {
        Icon(
            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
            contentDescription = "Back"
        )
    }
}