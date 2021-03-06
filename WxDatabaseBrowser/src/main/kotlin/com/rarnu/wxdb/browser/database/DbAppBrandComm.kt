package com.rarnu.wxdb.browser.database

import com.rarnu.wxdb.browser.util.Config
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SQLiteDatabaseHook
import java.io.File

class DbAppBrandComm(pwd: String?) : DbIntf(pwd) {

    @Suppress("HasPlatformType")
    override fun initDb(pwd: String?) = try {
        SQLiteDatabase.openOrCreateDatabase(File(Config.basePath(), "appbrandcomm.db"), pwd, null, object : SQLiteDatabaseHook {
            override fun preKey(database: SQLiteDatabase) {}
            override fun postKey(database: SQLiteDatabase) {
                try {
                    database.rawExecSQL("PRAGMA cipher_migrate;")
                } catch (e: Throwable) {
                }
            }
        })
    } catch (e: Throwable) {
        null
    }

}