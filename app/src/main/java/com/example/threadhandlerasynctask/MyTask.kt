package com.example.threadhandlerasynctask

import android.os.AsyncTask
import java.lang.ref.WeakReference

// 1 параметр передается в doInBackground
// 2 параметр передается в publishProgress и onProgressUpdate
// 3 параметр возвращается из doInBackground и передается в onPostExecute
class MyTask(private val activity: MainActivity) : AsyncTask<Unit, Int, Unit>() {

    override fun doInBackground(vararg params: Unit?) {
        for (i in 0..100) {
            try {
                Thread.sleep(50)
            } catch (e: InterruptedException) {
                e.printStackTrace()
            }

            publishProgress(i)
        }
        return
    }

    override fun onProgressUpdate(vararg values: Int?) {
        activity.progressUpdate(values[0])
    }

    override fun onPostExecute(result: Unit?) {

    }
}