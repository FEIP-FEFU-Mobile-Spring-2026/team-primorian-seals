import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.File

class JsonDataHandler(private val context: Context)
{
    val gson = Gson()

    // Вспомогательная функция для получения файла
    fun GetFile(fileName: String): File
    {
        return File(context.filesDir, fileName)
    }


    inline fun <reified T> SaveData( data: T, fileName: String)
    {
        val file = GetFile(fileName)

        file.writeText(gson.toJson(data))
    }


    // Загрузка списка
    inline fun <reified T> LoadData(fileName: String): T?
    {
        val file = GetFile(fileName)

        if (!file.exists()) return null

        val json = file.readText()

        val type = object : TypeToken<T>() {}.type

        return gson.fromJson(json, type)
    }
}