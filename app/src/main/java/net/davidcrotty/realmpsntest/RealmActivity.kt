package net.davidcrotty.realmpsntest

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import io.realm.Realm
import io.realm.RealmModel
import io.realm.RealmObject
import io.realm.SyncUser
import io.realm.annotations.PrimaryKey
import io.realm.annotations.RealmClass
import io.realm.annotations.Required
import java.util.*


class RealmActivity : AppCompatActivity() {

    companion object {
        fun intentFor(context: Context) : Intent {
            return Intent(context, RealmActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_realm)
        syncRealm()
        val realm = Realm.getDefaultInstance()
        val content = Content(UUID.randomUUID().toString(), "test")
        realm.beginTransaction()
        realm.copyToRealm(content)
        realm.close()
    }

    private fun syncRealm() {
        Realm.setDefaultConfiguration(SyncUser.current().defaultConfiguration)
    }
}
