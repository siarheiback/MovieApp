package happigin.inc.domain.models.trump

data class TrumpResult(
    val _embedded: Embedded,
    val _links: Links,
    val appeared_at: String,
    val created_at: String,
    val quote_id: String,
    val tags: List<String>,
    val updated_at: String,
    val value: String
)