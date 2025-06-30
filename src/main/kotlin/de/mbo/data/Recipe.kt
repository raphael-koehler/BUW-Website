package de.mbo.data

import kotlinx.serialization.Serializable

@Serializable
data class Recipe(val id: Int, val name: String)
