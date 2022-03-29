package com.example.dynamicfeature

import android.content.ContentResolver
import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestBuilder

class MyActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my)
        val image = findViewById<ImageView>(R.id.my_image)
        Glide.with(this)
            .asGif()
            .load(R.drawable.sample)
            .into(image)
    }
}

internal fun <T> RequestBuilder<T>.loadLookup(
    context: Context,
    resourceId: Int
): RequestBuilder<T> {
    val resources = context.resources
    val uri = Uri.Builder()
        .scheme(ContentResolver.SCHEME_ANDROID_RESOURCE)
        .authority(context.packageName) // Look up the resources in the application with its splits loaded
        .appendPath(resources.getResourceTypeName(resourceId))
        .appendPath(
            String.format(
                "%s:%s",
                resources.getResourcePackageName(resourceId),
                resources.getResourceEntryName(resourceId)
            )
        ) // Look up the dynamic resource in the split namespace.
        .build()
    return load(uri)
}

