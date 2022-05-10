package uk.co.bubblebearapps.pantry.quickadd.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import uk.co.bubblebearapps.pantry.ext.setOnImeActionListener
import uk.co.bubblebearapps.pantry.quickadd.databinding.QuickAddFragmentBinding

@AndroidEntryPoint
class QuickAddFragment : Fragment() {

    companion object {
        fun newInstance() = QuickAddFragment()
    }

    private lateinit var binding: QuickAddFragmentBinding

    private val viewModel: QuickAddViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = QuickAddFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.etAdd.setOnImeActionListener {
            viewModel.onProductNameEntered(text.toString())
            text = null
        }
    }
}
