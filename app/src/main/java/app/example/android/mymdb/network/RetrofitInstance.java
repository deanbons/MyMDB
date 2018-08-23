package app.example.android.mymdb.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

class RetrofitInstance {

    private static Retrofit retrofit;

    private static final String BASE_URL = "https://api.themoviedb.org";

    // returns instance of retrofit
    // if null it creates and configures instance first then returns
    public static Retrofit getRetrofitInstance() {
        if (retrofit == null) {
            retrofit = new retrofit2.Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
