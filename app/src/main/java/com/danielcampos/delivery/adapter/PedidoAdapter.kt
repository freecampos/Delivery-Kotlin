package com.danielcampos.delivery.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.danielcampos.delivery.R
import com.danielcampos.delivery.modelo.Pedido
import com.firebase.ui.firestore.FirestoreRecyclerAdapter
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import kotlinx.android.synthetic.main.card_layout.view.*

class PedidoAdapter(options: FirestoreRecyclerOptions<Pedido>) :
    FirestoreRecyclerAdapter<Pedido, PedidoAdapter.PedidoAdapterVH>(options) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PedidoAdapterVH {
        return PedidoAdapterVH(LayoutInflater.from(parent.context).
        inflate(R.layout.card_layout,parent,false))
    }

    override fun onBindViewHolder(holder: PedidoAdapterVH, position: Int, model: Pedido) {
        holder.id.text = model.id.toString()
        holder.direccion.text = model.direccion
        holder.poblacion.text = model.poblacion
        holder.cliente.text = model.nombreCliente
    }

    class PedidoAdapterVH(itemView: View) : RecyclerView.ViewHolder(itemView){
        var id = itemView.numEnvio
        var direccion = itemView.direccionCliente
        var poblacion = itemView.poblacionCliente
        var cliente = itemView.cliente
    }
}
