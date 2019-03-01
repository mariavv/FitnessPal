package mariavv.fitnesspal.data.repository;

import android.content.res.AssetManager;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.Callable;

import mariavv.fitnesspal.domain.TestData;

public class AssetsRepository {
    private static final String TEST_DATA_FILE_NAME = "db_test.json";

    public Callable<TestData> getTestData(AssetManager assetManager) {
        final InputStream inputStream = openAssert(assetManager);

        if (inputStream != null) {

            final Gson gson = new GsonBuilder().create();
            final String json = loadJSONFromAsset(inputStream);
            final TestData data = gson.fromJson(json, TestData.class);
            Callable<TestData> clb = new Callable<TestData>() {
                @Override
                public TestData call() throws Exception {
                    return data;
                }
            };
            return clb;

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

        return TestData::new;
    }

    private String loadJSONFromAsset(InputStream inputStream) {
        try {
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            inputStream.close();
            return new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    private InputStream openAssert(AssetManager assetManager) {
        try {
            return assetManager.open(TEST_DATA_FILE_NAME);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
