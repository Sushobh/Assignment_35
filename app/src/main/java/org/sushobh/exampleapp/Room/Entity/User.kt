package org.sushobh.exampleapp.Room.Entity

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class ClothItem(@PrimaryKey val fileUri: String, val imageType : String) {

}



@Entity(primaryKeys = arrayOf("shirtFileUri", "pantFileUri"))
data class FavoriteCombination( val shirtFileUri: String,
                                val pantFileUri: String,
                                val isFavorite : Boolean
                               ) {

}

const val IMAGE_TYPE_SHIRT = "ImageTypeShirt"
const val IMAGE_TYPE_PANT = "ImageTypePant"
