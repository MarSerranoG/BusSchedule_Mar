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

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Representa una sola tabla en la base de datos. Cada fila es una instancia separada de
 * la clase BusSchedule. Cada propiedad corresponde a una columna.
 * Además, se necesita una identificación como identificador único para
 * cada fila en la base de datos.
 */

//Esta clase representa una entidad en la base de datos de la aplicación y define las propiedades de la tabla correspondiente.
@Entity(tableName = "Schedule")
//La anotación @Entity se aplica a la clase BusSchedule para indicar que es una entidad de Room. Se especifica el nombre de
// la tabla de la base de datos (tableName = "Schedule").
data class BusSchedule(
    @PrimaryKey
    val id: Int,
    //La anotación @PrimaryKey se aplica a la propiedad id para indicar que es la clave primaria de la tabla.
    @NonNull
    @ColumnInfo(name = "stop_name")
    val stopName: String,
    //Las anotaciones @ColumnInfo se aplican a las propiedades stopName y arrivalTimeInMillis para indicar los nombres
    // de las columnas correspondientes en la tabla de la base de datos.
    @NonNull
    //La anotación @NonNull se aplica a las propiedades stopName y arrivalTimeInMillis para indicar que no pueden ser nulas.
    @ColumnInfo(name = "arrival_time")
    val arrivalTimeInMillis: Int
    //La clase BusSchedule es una clase de datos (data class) que automáticamente genera métodos equals(), hashCode(),
    // toString(), etc., basados en las propiedades declaradas.
)
//define la entidad BusSchedule en la base de datos de la aplicación. Cada instancia de esta clase representa una fila en
// la tabla de la base de datos, y cada propiedad de la clase corresponde a una columna en la tabla. La anotación @Entity
// y otras anotaciones de Room ayudan a definir la estructura de la tabla y las propiedades de la entidad.
