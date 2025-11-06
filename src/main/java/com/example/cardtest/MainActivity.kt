package com.example.cardtest

import android.graphics.Paint
import android.os.Bundle
import android.provider.ContactsContract.Data
import android.util.Printer
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.cardtest.ui.theme.CardTestTheme
import com.example.cardtest.ui.theme.Typography

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MainScreen()
        }
    }
}

@Composable
fun MainScreen(){
    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        Surface(modifier =  Modifier.padding(innerPadding)) {
            ListOfCards(modifier = Modifier.padding(start = 25.dp, end = 25.dp),startModifier = Modifier,listOfContent = listOf(CardContent(), CardContent(), CardContent(), CardContent()))

        }
    }

    }


data class CardContent(
    val text : String = "PlaceHolder",
    val imageId : Int = R.drawable.haha
)




@Composable
fun ListOfCards(
    modifier: Modifier = Modifier,
    customCardTemplate: CustomCardTemplate = CustomCardTemplate(),
    imageConfigTemplate : ImageConfig = ImageConfig(),
    textConfigTemplate : TextConfig = TextConfig(),
    listOfContent : List<CardContent>,
    startModifier: Modifier = customCardTemplate.modifier
){
    LazyColumn(modifier=modifier) {
        items(items = listOfContent) { cardContent ->
            var currentModifier = customCardTemplate.modifier
            if(cardContent === listOfContent[0]){
                currentModifier = startModifier
            }

            CustomCard(shape=customCardTemplate.shape,
                modifier = currentModifier,
                imageConfig = imageConfigTemplate,
                textConfig = textConfigTemplate,
                text = cardContent.text,
                imageId = cardContent.imageId)
        }
    }
}


data class ImageConfig(
    val description : String = "Place Holder",
    val modifier : Modifier = Modifier
        .fillMaxWidth()
        .height(194.dp),
    val scale: ContentScale = ContentScale.Crop
    )


data class TextConfig(
    val modifier: Modifier = Modifier.padding(16.dp),
    val style : TextStyle = Typography.titleMedium
)

data class CustomCardTemplate(
    val shape: RoundedCornerShape = RoundedCornerShape(25.dp),
    val modifier: Modifier = Modifier.padding(top = 25.dp)
)


@Composable
fun CustomCard(
    modifier: Modifier = CustomCardTemplate().modifier,
    shape : RoundedCornerShape = CustomCardTemplate().shape,
    text : String = "Placeholder",
    imageId : Int = R.drawable.haha,
    imageConfig : ImageConfig = ImageConfig(),
    textConfig: TextConfig = TextConfig()
){
    Card(modifier = modifier, shape = shape) {
        Column{
            Image(
                painter = painterResource(imageId),
                contentDescription = imageConfig.description ,
                modifier = imageConfig.modifier,
                contentScale = imageConfig.scale
            )
            Text(
                text = text,
                modifier = textConfig.modifier,
                style =textConfig.style
            )
        }

    }
}

@Preview(showBackground = true, showSystemUi = true, device = "id:pixel_7")
@Composable
fun MainActivityPreview() {
    CardTestTheme {
        MainScreen()
    }
}