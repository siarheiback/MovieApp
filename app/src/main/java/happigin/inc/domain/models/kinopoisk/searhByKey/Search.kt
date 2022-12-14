package happigin.inc.domain.models.kinopoisk.searhByKey

data class Search(
    val films: List<Film>,
    val keyword: String,
    val pagesCount: Int,
    val searchFilmsCountResult: Int
)