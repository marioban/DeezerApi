package hr.algebra.toppop.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.airbnb.epoxy.EpoxyRecyclerView
import hr.algebra.toppop.Constants
import hr.algebra.toppop.R
import hr.algebra.toppop.epoxy.ChartListEpoxyController
import hr.algebra.toppop.viewmodels.ChartViewModel

class MainActivity : AppCompatActivity() {

    private val viewModel: ChartViewModel by lazy {
        ViewModelProvider(this)[ChartViewModel::class.java]
    }


    private val epoxyController = ChartListEpoxyController(::onChartSelected)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel.chartsPagedListLiveData.observe(this) { pagedList ->
            epoxyController.submitList(pagedList)
        }
        findViewById<EpoxyRecyclerView>(R.id.epoxyRecyclerView).setController(epoxyController)

    }

    private fun onChartSelected(chartId: Int) {
        val intent = Intent(this, SongDetailsActivity::class.java)
        //intent.putExtra(Constants.INTENT_EXTRA_SONG_ID, chartId)
        startActivity(intent)
    }

}
