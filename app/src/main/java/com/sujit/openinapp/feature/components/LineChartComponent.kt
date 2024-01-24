package com.sujit.openinapp.feature.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.github.tehras.charts.line.LineChart
import com.github.tehras.charts.line.LineChartData
import com.github.tehras.charts.line.renderer.point.FilledCircularPointDrawer
import com.github.tehras.charts.line.renderer.point.HollowCircularPointDrawer
import com.github.tehras.charts.line.renderer.point.NoPointDrawer
import com.github.tehras.charts.line.renderer.point.PointDrawer
import com.github.tehras.charts.line.renderer.xaxis.SimpleXAxisDrawer
import com.github.tehras.charts.line.renderer.yaxis.SimpleYAxisDrawer
import com.github.tehras.charts.piechart.animation.simpleChartAnimation
import com.sujit.openinapp.R
import com.sujit.openinapp.ui.theme.DarkGrayColor


@Composable
fun LineChartComponent(
    modifier: Modifier = Modifier,
    point: LineChartData
) {
    val lineChartDataModel = LineChartDataModel()
    Column(
        modifier = modifier
            .height(250.dp)
            .fillMaxWidth()
    ) {
        Row {
            Text(
                text = stringResource(R.string.overview),
                style = MaterialTheme.typography.labelLarge.copy(
                    color = DarkGrayColor,
                    fontWeight = FontWeight.W400
                ),
                modifier = Modifier.weight(1f)
            )
            DateRow()
        }
        SpacerHeight(16.dp)
        LineChart(
            linesChartData = listOf(
                point,
            ),
            modifier = modifier.fillMaxSize(),
            animation = simpleChartAnimation(),
            pointDrawer = lineChartDataModel.pointDrawer,
            xAxisDrawer = SimpleXAxisDrawer(),
            yAxisDrawer = SimpleYAxisDrawer(),
            horizontalOffset = 5f,
            labels = point.points.map { it.label }
        )
    }
}


class LineChartDataModel {

    private var pointDrawerType by mutableStateOf(PointDrawerType.None)
    val pointDrawer: PointDrawer
        get() {
            return when (pointDrawerType) {
                PointDrawerType.None -> NoPointDrawer
                PointDrawerType.Filled -> FilledCircularPointDrawer()
                PointDrawerType.Hollow -> HollowCircularPointDrawer()
            }
        }

    enum class PointDrawerType {
        None,
        Filled,
        Hollow
    }
}

@Composable
fun DateRow(
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .background(
                Color.White,
                RoundedCornerShape(8.dp)
            )
            .border(1.dp, DarkGrayColor, RoundedCornerShape(8.dp))
            .padding(horizontal = 8.dp, vertical = 6.dp)
    ) {
        Text(
            text = stringResource(R.string._2023_12_25_2024_01_24),
            style = MaterialTheme.typography.labelLarge.copy(
                color = Color.Black,
                fontWeight = FontWeight.W400
            )
        )
    }
}