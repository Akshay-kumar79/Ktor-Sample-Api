package akshaw.com.models

data class Movie(
    val cast: List<String>,
    val countries: List<String>,
    val directors: List<String>,
    val fullplot: String,
    val genres: List<String>,
    val languages: List<String>,
    val lastupdated: String,
    val plot: String,
    val poster: String,
    val rated: String,
    val title: String,
)