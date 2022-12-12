package happigin.inc.domain.models.movie

data class Locations(
    var country: ArrayList<String>,
    var displayName: String? = null,
    var icon: String? = null,
    var id: String? = null,
    var name: String? = null,
    var url: String? = null
)
