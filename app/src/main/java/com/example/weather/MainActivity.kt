package com.example.weather

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.weather.ui.theme.WeatherTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            WeatherTheme {
                WeatherScreen()

            }
        }
    }
}
@Preview
@Composable
fun WeatherScreen(){
    Box(modifier = Modifier.fillMaxSize()
        .background(
            brush = Brush.verticalGradient(
                colors = listOf(
                    Color(android.graphics.Color.parseColor("#59469d")),
                        Color(android.graphics.Color.parseColor("#643d67"))
                )
            )
        ))
    {
        Column (modifier = Modifier.fillMaxSize()){
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                item {
                    Text(text = "Mostly Cloudy",
                        fontSize = 20.sp,
                        color = Color.White,
                        modifier = Modifier.fillMaxSize()
                            .padding(top =48.dp ),
                        textAlign = TextAlign.Center
                    )
                    Image(painter = painterResource(id = R.drawable.cloudy_sunny),
                        contentDescription = null,
                        modifier = Modifier.size(150.dp)
                            .padding(top = 8.dp)
                    )
                    // display date and time
                    Text(text = "Mon August 25 ",
                        fontSize = 19.sp,
                        color = Color.White,
                        modifier = Modifier.fillMaxWidth().padding(top = 8.dp),
                        textAlign = TextAlign.Center
                    )
                    Text(text =" 25"
                    , fontSize = 63.sp,
                       fontWeight = FontWeight.Bold,
                        color = Color.White,
                        modifier = Modifier.fillMaxWidth().padding(top = 8.dp),
                        textAlign = TextAlign.Center
                    )
                     Text(text ="H.27 L.18"
                    , fontSize = 16.sp,
                        color = Color.White,
                        modifier = Modifier.fillMaxWidth().padding(top = 8.dp),
                        textAlign = TextAlign.Center
                    )
                    //box containing weather detail
                    Box(modifier = Modifier.fillMaxWidth().padding(horizontal = 24.dp , vertical = 16.dp)
                        .background(
                            color= colorResource(id = R.color.purple_700),
                            shape = RoundedCornerShape(25.dp)
                        )
                    ){
                        Row (
                            modifier = Modifier.fillMaxWidth().height(100.dp)
                                .padding(horizontal = 8.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween


                        ){
                            WeatherDetailItem(
                                icon = R.drawable.rain, value = "22%", label = "Rain"
                            )
                            WeatherDetailItem(
                                icon = R.drawable.wind, value = "22%", label = "Wind Speed"
                            )
                            WeatherDetailItem(
                                icon = R.drawable.humidity, value = "22%", label = "Humidity"
                            )



                        }


                    }
                    //display today label
                    Text(text = "today",
                        fontSize = 20.sp,
                        color = Color.White,
                        modifier = Modifier.fillMaxWidth().padding(horizontal =24.dp, vertical = 8.dp),

                        )

                }
                //future forcest
                item {
                    LazyRow (
                        modifier = Modifier.fillMaxWidth(),
                        contentPadding = PaddingValues(horizontal = 20.dp),
                        horizontalArrangement = Arrangement.spacedBy(4.dp)
                    ){
                        items(items) { item ->
                            FutureModelViewHolder(item)
                        }
                    }
                }

                // display future label add next 7 day button
                item {
                    Row(
                        modifier = Modifier.fillMaxWidth()
                            .padding(horizontal = 24.dp , vertical = 16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(text = "Future",
                            fontSize = 20.sp,
                            color = Color.White,
                            modifier = Modifier.weight(1f)
                        )
                        Text(text = "Next 7 Days> ",
                            fontSize = 14.sp,
                            color = Color.White,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
                items(dailyitems){
                    FutureItem(item = it)


                }

            }
        }
    }

}
//display ecah future daily forcest
@Composable
fun FutureItem(item: FutureModel){
    Row (
        modifier = Modifier.fillMaxWidth().padding(horizontal = 24.dp, vertical = 4.dp),
        verticalAlignment = Alignment.CenterVertically,
    ){
        Text(text = item.day,
            fontSize = 16.sp,
            color = Color.White,
        )
        Image(painter = painterResource(id=getDrawableResource(picPath = item.picPath) )
            ,contentDescription = null,
            modifier = Modifier.padding(start = 32.dp)
                .size(45.dp)
        )
        Text(text = item.status,
            modifier = Modifier.weight(1f)
                .padding(start = 16.dp)
            ,color = Color.White,
            fontSize = 14.sp
        )
        Text(
            text = "${item.highTemp}°",
            modifier = Modifier.padding(end = 16.dp),
            color = Color.White,
            fontSize = 16.sp
        )
        Text(
            text = "${item.lowTemp}°",
            color = Color.White,
            fontSize = 16.sp
        )

    }
}
@Composable
fun getDrawableResource(picPath: String): Int {
    return when (picPath) {
        "strom" -> R.drawable.storm
        "cloudy" -> R.drawable.cloudy
        "windy" -> R.drawable.windy
        "cloudy_sunny" -> R.drawable.cloudy_sunny
        "sunny" -> R.drawable.sunny
        "rainy" -> R.drawable.rainy
        else -> R.drawable.sunny
    }
}
//simple daily data
val dailyitems = listOf(
    FutureModel("Sat","sunny","sunny",24,12),
    FutureModel("Sun","cloudy","cloudy",25,15),
    FutureModel("Mon","windy","windy",29,14),
    FutureModel("Tue","cloudy_sunny","cloudy_sunny",30,17),
    FutureModel("Wed","strom","strom",22,19),
    FutureModel("Thu","rainy","rainy",20,11),
)
//viwefolder for each hour forcast
@Composable
fun FutureModelViewHolder(model: HourlyModel){
    Column(
        modifier = Modifier.width(90.dp)
            .wrapContentWidth().padding(4.dp)
            .background(
                color = colorResource(id =  R.color.purple_700),
                shape = RoundedCornerShape(8.dp)
            )
            .padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = model.hour,
            color = Color.White,
            fontSize = 16.sp,
           modifier = Modifier.fillMaxWidth().padding(8.dp),
            textAlign = TextAlign.Center
        )
          Image(painter = painterResource(id = when (model.picPath){
              "cloudy" -> R.drawable.cloudy
              "rainy" -> R.drawable.rainy
              "strom" -> R.drawable.wind
              else -> R.drawable.sunny
          }),
              contentDescription = null,
              modifier = Modifier.size(45.dp)
                  .padding(8.dp)
                  , contentScale = ContentScale.Crop
              )


        Text(text = "${model.temp}",
            color = Color.White,
            fontSize = 16.sp,
            modifier = Modifier.fillMaxWidth().padding(8.dp),
            textAlign = TextAlign.Center
        )
    }

}


//sample hourly
val items = listOf(
    HourlyModel("9 pm",28,"cloudy"),
    HourlyModel("10 pm",29,"Sunny"),
    HourlyModel("11 pm",30,"Wind"),
    HourlyModel("12 pm",35,"rainy"),
    HourlyModel("1 pm",27,"Strom"),

)

@Composable
fun WeatherDetailItem(icon:Int,label:String,value:String){
    Column (
        modifier = Modifier.padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Image(painter = painterResource(id = icon ),
            contentDescription = null,
            modifier = Modifier.size(34.dp)
        )
        Text(text= value,
            fontWeight = FontWeight.Bold,
            color = colorResource(id = R.color.white),
            textAlign = TextAlign.Center
        )
        Text(text= label,
            color = colorResource(id = R.color.white),
            textAlign = TextAlign.Center
        )
    }

}