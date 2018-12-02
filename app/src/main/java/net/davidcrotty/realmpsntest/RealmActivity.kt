package net.davidcrotty.realmpsntest

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import io.realm.Realm
import io.realm.SyncUser
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
        realm.executeTransaction { realm ->
            realm.copyToRealm(content)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        Realm.getDefaultInstance().close()
    }

    private fun syncRealm() {
        Realm.setDefaultConfiguration(SyncUser.current().defaultConfiguration)
    }
}
