package com.example.campuseventexplorer

import android.content.Context
import android.content.Intent

/**
 * Opens the Android share sheet with an implicit ACTION_SEND intent.
 * No app is named; the system chooser lets the user pick any app that can handle text.
 */
fun shareEvent(context: Context, event: CampusEvent) {
    val shareText = "${event.title} — ${event.time} — ${event.location}"

    val sendIntent = Intent(Intent.ACTION_SEND).apply {
        type = "text/plain"
        putExtra(Intent.EXTRA_SUBJECT, event.title)
        putExtra(Intent.EXTRA_TEXT, shareText)
    }

    context.startActivity(Intent.createChooser(sendIntent, "Share event"))
}
