package top.wkbin.taixu.core.common.iteration

import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

/**
 * 自定义迭代版本信息纯逻辑单元测试。
 */
class IterationInfoTest {

    @Test
    fun resolveIterationTag_dev_returnsTaiXuDev() {
        assertEquals("TaiXuDev", resolveIterationTag("dev"))
    }

    @Test
    fun resolveIterationTag_isCaseInsensitive() {
        assertEquals("TaiXuDev", resolveIterationTag("DEV"))
        assertEquals("Debug", resolveIterationTag("Debug"))
        assertEquals("Stable", resolveIterationTag("RELEASE"))
    }

    @Test
    fun resolveIterationTag_unknown_returnsCustom() {
        assertEquals("Custom", resolveIterationTag("nightly"))
        assertEquals("Custom", resolveIterationTag(""))
    }

    @Test
    fun resolveIterationTag_trimsWhitespace() {
        assertEquals("TaiXuDev", resolveIterationTag("  dev  "))
        assertEquals("Stable", resolveIterationTag("\trelease\n"))
    }

    @Test
    fun isCustomIterationBuild_dev_returnsTrue() {
        assertTrue(isCustomIterationBuild("dev"))
        assertTrue(isCustomIterationBuild("DEV"))
        assertTrue(isCustomIterationBuild(" Dev "))
    }

    @Test
    fun isCustomIterationBuild_nonDev_returnsFalse() {
        assertFalse(isCustomIterationBuild("debug"))
        assertFalse(isCustomIterationBuild("release"))
        assertFalse(isCustomIterationBuild(""))
    }

    @Test
    fun formatIterationSummary_customIteration_hasStarMarker() {
        val info = IterationInfo(versionName = "0.5.0", tag = "TaiXuDev", isCustomIteration = true)
        assertEquals("★ TaiXuDev · v0.5.0", formatIterationSummary(info))
    }

    @Test
    fun formatIterationSummary_normalBuild_hasNoMarker() {
        val info = IterationInfo(versionName = "0.5.0", tag = "Stable", isCustomIteration = false)
        assertEquals("Stable · v0.5.0", formatIterationSummary(info))
    }

    @Test
    fun formatIterationSummary_defaultNotCustom() {
        val info = IterationInfo(versionName = "0.5.0", tag = "Debug")
        assertEquals("Debug · v0.5.0", formatIterationSummary(info))
    }

    @Test
    fun formatIterationSummary_includesVersionAndTag() {
        val info = IterationInfo(versionName = "1.2.3", tag = "TaiXuDev", isCustomIteration = true)
        val summary = formatIterationSummary(info)
        assertTrue(summary.contains("v1.2.3"))
        assertTrue(summary.contains("TaiXuDev"))
    }
}
