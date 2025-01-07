package com.example.todo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.todo.ui.theme.ToDoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ToDoTheme {
                ToDoPreview()
            }
        }
    }
}


class ListObject(val id: Int, var task: String, var status: Boolean = false){

}

@Composable
fun AppMainframe(modifier: Modifier = Modifier) {
    var todoItems by remember { mutableStateOf(listOf<ListObject>()) }


    Column(modifier.background(colorResource(R.color.dark_yellow)).padding(top = 16.dp)){
        Row (verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()){
            Text(
                text = "To-Do",
                color = Color.DarkGray,
                fontSize = 30.sp,
                fontWeight = FontWeight.ExtraBold,
                fontFamily = FontFamily.Serif,
                modifier = Modifier
                    .padding(16.dp)

            )
            Button(onClick =
            {
                val newTask = ListObject(id = todoItems.size+1, task = "New Task")
                todoItems = todoItems + newTask
            },
                colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent)) {
                Icon(imageVector = Icons.Outlined.Add ,
                    contentDescription = "Delete Task",
                    tint = Color.DarkGray,
                    modifier = Modifier.size(44.dp))
            }
        }
        LazyColumn (modifier.background(Color.DarkGray)){
            items(todoItems){item->
                var text by remember { mutableStateOf("") }
                var status by remember { mutableStateOf(item.status) }
            Column(modifier = Modifier.fillMaxWidth()){
                Row (horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                        .background(colorResource(R.color.black))
                ){
                    Checkbox(checked = status, enabled = true, onCheckedChange = {
                        if(!status){
                            status = true
                            item.status = status
                        }
                        else{
                            status = false
                            item.status = false
                        }
                    })
                    TextField(value = text,
                        onValueChange = {
                                newText:String -> text = newText
                            item.task = text
                        },
                        enabled = !item.status,
                        singleLine = true,
                    )
                    IconButton(
                        onClick = {
                            val index = todoItems.indexOf(item)
                            todoItems = todoItems.toMutableList().also {
                                it.removeAt(index)
                            }
                        }
                        , content = {
                            Icon(imageVector = Icons.Outlined.Delete , contentDescription = "Delete Task",
                                tint = Color.White,
                            )
                        }

                    )
                }
                HorizontalDivider(thickness = 1.dp, color = Color.White)
            }
            }

        }
    }

    
}



@Preview(showBackground = true)
@Composable
fun ToDoPreview() {
    ToDoTheme {
        AppMainframe(modifier = Modifier.fillMaxSize())
    }
}