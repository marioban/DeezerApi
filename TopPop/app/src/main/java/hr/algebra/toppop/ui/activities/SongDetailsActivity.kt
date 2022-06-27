package hr.algebra.toppop.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.airbnb.epoxy.EpoxyRecyclerView
import hr.algebra.toppop.Constants
import hr.algebra.toppop.R
import hr.algebra.toppop.epoxy.SongDetailsEpoxyController
import hr.algebra.toppop.viewmodels.SharedViewModel

class SongDetailsActivity : AppCompatActivity() {

    val viewModel: SharedViewModel by lazy {
        ViewModelProvider(this).get(SharedViewModel::class.java)
    }

    private val epoxyController = SongDetailsEpoxyController()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_song_details)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        viewModel.songByLiveData.observe(this) { response ->

            epoxyController.songResponse
            if (response == null) {
                Toast.makeText(
                    this@SongDetailsActivity,
                    "Unsuccessful network call",
                    Toast.LENGTH_SHORT
                ).show()
                return@observe
            }
        }


        //val id = intent.getIntExtra(Constants.INTENT_EXTRA_SONG_ID, 1785220827)
        viewModel.refreshSong(1785220827)


        val epoxyRecyclerView = findViewById<EpoxyRecyclerView>(R.id.epoxyRecyclerView)
        epoxyRecyclerView.setControllerAndBuildModels(epoxyController)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}