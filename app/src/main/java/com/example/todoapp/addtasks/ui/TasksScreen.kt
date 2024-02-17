package com.example.todoapp.addtasks.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog

// fun TasksScreen(tasksViewModel: TasksViewModel) {
@Preview
@Composable
fun TasksScreen() {
    Box(modifier = Modifier.fillMaxSize()) {
        AddTaskDialog(true, {})
        FabDialog(Modifier.align(Alignment.BottomEnd))
    }
}

@Composable
fun FabDialog(modifier: Modifier) {
    FloatingActionButton(
        onClick = { /*mostrar dialogo*/ },
        modifier = modifier.padding(16.dp),
    ) {
        Icon(Icons.Filled.Add, contentDescription = "Agregar nota")
//
    }
}

@Composable
fun AddTaskDialog(show: Boolean, onDismiss: () -> Unit) {
    var myTask by remember { mutableStateOf("") }

    if (show) {
        Dialog(onDismissRequest = { onDismiss() }) {
            Column(modifier = Modifier.fillMaxWidth()) {
                Text(text = "Añade tu tarea", fontSize = 16.sp)
                Spacer(modifier = Modifier.size(16.dp))
                TextField(value = myTask, onValueChange = { it:String -> myTask = it  })
                Spacer(modifier = Modifier.size(16.dp))
                Button(onClick = { /*mandar tarea*/ }, modifier = Modifier.fillMaxWidth()) { Text(text = "Añadir tarea") }
            }
        }
    }
}
