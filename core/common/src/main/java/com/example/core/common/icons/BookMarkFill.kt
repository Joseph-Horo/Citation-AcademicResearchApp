package com.example.core.common.icons
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

@Suppress("CheckReturnValue")
public val BookMarkFill: ImageVector
  get() {
    if (_bookmarkFill != null) {
      return _bookmarkFill!!
    }
    _bookmarkFill =
      ImageVector.Builder(
          name = "BookMarkFill",
          defaultWidth = 24.dp,
          defaultHeight = 24.dp,
          viewportWidth = 24f,
          viewportHeight = 24f,
        )
        .apply {
          path(
            fill = SolidColor(Color.Black),
            fillAlpha = 1f,
            stroke = null,
            strokeAlpha = 1f,
            strokeLineWidth = 1f,
            strokeLineCap = StrokeCap.Butt,
            strokeLineJoin = StrokeJoin.Bevel,
            strokeLineMiter = 1f,
            pathFillType = PathFillType.NonZero,
          ) {
            moveTo(9.73f, 14f)
            lineTo(12f, 12.63f)
            lineTo(14.28f, 14f)
            lineToRelative(-0.6f, -2.6f)
            lineToRelative(2f, -1.72f)
            lineTo(13.05f, 9.45f)
            lineTo(12f, 7f)
            lineTo(10.95f, 9.45f)
            lineTo(8.33f, 9.67f)
            lineToRelative(2f, 1.72f)
            lineTo(9.73f, 14f)
            close()
            moveTo(5f, 21f)
            verticalLineTo(5f)
            quadTo(5f, 4.17f, 5.59f, 3.59f)
            reflectiveQuadTo(7f, 3f)
            horizontalLineTo(17f)
            quadToRelative(0.82f, 0f, 1.41f, 0.59f)
            reflectiveQuadTo(19f, 5f)
            verticalLineTo(21f)
            lineTo(12f, 18f)
            lineTo(5f, 21f)
            close()
          }
        }
        .build()
    return _bookmarkFill!!
  }

private var _bookmarkFill: ImageVector? = null
