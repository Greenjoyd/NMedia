package ru.netology.nmedia

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.widget.EditText
import androidx.activity.result.contract.ActivityResultContract
import ru.netology.nmedia.databinding.ActivityNewPostBinding
import ru.netology.nmedia.viewmodel.PostViewModel

class NewPostActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityNewPostBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val textToEdit = intent.getStringExtra(Intent.EXTRA_TEXT)
        binding.content.setText(textToEdit)

        binding.ok.setOnClickListener{
            val text = binding.content.text.toString()
            if(text.isBlank()){
                setResult(Activity.RESULT_CANCELED)
            }else{
                val editedText = binding.content.text.toString()
                setResult(Activity.RESULT_OK, Intent().apply { putExtra(Intent.EXTRA_TEXT, editedText) })
            }
            finish()
        }

    }



    object NewPostContract : ActivityResultContract<Unit, String?>(){
        override fun createIntent(context: Context, input: Unit) = Intent(context, NewPostActivity::class.java)

        override fun parseResult(resultCode: Int, intent: Intent?) = intent?.getStringExtra(Intent.EXTRA_TEXT)

    }
    object EditPostContract : ActivityResultContract<String, String?>(){
        override fun createIntent(context: Context, input: String) = Intent(context, NewPostActivity::class.java).putExtra(Intent.EXTRA_TEXT,input)

        override fun parseResult(resultCode: Int, intent: Intent?) = intent?.getStringExtra(Intent.EXTRA_TEXT)

    }
}