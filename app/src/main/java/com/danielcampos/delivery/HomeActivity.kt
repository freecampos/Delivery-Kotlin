package com.danielcampos.delivery

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.danielcampos.delivery.adapter.PedidoAdapter
import com.danielcampos.delivery.modelo.Pedido
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {

    private var db:FirebaseFirestore = FirebaseFirestore.getInstance()
    private var collectionReference:CollectionReference = db.collection("pedido")
    var pedidoAdapter:PedidoAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        // Setup
        val bundle = intent.extras
        val email = bundle?.getString("email")
        setup(email ?: "")

        // Listado de pedidos, Recoger datos de la base de datos y Cargar bloques
        setUpRecyclerView(email ?: "")
    }

    private fun setUpRecyclerView(email: String) {
        val query : Query = collectionReference
        val firestoreRecyclerOptions: FirestoreRecyclerOptions<Pedido> = FirestoreRecyclerOptions.
        Builder<Pedido>().setQuery(query, Pedido::class.java).build()

        pedidoAdapter = PedidoAdapter(firestoreRecyclerOptions)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = pedidoAdapter

    }

    override fun onStart() {
        super.onStart()
        pedidoAdapter!!.startListening()
    }

    override fun onDestroy() {
        super.onDestroy()
        pedidoAdapter!!.stopListening()
    }

    private fun setup(email: String){
        title = "Inicio"
        emailTextView.text = email

    }
}