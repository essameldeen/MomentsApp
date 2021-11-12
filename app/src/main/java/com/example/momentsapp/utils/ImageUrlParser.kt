package com.example.momentsapp.utils

import android.content.ContentResolver
import android.graphics.Bitmap
import android.net.Uri
import android.provider.MediaStore
import java.io.ByteArrayOutputStream

object ImageUrlParser
{
    fun getUriFromExtra(contentResolver: ContentResolver, image: Bitmap): String
    {
        val bytes = ByteArrayOutputStream()
        image.compress(Bitmap.CompressFormat.JPEG, 100, bytes)
        return getRealPathFromURI(contentResolver, Uri.parse(MediaStore.Images.Media.insertImage(contentResolver, image, "Title", null)))
    }

    fun getRealPathFromURI(contentResolver: ContentResolver, uri: Uri): String
    {
        var path = ""
        val cursor = contentResolver.query(uri, null, null, null, null)
        if (cursor != null)
        {
            cursor.moveToFirst()
            val idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA)
            path = cursor.getString(idx)
            cursor.close()
        }
        return path
    }
}