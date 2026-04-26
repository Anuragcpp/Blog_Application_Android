package com.content.blogapplication.auth.screen

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.content.blogapplication.auth.viewmodel.AuthViewModel
import com.content.blogapplication.util.network.ApiStateResource

@Composable
fun SignUpScreen(
    innerPadding : PaddingValues,
    navigateToSingInScreen : () -> Unit,
    navigateToHomeScreen : () -> Unit
){

    val authViewModel : AuthViewModel = viewModel()
    val signUpSate = authViewModel.signUpUserState.collectAsState()
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var name by remember { mutableStateOf("") }
    val context : Context = LocalContext.current
    var loadingState by remember { mutableStateOf(false) }

    LaunchedEffect(signUpSate.value) {
        when(val state = signUpSate.value) {
            is ApiStateResource.Success -> {
                loadingState = false
                Toast.makeText(context, state.data.message, Toast.LENGTH_SHORT).show()
                navigateToHomeScreen();
            }
            is ApiStateResource.Error -> {
                loadingState = false
                val data = state.message
                Toast.makeText(context,data, Toast.LENGTH_SHORT).show()
            }

            ApiStateResource.Loading -> {
                loadingState = true
            }

            ApiStateResource.Idle -> {
                loadingState = false
            }
        }
    }

    Box(modifier = Modifier
        .fillMaxSize()
        .padding(innerPadding )
        .padding(16.dp)
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                text = "Blog Application",
                color = Color.DarkGray,
                fontSize = 26.sp,
                fontWeight = FontWeight.Bold,
                fontStyle = FontStyle.Normal,
                letterSpacing = 1.5.sp,
            )

            Column(
                modifier = Modifier
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "SignUp",
                    color = Color.DarkGray,
                    fontSize = 26.sp,
                    fontWeight = FontWeight.Bold,
                    fontStyle = FontStyle.Normal,
                    letterSpacing = 1.5.sp,
                )

                Spacer(modifier = Modifier.height(10.dp))

                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = name,
                    onValueChange = {name = it},
                    label = {Text("Name")},
                    singleLine = true
                )

                Spacer(modifier = Modifier.height(10.dp))

                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = email,
                    onValueChange = {email = it},
                    label = {Text("Email")},
                    singleLine = true
                )

                Spacer(modifier = Modifier.height(10.dp))

                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = password,
                    onValueChange = {password = it},
                    label = {Text("Password")},
                    singleLine = true
                )

                Spacer(modifier = Modifier.height(20.dp))

                Button (
                    modifier = Modifier
                        .background(
                            color = Color.Magenta,
                            shape = RoundedCornerShape(12.dp)
                        )
                        .padding(horizontal = 20.dp, vertical = 20.dp)
                        .fillMaxWidth(),
                    onClick = {authViewModel.signUpUser(name,email,password)}
                ){

                    if (loadingState) CircularProgressIndicator()
                    else {
                        Text(
                            modifier = Modifier
                                .padding(horizontal = 20.dp, vertical = 20.dp), //navigateToHomeScreen.invoke()}),
                            text = "Sign Up",
                            textAlign = TextAlign.Center,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }



                }

                Spacer(modifier = Modifier.height(20.dp))

                Text(
                    modifier = Modifier
                        .padding(horizontal = 20.dp, vertical = 20.dp)
                        .fillMaxWidth()
                        .clickable(onClick = {navigateToSingInScreen.invoke()})
                    , //navigateToHomeScreen.invoke()}),
                    text = "Sign In",
                    textAlign = TextAlign.Center,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )

            }


            if( signUpSate.value is ApiStateResource.Loading ){
                Box(
                    modifier = Modifier.fillMaxSize()
                        .background(Color.Black.copy(alpha = 0.3f)),
                    contentAlignment = Alignment.Center
                ){
                    CircularProgressIndicator()
                }
            }
        }

    }

}



//private fun signUpUser(authViewModel: AuthViewModel,name: JvmName)

@Composable
@Preview(showBackground = true)
fun SignUpScreenPreview(){
    //SignUpScreen()
}