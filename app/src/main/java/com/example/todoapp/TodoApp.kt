package com.cursokotlin.todoapp

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

/**
 * fichero útil para inyección de dependencias, Room, etc, su nombre termina siempre con "App"
 */
@HiltAndroidApp
class TodoApp : Application()
