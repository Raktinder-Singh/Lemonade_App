package com.example.leamonade

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.leamonade.ui.theme.LeamonadeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LeamonadeTheme {
                Lemonade()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Lemonade(modifier: Modifier = Modifier) {

    var currentStep by remember { mutableStateOf(1)}

    var sqeezeCount by remember { mutableStateOf(0) }
    Scaffold (
        topBar = {
            CenterAlignedTopAppBar(
                title = { 
                    Text(text = "Lemonade",
                        fontWeight = FontWeight.Bold
                        )
                },
                colors = TopAppBarDefaults.largeTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer
                )
                )
        }
    ) {innerPadding ->
        Surface(
            modifier = Modifier.fillMaxSize()
                .padding(innerPadding)
                .background(MaterialTheme.colorScheme.tertiaryContainer),
            color = MaterialTheme.colorScheme.background
        ) {
            when(currentStep){
                1 -> {
                    LemonTextAndImage(
                        textLabelResourceId = R.string.tap_tree,
                        drawableResourceId = R.drawable.lemon_tree,
                        contentDescriptionResourceId = R.string.Lemon_tree,
                        onImageClick = {
                            currentStep = 2
                            sqeezeCount = (2..4).random()
                        })
                }
                2-> {
                    LemonTextAndImage(
                        textLabelResourceId = R.string.sqeez,
                        drawableResourceId = R.drawable.lemon_squeeze,
                        contentDescriptionResourceId = R.string.Lemon,
                        onImageClick = {
                            sqeezeCount--
                            if (sqeezeCount == 0){
                                currentStep = 3
                            }
                        })
                }
                3 -> {
                    LemonTextAndImage(
                        textLabelResourceId = R.string.Glass_of_lemonade,
                        drawableResourceId = R.drawable.lemon_drink,
                        contentDescriptionResourceId = R.string.drink,
                        onImageClick = {
                            currentStep = 4
                        })
                }
                4 -> {
                    LemonTextAndImage(
                        textLabelResourceId = R.string.start_again,
                        drawableResourceId = R.drawable.lemon_restart,
                        contentDescriptionResourceId = R.string.Empty_glass,
                        onImageClick = {
                            currentStep = 1
                        })
                }

            }
        }


    }

    
}

@Composable
fun LemonTextAndImage(
    textLabelResourceId: Int,
    drawableResourceId: Int,
    contentDescriptionResourceId: Int,
    onImageClick: () -> Unit,
    modifier: Modifier = Modifier) {
    Box(modifier = modifier){
        Column (
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxSize()
        ){
            Button(
                onClick = onImageClick,
                shape = RoundedCornerShape(dimensionResource(id = R.dimen.button_corner_radius)),
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.tertiaryContainer)
            ) {
                Image(painter = painterResource(drawableResourceId),
                    contentDescription = stringResource(contentDescriptionResourceId),
                    modifier = Modifier
                        .width(dimensionResource(id = R.dimen.button_image_width))
                        .height(dimensionResource(id = R.dimen.button_image_height))
                        .padding(dimensionResource(id = R.dimen.button_interior_padding))
                    )

            }
            Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.padding_versical)))
            Text(text = stringResource(textLabelResourceId),
                style = MaterialTheme.typography.bodyLarge
                )

        }
    }
    
}

@Preview
@Composable
private fun LemonadePreview() {
    Lemonade()
}
