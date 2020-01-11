package com.example.alojamientoseuskadi;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class Adaptador extends BaseAdapter {
    private ArrayList<Entidad> listItems;
    private Context context;

    public Adaptador(ArrayList<Entidad> listItems, Context context) {
        this.listItems = listItems;
        this.context = context;
    }

    @Override
    public int getCount() {
        return listItems.size();//devuelve el tamaño de la lista de items
    }

    @Override
    public Object getItem(int position) {
        return listItems.get(position);//devuelve la posición del item
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //Crear objeto entidad que contenga la infor del item, de la posición indicada
        Entidad Item = (Entidad) getItem(position);

        //Este método se ejecuta tantas vbecves como le indique getCOunt(automaticamente)

        convertView = LayoutInflater.from(context).inflate(R.layout.item, null);
        //crear cada elemento que va a contener el item
        ImageView imgFoto = (ImageView)convertView.findViewById(R.id.imgFoto);
        TextView tvTitulo = (TextView) convertView.findViewById(R.id.tvTitulo);
        TextView tvContenido = (TextView) convertView.findViewById(R.id.tvContenido);

        //rellenan el contenido de la vista con el contenido del obtjeto
        imgFoto.setImageResource(Item.getImgFoto());
        tvTitulo.setText(Item.getTitulo());
        tvContenido.setText(Item.getContenido());

        return convertView;//Se crea cada item y se asignan los valores para cada elemento de cada item
    }
}
