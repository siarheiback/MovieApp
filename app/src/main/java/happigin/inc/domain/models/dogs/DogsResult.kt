package happigin.inc.domain.models.dogs

import com.google.gson.annotations.SerializedName

data class DogsResult(
    @SerializedName("message" ) var message : String? = null,
    @SerializedName("status"  ) var status  : String? = null
)
