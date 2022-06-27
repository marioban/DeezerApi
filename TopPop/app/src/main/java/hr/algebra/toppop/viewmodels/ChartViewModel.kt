package hr.algebra.toppop.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.*
import hr.algebra.toppop.framework.ChartDataSourceFactory
import hr.algebra.toppop.Constants
import hr.algebra.toppop.models.DataX
import hr.algebra.toppop.framework.ChartsRepository
import kotlinx.coroutines.Dispatchers

class ChartViewModel : ViewModel() {
    private val repository = ChartsRepository()

    private val pageListConfig:PagedList.Config=PagedList.Config.Builder()
        .setPageSize(Constants.PAGE_SIZE)
        .setPrefetchDistance(Constants.PREFETCH_DISTANCE)
        .build()

    private val dataSourceFactory = ChartDataSourceFactory(viewModelScope, repository)
    val chartsPagedListLiveData: LiveData<PagedList<DataX>> =
        LivePagedListBuilder(
            dataSourceFactory, pageListConfig
        ).build()
}