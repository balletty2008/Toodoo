package com.example.loginregister

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.center
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.loginregister.ui.theme.Blue1
import com.example.loginregister.ui.theme.LoginRegisterTheme
import com.example.loginregister.ui.theme.Yellow1
import com.example.loginui.ui.SignUpState

@Composable
fun LoginHomeScreen() {
    //LoginPageScreen()
    SignUpScreen()
}

@Composable
fun LandingPageScreen() {
    Surface(
        color = MaterialTheme.colors.primaryVariant) {      //Purple700
        Box(
            modifier = Modifier
                .fillMaxSize()
                .drawBehind {
                    val path = Path()
                    val x = size.width
                    val y = size.height
                    val center = size.center
                    path.apply {
                        moveTo(0f, 0f)
                        lineTo(x, 0f)
                        cubicTo(
                            x1 = x,
                            y1 = center.y / 2,
                            x2 = x,
                            y2 = center.y,
                            x3 = 0f,
                            y3 = center.y
                        )
                    }
                    drawPath(path = path, color = Yellow1)        //Top is path
                }
        ) {
            Column(modifier = Modifier.padding(20.dp)) {
                Text(
                    text = "Hello",
                    style = MaterialTheme.typography.h1,
                    fontSize = 50.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colors.primary
                )
                Text(
                    text = "Welcome",
                    style = MaterialTheme.typography.body1,
                    color = MaterialTheme.colors.primary
                )
                Spacer(modifier = Modifier.size(16.dp))
                Image(
                    painter = painterResource(id = R.drawable.girl3),       //Work is so big file
                    contentDescription = null,
                    modifier = Modifier
                        .size(200.dp)
                        .clip(CircleShape)
                )
            }
            Column(modifier = Modifier
                .fillMaxSize()
                .padding(20.dp)
                .wrapContentSize(align = Alignment.BottomCenter)) {

                Button(
                    onClick = { /*TODO*/ },
                    modifier = Modifier.fillMaxWidth(),
                    shape = MaterialTheme.shapes.medium,
                ) {
                    Text(text = "Sign In")                  //When I change top area color, button color also changed
                }
                Spacer(modifier = Modifier.size(16.dp))
                Button(
                    onClick = { /*TODO*/ },
                    modifier = Modifier.fillMaxWidth(),
                    shape = MaterialTheme.shapes.medium,
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = MaterialTheme.colors.onSurface
                    )
                ) {
                    Text(text = "Sign Up")          //changed top color, button change to black
                }
            }
        }
    }
}

@Composable
fun LoginPageScreen() {
    Surface(
        color = MaterialTheme.colors.primaryVariant,
        contentColor = MaterialTheme.colors.onSurface,
    ) {
        val (username, onUserNameChange) = remember {
            mutableStateOf("")
        }
        val (password, onPasswordChange) = remember {
            mutableStateOf("")
        }
        val (checked, onCheckedChange) = remember {
            mutableStateOf(false)
        }
        Column {
            Text(
                text = "Login",
                style = MaterialTheme.typography.h6,
                modifier = Modifier.padding(16.dp),
            )
            Spacer(modifier = Modifier.size(16.dp))
            OutlinedTextField(
                value = username,
                onValueChange = onUserNameChange,
                label = {Text(text = "UserName")},
                leadingIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.baseline_person_24),
                        contentDescription = null
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                shape = MaterialTheme.shapes.medium
            )
            Spacer(modifier = Modifier.size(16.dp))
            OutlinedTextField(
                value = password,
                onValueChange = onPasswordChange,
                label = {Text(text = "Password")},
                leadingIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.baseline_lock_24),
                        contentDescription = null
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                visualTransformation = PasswordVisualTransformation(),
                shape = MaterialTheme.shapes.medium
            )
            Spacer(modifier = Modifier.size(16.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically   //checkbox, text same height
                ){
                    Checkbox(
                        checked = checked,
                        onCheckedChange = onCheckedChange,
                        modifier = Modifier.heightIn(min=24.dp))
                    Spacer(modifier = Modifier.size(4.dp))
                    Text(text = "Remember me")
                }

                TextButton(
                    onClick = { /*TODO*/ },
                ) {
                    Text(text = "Forgot Password")
                }
            }
            Spacer(modifier = Modifier.size(16.dp))
            Button(
                onClick = { /*TODO*/ },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                shape = MaterialTheme.shapes.medium
            ) {
                Text(text = "Login")
            }
        }

        Row(
            modifier = Modifier
                .fillMaxSize()
                .wrapContentSize(align = Alignment.BottomCenter)
        ) {
            Text(text = "Don't have an Account?")
            TextButton(
                onClick = { /*TODO*/ },
            ) {
                Text(text = "Sign Up")
            }
        }
    }
}

