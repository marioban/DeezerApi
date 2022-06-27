package hr.algebra.toppop.epoxy

import com.airbnb.epoxy.EpoxyController
import com.squareup.picasso.Picasso
import hr.algebra.toppop.R
import hr.algebra.toppop.databinding.ModelSongDetailsDataPointBinding
import hr.algebra.toppop.databinding.ModelSongDetailsHeaderBinding
import hr.algebra.toppop.databinding.ModelSongDetailsImageBinding
import hr.algebra.toppop.models.Data
import hr.algebra.toppop.models.DataX

class SongDetailsEpoxyController : EpoxyController() {

    var isLoading:Boolean=true
    set(value) {
        field=value
        if (field){
            requestModelBuild()
        }
    }

    var songResponse: DataX? = null
        set(value) {
            field = value
            if (field != null) {
                isLoading = false
                requestModelBuild()
            }
        }


    override fun buildModels() {

        if (isLoading){
            //show loading state
            LoadingEpoxyModel().id("loading").addTo(this)
            return
        }

        if (songResponse==null){
            //error state
            return
        }




        //add header model
        HeaderEpoxyModel(
            name = songResponse!!.title
        ).id("header").addTo(this)

        //add image model
        ImageEpoxyModel(
            imageUrl = songResponse!!.album.cover_small
        ).id("image").addTo(this)

        //add data point(s)
        DataPointEpoxyModel(
            title = "Artist",
            description = songResponse!!.artist.name
        ).id("data_point_1").addTo(this)

        //add data point(s)
        DataPointEpoxyModel(
            title = "Album",
            description = songResponse!!.album.title
        ).id("data_point_2").addTo(this)


        //add data point(s)
        DataPointEpoxyModel(
            title = "Rank",
            description = songResponse!!.position.toString()
        ).id("data_point_3").addTo(this)




    }

    data class HeaderEpoxyModel(
        val name:String
    ):ViewBindingKotlinModel<ModelSongDetailsHeaderBinding>(R.layout.model_song_details_header){
        override fun ModelSongDetailsHeaderBinding.bind() {
            nameTextView.text=name

        }
    }

    data class ImageEpoxyModel(
        var imageUrl:String
    ):ViewBindingKotlinModel<ModelSongDetailsImageBinding>(R.layout.model_song_details_image){
        override fun ModelSongDetailsImageBinding.bind() {
            Picasso.get().load(imageUrl).into(headerImageView)
        }
    }

    data class DataPointEpoxyModel(
        val title:String,
        val description:String
    ):ViewBindingKotlinModel<ModelSongDetailsDataPointBinding>(R.layout.model_song_details_data_point){
        override fun ModelSongDetailsDataPointBinding.bind() {
            labelTextView.text=title
            textView.text=description
        }
    }
}