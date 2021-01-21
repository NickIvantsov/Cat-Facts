package com.example.android.catfacts.app.dialogs

import android.graphics.Bitmap
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.android.catfacts.databinding.CatFactItemDialogFragmentBinding

class CatFactItemDialogFragment : DialogFragment() {

    companion object {
        fun newInstance(bitmap: Bitmap?, text: String): CatFactItemDialogFragment {
            val fragment = CatFactItemDialogFragment()
            val args = Bundle()
            args.putParcelable(KEY_BITMAP, bitmap)
            args.putString(KEY_TEXT, text)

            fragment.arguments = args
            return fragment
        }

        private const val KEY_TEXT = "KEY_TEXT"
        private const val KEY_BITMAP = "KEY_BITMAP"

    }


    private var bindingImpl: CatFactItemDialogFragmentBinding? = null
    private val binding get() = bindingImpl!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        bindingImpl = CatFactItemDialogFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView(view)
    }

    private fun setupView(view: View) {
        binding.tvFact.text = arguments?.getString(KEY_TEXT)
        val bitmap = arguments?.getParcelable<Bitmap>(KEY_BITMAP)
        binding.ivCatImgCatItemDialog.setImageBitmap(bitmap)

    }
}