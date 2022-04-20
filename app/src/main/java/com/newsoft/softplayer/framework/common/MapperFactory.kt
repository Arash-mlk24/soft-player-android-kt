package com.newsoft.softplayer.framework.common

import java.lang.Exception


class MapperFactory(private val mappers: List<IMapper<*, *>>) {

    init {
        INSTANCE = this;
    }

    companion object  {

        @Volatile private var INSTANCE: MapperFactory? = null

        fun getInstance(): MapperFactory {
            if (INSTANCE == null) {

                INSTANCE = MapperFactory(ArrayList<IMapper<*,*>>())

            }
            return INSTANCE!!
        }

    }
//
//    companion object {
//
//        @Volatile private var INSTANCE: MapperFactory? = null
//
//        fun getInstance(context: Context): UsersDatabase =
//            INSTANCE ?: synchronized(this) {
//                INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
//            }
//
//        private fun buildDatabase(context: Context) =
//            Room.databaseBuilder(context.applicationContext,
//                UsersDatabase::class.java, "Sample.db")
//                .build()
//    }
//



     fun <T, K> getMapper(): IMapper<*, *>? {
        for (mapper in mappers) {
            val interfaceCount = mapper::class.java.interfaces.size == 1;
            if (interfaceCount){
                val src = mapper::class.java.interfaces.get(0)
                val dest = mapper::class.java.interfaces.get(1)
//                val x = checkType<T>(src)
//                val y = checkType<K>(dest)
//
//                return if (x && y) mapper else null;
                return mapper;
            }
        }
        throw Exception("no mapper found");
    }

    inline fun <reified T> checkType(c :Class<*>): Boolean {

        return c.simpleName.equals(T::class.java.simpleName);


    }


}