package com.cursokotlin.todoapp.addtasks.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.cursokotlin.todoapp.addtasks.ui.model.TaskModel

@Composable
fun TasksScreen(tasksViewModel: TasksViewModel) {
    val showDialog: Boolean by tasksViewModel.showDialog.observeAsState(false)

    Box(modifier = Modifier.fillMaxSize()) {
        AddTasksDialog(
            showDialog,
            onDismiss = { tasksViewModel.onDialogClose() },
            onTaskAdded = { tasksViewModel.onTasksCreated(it) },
        )
        FabDialog(Modifier.align(Alignment.BottomEnd), tasksViewModel)
        TasksList(tasksViewModel)
    }
}

@Composable
fun TasksList(tasksViewModel: TasksViewModel) {
    val myTasks: List<TaskModel> = tasksViewModel.task

    LazyColumn {
        // el key nos ayuda a optimizar el recycler view
        items(myTasks, key = { it.id }) { task ->
            ItemTask(task, tasksViewModel)
        }
    }
}

@Composable
fun ItemTask(taskModel: TaskModel, tasksViewModel: TasksViewModel) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .pointerInput(Unit) {
                detectTapGestures(onLongPress = {
                    tasksViewModel.onItemRemove(taskModel)
                })
            },
        shape = CardDefaults.elevatedShape,
        colors = CardDefaults.outlinedCardColors(),
        elevation = CardDefaults.outlinedCardElevation(),
        border = CardDefaults.outlinedCardBorder(),
    ) {
        Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = taskModel.task,
                modifier = Modifier
                    .padding(horizontal = 8.dp)
                    .weight(1f), // con weight le decimos que ocupe el espacio entero, que no necesita el resto
            )
            Checkbox(
                checked = taskModel.selected,
                onCheckedChange = { tasksViewModel.onCheckBoxSelected(taskModel) },
            )
        }
    }
}

@Composable
fun FabDialog(modifier: Modifier, tasksViewModel: TasksViewModel) {
    FloatingActionButton(onClick = {
        tasksViewModel.onShowDialogClick()
    }, modifier = modifier.padding(20.dp)) {
        Icon(Icons.Filled.Add, contentDescription = "")
    }
}

@Composable
fun AddTasksDialog(show: Boolean, onDismiss: () -> Unit, onTaskAdded: (String) -> Unit) {
    var myTask by remember { mutableStateOf("") }
    if (show) {
        Dialog(onDismissRequest = { onDismiss() }) {
            Column(
                Modifier
                    .fillMaxWidth()
                    .background(Color.White)
                    .padding(16.dp),
            ) {
                Text(
                    text = "Añade tu tarea",
                    fontSize = 18.sp,
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    fontWeight = FontWeight.Bold,
                )
                Spacer(modifier = Modifier.size(16.dp))
                TextField(
                    value = myTask,
                    onValueChange = { myTask = it },
                    singleLine = true,
                    maxLines = 2,
                )
                Spacer(modifier = Modifier.size(16.dp))
                Button(onClick = {
                    onTaskAdded(myTask)
                    myTask = ""
                }, modifier = Modifier.fillMaxWidth()) {
                    Text(text = "Añadir tarea")
                }
            }
        }
    }
}