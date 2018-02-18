package com.luminary.setyo.checkvalidfromrxandroid

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import com.jakewharton.rxbinding2.widget.RxTextView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rxfunction(edtuser)
        rxfunction(edtpw)


    }

    private fun rxfunction(edt: EditText) {

        RxTextView.textChanges(edt)
                .map {
                    t: CharSequence -> t.length <5 && t.length > 0

                }.debounce(1000,TimeUnit.MILLISECONDS).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { t ->
                    if (t){
                        edt.setError("Karakter harus lebih dari lima")
                    }
                }

    }
}
