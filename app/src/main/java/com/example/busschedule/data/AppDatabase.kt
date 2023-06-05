/*
 * Copyright (C) 2023 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.busschedule.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

//La clase AppDatabase extiende RoomDatabase, que es una clase abstracta proporcionada
// por Room que contiene la funcionalidad principal para trabajar con la base de datos.

//La anotación @Database se aplica a la clase AppDatabase para indicar que es una base de datos de Room.
// Se especifica la lista de entidades que forman parte de la base de datos
// (entities = arrayOf(BusSchedule::class)) y la versión de la base de datos (version = 1).
//La lista formara parte de la base de datos
@Database(entities = arrayOf(BusSchedule::class), version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun busScheduleDao(): BusScheduleDao
    //El método busScheduleDao() es un método abstracto que debe ser implementado y
    // proporcionar una instancia del DAO (Data Access Object) correspondiente a la entidad BusSchedule.

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null
    //El bloque companion object define un objeto compañero que contiene métodos y propiedades estáticas. En este caso,
    // el método getDatabase() es un método estático que se utiliza para obtener una instancia de la base de datos. Se implementa utilizando
    // una construcción de bloque synchronized para garantizar que solo se cree una única instancia de la base de datos.
        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                Room.databaseBuilder(
                    context,
                    AppDatabase::class.java,
                    "app_database"
                //Dentro del método getDatabase(), se utiliza Room.databaseBuilder() para crear la instancia de la base de datos.
                    // Se especifica el contexto de la aplicación, la clase AppDatabase y el nombre de la base de datos. Además,
                    // se utiliza el método createFromAsset() para cargar una base de datos preexistente desde el directorio assets.
                    // También se llama al método fallbackToDestructiveMigration() para indicar que, en caso de no haber un objeto de
                    // migración, la base de datos debe limpiarse y reconstruirse en lugar de realizar migraciones.

                    // Esto se utiliza para simplificar el código y no se recomienda en un entorno de producción.
                )
                    .createFromAsset("database/bus_schedule.db")
               // Limpia y reconstruye en lugar de migrar si no hay un objeto de migración.
               // La migración no es parte de este codelab.
                    .fallbackToDestructiveMigration()
                    .build()
                    .also {
                        INSTANCE = it
                    }
            }
        //crea y proporciona acceso a la base de datos de la aplicación utilizando la biblioteca Room.
        // Define la estructura de la base de datos, incluyendo las entidades y su versión, y proporciona métodos para
        // obtener una instancia de la base de datos y el DAO correspondiente.
        }
    }
}