@Composable
fun SignUpScreen() {
    val signUpState = SignUpState()
    Surface(
        color = MaterialTheme.colors.primary,
        contentColor = MaterialTheme.colors.onSurface,
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            Text(
                text = "Sign Up",
                style = MaterialTheme.typography.h6,
                modifier = Modifier.padding(16.dp),
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                OutlinedTextField(
                    value = signUpState.firstName,
                    onValueChange = { signUpState.firstNameChange(it) },
                    modifier = Modifier.weight(1f), //try input firstName
                    shape = MaterialTheme.shapes.medium,
                    label = { Text(text = "First Name") }
                )

                Spacer(modifier = Modifier.size(8.dp))
                OutlinedTextField(
                    value = signUpState.lastName,
                    onValueChange = { signUpState.lastNameChange(it) },
                    modifier = Modifier.weight(1f),
                    shape = MaterialTheme.shapes.medium,
                    label = { Text(text = "Last Name") }
                )
            }
            Spacer(modifier = Modifier.size(8.dp))
            OutlinedTextField(
                value = signUpState.emailAddress,
                onValueChange = { signUpState.emailAddressChange(it) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                shape = MaterialTheme.shapes.medium,
                label = { Text(text = "Email Address") }
            )
            Spacer(modifier = Modifier.size(16.dp))
            OutlinedTextField(
                value = signUpState.password,
                onValueChange = { signUpState.password(it) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                shape = MaterialTheme.shapes.medium,
                label = { Text(text = "Password") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                visualTransformation = PasswordVisualTransformation()
            )
            Spacer(modifier = Modifier.size(8.dp))
            OutlinedTextField(
                value = signUpState.confirmPassword,
                onValueChange = { signUpState.confirmPasswordChange(it) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                shape = MaterialTheme.shapes.medium,
                label = { Text(text = "Confirm Password") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                visualTransformation = PasswordVisualTransformation()
            )
            Spacer(modifier = Modifier.size(8.dp))
            Row(
                //modifier = Modifier.padding(horizontal = 16.dp)
                verticalAlignment = Alignment.CenterVertically    //checkbox, text are same height
            ) {
                Checkbox(
                    checked = signUpState.checked,
                    onCheckedChange = { signUpState.checkedChange(it) }
                )
                Spacer(modifier = Modifier.size(2.dp))   //between checkbox and "Agree..."
                Text(
                    text = "Agree with privacy policy"
                )
            }

            Spacer(modifier = Modifier.size(8.dp))
            Button(
                onClick = { /*TODO*/ },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                shape = MaterialTheme.shapes.medium
            ) {
                Text(text = "Sign Up")
            }
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
                    .wrapContentSize(align = Alignment.BottomCenter)
            ) {

                Text(text = "Already have an account?")
                Spacer(modifier = Modifier.size(8.dp))
                Text(
                    text = "Sign In",
                    modifier = Modifier.clickable {
                    },
                    color = MaterialTheme.colors.primary
                )
            }
        }
    }
}




@Preview(showBackground = true)
@Composable
fun LandingPagePrev() {
    LoginRegisterTheme() {
        LandingPageScreen()
        //LoginPageScreen()
        //SignUpScreen()
    }
}
