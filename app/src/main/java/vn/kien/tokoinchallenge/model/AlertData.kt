package vn.kien.tokoinchallenge.model

data class AlertData(
    val title: String,
    val content: String,
    val positiveTitle: String,
    val negativeTitle: String?
)