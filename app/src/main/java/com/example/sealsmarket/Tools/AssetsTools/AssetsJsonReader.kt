import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class AssetsJsonReader(val context: Context)

{
    val gson = Gson()

    inline fun <reified T> ReadFromAssets(fileName: String): T? { // Убрали context из аргументов
        return try {
            // Используем this.context
            val inputStream = context.assets.open(fileName)
            val jsonString = inputStream.bufferedReader().use { it.readText() }
            val type = object : TypeToken<T>() {}.type


            gson.fromJson(jsonString, type)
        }

        catch (e: Exception)
        {
            e.printStackTrace()
            null
        }
    }
}