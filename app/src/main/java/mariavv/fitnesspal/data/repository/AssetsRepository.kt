package mariavv.fitnesspal.data.repository

import android.content.res.AssetManager
import com.google.gson.GsonBuilder
import mariavv.fitnesspal.domain.TestData
import java.io.IOException
import java.io.InputStream
import java.util.concurrent.Callable

class AssetsRepository {

    fun getTestData(assetManager: AssetManager): Callable<TestData> {
        val inputStream = openAssert(assetManager)

        if (inputStream != null) {

            val gson = GsonBuilder().create()
            //val json = loadJSONFromAsset(inputStream)
            //val data = gson.fromJson<TestData>(json, TestData::class.java)

            return Callable { return@Callable gson.fromJson<TestData>(loadJSONFromAsset(inputStream), TestData::class.java) }

            /*final Dish2[] dish2s = gson.fromJson(json, Dish2[].class);
            Callable<List<Dish2>> clb = new Callable<List<Dish2>>() {
                @Override
                public List<Dish2> call() throws Exception {
                    return Arrays.asList(dish2s);
                }
            };*/

            /* чет не работает
            return new GsonBuilder().create()
                    .fromJson(loadJSONFromAsset(inputStream), new TypeToken<TestData>() {
                    }.getType());*/
        }

        return Callable { return@Callable TestData() }
    }

    private fun loadJSONFromAsset(inputStream: InputStream): String? {
        return try {
            val size = inputStream.available()
            val buffer = ByteArray(size)
            inputStream.read(buffer)
            inputStream.close()
            String(buffer/*, "UTF-8"*/)
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
