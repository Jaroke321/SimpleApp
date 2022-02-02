package com.example.myapplication

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.myapplication.ui.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    CreateBizCard()
                }
            }
        }
    }
}

@Composable
fun CreateBizCard() {

    // Variables for use in button toggling
    val buttonClickedState = remember {
        mutableStateOf(false)
    }

    // Create the main surface
    Surface( modifier = Modifier
        .fillMaxWidth()
        .fillMaxHeight() ) {

        // Inside main surface
        Card( modifier = Modifier
            .width(200.dp)
            .height(390.dp)
            .padding(12.dp),
            elevation = 4.dp,
            shape = RoundedCornerShape(corner = CornerSize(15.dp)),
            backgroundColor = Color.White) {

            // Inside card
            
            // Create column to hold image and profile
            Column(modifier = Modifier.height(300.dp),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally) {

                // Inside of column

                // Load Profile Image object
                CreateImageProfile()
                Divider()
                
                // Create the column for the information below the divider
                CreateInfo()

                // Create Portfolio Button
                Button(onClick = {
                    // Toggle the portfolio button
                    buttonClickedState.value = !buttonClickedState.value

                }) {
                    Text(text = "Portfolio", style = MaterialTheme.typography.button)
                }

                if (buttonClickedState.value) {

                    // Show portfolio if the toggle is true
                    Content()

                } else {
                    Box() {

                    }
                }

            }  // End Column

        } // End card

    }  // End main surface

} // End function

@Composable
fun Content() {
    
    Box(modifier = Modifier
        .fillMaxHeight()
        .fillMaxWidth()
        .padding(5.dp)) {

        Surface(modifier = Modifier
            .padding(3.dp)
            .fillMaxWidth()
            .fillMaxHeight(),
            shape = RoundedCornerShape(corner = CornerSize(6.dp)),
            border = BorderStroke(width = 2.dp, color = Color.LightGray)) {

            Portfolio(data = listOf("Project 1", "Project 2", "Project 3"))
            
        } // End Surface

    } // End box
    
} // End Content function

@Composable
fun Portfolio(data: List<String>) {

    LazyColumn {

        items(data) { item ->
            // Create a Card for each item in portfolio
            Card(modifier = Modifier
                .padding(13.dp)
                .fillMaxWidth(),
                shape = RectangleShape,
                elevation = 4.dp) {

                // Fill in each card
                Row(
                    modifier = Modifier
                        .padding(8.dp)
                        .background(MaterialTheme.colors.surface)
                        .padding(16.dp)) {

                    CreateImageProfile(modifier = Modifier.size(100.dp))

                    Column(modifier = Modifier
                        .padding(7.dp)
                        .align(Alignment.CenterVertically)) {
                        Text(text = item, fontWeight = FontWeight.Bold)
                        Text(text = "Super awesome project", style = MaterialTheme.typography.body2)
                    }

                }


            } // End Card

        } // End item

    } // End lazy column
    
} // End Portfolio function

@Composable
private fun CreateInfo() {
    Column(modifier = Modifier.padding(5.dp)) {

        // Inside inner column

        // Textbox for name
        Text(
            text = "Jacob Keller",
            style = MaterialTheme.typography.h4,
            color = MaterialTheme.colors.primaryVariant
        )

        // Text for position
        Text(
            text = "Computer Scientist",
            modifier = Modifier.padding(3.dp)
        )

        // Text for linking
        Text(
            text = "https://www.linkedin.com/in/kellerjake/",
            modifier = Modifier.padding(3.dp),
            style = MaterialTheme.typography.subtitle1
        )

    }
}

@Composable
private fun CreateImageProfile(modifier: Modifier = Modifier) {
    // Create Surface to hold the image
    Surface(
        modifier = modifier
            .size(150.dp)
            .padding(5.dp),
        shape = CircleShape,
        border = BorderStroke(width = 0.5.dp, color = Color.LightGray),
        elevation = 4.dp,
        color = MaterialTheme.colors.onSurface.copy(alpha = 0.5f) ) {

        // Inside of the Surface

        // Load the image 'R.drawable.profile' into the circle
        Image(
            painter = painterResource(id = R.drawable.profile),
            contentDescription = "Profile Image",
            modifier = Modifier.size(135.dp),
            contentScale = ContentScale.Crop
        )

    } // End Surface

}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MyApplicationTheme {
        CreateBizCard()
    }
}