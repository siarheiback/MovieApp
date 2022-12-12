package happigin.inc.domain.models.movie

data class MovieCollection(
    var id: String? = null,
    var locations: ArrayList<Locations>,
    var name: String? = null,
    var picture: String? = null,
    var provider: String? = null,
    var sourceIds: SourceIds? = null,
    var weight: Int? = null
)
