package happigin.inc.domain.models.randomactivity

data class RandomActivityResult(
    val accessibility: Double,
    val activity: String,
    val key: String,
    val link: String,
    val participants: Int,
    val price: Double,
    val type: String
)
