package happigin.inc.domain.models.news

data class News(
    val articles: List<Articles>,
    val status: String,
    val totalResults: Int
)