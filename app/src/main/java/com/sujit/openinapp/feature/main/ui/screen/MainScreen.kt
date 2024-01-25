package com.sujit.openinapp.feature.main.ui.screen

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.github.tehras.charts.line.LineChartData
import com.github.tehras.charts.line.renderer.line.SolidLineDrawer
import com.sujit.openinapp.R
import com.sujit.openinapp.feature.components.CustomAppIcon
import com.sujit.openinapp.feature.components.GreetingComponent
import com.sujit.openinapp.feature.components.HeaderComponent
import com.sujit.openinapp.feature.components.LineChartComponent
import com.sujit.openinapp.feature.components.LinkEachRow
import com.sujit.openinapp.feature.components.LoadingBarComponent
import com.sujit.openinapp.feature.components.SpacerHeight
import com.sujit.openinapp.feature.components.TabComponent
import com.sujit.openinapp.feature.components.ToggleChipComponent
import com.sujit.openinapp.feature.components.ViewAnalyticsComponent
import com.sujit.openinapp.feature.components.ViewReportRow
import com.sujit.openinapp.feature.main.ui.viewmodel.MainEvent
import com.sujit.openinapp.feature.main.ui.viewmodel.MainViewModel
import com.sujit.openinapp.preference.PreferenceStore
import com.sujit.openinapp.ui.theme.BackgroundColor
import com.sujit.openinapp.ui.theme.BlueColor
import com.sujit.openinapp.ui.theme.LightBlue100
import com.sujit.openinapp.ui.theme.LightBlue200
import com.sujit.openinapp.ui.theme.RedColor
import com.sujit.openinapp.ui.theme.VoiletColor


@Composable
fun MainScreen(
    viewModel: MainViewModel = hiltViewModel()
) {

    var selectedTab by remember { mutableIntStateOf(0) }
    val tabList = listOf("Top Links", "Recent Links")
    val apiResponse by viewModel.apiResponseEventFloe.collectAsStateWithLifecycle()
    val getToken by viewModel.getPref(PreferenceStore.token).collectAsStateWithLifecycle(
        initialValue = ""
    )

    LaunchedEffect(key1 = getToken) {
        if (getToken.isEmpty())
            viewModel.setPref(
                PreferenceStore.token,
                "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6MjU5MjcsImlhdCI6MTY3NDU1MDQ1MH0.dCkW0ox8tbjJA2GgUx2UEwNlbTZ7Rr38PVFJevYcXFI"
            )
    }

    LaunchedEffect(key1 = Unit) {
        viewModel.onEvent(MainEvent.GetApiResponseEvent)
    }

    Box(
        modifier = Modifier
            .background(BlueColor)
            .fillMaxSize()
    ) {
        Column {
            HeaderComponent()
            Box(
                modifier = Modifier
                    .background(
                        BackgroundColor,
                        RoundedCornerShape(
                            topStart = 16.dp,
                            topEnd = 16.dp
                        )
                    )
                    .fillMaxSize()
                    .padding(horizontal = 16.dp)
            ) {
                if (apiResponse.data != null) {
                    val data = apiResponse.data
                    val points = LineChartData(
                        points = listOf(
                            LineChartData.Point(
                                data?.data?.overall_url_chart?.date1?.toFloat() ?: 0f, "Jan"
                            ),
                            LineChartData.Point(
                                data?.data?.overall_url_chart?.date2?.toFloat() ?: 0f, "Feb"
                            ),
                            LineChartData.Point(
                                data?.data?.overall_url_chart?.date3?.toFloat() ?: 0f, "Mar"
                            ),
                            LineChartData.Point(
                                data?.data?.overall_url_chart?.date4?.toFloat() ?: 0f, "Apr"
                            ),
                            LineChartData.Point(
                                data?.data?.overall_url_chart?.date5?.toFloat() ?: 0f, "May"
                            ),
                            LineChartData.Point(
                                data?.data?.overall_url_chart?.date6?.toFloat() ?: 0f, "Jun"
                            ),
                            LineChartData.Point(
                                data?.data?.overall_url_chart?.date7?.toFloat() ?: 0f, "Jul"
                            )
                        ),
                        lineDrawer = SolidLineDrawer(
                            color = BlueColor
                        ),
                    )
                    LazyColumn{
                        item {
                            SpacerHeight(32.dp)
                            GreetingComponent()
                            SpacerHeight(24.dp)
                            LineChartComponent(point = points)
                            SpacerHeight(20.dp)
                            LazyRow {
                                item {
                                    ViewReportRow(
                                        icon = R.drawable.clicks,
                                        title = stringResource(R.string.today_s_click),
                                        count = "${data?.today_clicks ?: "0"}",
                                        backgroundColor = VoiletColor.copy(0.2f)
                                    )
                                    ViewReportRow(
                                        icon = R.drawable.location,
                                        title = stringResource(R.string.top_location),
                                        count = data?.top_location ?: "-",
                                        backgroundColor = BlueColor.copy(0.2f)
                                    )
                                    ViewReportRow(
                                        icon = R.drawable.web,
                                        title = stringResource(R.string.top_source),
                                        count = data?.top_source ?: "-",
                                        backgroundColor = RedColor.copy(0.2f)
                                    )

                                }
                            }
                            SpacerHeight(20.dp)
                            ViewAnalyticsComponent()
                            SpacerHeight(40.dp)
                            Row {
                                Row(
                                    modifier = Modifier.weight(1f)
                                ) {
                                    tabList.forEachIndexed { index, s ->
                                        ToggleChipComponent(
                                            title = s,
                                            selected = index == selectedTab,
                                            index = index,
                                            onValueChange = {
                                                selectedTab = it
                                            }
                                        )
                                    }
                                }
                                CustomAppIcon(
                                    icon = R.drawable.search,
                                    showBorder = true,
                                    background = BackgroundColor
                                ) {

                                }
                            }
                        }
                        item {
                            SpacerHeight(8.dp)
                        }
                        when (selectedTab) {
                            0 -> {
                                items(data?.data?.top_links ?: emptyList(), key = {
                                    it.url_id ?: it.hashCode()
                                }) {
                                    LinkEachRow(data = it)
                                }
                            }

                            else -> {
                                items(data?.data?.recent_links ?: emptyList(), key = {
                                    it.url_id ?: it.hashCode()
                                }) {
                                    LinkEachRow(data = it)
                                }
                            }
                        }

                        item {
                            SpacerHeight(20.dp)
                            ViewAnalyticsComponent(
                                icon = R.drawable.links,
                                text = stringResource(R.string.view_all_links)
                            )
                            SpacerHeight(40.dp)
                            TabComponent()
                            SpacerHeight(16.dp)
                            TabComponent(
                                icon = R.drawable.question,
                                text = stringResource(R.string.frequently_asked_questions),
                                backgroundColor = LightBlue100,
                                borderColor = LightBlue200
                            )
                            SpacerHeight(130.dp)
                        }
                    }
                }
                if (apiResponse.isLoading)
                    LoadingBarComponent(modifier = Modifier.align(Alignment.Center))
            }
        }
    }
}