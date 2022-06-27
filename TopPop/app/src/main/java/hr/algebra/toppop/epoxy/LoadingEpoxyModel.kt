package hr.algebra.toppop.epoxy

import hr.algebra.toppop.R
import hr.algebra.toppop.databinding.ModelLoadingBinding

class LoadingEpoxyModel : ViewBindingKotlinModel<ModelLoadingBinding>(R.layout.model_loading) {
    override fun ModelLoadingBinding.bind() {
        //nothing
    }

    override fun getSpanSize(totalSpanCount: Int, position: Int, itemCount: Int): Int {
        return totalSpanCount
    }

}