package com.example.anteeoneapp.domain.repository.interfaces

import android.content.Context
import com.example.anteeoneapp.data.Coordinates

interface LocationRepository {

    suspend fun getLocation(context:Context): Coordinates

    fun getDefaultLocation(): Coordinates

}