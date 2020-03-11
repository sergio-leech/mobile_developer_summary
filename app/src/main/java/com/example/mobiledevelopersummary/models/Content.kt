package com.example.mobiledevelopersummary.models

data class Content(val menuId: String, val name: String, val image: String) {
    constructor(menuId: String, name: String, image: String, description: String) : this(
        menuId,
        name,
        image
    )
}