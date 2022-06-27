package hr.algebra.toppop.epoxy

import com.airbnb.epoxy.EpoxyModel
import com.airbnb.epoxy.paging.PagedListEpoxyController
import com.squareup.picasso.Picasso
import hr.algebra.toppop.R
import hr.algebra.toppop.databinding.ModelSongListItemBinding
import hr.algebra.toppop.databinding.ModelSongListTitleBinding
import hr.algebra.toppop.models.DataX

class ChartListEpoxyController(
    private val onCharacterSelected:(Int)->Unit
):PagedListEpoxyController<DataX>() {


    override fun buildItemModel(currentPosition: Int, item: DataX?): EpoxyModel<*> {
        return ChartGridItemEpoxyModel(
            chartId = item!!.id,
            imageUrl = item.album.cover_small,
            name = item.title,
            onCharacterSelected = onCharacterSelected,
            duration = item.duration,
            rank = item.position
        ).id(item.id)
    }

    override fun addModels(models: List<EpoxyModel<*>>) {
        if (models.isEmpty()){
            LoadingEpoxyModel().id("loading").addTo(this)
            return
        }

        ChartGridTitleEpoxyModel("Top 10 Croatia")
            .id("top_10_croatia")
            .addTo(this)

        super.addModels(models)
    }

    data class ChartGridItemEpoxyModel(
        val chartId:Int,
        val imageUrl:String,
        val name:String,
        val rank:Int,
        val duration: Int,
        val onCharacterSelected: (Int) -> Unit
    ):ViewBindingKotlinModel<ModelSongListItemBinding>(R.layout.model_song_list_item){
        override fun ModelSongListItemBinding.bind() {
            Picasso.get().load(imageUrl).into(albumImageView)
            songRank.text=rank.toString()
            songNameTextView.text=name

            root.setOnClickListener {
                onCharacterSelected(chartId)
            }
        }
    }

    data class ChartGridTitleEpoxyModel(
        val title:String
    ):ViewBindingKotlinModel<ModelSongListTitleBinding>(R.layout.model_song_list_title){
        override fun ModelSongListTitleBinding.bind() {
            textView.text=title
        }

        override fun getSpanSize(totalSpanCount: Int, position: Int, itemCount: Int): Int {
            return totalSpanCount
        }

    }

}