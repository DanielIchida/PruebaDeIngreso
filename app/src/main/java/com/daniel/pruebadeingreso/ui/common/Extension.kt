package com.daniel.pruebadeingreso.ui.common

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.daniel.pruebadeingreso.PruebaIngresoApp
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlin.properties.Delegates

fun ViewGroup.inflate(@LayoutRes layoutRes: Int, attachToRoot: Boolean = true): View =
    LayoutInflater.from(context).inflate(layoutRes,this,attachToRoot)

inline fun <reified T : Activity> Context.intentFor(body: Intent.() -> Unit): Intent =
    Intent(this,T::class.java).apply(body)

inline fun <reified T : Activity> Context.startActivity(body: Intent.() -> Unit){
    startActivity(intentFor<T>(body))
}

inline fun <VH: RecyclerView.ViewHolder,T> RecyclerView.Adapter<VH>.mutableBasicDiffUtil(
    initialValue: MutableList<T>,
    crossinline areItemsTheSame: (T,T) -> Boolean = {old,new -> old == new},
    crossinline  areContentsTheSame: (T,T) -> Boolean = { old,new -> old == new}
) =
    Delegates.observable(initialValue) { _, old, new ->
        DiffUtil.calculateDiff(object : DiffUtil.Callback() {
            override fun getOldListSize(): Int = old.size

            override fun getNewListSize(): Int = new.size

            override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
                areItemsTheSame(old[oldItemPosition], new[newItemPosition])

            override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
                areContentsTheSame(old[oldItemPosition], new[newItemPosition])
        }).dispatchUpdatesTo(this@mutableBasicDiffUtil)
    }

val Context.app: PruebaIngresoApp
    get() = applicationContext as PruebaIngresoApp

fun MaterialAlertDialogBuilder.dialogPersonalize(title: String,msg: String,v: View):
        MaterialAlertDialogBuilder = this
        .setTitle(title)
        .setMessage(msg)
        .setView(v)




