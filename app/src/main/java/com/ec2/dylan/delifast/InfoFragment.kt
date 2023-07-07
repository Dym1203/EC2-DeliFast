package com.ec2.dylan.delifast

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import cn.pedant.SweetAlert.SweetAlertDialog
import com.ec2.dylan.delifast.databinding.FragmentInfoBinding


class InfoFragment : Fragment() {
    private lateinit var binding: FragmentInfoBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentInfoBinding.inflate(inflater, container, false)
        val view = binding.root
        binding.btnPresionar.setOnClickListener {
            SweetAlertDialog(requireContext(), SweetAlertDialog.SUCCESS_TYPE)
                .setTitleText("¡Muchas Gracias!")
                .setContentText("Por ver mi trabajo")
                .setConfirmText("Listo")
                .show()
        }
        return view
    }

}