package com.example.threadhandlerasynctask

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment

// "Безголовый" фрагмент
// используется для хранения AsyncTask
class TaskFragment : Fragment() {
    // Ссылка на AsyncTask
    //
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Фрагмент не пересоздается при уничтожении активити
        //
    }

    // Запуск таска из активности
    fun startProgress() {
        //
    }

    // Метод жизненного цикла фрагмента
    // Активность поменялась - "старая" удалилась, "новая" создалась
    // Используется для актуализации ссылки таска на активность
    override fun onAttach(context: Context) {
        super.onAttach(context)
        //
    }

    // Метод жизненного цикла фрагмента
    override fun onDetach() {
        super.onDetach()
        // Обнулить ссылку на активити
        //
    }
}