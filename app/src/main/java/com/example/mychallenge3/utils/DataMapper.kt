package com.example.mychallenge3.utils

import com.example.mychallenge3.data.model.Place
import com.example.mychallenge3.data.source.local.entity.PlaceEntity

object DataMapper {

    fun mapEntitiesToDomain(input: List<PlaceEntity>):List<Place>{
        return input.map {
            mapEntityToDomain(it)
        }
    }


    fun mapEntityToDomain(input: PlaceEntity)=Place(
        name = input.name,
        description = input.description,
        image = input.photo
    )

    fun mapDomainToEntity(input: Place)=PlaceEntity(
        name = input.name,
        description = input.description,
        photo = input.image
    )


}