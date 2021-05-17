package com.benny.penda

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.benny.annotation.Builder
import com.benny.annotation.Optional
import com.benny.annotation.Required
import kotlinx.android.synthetic.main.activity_repo.*

@Builder
class RepoActivity : AppCompatActivity() {

    @Required
    lateinit var name: String

    @Required
    lateinit var owner: String

    @Optional
    lateinit var title: String

    @Optional
    lateinit var company: String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_repo)
        nameTv.text = name
        ownerTv.text = owner
        urlTv.text = title
        timeTv.text = company
    }
}