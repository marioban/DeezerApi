package hr.algebra.toppop.framework

import androidx.paging.DataSource
import hr.algebra.toppop.models.DataX
import kotlinx.coroutines.CoroutineScope

class ChartDataSourceFactory(
    private val coroutineScope: CoroutineScope,
    private val repository: ChartsRepository
) : DataSource.Factory<Int,DataX>() {
    override fun create(): DataSource<Int, DataX> {
        return ChartsDataSource(coroutineScope, repository)
    }
}