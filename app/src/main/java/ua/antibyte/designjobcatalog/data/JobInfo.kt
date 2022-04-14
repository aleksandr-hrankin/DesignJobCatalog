package ua.antibyte.designjobcatalog.data

data class JobInfo(
    val logoResId: Int = 0,
    val title: String = "",
    var isActive: Boolean = false
)