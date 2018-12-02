package net.davidcrotty.realmpsntest

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import io.realm.ObjectServerError
import io.realm.SyncCredentials
import io.realm.SyncUser


class MainActivity : AppCompatActivity() {

    private val nickname = "dave"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (SyncUser.current() != null) {
            realmActivity()
        } else {
            val credentials = SyncCredentials.nickname(nickname, false)
            SyncUser.logInAsync(credentials, BuildConfig.REALM_AUTH, object: SyncUser.Callback<SyncUser> {
                override fun onSuccess(result: SyncUser) {
                    realmActivity()
                }

                override fun onError(error: ObjectServerError) {
                    Toast.makeText(this@MainActivity, "Login failed", Toast.LENGTH_SHORT).show()
                }
            })
        }
    }

    private fun realmActivity() {
        Toast.makeText(this@MainActivity, "Login worked", Toast.LENGTH_SHORT).show()
        this@MainActivity.startActivity(RealmActivity.intentFor(this@MainActivity))
    }
}
