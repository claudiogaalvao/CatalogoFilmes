package br.com.claudiogalvao.catalogofilmes.data.remote.retrofit

import android.content.Context
import br.com.claudiogalvao.catalogofilmes.data.remote.retrofit.service.FilmeService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInitializer(val context: Context) {

//    val cacheSize = (10*1024*1024)
//    val myCache = Cache(context.cacheDir, cacheSize.toLong())
//
//    @RequiresApi(Build.VERSION_CODES.M)
//    private fun hasNetwork(context: Context) : Boolean {
//        val connectivityManager =
//            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
//        val capabilities =
//            connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
//
//        if (capabilities != null) {
//            if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) {
//                Log.i("Internet", "NetworkCapabilities.TRANSPORT_CELLULAR")
//                return true
//            } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
//                Log.i("Internet", "NetworkCapabilities.TRANSPORT_WIFI")
//                return true
//            } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)) {
//                Log.i("Internet", "NetworkCapabilities.TRANSPORT_ETHERNET")
//                return true
//            }
//        }
//        return false
//    }
//
//    @RequiresApi(Build.VERSION_CODES.M)
//    val okHttpClient = OkHttpClient.Builder().cache(myCache).addInterceptor { chain ->
//        var request = chain.request()
//        request = if(hasNetwork(context))
//            request.newBuilder().header("Cache-Control", "public, max-age=" + 5).build()
//        else
//            request.newBuilder().header("Cache-Control", "public, only-if-cached, max-stale=" + 60 + 60 + 24 + 7).build()
//        chain.proceed(request)
//    }
//        .build()

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://api.themoviedb.org/3/movie/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun filmeService() : FilmeService = retrofit.create(FilmeService::class.java)

}