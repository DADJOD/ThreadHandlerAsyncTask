package com.example.threadhandlerasynctask

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.util.concurrent.Executor
import java.util.concurrent.Executors


class MainActivity : AppCompatActivity() {
    private var changeText: Button? = null
    private var changeTextSuccess: Button? = null
    private var progress: ProgressBar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        changeText = findViewById(R.id.change_text)
        changeTextSuccess = findViewById(R.id.change_text_success)
        progress = findViewById(R.id.progress)
    }

    // Попытка изменить виджет из фонового треда
    @SuppressLint("SetTextI18n")
    fun changeText(view: View?) {
        // срабатывает при повторном нажатии.
        // т.е. ui надо обновлять после завершения
//        val exServices = Executors.newFixedThreadPool(1)
//
//        exServices.submit {
//            run {
//                try {
//                    Thread.sleep(1000)
//                    changeText?.text = "TEXT CHANGED"
//                } catch (e: InterruptedException) {
//                    e.printStackTrace()
//                }
//            }
//        }
//
//        exServices.shutdown()

        Thread {
            kotlin.run {
                try {
                    Thread.sleep(1000)
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                }
                // без runUIThread не работает -> exception
                changeText?.text = "TEXT CHANGED 1"
            }
        }.start()
    }


    // Для изменения виджета из фонового потока можно использовать
    // View.post(Runnable) или runOnUiThread(Runnable)
    // Передаваемый Runnable выполняется в основном потоке
    @SuppressLint("SetTextI18n")
    fun changeTextSuccess(view: View?) {
        Thread {
            kotlin.run {
                try {
                    Thread.sleep(1000)
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                }
                runOnUiThread(Runnable {
                    changeTextSuccess?.text = "TEXT CHANGED 2"
                })
            }
        }.start()
    }

    // Нельзя останавливать или загружать
    // основной тред
    fun pause(view: View?) {
        Thread.sleep(10_000)
    }

    // Неправильный вариант обновления прогресс бара
    fun progressBad(view: View?) {
        val task = MyTask(this)
        task.execute()
    }

    // Правильный вариант обновления прогресс бара
    fun progressGood(view: View?) {
        // Найдем фргмент по тагу

        // Если его нет, создадим

        // и добавим

        // Запустим прогресс
    }

    // Разделяемый ресурс
    val array = intArrayOf(0)
    private fun add() {
        array[0]++
    }

    // Совместный доступ к разделяемому ресурсу
    fun jointAccess(view: View?) {
        array[0] = 0
        val r = Runnable {
            for (i in 0 until 1000000) {
                add()
            }
        }

        // Создаем два треда
        //

        // Запускаеи
        //

        // Ждем в главном треде окончания
        // запущенных тредов
        try {

            //
        } catch (e: Exception) {
            e.printStackTrace()
        }

        // Выводим результат
        Toast.makeText(this, "Value is: " + array[0], Toast.LENGTH_SHORT).show()
    }

    fun progressUpdate(i: Int?) {
        i?.let { progress?.setProgress(it) }
    }

    companion object {
        // Тэг фрагмента
        private const val FRAGMENT = "FRAGMENT"
    }
}
