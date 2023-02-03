package com.example.testedetela

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.testedetela.databinding.FragmentLoginBinding
import com.example.testedetela.model.Users
import com.example.testedetela.network.NetworkP
import com.example.testedetela.network.Path
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.io.path.Path

class LoginFragment : Fragment(R.layout.fragment_login) {

    private var _binding: FragmentLoginBinding? = null
    private val  binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setButtonClicked()

        }

    private fun setButtonClicked() {
        binding.buttonBuscar.setOnClickListener {
            getData()
        }
    }

    private fun getData() {
        val retrofitBase = NetworkP.getRetrofitInstance()
        val endPointPath = retrofitBase.create(Path::class.java)
        val callback = endPointPath.getUsers(binding.editTextUsuario.text.toString())

        callback.enqueue(object: Callback<Users> {

            override fun onFailure(call: Call<Users>, t: Throwable) {

                Toast.makeText(requireContext(), "Verifique os dados ou tente mais tarde", Toast.LENGTH_LONG).show()
            }
            override fun onResponse(call: Call<Users>, response: Response<Users>) {
                response.body()?.let {
                    var action = LoginFragmentDirections.actionLoginFragmentToHomeFragment(
                        it
                    )
                    findNavController().navigate(action)
                }
            }
        })
    }
}