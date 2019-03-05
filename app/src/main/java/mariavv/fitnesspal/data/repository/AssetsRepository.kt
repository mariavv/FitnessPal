package mariavv.fitnesspal.data.repository

import android.content.res.AssetManager
import com.google.gson.GsonBuilder
import mariavv.fitnesspal.domain.data.TestData
import java.io.IOException
import java.io.InputStream
import java.util.concurrent.Callable

class AssetsRepository {

    fun getTestData(assetManager: AssetManager): Callable<TestData> {
        return Callable {
            val inputStream = openAssert(assetManager)
            if (inputStream != null) {
                val json = loadJSONFromAsset(inputStream)
                return@Callable GsonBuilder().create().fromJson(json, TestData::class.java)
            }
            TestData()
        }
    }

    private fun loadJSONFromAsset(inputStream: InputStream): String? {
        return try {
            val size = inputStream.available()
            val buffer = ByteArray(size)
            inputStream.read(buffer)
            inputStream.close()
            String(buffer)
        } catch (ex: IOException) {
            ex.printStackTrace()
            null
        }

    }

    private fun openAssert(assetManager: AssetManager): InputStream? {
        return try {
            assetManager.open(TEST_DATA_FILE_NAME)
        } catch (e: IOException) {
            e.printStackTrace()
            null
        }

    }

    companion object {
        private const val TEST_DATA_FILE_NAME = "db_test.json"
    }
}
