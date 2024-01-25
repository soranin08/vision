////Apackage com.example.visiontest
//
//import androidx.room.Room
//
//class Application : Application() {
//    companion object{
//        lateinit var database: AppDatabase
//    }
//
//    override fun onCreate(){
//        super.onCreate()
//        //appDatabaseをビルドする
//        database = Room.databaseBuilder(
//            applicationContext,
//            AppDatabase::class.java,
//            "app_database"
//        ).build()
//    }
//}