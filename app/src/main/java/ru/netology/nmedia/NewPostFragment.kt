package ru.netology.nmedia

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import ru.netology.nmedia.databinding.FragmentNewPostBinding
import ru.netology.nmedia.viewmodel.PostViewModel

class NewPostFragment : Fragment() {

    private val viewModel: PostViewModel by activityViewModels()
    private lateinit var binding: FragmentNewPostBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNewPostBinding.inflate(inflater, container, false)

        val editedPost = viewModel.edited.value
        if (editedPost != null) {
            // Редактирование поста, загружаем данные
            binding.content.setText(editedPost.content)
        } else {
            // Создание нового поста
            arguments?.let {
                val text = it.getString("textArg", null)
                binding.content.setText(text)
            }
        }

        binding.ok.setOnClickListener {
            val text = binding.content.text.toString()
            if (!text.isNullOrBlank()) {
                if (editedPost != null) {
                    // Редактирование поста, обновляем контент
                    viewModel.changeContent(text)
                } else {
                    // Создание нового поста, сохраняем текст
                    viewModel.changeContent(text)
                }
                viewModel.save()
                viewModel.cancelEdit()
            }
            findNavController().navigateUp()
        }

        return binding.root
    }
}
