package prism6.com.easypark.ui.activity

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.transition.platform.MaterialContainerTransform
import com.google.android.material.transition.platform.MaterialContainerTransformSharedElementCallback
import prism6.com.easypark.R
import prism6.com.easypark.databinding.ActivityCarparkDetailBinding
import prism6.com.easypark.model.CarParkInfo

class CarparkDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCarparkDetailBinding
    val viewModel: CarparkDetailViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.repository.getCarParkDetail(
            intent.getStringExtra("park_id").toString()
        ).observe(this, {
            updateUI(it)
        })

        binding = ActivityCarparkDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.root.transitionName = "open_detail"
        setEnterSharedElementCallback(MaterialContainerTransformSharedElementCallback())
        window.sharedElementEnterTransition = MaterialContainerTransform().apply {
            duration = 300
            addTarget(binding.root)
        }
        window.sharedElementReturnTransition = MaterialContainerTransform().apply {
            duration = 300
            addTarget(binding.root)
        }

        setSupportActionBar(findViewById(R.id.toolbar))
        binding.toolbarLayout.title = title
    }

    private fun updateUI(carparkInfo: CarParkInfo) {
        binding.toolbarLayout.title = carparkInfo.name_tc
        binding.detailMainText.text = carparkInfo.getInfo()

        try {
            Glide.with(this)
                .load(carparkInfo.carpark_photo.replace("http:", "https:"))
                .placeholder(R.drawable.placeholder)
                .centerCrop()
                .into(binding.collapsImageview)
        }catch (e : Exception){
        }
    }
}