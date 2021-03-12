package vn.kien.tokoinchallenge.data.local

@Suppress("UNCHECKED_CAST")
class DataTransferHelper {
    private var index = 0
    private val data = mutableMapOf<String, Any>()

    fun push(value: Any): String {
        val key = "_data_${index++}"
        data[key] = value
        return key
    }

    fun <T> pop(key: String): T {
        return data.remove(key) as T
    }
}