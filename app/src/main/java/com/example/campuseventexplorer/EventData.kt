package com.example.campuseventexplorer

/** The application's event collection (single source of truth for list and detail). */
val sampleEvents: List<CampusEvent> = listOf(
    CampusEvent(
        id = 1L,
        title = "Resume Workshop",
        category = "Career",
        location = "Career Center, Room 120",
        time = "Mon, Nov 2 · 4:00 PM",
        description = "Bring a draft of your resume and get one-on-one feedback from career " +
            "advisors. Learn how to highlight projects, internships, and campus involvement " +
            "so recruiters notice you in the first six seconds."
    ),
    CampusEvent(
        id = 2L,
        title = "Mobile Development Meetup",
        category = "Tech",
        location = "CAS 214",
        time = "Tue, Nov 3 · 6:30 PM",
        description = "Monthly meetup for students building Android and iOS apps. This month: " +
            "lightning demos of Jetpack Compose projects, a short talk on navigation and state, " +
            "and pizza. Beginners are welcome."
    ),
    CampusEvent(
        id = 3L,
        title = "Fall Career Fair",
        category = "Career",
        location = "Student Union Ballroom",
        time = "Wed, Nov 4 · 10:00 AM",
        description = "Meet more than 60 employers hiring for internships and full-time roles " +
            "across engineering, business, health, and the arts. Bring printed resumes and " +
            "dress professionally."
    ),
    CampusEvent(
        id = 4L,
        title = "Intramural Basketball Night",
        category = "Sports",
        location = "Recreation Center, Court 2",
        time = "Thu, Nov 5 · 7:00 PM",
        description = "Open intramural games for all skill levels. Teams are formed on arrival, " +
            "so you can show up solo. Bring water and a pair of indoor court shoes."
    ),
    CampusEvent(
        id = 5L,
        title = "Jazz Ensemble Concert",
        category = "Music",
        location = "Fine Arts Concert Hall",
        time = "Fri, Nov 6 · 8:00 PM",
        description = "The university jazz ensemble performs classic standards and original " +
            "student arrangements. Admission is free with a student ID; doors open at 7:30 PM."
    ),
    CampusEvent(
        id = 6L,
        title = "Hackathon Kickoff",
        category = "Tech",
        location = "Engineering Building Atrium",
        time = "Sat, Nov 7 · 9:00 AM",
        description = "Form teams, hear the challenge themes, and start building. Mentors from " +
            "local companies will be on hand all day. Breakfast and lunch are provided."
    ),
    CampusEvent(
        id = 7L,
        title = "Community Garden Volunteer Day",
        category = "Volunteering",
        location = "Campus Community Garden",
        time = "Sun, Nov 8 · 11:00 AM",
        description = "Help prepare the garden beds for winter by mulching, composting, and " +
            "planting cover crops. Tools and gloves are provided. Wear clothes that can get dirty."
    ),
    CampusEvent(
        id = 8L,
        title = "Documentary Film Night",
        category = "Arts",
        location = "Library Auditorium",
        time = "Mon, Nov 9 · 7:00 PM",
        description = "A screening of an award-winning documentary on urban architecture, " +
            "followed by a Q&A with a visiting filmmaker. Free popcorn while it lasts."
    ),
    CampusEvent(
        id = 9L,
        title = "Study Abroad Info Session",
        category = "Academic",
        location = "International Programs Office",
        time = "Tue, Nov 10 · 12:30 PM",
        description = "Learn about semester and summer programs, scholarships, credit transfer, " +
            "and application deadlines. Returning students will share their experiences."
    ),
    CampusEvent(
        id = 10L,
        title = "Board Game Social",
        category = "Social",
        location = "Student Union Lounge",
        time = "Wed, Nov 11 · 6:00 PM",
        description = "Relax with strategy games, card games, and party games. The club supplies " +
            "dozens of titles and can teach you any of them. No experience needed."
    )
)

/**
 * True when the event's title or category contains [query] (case-insensitive).
 * A blank query matches every event.
 */
fun CampusEvent.matches(query: String): Boolean {
    val text = query.trim()
    if (text.isEmpty()) return true
    return title.contains(text, ignoreCase = true) || category.contains(text, ignoreCase = true)
}
