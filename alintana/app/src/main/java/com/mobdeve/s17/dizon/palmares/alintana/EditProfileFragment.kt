package com.mobdeve.s17.dizon.palmares.alintana

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.graphics.drawable.AnimatedImageDrawable
import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.text.TextUtils
import android.util.Base64
import android.util.Base64OutputStream
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.graphics.drawable.toBitmap
import androidx.core.net.toFile
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.mobdeve.s17.dizon.palmares.alintana.api.APIClient
import com.mobdeve.s17.dizon.palmares.alintana.databinding.FragmentEditProfileBinding
import com.mobdeve.s17.dizon.palmares.alintana.helpers.BaseProfileFragment
import com.mobdeve.s17.dizon.palmares.alintana.model.UpdatePasswordInformation
import com.mobdeve.s17.dizon.palmares.alintana.model.UpdateUserInformation
import com.mobdeve.s17.dizon.palmares.alintana.model.User
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.InputStream


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
/**
 * A simple [Fragment] subclass.
 * Use the [EditProfileFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class EditProfileFragment : BaseProfileFragment() {
    // TODO: Rename and change types of parameters
    private var _binding: FragmentEditProfileBinding? = null
    private val binding get() = _binding!!
    private lateinit var user : User
    private var bitmap : Bitmap? = null
    private var drawable : Drawable? = null
    private var format : String = ""
    private var imageFile : File? = null
    private var inputStream : InputStream? = null

    var resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            // There are no request codes
            val data: Intent? = result.data
            var path: Uri = data!!.data!!

            Log.d("VIDEO: ", path.toString())

            var pathCut = path.toString().split(".")
            format = pathCut[pathCut.size - 1]

            inputStream = requireActivity().applicationContext.contentResolver.openInputStream(path)

            try{
                if(Build.VERSION.SDK_INT < 28) {
                    bitmap = MediaStore.Images.Media.getBitmap(requireActivity().applicationContext.contentResolver, path)
                    binding.ivProfileImage.setImageBitmap(bitmap)
                }else{
                    val source = ImageDecoder.createSource(requireActivity().applicationContext.contentResolver, path)

                    binding.ivProfileImage.setImageBitmap(bitmap)
                    bitmap = ImageDecoder.decodeBitmap(source)

                    if(format == "gif"){
                        drawable = ImageDecoder.decodeDrawable(source)
                        if (drawable is AnimatedImageDrawable) {
                            (drawable as AnimatedImageDrawable).start()
                        }
                        binding.ivProfileImage.setImageDrawable(drawable)
                    }else{
                        binding.ivProfileImage.setImageBitmap(bitmap)
                    }
                }
            }catch (e: Exception){
                e.printStackTrace()
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        user = ACTIVITY.user
        ACTIVITY.title = "Edit Profile"
        bitmap = null
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentEditProfileBinding.inflate(inflater, container, false)

        val sexList = listOf("Prefer Not To Say", "Male", "Female", "Non-binary", "Gay", "Lesbian", "Other")
        val sexAdapter = ArrayAdapter(requireActivity().applicationContext, R.layout.item_list, sexList)
        (binding.actvSex)?.setAdapter(sexAdapter)

        val prefList = listOf("Any", "Male", "Female", "Non-binary", "Gay", "Lesbian", "Other")
        val prefAdapter = ArrayAdapter(requireActivity().applicationContext, R.layout.item_list, prefList)
        (binding.actvPref)?.setAdapter(prefAdapter)

        binding.etUsername.setText(user.username)
        binding.etBirthdate.setText(user.birthdate)
        binding.actvSex.setText(sexAdapter.getItem(sexAdapter.getPosition(user.sex)), false)

        if(sexAdapter.getPosition(user.preference) == -1)
            binding.actvPref.setText(prefAdapter.getItem(0), false)
        else
            binding.actvPref.setText(prefAdapter.getItem(sexAdapter.getPosition(user.preference)), false)

        binding.etHeadline.setText(user.headline)
        binding.actvLoc.setText(user.location)
        binding.actvMobilenumber.setText(user.mobileNumber)
        if(user.userImage != null && !TextUtils.isEmpty(user.userImage)){
            if(user.isMyImageGIF()){
                Glide.with(requireActivity().applicationContext).load(user.userImage).into(binding.ivProfileImage)
            }else{
                Picasso.get().load(user.userImage).into(binding.ivProfileImage)
            }
        }
        else {
            binding.ivProfileImage.setImageResource(R.drawable.ic_baseline_person_24)
        }

        binding.btnSelectImage.setOnClickListener {
            ACTIVITY.sfx.clickSoundEffect()
            selectImage()
        }

        var client = APIClient.create()

        binding.btnSaveChanges.setOnClickListener {
            ACTIVITY.sfx.clickSoundEffect()
            lateinit var stringImg: String
            if (bitmap == null){
                stringImg = ""
            } else {
                if(format == "gif")
                    stringImg = "<GIF>" + convertImageFileToBase64()
                else
                    stringImg = "<NOTGIF>" + convertImageFileToBase64()
            }

            var updateUserInformation = UpdateUserInformation(
                ACTIVITY.user._id, binding.etUsername.text.toString(), binding.etBirthdate.text.toString(),
                binding.actvSex.text.toString(), binding.actvMobilenumber.editableText.toString(),
                binding.actvLoc.editableText.toString(), binding.etHeadline.text.toString(),
                binding.actvPref.editableText.toString(), stringImg)

            client.updateUser(updateUserInformation).enqueue(object: Callback<User>{
                override fun onResponse(call: Call<User>, response: Response<User>) {
                    ACTIVITY.user = response.body()!!
                    ACTIVITY.loadFragment(UserProfileFragment())
                   ACTIVITY.binding.navView.menu.getItem(0).setChecked(true)
                }
                override fun onFailure(call: Call<User>, t: Throwable) {
                    t.printStackTrace()
                }
            })
        }

        binding.btnSavePw.setOnClickListener {
            ACTIVITY.sfx.clickSoundEffect()
            if(binding.etOldPassword.editableText.toString().isEmpty()){
                binding.etOldPassword.error = "Old Password required"
                binding.etUsername.requestFocus()
                return@setOnClickListener
            }
            if(binding.etPassword.editableText.toString().isEmpty()){
                binding.etPassword.error = "New Password required"
                binding.etPassword.requestFocus()
                return@setOnClickListener
            }
            if(binding.etConfirmPassword.editableText.toString().isEmpty()){
                binding.etConfirmPassword.error = "Confirm New Password required"
                binding.etConfirmPassword.requestFocus()
                return@setOnClickListener
            }

            if(binding.etConfirmPassword.editableText.toString() !== (binding.etPassword.editableText.toString())){
                binding.etConfirmPassword.error = "Password does not match"
                binding.etConfirmPassword.requestFocus()
                return@setOnClickListener
            }

            var updatePasswordInformation = UpdatePasswordInformation(
                ACTIVITY.user._id,
                binding.etOldPassword.text.toString(),
                binding.etPassword.text.toString(),
                binding.etConfirmPassword.text.toString()
            )

            client.updatePassword(updatePasswordInformation).enqueue(object: Callback<User>{
                override fun onResponse(call: Call<User>, response: Response<User>) {
                    ACTIVITY.user = response.body()!!
                    ACTIVITY.loadFragment(UserProfileFragment())
                    ACTIVITY.binding.navView.menu.getItem(0).setChecked(true)
                }
                override fun onFailure(call: Call<User>, t: Throwable) {
                    Toast.makeText(requireActivity().applicationContext,"Error changing password. Make sure the inputs are correct", Toast.LENGTH_LONG).show()
                }
            })
        }

        return binding.root
    }

    fun selectImage(){
        var intent : Intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT
        resultLauncher.launch(intent)
    }

    fun imgToString(): String{
        lateinit var imgByte : ByteArray
        var byteArrayOutputStream : ByteArrayOutputStream = ByteArrayOutputStream()
            bitmap!!.compress(Bitmap.CompressFormat.PNG,100, byteArrayOutputStream)
        imgByte = byteArrayOutputStream.toByteArray()

        return Base64.encodeToString(imgByte, Base64.NO_WRAP)
    }

    fun convertImageFileToBase64(): String {
        return ByteArrayOutputStream().use { outputStream ->
            Base64OutputStream(outputStream, Base64.DEFAULT).use { base64FilterStream ->
                inputStream!!.use { inputStream ->
                    inputStream.copyTo(base64FilterStream)
                }
            }
            return@use outputStream.toString()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}