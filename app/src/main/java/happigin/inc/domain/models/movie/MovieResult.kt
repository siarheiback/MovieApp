package happigin.inc.domain.models.movie

data class MovieResult(
    var collection: MovieCollection?,
    var id: String? = null,
    var statusCode: Int? = null,
    var type: String? = null,
    var variant: String? = null
)