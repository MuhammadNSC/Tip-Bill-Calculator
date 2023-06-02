package com.example.tip_billtime

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.tip_billtime.ui.theme.TipBillTimeTheme
import java.text.NumberFormat



//
//class MainActivity : ComponentActivity() {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContent {
//            TipBillTimeTheme() {
//                // A surface container using the 'background' color from the theme
//                Surface(
//                    modifier = Modifier.fillMaxSize(),
//                    color = MaterialTheme.colorScheme.background
//                ) {
//                    TipBillTimeLayout()
//                }
//            }
//        }
//    }
//}
//
//@Composable
//fun TipBillTimeLayout() {
//    var amountInput by remember { mutableStateOf("")}
//    val amount = amountInput.toDoubleOrNull() ?: 0.0
//
//    var tipInput by remember { mutableStateOf("") }
//    val tipPercent = tipInput.toDoubleOrNull() ?: 0.0
//
//    val tip = calculateTip(amount, tipPercent)
//
//    Column(
//        modifier = Modifier.padding(40.dp),
//        horizontalAlignment = Alignment.CenterHorizontally,
//        verticalArrangement = Arrangement.Center
//    ) {
//        Text(
//            text = stringResource(R.string.calculate_tip),
//            modifier = Modifier
//                .padding(bottom = 16.dp)
//                .align(alignment = Alignment.Start)
//        )
//        EditNumberField(
//            value = amountInput,
//            onValueChange = {amountInput = it},
//            label = R.string.bill_amount,
//            modifier = Modifier
//                .padding(bottom = 32.dp)
//                .fillMaxWidth())
//
//        EditNumberField(
//            value = tipInput,
//            onValueChange = {tipInput = it },
//            label = R.string.tip_percentage,
//            modifier = Modifier
//                .padding(bottom = 32.dp)
//                .fillMaxWidth()
//        )
//
//
//        Text(
//            text = stringResource(R.string.tip_amount, tip),
//            style = MaterialTheme.typography.displaySmall
//        )
//        Spacer(modifier = Modifier.height(150.dp))
//    }
//}
//
//@Composable
//fun EditNumberField(value: String,
//                    onValueChange: (String) -> Unit,
//                    @StringRes label: Int,
//                    modifier: Modifier = Modifier) {
//    TextField(
//        value = value,
//        onValueChange = onValueChange,
//        modifier = modifier,
//        singleLine = true,
//        keyboardOptions =   KeyboardOptions(
//            keyboardType =  KeyboardType.Number),
//        label = {Text(stringResource(label))}
//    )
//}
//
///**
// * Calculates the tip based on the user input and format the tip amount
// * according to the local currency.
// * Example would be "$10.00".
// */
//private fun calculateTip(amount: Double, tipPercent: Double = 15.0): String {
//    val tip = tipPercent / 100 * amount
//    return NumberFormat.getCurrencyInstance().format(tip)
//}
//
//
//@Preview(showBackground = true)
//@Composable
//fun TipBillTimLayout() {
//    TipBillTimeTheme() {
//        TipBillTimeLayout()
//    }
//}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TipBillTimeTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    TipBillTimeLayout()
                }
            }
        }
    }
}

@Composable
fun TipBillTimeLayout() {
    var amountInput by remember { mutableStateOf("") }
    val amount = amountInput.toDoubleOrNull() ?: 0.0

    var tipInput by remember { mutableStateOf("") }
    val tipPercent = tipInput.toDoubleOrNull() ?: 0.0

    var splitInput by remember { mutableStateOf("") }
    val splitCount = splitInput.toIntOrNull() ?: 1

    val tip = calculateTip(amount, tipPercent)
    val totalAmount = amount + tip
    val splitAmount = totalAmount / splitCount

    Column(
        modifier = Modifier.padding(40.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = stringResource(R.string.calculate_tip),
            modifier = Modifier
                .padding(bottom = 16.dp)
                .align(alignment = Alignment.Start)
        )
        EditNumberField(
            value = amountInput,
            onValueChange = { amountInput = it },
            label = R.string.bill_amount,
            modifier = Modifier
                .padding(bottom = 16.dp)
                .fillMaxWidth()
        )

        EditNumberField(
            value = tipInput,
            onValueChange = { tipInput = it },
            label = R.string.tip_percentage,
            modifier = Modifier
                .padding(bottom = 16.dp)
                .fillMaxWidth()
        )

        EditNumberField(
            value = splitInput,
            onValueChange = { splitInput = it },
            label = R.string.bill_split,
            modifier = Modifier
                .padding(bottom = 16.dp)
                .fillMaxWidth()
        )

        Text(
            text = stringResource(R.string.tip_amount, tip),
            style = MaterialTheme.typography.displaySmall
        )

        Text(
            text = stringResource(R.string.total_amount, totalAmount),
            style = MaterialTheme.typography.displaySmall
        )

        Text(
            text = stringResource(R.string.split_amount, splitAmount),
            style = MaterialTheme.typography.displaySmall
        )

        EditTextField(
            value = "",
            onValueChange = {},
            label = R.string.customer_feedback,
            modifier = Modifier
                .padding(top = 16.dp)
                .fillMaxWidth()
        )
    }
}


@Composable
fun EditNumberField(
    value: String,
    onValueChange: (String) -> Unit,
    @StringRes label: Int,
    modifier: Modifier = Modifier
) {
    TextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier,
        singleLine = true,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Number
        ),
        label = { Text(stringResource(label)) }
    )
}

@Composable
fun EditTextField(
    value: String,
    onValueChange: (String) -> Unit,
    @StringRes label: Int,
    modifier: Modifier = Modifier
) {
    TextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier,
        singleLine = true,
        label = { Text(stringResource(label)) }
    )
}

//private fun calculateTip(amount: Double, tipPercent: Double = 15.0): String {
//    val tip = tipPercent / 100 * amount
//    return NumberFormat.getCurrencyInstance().format(tip)
//}
private fun calculateTip(amount: Double, tipPercent: Double = 15.0): Double {
    val tip = tipPercent / 100 * amount
    return tip
}
@Preview(showBackground = true)
@Composable
fun TipBillTimeLayoutPreview() {
    TipBillTimeTheme {
        TipBillTimeLayout()
    }
}

