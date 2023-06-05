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

import androidx.room.Dao
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

/**
 * Proporciona acceso a operaciones de lectura/escritura en la tabla de programación.
 * Utilizado por los modelos de vista para dar formato a los resultados de la consulta para su uso en la interfaz de usuario.
 */
//Esta interfaz define métodos de acceso a operaciones de lectura/escritura
// en la tabla de horarios (schedule) de la base de datos.
@Dao
//@Dao se aplica a la interfaz BusScheduleDao para indicar que es un Data Access Object (DAO) de Room.
// Un DAO
// proporciona métodos para realizar operaciones en la base de datos.
interface BusScheduleDao {
    @Query(
        """
        SELECT * FROM schedule 
        ORDER BY arrival_time ASC    
        """
    )
    //Ambos métodos devuelven un flujo (Flow) de listas de objetos BusSchedule. Un flujo es una
    // secuencia asincrónica de valores que puede ser observada y reaccionar a los cambios en tiempo real.
    fun getAll(): Flow<List<BusSchedule>>
    //El método getAll() realiza una consulta a la tabla de horarios y devuelve todos
    // los registros ordenados por hora de llegada (arrival_time). Utiliza la anotación @Query para escribir
    // la consulta SQL en línea.

    @Query(
        """
        SELECT * FROM schedule 
        WHERE stop_name = :stopName 
        ORDER BY arrival_time ASC 
        """
    )
    fun getByStopName(stopName: String): Flow<List<BusSchedule>>
    //El método getByStopName(stopName: String) realiza una consulta a la tabla de horarios y devuelve los registros que
    // coinciden con el nombre de la parada (stop_name) especificado. También utiliza la anotación @Query para escribir
    // la consulta SQL en línea, y utiliza un parámetro stopName para filtrar los resultados.
}
//esta interfaz define métodos que permiten acceder y consultar la tabla de horarios de la base de datos.
// Estos métodos son utilizados por los modelos de vista (view models) para formatear los resultados de la
// consulta y utilizarlos en la interfaz de usuario.