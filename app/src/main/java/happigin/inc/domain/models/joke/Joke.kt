package happigin.inc.domain.models.joke

import com.google.gson.annotations.SerializedName

data class Joke(
    @SerializedName("id") var id: Int? = null,
    @SerializedName("type") var type: String? = null,
    @SerializedName("setup") var setup: String? = null,
    @SerializedName("punchline") var punchline: String? = null

)
