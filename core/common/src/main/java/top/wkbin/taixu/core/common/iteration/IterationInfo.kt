package top.wkbin.taixu.core.common.iteration

/**
 * 自定义迭代版本信息（纯 Kotlin 数据模型，无平台依赖）。
 *
 * 用于在运行仪表盘展示当前构建的迭代身份与版本摘要，
 * 标识 TaiXuDev 自定义迭代变体与正式版的独立共存关系。
 *
 * @param versionName 语义化版本号，例如 "0.5.0"。
 * @param tag 迭代标签，例如 "TaiXuDev" / "Debug" / "Stable"。
 * @param isCustomIteration 是否为自定义迭代构建（dev 变体）。
 */
data class IterationInfo(
    val versionName: String,
    val tag: String,
    val isCustomIteration: Boolean = false,
)

/**
 * 根据构建类型解析迭代标签。
 *
 * - dev → TaiXuDev（自定义迭代变体）
 * - debug → Debug
 * - release → Stable
 * - 其它 → Custom
 *
 * 输入大小写与首尾空白不敏感。
 */
fun resolveIterationTag(buildType: String): String =
    when (buildType.trim().lowercase()) {
        "dev" -> "TaiXuDev"
        "debug" -> "Debug"
        "release" -> "Stable"
        else -> "Custom"
    }

/**
 * 判断是否为自定义迭代构建（dev 变体）。
 */
fun isCustomIterationBuild(buildType: String): Boolean =
    buildType.trim().lowercase() == "dev"

/**
 * 格式化迭代版本摘要文本。
 *
 * 自定义迭代以 ★ 标记，普通构建不带标记，例如：
 * - `★ TaiXuDev · v0.5.0`
 * - `Stable · v0.5.0`
 */
fun formatIterationSummary(info: IterationInfo): String {
    val marker = if (info.isCustomIteration) "★ " else ""
    return "$marker${info.tag} · v${info.versionName}".trim()
}
