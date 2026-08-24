package top.wkbin.taixu.ui.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import top.wkbin.taixu.core.common.iteration.IterationInfo
import top.wkbin.taixu.core.common.iteration.formatIterationSummary
import top.wkbin.taixu.feature.home.R
import top.wkbin.taixu.ui.components.RuntimeCard
import top.wkbin.taixu.ui.components.RuntimeIcon
import top.wkbin.taixu.ui.components.RuntimeIconName

/**
 * 太墟 · 自定义迭代版本卡片 (Custom Iteration Identity Card)
 *
 * 在运行仪表盘顶部展示当前构建的迭代身份与版本摘要，
 * 标识 TaiXuDev 自定义迭代变体与正式版独立共存的语义。
 * 遵循 M3 组件规范，使用 [RuntimeCard] 承载内容。
 */
@Composable
fun IterationCard(
    modifier: Modifier = Modifier,
) {
    val version = stringResource(R.string.home_iteration_version)
    val tag = stringResource(R.string.home_iteration_tag)
    val title = stringResource(R.string.home_iteration_title)
    val subtitle = stringResource(R.string.home_iteration_subtitle)

    val info = IterationInfo(
        versionName = version,
        tag = tag,
        isCustomIteration = tag == "TaiXuDev",
    )
    val summary = formatIterationSummary(info)

    RuntimeCard(
        modifier = modifier.fillMaxWidth(),
        containerColor = MaterialTheme.colorScheme.surfaceContainerLow,
        borderColor = MaterialTheme.colorScheme.outlineVariant,
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier.fillMaxWidth(),
        ) {
            RuntimeIcon(
                name = RuntimeIconName.Refresh,
                modifier = Modifier.size(22.dp),
                tint = MaterialTheme.colorScheme.primary,
            )
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.onSurface,
                )
                Text(
                    text = subtitle,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                )
            }
            Text(
                text = summary,
                style = MaterialTheme.typography.labelMedium,
                fontWeight = FontWeight.Medium,
                color = MaterialTheme.colorScheme.primary,
            )
        }
    }
}
