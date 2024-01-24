package com.sujit.openinapp.data.model

import com.squareup.moshi.Json

data class ApiModelResponse(
    val message: String? = null,
    val status: Boolean? = null,
    val statusCode: Int? = null,
    val support_whatsapp_number: String? = null,
    val extra_income: Double? = null,
    val total_links: Int? = null,
    val total_clicks: Int? = null,
    val today_clicks: Int? = null,
    val top_source: String? = null,
    val top_location: String? = null,
    val startTime: String? = null,
    val links_created_today: Int? = null,
    val applied_campaign: Int? = null,
    val data: ApiData? = null
)

data class ApiData(
    val recent_links: List<RecentLinks>,
    val top_links: List<RecentLinks>? = null,
    val overall_url_chart: OverallUrlChart? = null
)

data class OverallUrlChart(
    @Json(name = "2023-12-25")
    val date1: String? = null,
    @Json(name = "2023-12-26")
    val date2: String? = null,
    @Json(name = "2023-12-27")
    val date3: String? = null,
    @Json(name = "2023-12-28")
    val date4: String? = null,
    @Json(name = "2023-12-29")
    val date5: String? = null,
    @Json(name = "2024-01-23")
    val date6: String? = null,
    @Json(name = "2024-01-24")
    val date7: String? = null,
)

data class RecentLinks(
    val url_id: Long? = null,
    val web_link: String? = null,
    val smart_link: String? = null,
    val title: String? = null,
    val total_clicks: String? = null,
    val original_image: String? = null,
    val thumbnail: String? = null,
    val times_ago: String? = null,
    val created_at: String? = null,
    val domain_id: String? = null,
    val url_prefix: String? = null,
    val url_suffix: String? = null,
    val app: String? = null,
    val is_favourite: Boolean? = null
)
