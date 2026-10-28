package com.example.campuseventexplorer.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.campuseventexplorer.CampusEvent
import com.example.campuseventexplorer.sampleEvents
import com.example.campuseventexplorer.ui.theme.CampusEventTheme

/**
 * Stateless detail screen. [event] is null when the route's id matches no event;
 * in that case an "Event unavailable" message is shown instead of crashing.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EventDetailScreen(
    event: CampusEvent?,
    onBack: () -> Unit,
    onShare: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Event details") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Up"
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        if (event == null) {
            EventUnavailable(
                onBack = onBack,
                modifier = Modifier.padding(innerPadding)
            )
        } else {
            EventDetails(
                event = event,
                onShare = onShare,
                modifier = Modifier.padding(innerPadding)
            )
        }
    }
}

@Composable
private fun EventDetails(
    event: CampusEvent,
    onShare: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = event.title,
            style = MaterialTheme.typography.headlineMedium
        )
        DetailField(label = "Category", value = event.category)
        DetailField(label = "Date/time", value = event.time)
        DetailField(label = "Location", value = event.location)
        DetailField(label = "Description", value = event.description)

        Button(
            onClick = onShare,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Share event")
        }
    }
}

@Composable
private fun DetailField(label: String, value: String) {
    Column {
        Text(
            text = label,
            style = MaterialTheme.typography.labelLarge,
            color = MaterialTheme.colorScheme.primary
        )
        Text(
            text = value,
            style = MaterialTheme.typography.bodyLarge
        )
    }
}

@Composable
private fun EventUnavailable(
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Event unavailable",
            style = MaterialTheme.typography.headlineSmall
        )
        Text(
            text = "This event could not be found.",
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.padding(top = 8.dp, bottom = 24.dp)
        )
        Button(onClick = onBack) {
            Text("Return to events")
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun EventDetailScreenPreview() {
    CampusEventTheme {
        EventDetailScreen(event = sampleEvents.first(), onBack = {}, onShare = {})
    }
}

@Preview(showBackground = true)
@Composable
private fun EventUnavailablePreview() {
    CampusEventTheme {
        EventDetailScreen(event = null, onBack = {}, onShare = {})
    }
}
