package hr.algebra.toppop.framework

import androidx.paging.PageKeyedDataSource
import hr.algebra.toppop.models.DataX
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class ChartsDataSource(
    private val coroutineScope: CoroutineScope,
    private val repository: ChartsRepository,
) : PageKeyedDataSource<Int, DataX>() {

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, DataX>
    ) {
        coroutineScope.launch {
            val data = repository.getChartDetails()

            if (data == null) {
                callback.onResult(emptyList(), null, null)
                return@launch
            }
            callback.onResult(data.data,null,null)
        }
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, DataX>) {
        coroutineScope.launch {
            val data=repository.getChartDetails()

            if (data==null){
                callback.onResult(emptyList(),null)
                return@launch
            }
            callback.onResult(data.data,null)
        }
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, DataX>) {
    }


}