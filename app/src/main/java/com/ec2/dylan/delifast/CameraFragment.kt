package com.ec2.dylan.delifast

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import cn.pedant.SweetAlert.SweetAlertDialog
import com.ec2.dylan.delifast.databinding.FragmentCameraBinding

class CameraFragment : Fragment() {
    private lateinit var binding : FragmentCameraBinding
    private lateinit var openCameraLauncher: ActivityResultLauncher<Intent>
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCameraBinding.inflate(inflater, container, false)
        val view = binding.root
        binding.btnTomarfoto.setOnClickListener {
            if (permissionValidated()) {
                tomarFoto()
            }
        }
        openCameraLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val photo = result.data?.extras?.get("data") as Bitmap
                binding.imgPhoto.setImageBitmap(photo)
            }
        }
        return view
    }

    private fun tomarFoto() {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        openCameraLauncher.launch(intent)
    }

    private fun permissionValidated(): Boolean {
        val cameraPermission = ContextCompat.checkSelfPermission(requireContext(), android.Manifest.permission.CAMERA)
        val permissionList: MutableList<String> = mutableListOf()
        if (cameraPermission != PackageManager.PERMISSION_GRANTED) {
            permissionList.add(android.Manifest.permission.CAMERA)
        }

        if (permissionList.isNotEmpty()) {
            ActivityCompat.requestPermissions(requireActivity(), permissionList.toTypedArray(), 1000)
            return false
        }
        return true
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            1000 -> {
                if (ContextCompat.checkSelfPermission(requireContext(), android.Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
                    tomarFoto()
                }
            }
        }
    }

}