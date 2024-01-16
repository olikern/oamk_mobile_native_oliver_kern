package com.example.oamk_mobile_native

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.oamk_mobile_native.ui.theme.Oamk_mobile_nativeTheme
import com.example.oamk_mobile_native.ui.theme.Green
import com.example.oamk_mobile_native.ui.theme.Orange
import com.example.oamk_mobile_native.ui.theme.Red

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Oamk_mobile_nativeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Bmi()
                }
            }
        }
    }
}

@Composable
fun Bmi() {
    var heightInput: String by remember {
        mutableStateOf("")
    }
    var weightInput: String by remember {
        mutableStateOf("")
    }

    val height = heightInput.toFloatOrNull() ?: 0.0f
    val weight = weightInput.toIntOrNull() ?: 0
    val bmi: Float = if (weight > 0 && height > 0) weight / (height * height) else 0f

    val bmiUnderweight = 18.5
    val bmiHealthy = 25.0
    val bmiOverweight = 30.0

    val healthStatus: String =
        if (bmi < bmiUnderweight) {
            stringResource(R.string.underweight)
        } else if (bmi < bmiHealthy) {
            stringResource(R.string.healthy)
        } else if (bmi < bmiOverweight) {
            stringResource(R.string.overweight)
        } else {
            stringResource(R.string.obese)
        }


    Column {
        Text(
            text = stringResource(R.string.bmi),
            fontSize = 24.sp,
            color = MaterialTheme.colorScheme.primary,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp, bottom = 16.dp)
        )
        OutlinedTextField(
            value = heightInput,
            onValueChange = { heightInput = it.replace(',', '.') },
            label = { Text(stringResource(R.string.height)) },
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = weightInput,
            onValueChange = { weightInput = it.replace(',', '.') },
            label = { Text(stringResource(R.string.weight)) },
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()

        )
        if (bmi != 0f) {
            Text(
                text = stringResource(
                    R.string.result_text_bmi,
                    String.format("%.2f", bmi).replace(',', '.')
                ),
                fontSize = 18.sp,
                color = MaterialTheme.colorScheme.primary,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp, bottom = 8.dp)
            )
            Text(
                text = healthStatus,
                fontSize = 18.sp,
                color = when (healthStatus) {
                    stringResource(R.string.healthy) -> Green
                    stringResource(R.string.underweight),
                    stringResource(R.string.overweight) -> Orange
                    else -> Red
                },
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp, bottom = 8.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    Oamk_mobile_nativeTheme {
        Bmi()
    }
}