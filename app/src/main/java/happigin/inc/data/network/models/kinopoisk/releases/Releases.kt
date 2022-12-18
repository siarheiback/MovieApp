package happigin.inc.data.network.models.kinopoisk.releases

import happigin.inc.data.network.models.kinopoisk.searhByKey.Film

data class Releases(
    val page:Int,
    val total:Int,
    val releases: List<Film>
)
