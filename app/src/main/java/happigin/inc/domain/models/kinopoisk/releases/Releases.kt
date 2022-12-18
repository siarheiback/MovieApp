package happigin.inc.domain.models.kinopoisk.releases

import happigin.inc.domain.models.kinopoisk.searhByKey.Film

data class Releases(
    val page:Int,
    val total:Int,
    val releases: List<Film>
)
