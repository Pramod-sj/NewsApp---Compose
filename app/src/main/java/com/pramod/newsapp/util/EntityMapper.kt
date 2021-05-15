package com.pramod.newsapp.util

interface EntityMapper<E, D> {
    fun fromEntity(entity: E): D
    fun toEntity(domain: D): E
}