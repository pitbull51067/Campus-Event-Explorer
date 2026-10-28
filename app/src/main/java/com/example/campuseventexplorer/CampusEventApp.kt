package com.example.campuseventexplorer

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.campuseventexplorer.ui.EventDetailScreen
import com.example.campuseventexplorer.ui.EventListScreen

/**
 * Navigation wiring. This is the only place that touches the NavController;
 * the screen composables only receive plain lambdas.
 */
@Composable
fun CampusEventApp(events: List<CampusEvent> = sampleEvents) {
    val navController = rememberNavController()
    val context = LocalContext.current

    NavHost(
        navController = navController,
        startDestination = EventList
    ) {
        composable<EventList> {
            // This destination owns the search state. rememberSaveable keeps it across
            // rotation and while the detail destination is on top of this entry.
            var query by rememberSaveable { mutableStateOf("") }

            // Derived from `query`, not a second mutable list.
            val filteredEvents = remember(events, query) {
                events.filter { it.matches(query) }
            }

            EventListScreen(
                events = filteredEvents,
                query = query,
                onQueryChange = { query = it },
                onEventSelected = { eventId ->
                    navController.navigate(EventDetail(eventId = eventId))
                }
            )
        }

        composable<EventDetail> { backStackEntry ->
            val route = backStackEntry.toRoute<EventDetail>()

            // Identity, not the object: look the event up from the collection.
            // null (unknown id) is handled by the screen, so it cannot crash.
            val event = events.firstOrNull { it.id == route.eventId }

            EventDetailScreen(
                event = event,
                onBack = { navController.returnToList() },
                onShare = { event?.let { shareEvent(context, it) } }
            )
        }
    }
}

/** Pops back to the event list (the same result as the system Back button). */
private fun NavHostController.returnToList() {
    if (!popBackStack<EventList>(inclusive = false)) {
        navigate(EventList)
    }
}
