package com.example.groupgetter;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

//A class that provides a singleton retrofit instance with a base URL and
// JSON converter used to communicate with a backend (VS Code)
public class Client {
    private static final String BASE_URL = "http://10.0.2.2:3000/";
        private static Retrofit retrofit;

        public static Retrofit getRetrofitInstance() {
            if (retrofit == null) {
                retrofit = new Retrofit.Builder()
                        .baseUrl(BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
            }
            return retrofit;
        }

        public static Service getService() {
            return getRetrofitInstance().create(Service.class);
        }
    }
