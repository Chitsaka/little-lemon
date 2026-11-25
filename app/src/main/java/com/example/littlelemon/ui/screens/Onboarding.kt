package com.example.littlelemon.ui.screens


import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.edit
import com.example.littlelemon.R
import com.example.littlelemon.ui.theme.LittleLemonColor.Black
import com.example.littlelemon.ui.theme.LittleLemonColor.DisabledState
import com.example.littlelemon.ui.theme.LittleLemonColor.GreenDark
import com.example.littlelemon.ui.theme.LittleLemonColor.White
import com.example.littlelemon.ui.theme.LittleLemonColor.Yellow


@Composable
fun Onboarding (
    modifier: Modifier = Modifier,
    onLoginClick: () -> Unit,
) {
    val context = LocalContext.current
    val sharedPreferences = remember {
        context.getSharedPreferences(
            "LittleLemonPrefs",
            Context.MODE_PRIVATE
        )
    }
    var firstName by rememberSaveable {
        mutableStateOf(
            sharedPreferences.getString("firstName", "") ?: ""
        )
    }

    var lastName by rememberSaveable {
        mutableStateOf(
            sharedPreferences.getString("lastName", "") ?: ""
        )
    }

    var email by rememberSaveable {
        mutableStateOf(
            sharedPreferences.getString("email", "") ?: ""
        )
    }

    val isFormValid = firstName.trim().isNotBlank() && lastName.trim().isNotBlank()

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
        Row(
            Modifier
                .fillMaxWidth()
                .height(100.dp)
                .background(GreenDark),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text="Let's get to know you",
                color=White,
            )
        }
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Text(
                text="Personal Information",
                fontWeight = FontWeight.Medium,
                modifier=Modifier.padding(vertical = 24.dp)
            )
            Text(
                text="First Name"
            )
            TextField(
                value = firstName,
                onValueChange = { firstName = it},
                colors = TextFieldDefaults.colors(
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent,
                ),
                modifier = Modifier.fillMaxWidth()
                    .clip(RoundedCornerShape(8.dp))
            )
            Text(
                text="Last Name"
            )
            TextField(
                value = lastName,
                onValueChange = {lastName = it},
                colors = TextFieldDefaults.colors(
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent,
                ),
                modifier = Modifier.fillMaxWidth()
                    .clip(RoundedCornerShape(8.dp))
            )
            Text(
                text="Email"
            )
            TextField(
                value = email,
                onValueChange = {email = it},
                colors = TextFieldDefaults.colors(
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent,
                ),
                modifier = Modifier.fillMaxWidth()
                    .clip(RoundedCornerShape(8.dp))
            )

            Spacer(modifier = Modifier.height(20.dp))

        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.Bottom
            ,
            horizontalArrangement = Arrangement.Center
        ) {
            Button(
                modifier = Modifier.fillMaxWidth()
                    .padding(8.dp),

                onClick = {
                    if (isFormValid){

                        sharedPreferences.edit {
                            putString("firstName", firstName)
                                .putString("lastName", lastName)
                                .putString("email", email)
                        }

                        Toast.makeText(context, "Registration successful!" , Toast.LENGTH_LONG).show()
                        onLoginClick()
                    } else {
                        Toast.makeText(context, "Registration unsuccessful. Please enter all data." , Toast.LENGTH_LONG).show()
                    }
                },
                shape = MaterialTheme.shapes.medium,
                enabled = isFormValid ,

                colors = ButtonDefaults.buttonColors(

                    containerColor = Yellow,

                    contentColor = Black,

                    disabledContainerColor =
                        DisabledState,

                    disabledContentColor =
                        Color.DarkGray
                )
            ) {
                Text(text="Register",
                    color=Color.Black
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun OnboardingPreview (){
    Onboarding(onLoginClick = {})
}



