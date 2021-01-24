package com.tom.template.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.tom.template.databinding.PhotoFragmentBinding
import com.tom.base.BaseFragment
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.ObsoleteCoroutinesApi
import org.koin.android.viewmodel.ext.android.viewModel

@ObsoleteCoroutinesApi
@ExperimentalCoroutinesApi
class PhotoListFragment : BaseFragment() {
    private val photoListViewModel: PhotoListViewModel by viewModel()

    private lateinit var photoFragmentBinding: PhotoFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        photoFragmentBinding = PhotoFragmentBinding.inflate(inflater, container, false)
            .apply {
                viewmodel = photoListViewModel
            }

        photoFragmentBinding.lifecycleOwner = this.viewLifecycleOwner
        return photoFragmentBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        photoFragmentBinding.photoList.layoutManager =
            LinearLayoutManager(this@PhotoListFragment.context)
        photoFragmentBinding.photoList.adapter = PhotoAdapter()
        photoListViewModel.requestItems()
    }
}