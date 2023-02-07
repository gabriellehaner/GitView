package com.example.testedetela

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.testedetela.databinding.FragmentHomeBinding
import com.example.testedetela.model.ModelRepository
import com.example.testedetela.model.RepositoryListAdapter
import com.example.testedetela.network.NetworkP
import com.example.testedetela.network.Path
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.create
import kotlin.io.path.Path

class HomeFragment : Fragment(R.layout.fragment_home) {


    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val args: HomeFragmentArgs by navArgs()
    private lateinit var repositoryAdapter: RepositoryListAdapter

    override fun onCreateView(

        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        carregarVisualizacao()
        imagem()
    }

    private fun carregarVisualizacao() {
        binding.textViewName.text = args.user.name
        binding.textViewUser.text = args.user.login
        getData()
    }

    private fun getData() {
        val retrofitBase = NetworkP.getRetrofitInstance()
        val endPointPath = retrofitBase.create(Path::class.java)
        val callback = endPointPath.getRepos(args.user.login)

        callback.enqueue(object: Callback<List<ModelRepository>> {
            override fun onFailure(call: Call<List<ModelRepository>>, t: Throwable) {
                Toast.makeText(requireContext(), "Não funciona", Toast.LENGTH_LONG).show()
            }

            override fun onResponse(call: Call<List<ModelRepository>>, response: Response<List<ModelRepository>>) {
                response.body()?.let {
                    setAdapter(it)
                }
            }
        })

    }

    private fun setAdapter(listRepos: List<ModelRepository>) {
        repositoryAdapter = RepositoryListAdapter(listRepos) {
            openGithub(it)
        }

        binding.recyclerView.apply {
            adapter = repositoryAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
    }

    private fun openGithub(repository: ModelRepository) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(repository.htmlUrl))
        startActivity(intent)
    }

    fun imagem(){
        val foto = args.user.avatarUrl

        if (foto != null){
        Glide.with(this)
            .load(foto)
            .into(binding.imageView3)
        }else{
            binding.imageView3.setImageResource(R.drawable.githubicon)
        }
    }

}