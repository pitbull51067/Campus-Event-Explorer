## Design questions

**1. Why does the route contain an event ID instead of the complete event object?**
A route should describe *which* event to show, not carry a copy of it. `EventDetail(eventId: Long)` is small, serializable, and survives rotation. The detail destination looks the event up in the single event collection, so the data is never duplicated or stale, and an unknown ID can be handled gracefully.

**2. Which composable owns the search state?**
The `composable<EventList>` destination inside `CampusEventApp` owns it. `EventListScreen` is stateless: it receives `query` and `onQueryChange`. The filtered list is derived from `query` with `remember(events, query)`, not stored as a separate mutable list.

**3. Why does the search value use `rememberSaveable`?**
Rotation recreates the Activity, and plain `remember` would reset the text. `rememberSaveable` stores it in the saved instance state, so the query survives rotation and returning from the detail screen.

**4. What happens to the back stack when an event is selected?**
`navigate(EventDetail(id))` pushes the detail entry on top of `EventList`. The list entry stays on the stack with its saved state. Back or Up pops the detail entry and reveals the list with the same search text.

**5. How does the UI request navigation without accessing the `NavController`?**
Screens receive lambdas (`onEventSelected`, `onBack`, `onShare`). `CampusEventApp`, which owns the `NavController`, supplies lambdas that call `navigate` and `popBackStack`.

**6. What makes Share an implicit intent?**
It uses `Intent(ACTION_SEND)` with `text/plain` and `EXTRA_TEXT`, wrapped in `Intent.createChooser()`. No app or component is named; Android finds the apps that can handle it.

## AI / collaboration disclosure

I used Claude to help me with errors and understand the code better. It also helped me with writing out my answers so they could be understood and get to the point.
